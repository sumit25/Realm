package com.realm.sumit.utils;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by sumit on 28/07/17.
 */

public class SnackbarUtils {

    public static synchronized void showSnackBar(@NonNull Activity activity, String msg) {
        if (null != msg) {
            Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
        }
    }

    public static synchronized void showSnackBar(@NonNull Activity activity, int stringResId) {
        String msg = activity.getString(stringResId);
        Snackbar.make(activity.findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    public static synchronized void showSnackBar(@NonNull View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static synchronized void showSnackBar(@NonNull View view, int stringResId) {
        String msg = view.getContext().getString(stringResId);
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }
}
