// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.domain.interactor;

/**
 * Interactor para loguear un usuario
 *
 * @author emiliano.gudino
 */
public interface LoginInteractor {

    interface OnLoginFinishedListener {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username, String password, OnLoginFinishedListener listener);
}
