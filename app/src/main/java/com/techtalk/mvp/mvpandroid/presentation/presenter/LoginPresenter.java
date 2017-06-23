// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.presentation.presenter;

import android.support.test.espresso.core.deps.guava.base.Preconditions;

import com.techtalk.mvp.mvpandroid.domain.interactor.LoginInteractor;
import com.techtalk.mvp.mvpandroid.domain.interactor.LoginInteractorImpl;
import com.techtalk.mvp.mvpandroid.presentation.view.LoginView;

/**
 * Represents the Presenter for the Log in View
 *
 * @author @emiliano.gudiño
 */
public class LoginPresenter implements LoginInteractor.OnLoginFinishedListener {

    private LoginView view;

    /**
     * It attaches a {@param view} that implements {@link LoginView}
     *
     * @param view to be attached to the presenter
     */
    public void attachView(LoginView view) {
        Preconditions.checkNotNull(view, "view to be attached must not be null");
        this.view = view;
    }

    /**
     * It detaches the view attached on {@link #attachView(LoginView)}
     */
    public void detachView() {
        this.view = null;
    }

    /**
     * @return true is the view is attached to the presenter, false otherwise
     */
    private boolean isViewAttached() {
        return view != null;
    }

    /**
     * Validate credential from UI.
     *
     * @param username entered.
     * @param password entered.
     */
    public void validateCredentials(String username, String password) {
        if (isViewAttached()) {
            LoginInteractor loginInteractor = new LoginInteractorImpl(this);
            loginInteractor.login(username, password);
        }
    }

    @Override
    public void onUsernameError() {
        if (isViewAttached()) {
            view.showUsernameError();
        }
    }

    @Override
    public void onPasswordError() {
        if (isViewAttached()) {
            view.showPasswordError();
        }
    }

    @Override
    public void onSuccess() {
        if (isViewAttached()) {
            view.showSuccessMessage();
        }
    }
}
