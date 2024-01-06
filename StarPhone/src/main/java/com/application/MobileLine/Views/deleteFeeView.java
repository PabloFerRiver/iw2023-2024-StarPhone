package com.application.MobileLine.Views;

import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.application.User.Views.menu;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_MARKETING" })
@CssImport("./styles/styles.css")
@PageTitle("Eliminar Tarifa")
@Route(value = "/eliminarrtarifa", layout = menu.class)
public class deleteFeeView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;
    Select<String> titles;
    Button confirmar;
    FeeService feeService;

    public deleteFeeView(FeeService fService) {
        feeService = fService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        List<Fee> fees = feeService.getAll();
        List<String> titlesFee = new ArrayList<>();
        for (Fee fee : fees) {
            titlesFee.add(fee.getTitle());
        }

        titles = new Select<String>();
        titles.addClassName("activefield");
        titles.setLabel("Tarifas:");
        if (titlesFee.size() > 0) {
            titles.setItems(titlesFee);
            titles.setValue(titlesFee.get(0));
        } else {
            titles.setItems("No hay tarifas disponibles!");
            titles.setValue("No hay tarifas disponibles!");
        }
        titles.setId("titles");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onDeleteButtonClick());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("380px");
        confirmSquare.setHeight("320px");
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
        titleDelete = new H3("Eliminar Tarifa");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(titles, confirmar);
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

    public void onDeleteButtonClick() {
        if (!titles.getValue().isEmpty()) {
            if (feeService.deleteFeeByTitle(titles.getValue())) {
                Notification.show("Genial. Eliminada correctamente!!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Algo falló! Inténtelo de nuevo.").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }
}