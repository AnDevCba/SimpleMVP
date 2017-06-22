// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.techtalk.mvp.mvpandroid.R;
import com.techtalk.mvp.mvpandroid.presentation.presenter.LoginPresenter;
import com.techtalk.mvp.mvpandroid.presentation.view.LoginView;

/**
 * Main Activity for Log in screen
 *
 * @author emiliano.gudino
 */
public class MainActivity extends AppCompatActivity implements LoginView {

    private EditText username;
    private EditText password;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        Button logIn = (Button) findViewById(R.id.btn_logIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateCredentials(username.getText().toString(), password.getText().toString());
            }
        });

        presenter = new LoginPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.detachView();
    }

    @Override
    public void showUsernameError() {
        username.getText().clear();
        username.setHintTextColor(Color.RED);
        username.setHint(R.string.wrong_username);
    }

    @Override
    public void showPasswordError() {
        password.getText().clear();
        password.setHintTextColor(Color.RED);
        password.setHint(R.string.wrong_password);
    }

    @Override
    public void showSuccessMessage() {
        Toast.makeText(this, R.string.successful_log_in, Toast.LENGTH_LONG).show();
    }
}
