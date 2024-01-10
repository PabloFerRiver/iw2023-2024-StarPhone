package com.application.Contract.Views;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class contract extends VerticalLayout {

    HorizontalLayout titleDiv, footerDiv;
    VerticalLayout bodyDiv;
    H3 titleText, monthlyPriceField;

    public contract(String title, String descMobile, String descFiber, String descTV, int maxMLines, double p) {
        setWidth("400px");
        setHeight("480px");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleText = new H3(title);
        titleText.getStyle().set("font-size", "26px");
        titleText.getStyle().set("color", "white");
        titleDiv.add(titleText);
        add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.add(new H3("Móvil"));
        bodyDiv.add(new H4(descMobile));
        bodyDiv.add(new H3("Fibra"));
        bodyDiv.add(new H4(descFiber));
        bodyDiv.add(new H3("TV"));
        bodyDiv.add(new H4(descTV));
        bodyDiv.add(new H3("Total líneas móviles"));
        bodyDiv.add(new H4(String.valueOf(maxMLines)));
        add(bodyDiv);

        footerDiv = new HorizontalLayout();
        footerDiv.setWidthFull();
        footerDiv.setHeight("60px");
        footerDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        footerDiv.setAlignItems(Alignment.CENTER);
        footerDiv.getStyle().set("border-radius", "0 0 12px 12px");
        footerDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        monthlyPriceField = new H3("Precio mensual: " + p + " €");
        monthlyPriceField.getStyle().set("font-size", "24px");
        monthlyPriceField.getStyle().set("color", "white");
        footerDiv.add(monthlyPriceField);
        add(footerDiv);
        expand(bodyDiv);
    }
}
