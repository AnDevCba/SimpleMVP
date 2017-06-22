// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.presentation.presenter;

import android.text.TextUtils;

import com.techtalk.mvp.mvpandroid.domain.interactor.LoginInteractor;
import com.techtalk.mvp.mvpandroid.presentation.view.LoginView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Clase de test para LoginPresenter.
 *
 * @author Gustavo Morales
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class LoginPresenterTest {

    @Mock
    LoginView view;

    @Mock
    LoginInteractor interactor;

    private LoginPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(TextUtils.class);
        PowerMockito.when(TextUtils.isEmpty(any(CharSequence.class))).thenAnswer(new Answer<Boolean>() {
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                CharSequence a = (CharSequence) invocation.getArguments()[0];
                return !(a != null && a.length() > 0);
            }
        });
        presenter = new LoginPresenter();
        presenter.attachView(view);
    }

    @Test
    public void validateCredentialsShouldNotCallInteractorWhenViewIsDetached() {
        presenter.detachView();
        presenter.validateCredentials("Globant", "Globant");

        verifyZeroInteractions(interactor);
    }

    @Test
    public void onUsernameErrorShouldNotShowUsernameErrorMessageWhenViewIsDetached() {
        presenter.detachView();
        presenter.onUsernameError();

        verifyZeroInteractions(view);
    }

    @Test
    public void onUsernameErrorShouldShowUsernameErrorMessageWhenViewIsAttached() {
        presenter.onUsernameError();

        verify(view).showUsernameError();
    }

    @Test
    public void onPasswordErrorShouldNotShowPasswordErrorMessageWhenViewIsDetached() {
        presenter.detachView();
        presenter.onPasswordError();

        verifyZeroInteractions(view);
    }

    @Test
    public void onPasswordErrorShouldShowPasswordErrorMessageWhenViewIsAttached() {
        presenter.onPasswordError();

        verify(view).showPasswordError();
    }

    @Test
    public void onSuccessfulLoginShouldNotShowSuccessMessageWhenViewIsDetached() {
        presenter.detachView();
        presenter.onSuccess();

        verifyZeroInteractions(view);
    }

    @Test
    public void onSuccessfulLoginShouldShowSuccessMessageWhenViewIsAttached() {
        presenter.onSuccess();

        verify(view).showSuccessMessage();
    }
}