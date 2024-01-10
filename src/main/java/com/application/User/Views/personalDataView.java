package com.application.User.Views;

import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle(" Datos Personales")
@Route(value = "/datospersonales", layout = menu.class)
public class personalDataView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv;
    H3 titleConsult;
    H4 dni, name, surname, username, city, country, birthdate, phoneNumber, email;

    private final AuthenticatedUser authenticatedUser;

    public personalDataView(AuthenticatedUser authUser) {
        authenticatedUser = authUser;

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
        titleConsult = new H3("Datos Personales");
        titleConsult.getStyle().set("font-size", "28px");
        titleConsult.getStyle().set("color", "white");
        titleDiv.add(titleConsult);
        confirmSquare.add(titleDiv);

        dni = new H4("DNI: " + authenticatedUser.get().get().getDNI());
        dni.addClassName("personaldatafield");
        name = new H4("Nombre: " + authenticatedUser.get().get().getName());
        name.addClassName("personaldatafield");
        surname = new H4("Apellidos: " + authenticatedUser.get().get().getSurname());
        surname.addClassName("personaldatafield");
        username = new H4("Nombre de usuario: " + authenticatedUser.get().get().getUsername());
        username.addClassName("personaldatafield");
        city = new H4("Ciudad: " + authenticatedUser.get().get().getCity());
        city.addClassName("personaldatafield");
        country = new H4("País: " + authenticatedUser.get().get().getCountry());
        country.addClassName("personaldatafield");
        birthdate = new H4("Fecha de nacimiento: " + authenticatedUser.get().get().getBirthdate());
        birthdate.addClassName("personaldatafield");
        phoneNumber = new H4("Número de teléfono: " + authenticatedUser.get().get().getPhoneNumber());
        phoneNumber.addClassName("personaldatafield");
        email = new H4("Correo electrónico: " + authenticatedUser.get().get().getEmail());
        email.addClassName("personaldatafield");

        bodyDiv = new VerticalLayout(dni, name, surname, username, city, country, birthdate, phoneNumber, email);
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
