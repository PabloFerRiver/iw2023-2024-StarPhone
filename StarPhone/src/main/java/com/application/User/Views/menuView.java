package com.application.User.Views;

import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Menu")
@Route(value = "/menu", layout = menu.class)
public class menuView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv;
    VerticalLayout center, bodyDiv, container;
    H3 titleMenu, p1, p2;
    AuthenticatedUser authenticatedUser;

    public menuView(AuthenticatedUser authUser) {
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

        container = new VerticalLayout();
        container.setWidth("450px");
        container.setHeight("280px");
        container.setPadding(false);
        container.setSpacing(false);
        container.setAlignItems(Alignment.CENTER);
        container.getStyle().set("border-radius", "12px");

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleMenu = new H3("StarPhone");
        titleMenu.getStyle().set("font-size", "30px");
        titleMenu.getStyle().set("color", "white");
        titleDiv.add(titleMenu);
        container.add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        p1 = new H3("Bienvenido a Starphone,");
        p1.getStyle().set("font-size", "32px");
        p2 = new H3(authenticatedUser.get().get().getName() + "!");
        p2.getStyle().set("font-size", "32px");
        bodyDiv.add(p1, p2);

        container.add(bodyDiv);
        expand(bodyDiv);

        center.add(container);
        add(center);
        expand(center);
    }
}
