package com.application.User.Services;

import com.application.User.Entities.User;

public interface EmailService {
    boolean sendForgotPasswordEmail(User usu, String password);

    boolean sendActivateEmail(User usu);
}
