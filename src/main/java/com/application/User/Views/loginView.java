package com.application.User.Views;

import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Login")
@Route(value = "/login")
@CssImport("./styles/styles.css")
public class loginView extends LoginOverlay implements BeforeEnterObserver, HasComponents {

    private final AuthenticatedUser authenticatedUser;

    public loginView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        setAction("login");

        // Formulario de inicio de sesión
        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setHeader(new LoginI18n.Header());
        i18n.getHeader().setTitle("Inicio de Sesión");
        i18n.setAdditionalInformation(null);
        LoginI18n.Form i18nForm = i18n.getForm();
        i18nForm.setTitle("");
        i18nForm.setUsername("Usuario");
        i18nForm.setPassword("Contraseña");
        i18nForm.setSubmit("Confirmar");
        i18nForm.setForgotPassword("Ha olvidado su contraseña?");
        i18n.setForm(i18nForm);

        LoginI18n.ErrorMessage i18nErrorMessage = i18n.getErrorMessage();
        i18nErrorMessage.setTitle("Usuario o contraseña incorrecto(s)");
        i18nErrorMessage.setMessage("Por favor, revise los datos introducidos");
        i18n.setErrorMessage(i18nErrorMessage);

        setI18n(i18n);

        setForgotPasswordButtonVisible(true);
        addForgotPasswordListener(e -> UI.getCurrent().navigate(forgotPasswordView.class));
        setOpened(true);

        // ------------------------------
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (authenticatedUser.get().isPresent()) {
            // Already logged in
            setOpened(false);
            event.forwardTo("");
        }

        setError(event.getLocation().getQueryParameters().getParameters().containsKey("error"));
    }
}