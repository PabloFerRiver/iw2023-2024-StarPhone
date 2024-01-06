package com.application.MobileLine.Views;

import com.application.User.Views.menu;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import jakarta.annotation.security.RolesAllowed;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_MARKETING" })
@CssImport("./styles/styles.css")
@PageTitle("Crear Tarifa")
@Route(value = "/creartarifa", layout = menu.class)
public class createFeeView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4, bodySubDiv5, footerDiv;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField title, descriptionMobile, descriptionFiber, descriptionTV;
    NumberField monthlyData, monthlyPrice;
    IntegerField monthlyCalls, monthlySMS, maxMobileLines;
    Button confirmar;

    private final FeeService feeService;
    private final BeanValidationBinder<Fee> binder;

    public createFeeView(FeeService fService) {
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
        registerForm.setWidth("1150px");
        registerForm.setHeight("550px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        title = new TextField();
        title.addClassName("registerformfield");
        title.setLabel("Nuevo Título:");
        title.setId("title");

        monthlyPrice = new NumberField();
        monthlyPrice.addClassName("registerformfield");
        monthlyPrice.setLabel("Precio Mensual:");
        monthlyPrice.setId("monthlyPrice");

        maxMobileLines = new IntegerField();
        maxMobileLines.addClassName("registerformfield");
        maxMobileLines.setLabel("Líneas Móviles:");
        maxMobileLines.setId("maxMobileLines");

        descriptionMobile = new TextField();
        descriptionMobile.addClassName("registerformfield");
        descriptionMobile.setLabel("Descripción Móvil:");
        descriptionMobile.setId("descriptionMobile");

        descriptionFiber = new TextField();
        descriptionFiber.addClassName("registerformfield");
        descriptionFiber.setLabel("Descripción Fibra:");
        descriptionFiber.setId("descriptionFiber");

        descriptionTV = new TextField();
        descriptionTV.addClassName("registerformfield");
        descriptionTV.setLabel("Descripción TV:");
        descriptionTV.setId("descriptionTV");

        monthlyData = new NumberField();
        monthlyData.addClassName("registerformfield");
        monthlyData.setLabel("Datos Mensuales:");
        monthlyData.setId("monthlyData");

        monthlyCalls = new IntegerField();
        monthlyCalls.addClassName("registerformfield");
        monthlyCalls.setLabel("Minutos Mensuales:");
        monthlyCalls.setId("monthlyCalls");

        monthlySMS = new IntegerField();
        monthlySMS.addClassName("registerformfield");
        monthlySMS.setLabel("SMS Mensuales:");
        monthlySMS.setId("monthlySMS");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("registerformbutton");
        confirmar.addClickListener(e -> {
            onCreateButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleCreate = new H3("Crear Tarifa");
        titleCreate.getStyle().set("font-size", "28px");
        titleCreate.getStyle().set("color", "white");
        titleDiv.add(titleCreate);
        registerForm.add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        bodySubDiv1 = new HorizontalLayout(title, monthlyPrice, maxMobileLines);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysregister");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(descriptionMobile, descriptionFiber, descriptionTV);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysregister");
        bodySubDiv3 = new HorizontalLayout(monthlyData, monthlyCalls, monthlySMS);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysregister");
        bodySubDiv4 = new HorizontalLayout(confirmar);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysregister");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);

        // Registro TARIFA
        binder = new BeanValidationBinder<>(Fee.class);
        binder.bindInstanceFields(this);
        binder.setBean(new Fee());
    }

    public void onCreateButtonClick() {
        if (feeService.count() >= 4) {
            Notification.show("Error! No se pueden crear más tarifas.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (binder.validate().isOk()) {
            if (feeService.saveFee(binder.getBean())) {
                Notification.show("Genial. Tarifa creada correctamente!!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Algo falló! Revise los datos.").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        } else {
            Notification.show("Error! Revise los datos introducidos.");
        }
    }
}
