package com.application.User.Views;

import com.application.User.Services.UserService;
import com.application.User.Entities.User;
import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Modificar Datos Personales")
@Route(value = "/modificardatospersonales", layout = menu.class)
public class modifyPersonalDataView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleRegister;
    TextField DNI, username, city, country;
    EmailField email;
    Button confirmar;

    private final UserService userService;
    private final AuthenticatedUser authenticatedUser;

    public modifyPersonalDataView(UserService service, AuthenticatedUser authUser) {
        userService = service;
        authenticatedUser = authUser;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        center = new VerticalLayout();
        center.setWidthFull();
        center.setPadding(false);
        center.setSpacing(false);
        center.setAlignItems(Alignment.CENTER);
        center.setJustifyContentMode(JustifyContentMode.CENTER);

        registerForm = new VerticalLayout();
        registerForm.setWidth("750px");
        registerForm.setHeight("550px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI:");
        DNI.addClassName("modifyformfield");
        DNI.setMinLength(9);
        DNI.setMaxLength(9);
        DNI.setRequired(true);
        DNI.setHelperText("Introduzca su DNI completo para poder modificar sus datos.");
        DNI.setId("DNI");

        username = new TextField("Nombre de Usuario:");
        username.addClassName("modifyformfield");
        username.setHelperText("Este cambio conlleva el cierre de sesión.");
        username.setId("username");

        email = new EmailField("Email:");
        email.addClassName("modifyformfield");
        email.setId("email");

        city = new TextField("Ciudad:");
        city.addClassName("modifyformfield");
        city.setId("city");

        country = new TextField("País:");
        country.addClassName("modifyformfield");
        country.setId("country");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("modifyformbutton");
        confirmar.addClickListener(e -> {
            onModifyButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleRegister = new H3("Modificar Datos Usuario");
        titleRegister.getStyle().set("font-size", "28px");
        titleRegister.getStyle().set("color", "white");
        titleDiv.add(titleRegister);
        registerForm.add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        bodySubDiv1 = new HorizontalLayout(DNI);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysmodify");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(username, email);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(city, country);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");
        bodySubDiv4 = new HorizontalLayout(confirmar);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysmodify");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);
    }

    public void onModifyButtonClick() {
        if (!DNI.getValue().isEmpty()) {
            User user = authenticatedUser.get().get();
            confirmar.setEnabled(false);
            if (!username.getValue().isEmpty())
                user.setUsername(username.getValue());

            if (!email.getValue().isEmpty())
                user.setEmail(email.getValue());

            if (!city.getValue().isEmpty())
                user.setCity(city.getValue());

            if (!country.getValue().isEmpty())
                user.setCountry(country.getValue());

            if (userService.saveUser(user)) {
                Notification.show("Genial. Datos modificados correctamente!!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                if (!username.getValue().isEmpty()) {
                    authenticatedUser.logout();
                } else {
                    UI.getCurrent().navigate("/menu");
                }
            } else {
                Notification.show("Algo falló! Revise los datos.").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }

        } else {
            Notification.show("Error! Rellene el campo DNI.").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
