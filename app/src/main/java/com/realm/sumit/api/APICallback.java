package com.realm.sumit.api;


import android.content.Intent;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sumit on 26/07/17.
 */

public abstract class APICallback<T> implements Callback<T> {

    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_NOT_FOUND = 404;
    private Status mStatus;
    private int mStatusCode;

    public APICallback() {
        super();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        mStatusCode = response.code();
        if (HTTP_OK == response.code() || HTTP_CREATED == response.code()) {
            mStatus = Status.SUCCESS;
            onResponse(response.body());
        } else {
            processResponse(response.code(), response.errorBody());
        }
    }

    public abstract void onResponse(T body);

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        mStatusCode = -1;
        mStatus = Status.FAILURE;
        onFailure(null);
    }

    public void processResponse(int responseCode, ResponseBody errorBody) {
        if (HTTP_UNAUTHORIZED == responseCode) {
            mStatus = Status.UNAUTHORIZED;
            onUnauthorizedAccess();
        } else if (HTTP_NOT_FOUND == responseCode) {
            mStatus = Status.PAGE_NOT_FOUND;
            onPageNotFound();
        } else {
            mStatus = Status.FAILURE;
            String errorMessage = getErrorMessage(errorBody);

            onFailure(errorMessage);
        }
    }

    public static String getErrorMessage(ResponseBody errorBody) {
        String message = null;
        if (null != errorBody) {
            try {
                message = errorBody.string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    //TODO handle error case
    public void onFailure(String message) {
//        if (null != mActivity) {
//            if (null == message) {
//                message = mActivity.getString(R.string.error_connecting_server);
//            }
//            SnackbarUtils.showSnackBar(mActivity, message);
//        }
    }

    //TODO handle case
    public void onUnauthorizedAccess() {
//        if (null != mActivity) {
//            KisanHubApp.getPreferences().clearPreferencesData();
//            Intent intent = new Intent(mActivity, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            mActivity.startActivity(intent);
//            mActivity.delayedFinishActivity();
//        }
    }

    public void onPageNotFound() {
    }


    public enum Status {
        SUCCESS, UNAUTHORIZED, PAGE_NOT_FOUND, FAILURE
    }
}
