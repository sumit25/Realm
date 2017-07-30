package com.realm.sumit.api;

import android.util.Log;

import com.realm.sumit.config.RealmApp;
import com.realm.sumit.dtos.RMTokenDTO;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RmUserProfileResponse;
import com.realm.sumit.utils.AppConstants;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Created by sumit on 26/07/17.
 */

public class APIClient extends ClientConfig implements Authenticator {
    private final int HTTP_OK = 200;
    private final int HTTP_CREATED = 201;
    private final int HTTP_UNAUTHORIZED = 401;
    private final int HTTP_NOT_FOUND = 404;

    private static APIClient mAPIClient;


    @Override
    void initAPIInterface() {
        mAPIInterface = APIUtils.initRetrofit(this, 1L, 1L, 1L).create(APIInterface.class);
    }

    /* Singleton Configuration */
    private APIClient() {
    }

    public static APIClient init() {
        if (null == mAPIClient) {
            mAPIClient = new APIClient();
            mAPIClient.initAPIInterface();
        }
        return mAPIClient;
    }
	/* End Singleton Configuration */



    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (null != response.request() && null != response.request().url()) {
            if (response.request().url().toString().contains("oauth/token.json")) {
                return null;
            }
        }

        if (HTTP_UNAUTHORIZED == response.code()) {
            RMTokenDTO tokenDTO = getRefreshToken();
            if (null != tokenDTO) {
                RealmApp.getPreferences().setTokenDTO(tokenDTO);
            } else if (APIUtils.getResponseCount(response) >= 2) {
                return null;
            }
        }
        return response.request();
    }

    private RMTokenDTO getRefreshToken() {
        Log.d("TOKEN REFRESH:: ","Requesting new access token...");
        try {
            retrofit2.Response<RMTokenDTO> response = mAPIInterface.refreshAccessToken(AppConstants.GRANT_TYPE_REFRESH_TOKEN, AppConstants.SCOPE, RealmApp.getPreferences().getTokenDTO().getRefreshToken()).execute();
            if (APICallback.HTTP_OK == response.code()) {
                return response.body();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void getAccessToken(String username, String password, final APICallback<RMTokenDTO> apiCallback) {

        mAPIInterface.getAccessToken(AppConstants.GRANT_TYPE_PASSWORD, AppConstants.SCOPE, username, password).enqueue(apiCallback);
    }

    public void getCurrentUser(final APICallback<RMUserResponse> apiCallback){

        mAPIInterface.getCurrentUser().enqueue(apiCallback);
    }

    public void getUserProfile(String companyId, String userId, final APICallback<RmUserProfileResponse> apiCallback){

        mAPIInterface.getUserProfile(companyId,
                userId,
                "status",
                "title",
                "lesson_id",
                "_id",
                "user_document").enqueue(apiCallback);
    }
}
