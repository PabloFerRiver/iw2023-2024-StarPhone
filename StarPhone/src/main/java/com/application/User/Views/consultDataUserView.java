package com.application.User.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
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
    H4 dni, name, surname, username, city, country, birthdate, phoneNumber, email;

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
        confirmSquare.setWidth("auto");
        confirmSquare.setHeight("auto");
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
        titleConsult = new H3("Consultar Datos Usuario");
        titleConsult.getStyle().set("font-size", "28px");
        titleConsult.getStyle().set("color", "white");
        titleDiv.add(titleConsult);
        confirmSquare.add(titleDiv);

        DNI = new TextField("DNI:");
        DNI.addClassName("activefield");
        DNI.setHelperText("DNI del usuario a consultar CON letra.");
        DNI.setId("DNI");

        DNI.addValueChangeListener(event -> {
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                dni.setText("DNI: " + user.getDNI());
                name.setText("Nombre: " + user.getName());
                surname.setText("Apellidos: " + user.getSurname());
                username.setText("Nombre de usuario: " + user.getUsername());
                city.setText("Ciudad: " + user.getCity());
                country.setText("País: " + user.getCountry());
                birthdate.setText("Fecha de nacimiento: " + user.getBirthdate());
                phoneNumber.setText("Número de teléfono: " + user.getPhoneNumber());
                email.setText("Correo electrónico: " + user.getEmail());
            }
        });

        dni = new H4("DNI: ");
        dni.addClassName("personaldatafield");
        dni.setId("dni");
        name = new H4("Nombre: ");
        name.addClassName("personaldatafield");
        name.setId("name");
        surname = new H4("Apellidos: ");
        surname.addClassName("personaldatafield");
        surname.setId("surname");
        username = new H4("Nombre de usuario: ");
        username.addClassName("personaldatafield");
        username.setId("username");
        city = new H4("Ciudad: ");
        city.addClassName("personaldatafield");
        city.setId("city");
        country = new H4("País: ");
        country.addClassName("personaldatafield");
        country.setId("country");
        birthdate = new H4("Fecha de nacimiento: ");
        birthdate.addClassName("personaldatafield");
        birthdate.setId("birthdate");
        phoneNumber = new H4("Número de teléfono: ");
        phoneNumber.addClassName("personaldatafield");
        phoneNumber.setId("phoneNumber");
        email = new H4("Correo electrónico: ");
        email.addClassName("personaldatafield");
        email.setId("email");

        bodyDiv = new VerticalLayout(DNI, dni, name, surname, username, city, country, birthdate, phoneNumber, email);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        confirmSquare.add(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
        expand(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);
    }

}
