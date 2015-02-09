package hr.tvz.zavrsni.transportapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

    private App mApp;
    private User mUser;

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
    }

    @Override
    protected void onResume() {
        super.onResume();

        mApp.setTransportApiListener(mGetProfileApiListener);
        mApp.getUser();
    }

    public void onClickUpdate(View view) {
    }
}
