package hr.tvz.zavrsni.transportapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBarActivity;

import hr.tvz.zavrsni.domain.api.BasicModel;
import hr.tvz.zavrsni.util.Const;
import hr.tvz.zavrsni.util.TransportPreferences;

/**
 * Abstract activity which holds a common code for other activities.
 */
public abstract class TransportActivity extends ActionBarActivity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (BuildConfig.DEBUG) {
            onDebugBuildOnly();
        }
    }

    protected void alert(String message) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(message)

                .setTitle(R.string.api_alert_dialog_title)
                .create();

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                TransportActivity.this.onAlertDismissed();
            }
        });
        dialog.show();
    }

    protected void alert(@StringRes int stringResource) {
        alert(getString(stringResource));
    }

    /**
     * Called after alert dialog has been dismissed.
     * This method can be used for common cleanup code after dialogs are dismissed.
     */
    protected void onAlertDismissed() {
        // pass implementation
    }

    /**
     * Called only if build is defined as DEBUG.
     * This check is run after <i>onCreate</i> method is called in
     * your Activity. This method can be used to insert debug data, for example,
     * into views to speed up manual testing.
     */
    protected void onDebugBuildOnly() {
        // pass implementation
    }

    /**
     * Checks for a proper user authentication in a given model. If user is not properly
     * authenticated, Activity calls for a reset and launches
     * {@link hr.tvz.zavrsni.transportapplication.LoginActivity}. All login information
     * about login is deleted from SharedPreferences.
     * @param basicModel response object from API which <b>should</b> contain a success field
     * @return false only if user is <b>not</b> authenticated, ignores any other error and returns true
     */
    protected boolean checkUserAuthenticationResponseAndReset(BasicModel basicModel) {
        if (Const.ERROR_AUTH == basicModel.getSuccess()) {
            TransportPreferences prefs = new TransportPreferences(this);
            prefs.clearLoginInfo();

            Intent resetAndLoginIntent = new Intent(this, LoginActivity.class);
            resetAndLoginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(resetAndLoginIntent);
            finish();
            return false;
        }
        return true;
    }

}
