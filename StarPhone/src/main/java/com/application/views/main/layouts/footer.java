package com.application.views.main.layouts;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class footer extends VerticalLayout {

    HorizontalLayout topDiv, leftTopDiv, rightTopDiv, bottomDiv;
    H1 titulo;
    H3 a4;
    Anchor linkIg, linkTw, linkFb, a1, a2, a3;
    Image iconIg, iconTw, iconFb;

    public footer() {
        setWidthFull();
        setHeight("90px");
        getStyle().set("background-color", "rgb(135, 206, 235)");
        setPadding(false);
        setSpacing(false);

        topDiv = new HorizontalLayout();
        topDiv.setWidthFull();
        topDiv.setHeight(50, Unit.PERCENTAGE);

        leftTopDiv = new HorizontalLayout();
        leftTopDiv.setWidth(50, Unit.PERCENTAGE);
        leftTopDiv.setHeightFull();
        leftTopDiv.setJustifyContentMode(JustifyContentMode.START);
        leftTopDiv.setAlignItems(Alignment.CENTER);
        titulo = new H1("StarPhone");
        titulo.getStyle().set("font-size", "35px");
        titulo.getStyle().set("color", "white");
        titulo.getStyle().set("padding-left", "30px");
        leftTopDiv.add(titulo);
        topDiv.add(leftTopDiv);

        rightTopDiv = new HorizontalLayout();
        rightTopDiv.setWidth(50, Unit.PERCENTAGE);
        rightTopDiv.setHeightFull();
        rightTopDiv.setJustifyContentMode(JustifyContentMode.END);
        rightTopDiv.setAlignItems(Alignment.CENTER);
        linkIg = new Anchor("https://www.instagram.com/");
        linkIg.addClassName("footerIcon1");
        iconIg = new Image("images/iconIG.svg", "iconIg");
        iconIg.addClassName("footerIcon1");
        linkIg.add(iconIg);
        rightTopDiv.add(linkIg);
        linkTw = new Anchor("https://twitter.com/");
        linkTw.addClassName("footerIcon1");
        iconTw = new Image("images/iconTw.svg", "iconTw");
        iconTw.addClassName("footerIcon1");
        linkTw.add(iconTw);
        rightTopDiv.add(linkTw);
        linkFb = new Anchor("https://facebook.com/");
        linkFb.addClassName("footerIcon2");
        iconFb = new Image("images/iconFb.svg", "iconFb");
        iconFb.addClassName("footerIcon2");
        linkFb.add(iconFb);
        rightTopDiv.add(linkFb);
        topDiv.add(rightTopDiv);
        add(topDiv);

        bottomDiv = new HorizontalLayout();
        bottomDiv.setWidthFull();
        bottomDiv.setHeight(50, Unit.PERCENTAGE);
        bottomDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        bottomDiv.setAlignItems(Alignment.END);
        bottomDiv.getStyle().set("gap", "200px");
        a1 = new Anchor("/avisolegal", "Aviso Legal");
        a1.addClassName("anchor");
        bottomDiv.add(a1);
        a2 = new Anchor("/cookies", "Política de Cookies");
        a2.addClassName("anchor");
        bottomDiv.add(a2);
        a3 = new Anchor("/trabajaconnosotros", "Trabaja con nosotros");
        a3.addClassName("anchor");
        bottomDiv.add(a3);
        a4 = new H3("Copyright© 2023. Todos los derechos reservados.");
        a4.addClassName("anchor");
        bottomDiv.add(a4);
        add(bottomDiv);

    }

}
