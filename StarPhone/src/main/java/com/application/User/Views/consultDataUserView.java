package com.application.User.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT", "ROLE_MARKETING", "ROLE_FINANCE" })
@CssImport("./styles/styles.css")
@PageTitle("Consultar Datos Usuario")
@Route(value = "/consultardatosusuario", layout = menu.class)
public class consultDataUserView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv;
    H3 titleConsult;
    TextField DNI;
    H4 dniText, nameText, surnameText, usernameText, cityText, countryText, birthdateText, phoneNumberText, emailText;

    private final UserService userService;

    public consultDataUserView(UserService uService) {
        userService = uService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("400px");
        confirmSquare.setHeight("280px");
        confirmSquare.setPadding(false);
        confirmSquare.setSpacing(false);
        confirmSquare.setJustifyContentMode(JustifyContentMode.CENTER);
        confirmSquare.setAlignItems(Alignment.CENTER);
        confirmSquare.getStyle().set("border-radius", "12px");

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleConsult = new H3("Consultar Datos Usuario");
        titleConsult.getStyle().set("font-size", "28px");
        titleConsult.getStyle().set("color", "white");
        titleDiv.add(titleConsult);
        confirmSquare.add(titleDiv);

        DNI = new TextField("DNI:");
        DNI.addClassName("activefield");
        DNI.setHelperText("DNI del usuario a consultar CON letra.");
        DNI.setId("DNI");

        bodyDiv = new VerticalLayout(DNI);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        confirmSquare.add(bodyDiv);

        DNI.setValueChangeMode(ValueChangeMode.EAGER);
        DNI.addValueChangeListener(event -> {
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                confirmSquare.setWidth("auto");
                confirmSquare.setHeight("auto");
                dniText.setText("DNI: " + user.getDNI());
                nameText.setText("Nombre: " + user.getName());
                surnameText.setText("Apellidos: " + user.getSurname());
                usernameText.setText("Nombre de usuario: " + user.getUsername());
                cityText.setText("Ciudad: " + user.getCity());
                countryText.setText("País: " + user.getCountry());
                birthdateText.setText("Fecha de nacimiento: " + user.getBirthdate());
                phoneNumberText.setText("Número de teléfono: " + user.getPhoneNumber());
                emailText.setText("Correo electrónico: " + user.getEmail());
                bodyDiv.add(dniText, nameText, surnameText, usernameText, cityText, countryText,
                        birthdateText, phoneNumberText, emailText);
            }
        });

        dniText = new H4("");
        dniText.addClassName("personaldatafield");
        nameText = new H4("");
        nameText.addClassName("personaldatafield");
        surnameText = new H4("");
        surnameText.addClassName("personaldatafield");
        usernameText = new H4("");
        usernameText.addClassName("personaldatafield");
        cityText = new H4("");
        cityText.addClassName("personaldatafield");
        countryText = new H4("");
        countryText.addClassName("personaldatafield");
        birthdateText = new H4("");
        birthdateText.addClassName("personaldatafield");
        phoneNumberText = new H4("");
        phoneNumberText.addClassName("personaldatafield");
        emailText = new H4("");
        emailText.addClassName("personaldatafield");

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
        expand(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
    }

}
