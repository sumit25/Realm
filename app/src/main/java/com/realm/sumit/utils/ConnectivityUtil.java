package com.realm.sumit.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.realm.sumit.R;
import com.realm.sumit.config.RealmApp;

/**
 * Created by sumit on 30/07/17.
 */

public class ConnectivityUtil {



    /**
     * Gets the network info
     *
     * @return {@link NetworkInfo}
     */
    public static NetworkInfo getNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) RealmApp.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    /**
     * Checks if there is any connectivity
     *
     * @return true when the device is connected to any data network false otherwise
     */
    public static boolean isConnected() {
        NetworkInfo info = getNetworkInfo();
        if (isConnectedWifi(info)) {
            return true;
        } else if (isConnectedMobile(info)) {
            return (isConnectivityGood(info) || isConnectivityModerate(info));
        }
        return false;
    }

    /**
     * Checks if there is any connectivity and shows the message on SnackBar
     *
     * @return true when the device is connected to any data network false otherwise
     */
    public static boolean isConnected(Activity activity) {
        if (isConnected()) {
            return true;
        } else {
            SnackbarUtils.showSnackBar(activity, R.string.error_no_internet_connection);
            return false;
        }

    }

    /**
     * Checks if there is good connectivity for data sync
     */
    public static boolean isConnectivityGoodForSync() {
        NetworkInfo info = getNetworkInfo();
        return isConnectedWifi(info) || (isConnectedMobile(info) && isConnectivityGood(info));
    }

    /**
     * Checks if there is any connectivity to a Wifi network
     *
     * @return true when the device is connected to a wifi network false otherwise
     */
    private static boolean isConnectedWifi(NetworkInfo info) {
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    /**
     * Checks if there is any connectivity to a mobile network
     *
     * @return true when the device is connected to a mobile data network false otherwise
     */
    private static boolean isConnectedMobile(NetworkInfo info) {
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    /**
     * Checks if there is good connectivity
     *
     * @return true when the device is connected to a fast mobile data network, false otherwise
     */
    private static boolean isConnectivityGood(NetworkInfo info) {
        switch (info.getSubtype()) {
            case TelephonyManager.NETWORK_TYPE_UMTS:
                // ~ 400-7000 kbps

            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                // ~ 400-1000 kbps

            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                // ~ 600-1400 kbps

            case TelephonyManager.NETWORK_TYPE_HSPA:
                // ~ 700-1700 kbps

            case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                // ~ 1-2 Mbps

            case TelephonyManager.NETWORK_TYPE_HSUPA:
                // ~ 1-23 Mbps

            case TelephonyManager.NETWORK_TYPE_HSDPA:
                // ~ 2-14 Mbps

            case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                // ~ 5 Mbps

            case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                // ~ 10-20 Mbps

            case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                // ~ 10+ Mbps
                return true;

            default:
                return false;
        }
    }

    /**
     * Checks if the connection is moderate
     *
     * @return true when the device is connected to mobile data network with moderate speed, false otherwise
     */
    private static boolean isConnectivityModerate(NetworkInfo info) {
        switch (info.getSubtype()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:// ~ 100 kbps
            case TelephonyManager.NETWORK_TYPE_EDGE:// ~ 50-100 kbps
            case TelephonyManager.NETWORK_TYPE_1xRTT:// ~ 50-100 kbpss
            case TelephonyManager.NETWORK_TYPE_CDMA:// ~ 14-64 kbps
            case TelephonyManager.NETWORK_TYPE_IDEN:// ~25 kbps
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
            default:
                return true;
        }
    }

}
