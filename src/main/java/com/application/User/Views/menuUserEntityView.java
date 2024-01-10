package com.application.User.Views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;

import jakarta.annotation.security.RolesAllowed;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT", "ROLE_MARKETING", "ROLE_FINANCE" })
@PageTitle("Usuario")
@Route(value = "/usuario", layout = menu.class)
@CssImport("./styles/styles.css")
public class menuUserEntityView extends VerticalLayout {

    HorizontalLayout centerDiv;
    Button b1, b2, b3, b4, b5;
    private AccessAnnotationChecker accessChecker;

    public menuUserEntityView(AccessAnnotationChecker aChecker) {
        this.accessChecker = aChecker;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        b1 = new Button("REGISTRAR");
        b1.addClassName("buttonsX");
        b1.setId("b1");
        b1.addClickListener(e -> UI.getCurrent().navigate("/registrarusuario"));

        b2 = new Button("MODIFICAR");
        b2.addClassName("buttonsX");
        b2.setId("b2");
        b2.addClickListener(e -> UI.getCurrent().navigate("/modificarusuario"));

        b3 = new Button("BORRAR");
        b3.addClassName("buttonsX");
        b3.setId("b3");
        b3.addClickListener(e -> UI.getCurrent().navigate("/borrarusuario"));

        b4 = new Button("GESTIONAR ROLES");
        b4.addClassName("buttonsX");
        b4.setId("b4");
        b4.addClickListener(e -> UI.getCurrent().navigate("/gestionarroles"));

        b5 = new Button("CONSULTAR DATOS");
        b5.addClassName("buttonsX");
        b5.setId("b5");
        b5.addClickListener(e -> UI.getCurrent().navigate("/consultardatosusuario"));

        centerDiv = new HorizontalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        centerDiv.getStyle().set("gap", "80px");

        if (accessChecker.hasAccess(adminRegisterUserView.class)) {
            centerDiv.add(b1);
        }

        if (accessChecker.hasAccess(modifyDataUserView.class)) {
            centerDiv.add(b2);
        }

        if (accessChecker.hasAccess(adminDeleteUserView.class)) {
            centerDiv.add(b3);
        }

        if (accessChecker.hasAccess(rolManagementView.class)) {
            centerDiv.add(b4);
        }

        if (accessChecker.hasAccess(consultDataUserView.class)) {
            centerDiv.add(b5);
        }

        add(centerDiv);
        expand(centerDiv);
    }
}
