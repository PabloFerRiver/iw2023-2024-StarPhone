package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Security.AuthenticatedUser;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Consultar Consumos Linea")
@Route(value = "/consultarconsumos", layout = menu.class)
public class consultUserView extends VerticalLayout {
    Select<Integer> lines;
    Select<String> period;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;
    H4 dataText, callsText, smsText, shareDataText, roamingText;

    Button confirmar;
    private final MobileLineService mobileService;
    private final AuthenticatedUser authenticatedUser;
    private final ContractService contractService;

    public consultUserView(AuthenticatedUser authUser, MobileLineService mService, ContractService cService) {
        this.authenticatedUser = authUser;
        this.mobileService = mService;
        this.contractService = cService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        period = new Select<String>();
        period.addClassName("activefield");
        period.setLabel("Período:");
        period.setItems("Hoy", "Mes");
        period.setValue("Hoy");
        period.setId("period");

        List<Contract> contracts = contractService.getContractsByUser_Id(authenticatedUser.get().get().getId());
        List<MobileLine> mobileLines = new ArrayList<>();
        for (var c : contracts) {
            mobileLines.addAll(mobileService.getMobileLineByContractId(c.getId()));
        }

        List<Integer> phoneNumberlines = new ArrayList<>();
        for (var m : mobileLines) {
            phoneNumberlines.add(m.getPhoneNumber());
        }

        lines = new Select<Integer>();
        lines.addClassName("activefield");
        lines.setLabel("Línea:");
        lines.setItems(phoneNumberlines);

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onGetConsume());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("400px");
        confirmSquare.setHeight("400px");
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
        titleDelete = new H3("Consultar consumos Línea");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        dataText = new H4("");
        dataText.getStyle().set("font-size", "22px");
        callsText = new H4("");
        callsText.getStyle().set("font-size", "22px");
        smsText = new H4("");
        smsText.getStyle().set("font-size", "22px");
        roamingText = new H4("");
        roamingText.getStyle().set("font-size", "22px");
        shareDataText = new H4("");
        shareDataText.getStyle().set("font-size", "22px");

        bodyDiv = new VerticalLayout(period, lines, confirmar);
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

    public void onGetConsume() {
        if (lines.getValue() != null) {
            confirmSquare.setHeight("auto");
            confirmar.setEnabled(false);
            MobileLine mobileLine = mobileService.getMobileLineByPhoneNumber(lines.getValue());
            boolean roaming = mobileLine.getRoaming();
            boolean shareData = mobileLine.getShareData();
            double data = 0;
            int calls = 0, sms = 0;
            if (period.getValue().equals("Hoy")) {
                data = mobileLine.getTotalDataToday();
                calls = mobileLine.getTotalCallsToday();
                sms = mobileLine.getTotalSMSToday();
            } else if (period.getValue().equals("Mes")) {
                data = mobileLine.getTotalDataMonth();
                calls = mobileLine.getTotalCallsMonth();
                sms = mobileLine.getTotalSMSMonth();
            }

            dataText.setText("Total datos: " + data);
            callsText.setText("Total llamadas: " + calls);
            smsText.setText("Total SMS: " + sms);

            String text = "";
            if (roaming) {
                text = new String("Activado");
            } else {
                text = new String("Desactivado");
            }
            roamingText.setText("Roaming: " + text);

            if (shareData) {
                text = new String(" Activado");
            } else {
                text = new String(" Desactivado");
            }
            shareDataText.setText("Compartir datos: " + text);
            bodyDiv.add(dataText, callsText, smsText, roamingText, shareDataText);
        } else {
            Notification.show("Por favor, rellene todos los campos")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}