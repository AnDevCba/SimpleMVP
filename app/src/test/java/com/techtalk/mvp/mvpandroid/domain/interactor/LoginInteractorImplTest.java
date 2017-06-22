// MVP Tech Talk
package com.techtalk.mvp.mvpandroid.domain.interactor;

import android.text.TextUtils;

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

/**
 * Clase de test para LoginInteractor.
 *
 * @author Gustavo Morales.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(TextUtils.class)
public class LoginInteractorImplTest {

    @Mock
    LoginInteractor.OnLoginFinishedListener listener;

    private LoginInteractor interactor;

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
        interactor = new LoginInteractorImpl(listener);
    }

    @Test
    public void loginWithIncorrectUsernameShouldReturnUsernameError() {
        interactor.login("guest", "Globant");

        verify(listener).onUsernameError();
    }

    @Test
    public void loginWithNullUsernameShouldReturnUsernameError() {
        interactor.login(null, "Globant");

        verify(listener).onUsernameError();
    }

    @Test
    public void loginWithIncorrectPasswordShouldReturnPasswordError() {
        interactor.login("Globant", "password");

        verify(listener).onPasswordError();
    }

    @Test
    public void loginWithNullPasswordShouldReturnPasswordError() {
        interactor.login("Globant", null);

        verify(listener).onPasswordError();
    }

    @Test
    public void loginValidCredentialsShouldCallSuccess() {
        interactor.login("Globant", "Globant");

        verify(listener).onSuccess();
    }
}