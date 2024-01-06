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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_MARKETING" })
@CssImport("./styles/styles.css")
@PageTitle("Modificar Tarifa")
@Route(value = "/modificartarifa", layout = menu.class)
public class modifyFeeView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4, bodySubDiv5, footerDiv;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleModify;
    Select<String> title;
    TextField newTitle, descriptionMobile, descriptionFiber, descriptionTV;
    NumberField monthlyData, monthlyPrice;
    IntegerField monthlyCalls, monthlySMS, maxMobileLines;
    Button confirmar;

    private final FeeService feeService;

    public modifyFeeView(FeeService fService) {
        feeService = fService;
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

        registerForm = new VerticalLayout();
        registerForm.setWidth("1100px");
        registerForm.setHeight("680px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        List<Fee> f = feeService.getAll();
        List<String> titlesFee = new ArrayList<>();
        for (Fee fee : f) {
            titlesFee.add(fee.getTitle());
        }
        title = new Select<String>();
        title.addClassName("modifyformfield");
        title.setLabel("Título:");
        if (titlesFee.size() > 0) {
            title.setItems(titlesFee);
            title.setValue(titlesFee.get(0));
        } else {
            title.setItems("No hay tarifas disponibles!");
            title.setValue("No hay tarifas disponibles!");
        }
        title.setId("title");

        newTitle = new TextField();
        newTitle.addClassName("modifyformfield");
        newTitle.setLabel("Nuevo Título:");
        newTitle.setId("newTitle");

        monthlyPrice = new NumberField();
        monthlyPrice.addClassName("modifyformfield");
        monthlyPrice.setLabel("Precio Mensual:");
        monthlyPrice.setId("monthlyPrice");

        maxMobileLines = new IntegerField();
        maxMobileLines.addClassName("modifyformfield");
        maxMobileLines.setLabel("Líneas Móviles:");
        maxMobileLines.setId("maxMobileLines");

        descriptionMobile = new TextField();
        descriptionMobile.addClassName("modifyformfield");
        descriptionMobile.setLabel("Descripción Móvil:");
        descriptionMobile.setId("descriptionMobile");

        descriptionFiber = new TextField();
        descriptionFiber.addClassName("modifyformfield");
        descriptionFiber.setLabel("Descripción Fibra:");
        descriptionFiber.setId("descriptionFiber");

        descriptionTV = new TextField();
        descriptionTV.addClassName("modifyformfield");
        descriptionTV.setLabel("Descripción TV:");
        descriptionTV.setId("descriptionTV");

        monthlyData = new NumberField();
        monthlyData.addClassName("modifyformfield");
        monthlyData.setLabel("Datos Mensuales:");
        monthlyData.setId("monthlyData");

        monthlyCalls = new IntegerField();
        monthlyCalls.addClassName("modifyformfield");
        monthlyCalls.setLabel("Minutos Mensuales:");
        monthlyCalls.setId("monthlyCalls");

        monthlySMS = new IntegerField();
        monthlySMS.addClassName("modifyformfield");
        monthlySMS.setLabel("SMS Mensuales:");
        monthlySMS.setId("monthlySMS");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("modifyformbutton");
        confirmar.addClickListener(e -> {
            onModifyButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleModify = new H3("Modificar Datos Tarifa");
        titleModify.getStyle().set("font-size", "28px");
        titleModify.getStyle().set("color", "white");
        titleDiv.add(titleModify);
        registerForm.add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        bodySubDiv1 = new HorizontalLayout(title);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysmodify");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(newTitle, monthlyPrice, maxMobileLines);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(descriptionMobile, descriptionFiber, descriptionTV);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");
        bodySubDiv4 = new HorizontalLayout(monthlyData, monthlyCalls, monthlySMS);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysmodify");
        bodySubDiv5 = new HorizontalLayout(confirmar);
        bodySubDiv5.setSpacing(false);
        bodySubDiv5.setPadding(false);
        bodySubDiv5.addClassName("bodysmodify");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4, bodySubDiv5);
        registerForm.add(bodyDiv);

        expand(bodyDiv);
        center.add(registerForm);
        add(center);
        expand(center);
    }

    public void onModifyButtonClick() {
        Fee fee = feeService.getFeeByTitle(title.getValue());
        if (title.getValue().equals("No hay tarifas disponibles!")) {
            Notification.show("No hay tarifas disponibles!").addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (fee.getId() != null) {
            if (!newTitle.getValue().isEmpty()) {
                fee.setTitle(newTitle.getValue());
            }

            if (monthlyPrice.getValue() != null) {
                fee.setMonthlyprice(monthlyPrice.getValue());
            }

            if (maxMobileLines.getValue() != null) {
                fee.setMaxMobileLines(maxMobileLines.getValue());
            }

            if (!descriptionMobile.isEmpty()) {
                fee.setDescriptionMobile(descriptionMobile.getValue());
            }

            if (!descriptionFiber.isEmpty()) {
                fee.setDescriptionFiber(descriptionFiber.getValue());
            }

            if (!descriptionTV.isEmpty()) {
                fee.setDescriptionTV(descriptionTV.getValue());
            }

            if (monthlyData.getValue() != null) {
                fee.setMonthlyData(monthlyData.getValue());
            }

            if (monthlyCalls.getValue() != null) {
                fee.setMonthlyCalls(monthlyCalls.getValue());
            }

            if (monthlySMS.getValue() != null) {
                fee.setMonthlySMS(monthlySMS.getValue());
            }
            System.out.println(fee.getId());
            System.out.println(fee.getTitle());
            System.out.println(fee.getMonthlyprice());
            System.out.println(fee.getMaxMobileLines());
            System.out.println(fee.getDescriptionMobile());
            System.out.println(fee.getDescriptionFiber());
            System.out.println(fee.getDescriptionTV());
            System.out.println(fee.getMonthlyData());
            System.out.println(fee.getMonthlyCalls());
            System.out.println(fee.getMonthlySMS());

            if (feeService.saveFee(fee)) {
                Notification.show("Tarifa modificada correctamente!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Algo falló! Revise los datos.").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }
}