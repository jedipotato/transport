package hr.tvz.zavrsni.transportapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.domain.api.User;
import hr.tvz.zavrsni.json.TransportApiListener;

public class ProfileActivity extends TransportActivity {

    private EditText mEditTextName;
    private EditText mEditTextSurname;
    private EditText mEditTextUsername;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextNewPassword;
    private EditText mEditTextRepeatNewPassword;
    private TextView mTextViewPassword;
    private TextView mTextViewNewPassword;
    private TextView mTextViewRepeatNewPassword;

    private App mApp;
    private User mUser;
    private boolean mIsInEditMode = false;

    private TransportApiListener<User> mGetProfileApiListener = new TransportApiListener<User>() {
        @Override
        public void onApiResponse(User response) {
            mApp.removeTransportApiListener();

            if (response.isSuccess()) {
                fillForm(response);
            }
        }

        @Override
        public void onApiFailure(String message) {
            mApp.removeTransportApiListener();
        }
    };
    private TransportApiListener<BasicModel> mUpdateProfileApiListener = new TransportApiListener<BasicModel>() {
        @Override
        public void onApiResponse(BasicModel response) {
            mApp.removeTransportApiListener();
        }

        @Override
        public void onApiFailure(String message) {
            mApp.removeTransportApiListener();
        }
    };

    private void fillForm(User user) {
        mEditTextName.setText(user.getName());
        mEditTextSurname.setText(user.getSurname());
        mEditTextUsername.setText(user.getUsername());
        mEditTextEmail.setText(user.getEmail());
    }

    private void switchMode(boolean isEditMode) {
        mEditTextName.setEnabled(isEditMode);
        mEditTextSurname.setEnabled(isEditMode);
        mEditTextUsername.setEnabled(isEditMode);
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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mApp = (App) getApplication();

        mEditTextName = (EditText) findViewById(R.id.profileInputName);
        mEditTextSurname = (EditText) findViewById(R.id.profileInputSurname);
        mEditTextUsername = (EditText) findViewById(R.id.profileInputUsername);
        mEditTextEmail = (EditText) findViewById(R.id.profileInputEmail);
        mEditTextPassword = (EditText) findViewById(R.id.profileInputPassword);
        mEditTextNewPassword = (EditText) findViewById(R.id.profileInputNewPassword);
        mEditTextRepeatNewPassword = (EditText) findViewById(R.id.profileInputRepeatNewPassword);
        mTextViewPassword = (TextView) findViewById(R.id.profileTextPassword);
        mTextViewNewPassword = (TextView) findViewById(R.id.profileTextNewPassword);
        mTextViewRepeatNewPassword = (TextView) findViewById(R.id.profileTextRepeatNewPassword);
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
            //TODO api
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
