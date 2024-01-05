package com.application.User.Views;

import com.application.User.Security.AuthenticatedUser;
import com.application.User.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@PageTitle("Cambiar Credenciales")
@Route(value = "/cambiarcredenciales", layout = menu.class)
@CssImport("./styles/styles.css")
public class changePasswordView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 confirmTitle;
    PasswordField password, repeatPassword;
    Button confirmar;
    private final UserService userService;
    private final AuthenticatedUser authenticatedUser;

    public changePasswordView(UserService uService, AuthenticatedUser authUser) {
        userService = uService;
        authenticatedUser = authUser;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        password = new PasswordField("Contraseña");
        password.addClassName("activefield");
        password.setRequired(true);
        password.setId("password");

        repeatPassword = new PasswordField("Repetir Contraseña");
        repeatPassword.addClassName("activefield");
        repeatPassword.setRequired(true);
        repeatPassword.setId("repeatPassword");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onChangePasswordButton());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("350px");
        confirmSquare.setHeight("350px");
        confirmSquare.setPadding(false);
        confirmSquare.setSpacing(false);
        confirmSquare.setAlignItems(Alignment.CENTER);
        confirmSquare.getStyle().set("border-radius", "12px");

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        confirmTitle = new H3("Cambiar Credenciales");
        confirmTitle.getStyle().set("font-size", "28px");
        confirmTitle.getStyle().set("color", "white");
        titleDiv.add(confirmTitle);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(password, repeatPassword, confirmar);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        confirmSquare.add(bodyDiv);
        expand(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
    }

    public void onChangePasswordButton() {
        if (!password.getValue().isEmpty() && !repeatPassword.getValue().isEmpty()) {
            confirmar.setEnabled(false);
            if (password.getValue().equals(repeatPassword.getValue())) {
                userService.changePassword(authenticatedUser.get().get(), password.getValue());
                Notification.show("Contraseña cambiada correctamente")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else {
                Notification.show("No coinciden las contraseñas").addThemeVariants(NotificationVariant.LUMO_ERROR);
                confirmar.setEnabled(true);
            }
        } else {
            Notification.show("Rellene todos los campos!").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}