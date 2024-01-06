package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Roaming&DatosCompartidos")
@Route(value = "/roaming&datoscompartidos", layout = menu.class)
public class shareDataAndRoamingView extends VerticalLayout {
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleSDR;
    Select<String> actions;
    Select<Integer> lines;
    RadioButtonGroup<String> answer;
    TextField DNI;
    Button confirmar;

    private final UserService userService;
    private final ContractService contractService;
    private final MobileLineService mobileLineService;

    public shareDataAndRoamingView(UserService uService, ContractService cService, MobileLineService mLService) {
        userService = uService;
        contractService = cService;
        mobileLineService = mLService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        actions = new Select<String>();
        actions.addClassName("activefield");
        actions.setLabel("Acción:");
        actions.setItems("Roaming", "Share Data");
        actions.setValue("Roaming");
        actions.setId("actions");

        DNI = new TextField("DNI:");
        DNI.addClassName("activefield");
        DNI.setHelperText("Introduzca DNI con letra");
        DNI.setId("DNI");

        List<MobileLine> mobileLines = new ArrayList<>();
        List<Integer> phoneNumberlines = new ArrayList<>();
        lines = new Select<Integer>();
        lines.addClassName("activefield");
        lines.setLabel("Línea:");
        lines.setItems(phoneNumberlines);

        DNI.addValueChangeListener(event -> {
            User user = userService.getUserByDNI(event.getValue());
            System.out.println(user.getId());
            List<Contract> contracts = contractService
                    .getContractsByUser_Id(user.getId());
            for (var c : contracts) {
                mobileLines.addAll(mobileLineService.getMobileLineByContractId(c.getId()));
            }

            for (var m : mobileLines) {
                phoneNumberlines.add(m.getPhoneNumber());
            }
            if (phoneNumberlines.size() > 0) {
                lines.setItems(phoneNumberlines);
            }
        });

        answer = new RadioButtonGroup<String>();
        answer.addClassName("activefield");
        answer.setLabel("Estado:");
        answer.setItems("Activar", "Desactivar");
        answer.setValue("Share Data");
        answer.setId("answer");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onManageRolButton());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("420px");
        confirmSquare.setHeight("550px");
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
        titleSDR = new H3("Roaming & Datos Compartidos");
        titleSDR.getStyle().set("font-size", "28px");
        titleSDR.getStyle().set("color", "white");
        titleDiv.add(titleSDR);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(actions, DNI, lines, answer, confirmar);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
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

    public void onManageRolButton() {
        if (actions.getValue() != null && lines.getValue() != null && answer.getValue() != null) {
            Integer phoneNumber = lines.getValue();
            if (actions.getValue().equals("Share Data")) {
                if (answer.getValue().equals("Activar")) {
                    mobileLineService.manageShareData(phoneNumber, true);
                } else {
                    mobileLineService.manageShareData(phoneNumber, false);
                }
                Notification.show("Estado de ShareData actualizado con éxito.")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            } else if (actions.getValue().equals("Roaming")) {
                if (answer.getValue().equals("Activar")) {
                    mobileLineService.manageRoaming(phoneNumber, true);
                } else {
                    mobileLineService.manageRoaming(phoneNumber, false);
                }
                Notification.show("Estado de Roaming actualizado con éxito.")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
        } else {
            Notification.show("Debe completar todos los campos.").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}