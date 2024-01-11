package com.application.MobileLine.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.FeeService;
import com.application.MobileLine.Service.MobileLineService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Eliminar Línea")
@Route(value = "/eliminarlinea", layout = menu.class)
public class deleteMobileLineView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI, contract;
    Select<Integer> mobileLinesSet;
    Button confirm;
    private final MobileLineService mobileLineService;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;

    public deleteMobileLineView(MobileLineService mLService, ContractService cService, UserService uService,
            FeeService fService) {
        mobileLineService = mLService;
        contractService = cService;
        userService = uService;
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
        registerForm.setWidth("400px");
        registerForm.setHeight("560px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI");
        DNI.addClassName("registerformfield");
        DNI.setRequired(true);
        DNI.setHelperText("DNI del Usuario a modificar el Contrato.");
        DNI.setId("DNI");

        mobileLinesSet = new Select<Integer>();
        mobileLinesSet.setLabel("Líneas móvil del usuario:");
        mobileLinesSet.addClassName("registerformfield");
        mobileLinesSet.setId("mobileLinesSet");

        DNI.setValueChangeMode(ValueChangeMode.EAGER);
        DNI.addValueChangeListener(event -> {
            List<MobileLine> mLines = new ArrayList<>();
            List<Integer> phoneNumbers = new ArrayList<>();
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                mLines = mobileLineService.getMobileLinesByUserId(user.getId());
                for (MobileLine mL : mLines) {
                    phoneNumbers.add(mL.getPhoneNumber());
                }

                if (phoneNumbers.size() > 0) {
                    mobileLinesSet.setItems(phoneNumbers);
                    mobileLinesSet.setValue(phoneNumbers.get(0));
                } else {
                    mobileLinesSet.setItems(0);
                    mobileLinesSet.setValue(0);
                }
            } else {
                mobileLinesSet.clear();
            }
        });

        contract = new TextField("Contrato con Tarifa:");
        contract.addClassName("registerformfield");
        contract.setReadOnly(true);
        contract.setId("contract");

        mobileLinesSet.addValueChangeListener(event -> {
            MobileLine mLine = mobileLineService.getMobileLineByPhoneNumber(event.getValue());
            if (!DNI.getValue().isEmpty()) {
                if (mLine.getId() != null) {
                    Contract c = contractService.getContractById(mLine.getContract().getId());
                    if (c.getId() != null) {
                        Fee f = feeService.getFeeById(c.getFee().getId());
                        if (f.getId() != null) {
                            contract.setValue(f.getTitle());
                        }
                    }
                }
            } else {
                contract.clear();
            }
        });

        confirm = new Button("Confirmar");
        confirm.addClassName("registerformbutton");
        confirm.addClickListener(e -> {
            onDeleteButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleCreate = new H3("Eliminar Línea Móvil");
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

        bodySubDiv1 = new HorizontalLayout(DNI);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysmodify");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(mobileLinesSet);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(contract);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");
        bodySubDiv4 = new HorizontalLayout(confirm);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysmodify");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);
    }

    public void onDeleteButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (mobileLinesSet.getValue().equals(0)) {
            Notification.show("Error! No existen números de móvil para este usuario!.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/linea");
        } else if (user.getId() != null && mobileLinesSet.getValue() != null) {
            MobileLine mLine = mobileLineService.getMobileLineByPhoneNumber(mobileLinesSet.getValue());
            if (mobileLineService.deleteMobileLineById(mLine.getId())) {
                Notification.show("Línea de móvil eliminada correctamente!").addThemeVariants(
                        NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/linea");
            } else {
                Notification.show("Error! Línea de móvil no eliminada.").addThemeVariants(
                        NotificationVariant.LUMO_ERROR);
            }
        } else {
            Notification.show("Error! Rellene todos los campos.").addThemeVariants(
                    NotificationVariant.LUMO_ERROR);
        }
    }
}