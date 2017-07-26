package com.realm.sumit.config;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.realm.sumit.dtos.RMTokenDTO;

/**
 * Created by sumit on 26/07/17.
 */

public class AppPreferences {
    private static final String KEY_RM_TOKEN_DTO = "token_dto";

    /* Singleton Configuration */
    private static AppPreferences mAppPreferences;
    private static Gson mGson;
    private SharedPreferences mSharedPreferences;

    private AppPreferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(RealmApp.getContext());
    }

    public static AppPreferences init() {
        if (null == mAppPreferences) {
            mAppPreferences = new AppPreferences();
            mGson = new GsonBuilder().serializeNulls().create();
        }
        return mAppPreferences;
    }
	/* End Singleton Configuration */


    public RMTokenDTO getTokenDTO() {
        RMTokenDTO tokenDTO = null;
        try {
            String tokenString = mSharedPreferences.getString(KEY_RM_TOKEN_DTO, null);
            if (!tokenString.isEmpty()) {
                tokenDTO = mGson.fromJson(tokenString, RMTokenDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenDTO;
    }

    public void setTokenDTO(RMTokenDTO tokenDTO) {
        if (null != tokenDTO) {
            try {
                mSharedPreferences.edit().putString(KEY_RM_TOKEN_DTO, mGson.toJson(tokenDTO)).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
