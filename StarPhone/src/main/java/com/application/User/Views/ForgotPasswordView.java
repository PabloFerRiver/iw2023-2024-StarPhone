package com.application.User.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserEmailService;
import com.application.User.Services.UserService;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Recuperar Credenciales")
@Route(value = "/recuperarcredenciales")
public class forgotPasswordView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 confirmTitle;
    EmailField email;
    Button confirmar;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserEmailService userEmailService;

    public forgotPasswordView(UserService uService, PasswordEncoder pEncoder, UserEmailService uEmailService) {
        userService = uService;
        passwordEncoder = pEncoder;
        userEmailService = uEmailService;
        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        email = new EmailField("Email:");
        email.addClassName("activefield");
        email.setId("email");
        email.setRequired(true);

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onGetNewCredentialsClick());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("350px");
        confirmSquare.setHeight("300px");
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
        confirmTitle = new H3("Recuperar Credenciales");
        confirmTitle.getStyle().set("font-size", "28px");
        confirmTitle.getStyle().set("color", "white");
        titleDiv.add(confirmTitle);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(email, confirmar);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        confirmSquare.add(bodyDiv);
        expand(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
    }

    public void onGetNewCredentialsClick() {
        confirmar.setEnabled(false);
        User user = userService.getUserByEmail(email.getValue());
        String nText = "";
        if (user.getId() != null && user.getActivate() == true) {
            String passWord = UUID.randomUUID().toString().substring(0, 8);
            user.setPassword(passwordEncoder.encode(passWord));

            Button closeButton = new Button(new Icon("lumo", "cross"));
            closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            closeButton.getElement().setAttribute("aria-label", "Close");
            Notification n = new Notification();

            if (userService.saveUser(user)) {
                userEmailService.sendForgotPasswordEmail(user, passWord);
                closeButton.addClickListener(event -> {
                    n.close();
                    UI.getCurrent().getPage().setLocation("/login");
                });
                Pre text = new Pre("Genial. Por favor, revisa tu email!\nClick en la cruz para continuar.");
                HorizontalLayout layout = new HorizontalLayout(text, closeButton);
                layout.setAlignItems(Alignment.CENTER);
                n.add(layout);
                n.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                n.open();
            } else {
                nText = "Algo falló! Contacte con un operador.";
                Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        } else {
            nText = "Datos incorrectos! Revise los datos introducidos.";
            Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }

}
