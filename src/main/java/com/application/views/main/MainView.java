package com.application.views.main;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.application.views.main.layouts.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("StarPhone")
@Route(value = "/")
public class MainView extends VerticalLayout {

    navBar m;
    VerticalLayout centerDiv, leftIntoCenterDiv, rightIntoCenterDiv;
    HorizontalLayout intoCenterDiv;
    Button butregister, butlogin;

    public MainView() {
        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        add(new header());

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.getStyle().set("gap", "140px");
        centerDiv.setAlignItems(Alignment.CENTER);

        m = new navBar();
        centerDiv.add(m);

        intoCenterDiv = new HorizontalLayout();
        intoCenterDiv.setWidth(60, Unit.PERCENTAGE);
        intoCenterDiv.setHeight("250px");
        intoCenterDiv.getStyle().set("background-color", "rgba(255, 255, 255, 0.2)");

        leftIntoCenterDiv = new VerticalLayout();
        leftIntoCenterDiv.setWidth(50, Unit.PERCENTAGE);
        leftIntoCenterDiv.setHeightFull();
        leftIntoCenterDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        leftIntoCenterDiv.setAlignItems(Alignment.CENTER);
        butlogin = new Button("Iniciar Sesión");
        butlogin.addClickListener(e -> UI.getCurrent().getPage().setLocation("/menu"));
        butlogin.addClassName("mainViewButton");
        butlogin.getStyle().set("cursor", "pointer");
        leftIntoCenterDiv.add(butlogin);
        intoCenterDiv.add(leftIntoCenterDiv);

        rightIntoCenterDiv = new VerticalLayout();
        rightIntoCenterDiv.setWidth(50, Unit.PERCENTAGE);
        rightIntoCenterDiv.setHeightFull();
        rightIntoCenterDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        rightIntoCenterDiv.setAlignItems(Alignment.CENTER);
        butregister = new Button("Registrarse");
        butregister.addClickListener(e -> UI.getCurrent().getPage().setLocation("/starphonetarifas"));
        butregister.addClassName("mainViewButton");
        butregister.getStyle().set("cursor", "pointer");
        rightIntoCenterDiv.add(butregister);
        intoCenterDiv.add(rightIntoCenterDiv);

        centerDiv.add(intoCenterDiv);
        add(centerDiv);
        expand(centerDiv);

        add(new footer());
    }
}
