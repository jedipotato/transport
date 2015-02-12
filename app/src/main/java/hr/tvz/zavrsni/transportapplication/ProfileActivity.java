package hr.tvz.zavrsni.transportapplication;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.json.TransportApiListener;
import hr.tvz.zavrsni.util.TransportPreferences;

public class ProfileActivity extends TransportActivity {

    private EditText mEditTextName;
    private EditText mEditTextSurname;
    private TextView mTextViewUsername;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextNewPassword;
    private EditText mEditTextRepeatNewPassword;
    private TextView mTextViewPassword;
    private TextView mTextViewNewPassword;
    private TextView mTextViewRepeatNewPassword;
    private Button mButtonUpdateProfile;

    private App mApp;
    private User mUser;
    private boolean mIsInEditMode = false;

    private TransportApiListener<User> mGetProfileApiListener = new TransportApiListener<User>() {
        @Override
        public void onApiResponse(User response) {
            mApp.removeTransportApiListener();
            if (!checkUserAuthenticationResponseAndReset(response)) return;

            if (response.isSuccess()) {
                mUser = response;
                fillForm(response);
            }
        }

        @Override
        public void onApiFailure(String message) {
            mApp.removeTransportApiListener();
            alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
        }
    };
    private TransportApiListener<BasicModel> mUpdateProfileApiListener = new TransportApiListener<BasicModel>() {
        @Override
        public void onApiResponse(BasicModel response) {
            mApp.removeTransportApiListener();
            if (!checkUserAuthenticationResponseAndReset(response)) return;

            if (response.isSuccess()) {
                if (!TextUtils.isEmpty(mEditTextNewPassword.getText().toString())) {
                    TransportPreferences prefs = new TransportPreferences(ProfileActivity.this);
                    prefs.savePassword(mEditTextNewPassword.getText().toString());
                }

                mIsInEditMode = false;
                switchMode(false);
            }
        }

        @Override
        public void onApiFailure(String message) {
            mApp.removeTransportApiListener();
            alert(TextUtils.isEmpty(message) ? getString(R.string.api_alert_dialog_body) : message);
        }
    };

    private void fillForm(User user) {
        mEditTextName.setText(user.getName());
        mEditTextSurname.setText(user.getSurname());
        mTextViewUsername.setText(user.getUsername());
        mEditTextEmail.setText(user.getEmail());
    }

    private void switchMode(boolean isEditMode) {
        mEditTextName.setEnabled(isEditMode);
        mEditTextSurname.setEnabled(isEditMode);
        mEditTextEmail.setEnabled(isEditMode);
        mEditTextPassword.setEnabled(isEditMode);
        mEditTextPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        mEditTextNewPassword.setEnabled(isEditMode);
        mEditTextNewPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        mEditTextRepeatNewPassword.setEnabled(isEditMode);
        mEditTextRepeatNewPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        mTextViewPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        mTextViewNewPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);
        mTextViewRepeatNewPassword.setVisibility(isEditMode ? View.VISIBLE : View.GONE);

        if (isEditMode) {
            mButtonUpdateProfile.setText(getString(R.string.profile_button_update));
        } else {
            mButtonUpdateProfile.setText(getString(R.string.profile_button_edit));
        }
    }

    private boolean formValidation() {
        mEditTextName.setError(null);
        mEditTextSurname.setError(null);
        mEditTextEmail.setError(null);
        mEditTextPassword.setError(null);
        mEditTextNewPassword.setError(null);
        mEditTextRepeatNewPassword.setError(null);

        boolean isOK = true;
        String name = mEditTextName.getText().toString();
        if (name.length() < 1 || name.length() > 30) {
            isOK = false;
            mEditTextName.setError("Name input error!");
        }
        String surname = mEditTextSurname.getText().toString();
        if (surname.length() < 1 || surname.length() > 30) {
            isOK = false;
            mEditTextSurname.setError("Last name input error!");
        }
        String email = mEditTextEmail.getText().toString();
        if (email.length() < 2 || email.length() > 40) {
            isOK = false;
            mEditTextEmail.setError("E-mail input error!");
        }

        TransportPreferences prefs = new TransportPreferences(this);
        String password = prefs.getPassword();
        String inputPassword = mEditTextPassword.getText().toString();
        if (inputPassword.length() > 0 && !password.equals(inputPassword)) {
            isOK = false;
            mEditTextPassword.setError("Password is not correct!");
        }
        String newPassword = mEditTextNewPassword.getText().toString();
        if (inputPassword.length() > 0 && (newPassword.length() < 4 || newPassword.length() > 30)) {
            isOK = false;
            mEditTextNewPassword.setError("Password minimum is 4 characters!");
        }
        if (inputPassword.length() > 0 && newPassword.equals(password)) {
            isOK = false;
            mEditTextNewPassword.setError("New password must not be the same as old one.");
        }
        String repeatNewPassword = mEditTextRepeatNewPassword.getText().toString();
        if (inputPassword.length() > 0 && !repeatNewPassword.equals(newPassword)) {
            isOK = false;
            mEditTextRepeatNewPassword.setError("Repeated password has to be the same!");
        }

        return isOK;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mApp = (App) getApplication();

        mEditTextName = (EditText) findViewById(R.id.profileInputName);
        mEditTextSurname = (EditText) findViewById(R.id.profileInputSurname);
        mTextViewUsername = (TextView) findViewById(R.id.profileInputUsername);
        mEditTextEmail = (EditText) findViewById(R.id.profileInputEmail);
        mEditTextPassword = (EditText) findViewById(R.id.profileInputPassword);
        mEditTextNewPassword = (EditText) findViewById(R.id.profileInputNewPassword);
        mEditTextRepeatNewPassword = (EditText) findViewById(R.id.profileInputRepeatNewPassword);
        mTextViewPassword = (TextView) findViewById(R.id.profileTextPassword);
        mTextViewNewPassword = (TextView) findViewById(R.id.profileTextNewPassword);
        mTextViewRepeatNewPassword = (TextView) findViewById(R.id.profileTextRepeatNewPassword);
        mButtonUpdateProfile = (Button) findViewById(R.id.profileButtonSubmit);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mApp.setTransportApiListener(mGetProfileApiListener);
        mApp.getUser();
    }

    public void onClickUpdate(View view) {
        if (!mIsInEditMode) {
            mIsInEditMode = true;
            switchMode(true);
        } else {
            if (formValidation()) {
                mApp.setTransportApiListener(mUpdateProfileApiListener);

                String name = "";
                if (!mUser.getName().equals(mEditTextName.getText().toString())) {
                    name = mEditTextName.getText().toString();
                }
                String surname = "";
                if (!mUser.getSurname().equals(mEditTextSurname.getText().toString())) {
                    surname = mEditTextSurname.getText().toString();
                }
                String email = "";
                if (!mUser.getEmail().equals(mEditTextEmail.getText().toString())) {
                    email = mEditTextEmail.getText().toString();
                }
                String password = mEditTextNewPassword.getText().toString();

                mApp.updateUser(TextUtils.isEmpty(name) ? null : name,
                        TextUtils.isEmpty(surname) ? null : surname,
                        mUser.getUsername(),
                        TextUtils.isEmpty(email) ? null : email,
                        TextUtils.isEmpty(password) ? null : password);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (mIsInEditMode) {
            mIsInEditMode = false;
            switchMode(false);
        } else {
            super.onBackPressed();
        }
    }
}
