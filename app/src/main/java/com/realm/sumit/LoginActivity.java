package com.realm.sumit;


import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;


import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.realm.sumit.api.APICallback;

import com.realm.sumit.config.RealmApp;
import com.realm.sumit.dtos.RMTokenDTO;
import com.realm.sumit.dtos.RMUserResponse;
import com.realm.sumit.dtos.RmUserProfileResponse;

import com.realm.sumit.utils.ConnectivityUtil;
import com.realm.sumit.utils.SnackbarUtils;


import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RMTokenDTO rmTokenDTO = RealmApp.getPreferences().getTokenDTO();
        if (null != rmTokenDTO) {
            Intent lessonsActivityIntent = new Intent(LoginActivity.this, LessonsActivity.class);
            lessonsActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(lessonsActivityIntent);

        }
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            if (ConnectivityUtil.isConnected(this)) {
                mProgressDialog = ProgressDialog.show(this, "Please wait", "authenticating user...");
                RealmApp.getAPIClient().getAccessToken(email, password, new APICallback<RMTokenDTO>() {
                    @Override
                    public void onResponse(RMTokenDTO body) {
                        Log.d("access token", body.getAccessToken());
                        RealmApp.getPreferences().setTokenDTO(body);
                        Log.d("access token from prefs", RealmApp.getPreferences().getTokenDTO().getAccessToken());

                        getCurrentUser();

                    }

                    @Override
                    public void onUnauthorizedAccess() {
                        mProgressDialog.dismiss();
                        SnackbarUtils.showSnackBar(LoginActivity.this, R.string.error_login_failed);

                        mPasswordView.getText().clear();
                        mPasswordView.requestFocus();
                    }
                });

            }

        }
    }

    private void getCurrentUser() {

        RealmApp.getAPIClient().getCurrentUser(new APICallback<RMUserResponse>() {
            @Override
            public void onResponse(RMUserResponse userResponse) {
                Log.d("username", userResponse.getUser().getName());
                Log.d("company id", userResponse.getUser().getCompanyIds().get(0).toString());

                RealmApp.getRealmHelper().saveUserToRealm(userResponse);
                getUserProfile(userResponse);
            }
        });
    }

    private void getUserProfile(RMUserResponse userResponse) {

        RealmApp.getAPIClient().getUserProfile(userResponse.getUser().getCompanyIds().get(0).toString(), userResponse.getUser().getId(), new APICallback<RmUserProfileResponse>() {
            @Override
            public void onResponse(RmUserProfileResponse body) {
                RealmApp.getRealmHelper().saveUserProfileToRealm(body);

                mProgressDialog.dismiss();
                Intent lessonsActivityIntent = new Intent(LoginActivity.this, LessonsActivity.class);
                lessonsActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(lessonsActivityIntent);

            }
        });
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}

