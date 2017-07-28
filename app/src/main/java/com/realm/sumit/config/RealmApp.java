package com.realm.sumit.config;

import android.app.Application;
import android.content.Context;

import com.realm.sumit.R;
import com.realm.sumit.Realm.RealmHelper;
import com.realm.sumit.api.APIClient;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sumit on 26/07/17.
 */

public class RealmApp extends Application {

    private static Context mContext;
    private static String mBaseUrl;

    private static APIClient apiClient;
    private static AppPreferences appPreferences;
    private static RealmHelper realmHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;
        apiClient = APIClient.init();
        appPreferences = AppPreferences.init();
        mBaseUrl = mContext.getString(R.string.base_url);
        realmHelper = RealmHelper.init();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getBaseUrl() {
        return mBaseUrl;
    }

    public static APIClient getAPIClient() {
        return apiClient;
    }

    public static AppPreferences getPreferences() {
        return appPreferences;
    }

    public static RealmHelper getRealmHelper() {
        return realmHelper;
    }
}
