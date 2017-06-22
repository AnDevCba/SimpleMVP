// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.domain.interactor;

import android.text.TextUtils;

/**
 * Implementación del interactor {{@link LoginInteractor}
 *
 * @author emiliano.gudino
 */
public class LoginInteractorImpl implements LoginInteractor {

    private static final String GLOBANT_USERNAME = "Globant";
    private static final String GLOBANT_PASSWORD = "Globant";

    private OnLoginFinishedListener listener;

    public LoginInteractorImpl(OnLoginFinishedListener listener) {
        this.listener = listener;
    }

    @Override
    public void login(final String username, final String password) {
        // Acá llamaríamos a una API de logueo, Base de datos, caché (shared preferences)
        if (TextUtils.isEmpty(username) || !username.equals(GLOBANT_USERNAME)) {
            listener.onUsernameError();
            return;
        }
        if (TextUtils.isEmpty(password) || !password.equals(GLOBANT_PASSWORD)) {
            listener.onPasswordError();
            return;
        }
        listener.onSuccess();
    }
}
