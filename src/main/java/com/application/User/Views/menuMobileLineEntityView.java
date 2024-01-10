package com.application.User.Views;

import com.application.MobileLine.Views.consultMobileLinesUserView;
import com.application.MobileLine.Views.createMobileLineView;
import com.application.MobileLine.Views.deleteMobileLineView;
import com.application.MobileLine.Views.modifyMobileLineView;
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
@PageTitle("Línea")
@Route(value = "/linea", layout = menu.class)
@CssImport("./styles/styles.css")
public class menuMobileLineEntityView extends VerticalLayout {

    HorizontalLayout centerDiv;
    Button b1, b2, b3, b4;
    private AccessAnnotationChecker accessChecker;

    public menuMobileLineEntityView(AccessAnnotationChecker aChecker) {
        this.accessChecker = aChecker;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        b1 = new Button("CREAR");
        b1.addClassName("buttonsX");
        b1.setId("b1");
        b1.addClickListener(e -> UI.getCurrent().navigate("/crearlinea"));

        b2 = new Button("MODIFICAR");
        b2.addClassName("buttonsX");
        b2.setId("b2");
        b2.addClickListener(e -> UI.getCurrent().navigate("/modificarlinea"));

        b3 = new Button("ELIMINAR");
        b3.addClassName("buttonsX");
        b3.setId("b3");
        b3.addClickListener(e -> UI.getCurrent().navigate("/eliminarlinea"));

        b4 = new Button("CONSULTAR USUARIOS");
        b4.addClassName("buttonsX");
        b4.setId("b4");
        b4.addClickListener(e -> UI.getCurrent().navigate("/consultarlineasusuario"));

        centerDiv = new HorizontalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        centerDiv.getStyle().set("gap", "80px");

        if (accessChecker.hasAccess(createMobileLineView.class)) {
            centerDiv.add(b1);
        }

        if (accessChecker.hasAccess(modifyMobileLineView.class)) {
            centerDiv.add(b2);
        }

        if (accessChecker.hasAccess(deleteMobileLineView.class)) {
            centerDiv.add(b3);
        }

        if (accessChecker.hasAccess(consultMobileLinesUserView.class)) {
            centerDiv.add(b4);
        }

        add(centerDiv);
        expand(centerDiv);
    }
}