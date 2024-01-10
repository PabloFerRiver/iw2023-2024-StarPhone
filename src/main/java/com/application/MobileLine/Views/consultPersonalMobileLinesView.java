package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.CallRecords;
import com.application.MobileLine.Entities.DataUsageRecords;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Entities.SmsRecords;
import com.application.MobileLine.Service.CallRecordsService;
import com.application.MobileLine.Service.DataUsageRecordsService;
import com.application.MobileLine.Service.MobileLineService;
import com.application.MobileLine.Service.SmsRecordsService;
import com.application.User.Security.AuthenticatedUser;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Consultar Líneas Móviles")
@Route(value = "/lineasmoviles", layout = menu.class)
public class consultPersonalMobileLinesView extends VerticalLayout {
    Select<Integer> lines;
    Select<String> period;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, volume, bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4;
    Scroller dataRecordsScroller, callRecordsScroller, smsRecordsScroller;
    H3 titleDelete;
    H4 dataText, callsText, smsText, shareDataText, roamingText;
    H4 dataRecords, callsRecords, smsRecords;

    Button confirm;
    private final MobileLineService mobileService;
    private final AuthenticatedUser authenticatedUser;
    private final ContractService contractService;
    private final DataUsageRecordsService dataUsageRecordsService;
    private final CallRecordsService callRecordsService;
    private final SmsRecordsService smsRecordsService;

    public consultPersonalMobileLinesView(AuthenticatedUser authUser, MobileLineService mService,
            ContractService cService, DataUsageRecordsService dURService, CallRecordsService cRService,
            SmsRecordsService sRService) {
        authenticatedUser = authUser;
        mobileService = mService;
        contractService = cService;
        dataUsageRecordsService = dURService;
        callRecordsService = cRService;
        smsRecordsService = sRService;

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

        List<Contract> contracts = contractService.getContractsByUserId(authenticatedUser.get().get().getId());
        List<MobileLine> mobileLines = new ArrayList<>();
        for (var c : contracts) {
            mobileLines.addAll(mobileService.getMobileLinesByContractId(c.getId()));
        }

        List<Integer> phoneNumberlines = new ArrayList<>();
        for (var m : mobileLines) {
            phoneNumberlines.add(m.getPhoneNumber());
        }

        lines = new Select<Integer>();
        lines.addClassName("activefield");
        lines.setLabel("Línea:");
        lines.setItems(phoneNumberlines);

        confirm = new Button("Confirmar");
        confirm.addClassName("activebutton");
        confirm.getStyle().set("margin-top", "28px");
        confirm.addClickListener(e -> onGetConsume());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("1500px");
        confirmSquare.setHeight("auto");
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
        titleDelete = new H3("Consultar Líneas Móviles");
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

        dataRecords = new H4("");
        dataRecords.getStyle().set("font-size", "22px");
        callsRecords = new H4("");
        callsRecords.getStyle().set("font-size", "22px");
        smsRecords = new H4("");
        smsRecords.getStyle().set("font-size", "22px");

        bodySubDiv1 = new HorizontalLayout(lines, period, confirm);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysregister");
        bodySubDiv1.getStyle().set("border-bottom", "4px solid black");
        bodySubDiv2 = new HorizontalLayout();
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysregister");
        bodySubDiv2.setJustifyContentMode(JustifyContentMode.CENTER);
        bodySubDiv2.getStyle().set("border-bottom", "3px solid black");
        bodySubDiv3 = new HorizontalLayout();
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysregister");
        bodySubDiv3.getStyle().set("gap", "400px");
        bodySubDiv3.getStyle().set("border-bottom", "2px solid black");
        bodySubDiv4 = new HorizontalLayout();
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysregister");
        bodySubDiv4.getStyle().set("gap", "30px");

        bodyDiv = new VerticalLayout(bodySubDiv1);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.setSpacing(false);
        bodyDiv.setPadding(false);
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
            confirmSquare.setHeight("700px");
            bodySubDiv2.removeAll();
            bodySubDiv3.removeAll();
            bodySubDiv4.removeAll();
            bodyDiv.add(bodySubDiv2, bodySubDiv3, bodySubDiv4);
            // VOLUMEN
            MobileLine mobileLine = mobileService.getMobileLineByPhoneNumber(lines.getValue());
            boolean roaming = mobileLine.getRoaming();
            boolean shareData = mobileLine.getShareData();
            double data = 0;
            int calls = 0, sms = 0;
            if (period.getValue().equals("Hoy")) {
                data = Math
                        .round(dataUsageRecordsService.getDataUsageRecordsByMobileLineToday(mobileLine).stream()
                                .mapToDouble(x -> x.getMegas()).sum());
                calls = callRecordsService.getCallRecordsByMobileLineToday(mobileLine).size();
                sms = smsRecordsService.getSmsRecordsByMobileLineToday(mobileLine).size();
            } else if (period.getValue().equals("Mes")) {
                data = Math
                        .round(dataUsageRecordsService.getDataUsageRecordsByMobileLineCurrentMonth(mobileLine).stream()
                                .mapToDouble(x -> x.getMegas()).sum());
                calls = callRecordsService.getCallRecordsByMobileLineCurrentMonth(mobileLine).size();
                sms = smsRecordsService.getSmsRecordsByMobileLineCurrentMonth(mobileLine).size();
            }

            dataText.setText("Total datos: " + data + " MB");
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
            bodySubDiv2.add(dataText, callsText, smsText, roamingText, shareDataText);
            bodySubDiv2.getStyle().set("height", "60px");

            // DESGLOSE
            dataRecords.setText("Registros de Datos");
            callsRecords.setText("Registros de Llamadas");
            smsRecords.setText("Registros de SMS");
            bodySubDiv3.add(dataRecords, callsRecords, smsRecords);
            bodySubDiv3.getStyle().set("height", "60px");

            List<DataUsageRecords> dataUsageRecords = new ArrayList<>();
            List<CallRecords> callRecords = new ArrayList<>();
            List<SmsRecords> smsRecords = new ArrayList<>();

            if (period.getValue().equals("Hoy")) {
                dataUsageRecords = dataUsageRecordsService.getDataUsageRecordsByMobileLineToday(mobileLine);
                callRecords = callRecordsService.getCallRecordsByMobileLineToday(mobileLine);
                smsRecords = smsRecordsService.getSmsRecordsByMobileLineToday(mobileLine);
            } else if (period.getValue().equals("Mes")) {
                dataUsageRecords = dataUsageRecordsService.getDataUsageRecordsByMobileLineCurrentMonth(mobileLine);
                callRecords = callRecordsService.getCallRecordsByMobileLineCurrentMonth(mobileLine);
                smsRecords = smsRecordsService.getSmsRecordsByMobileLineCurrentMonth(mobileLine);
            }
            // TEXTOS SCROLLERS
            Pre dataUsageTextScroller = new Pre(dataUsageRecords.stream().map(x -> new H4(
                    "(Megas: " + x.getMegas() + ", Fecha:" + x.getDataUsageDate() + ")"))
                    .toArray(H4[]::new));

            Pre callTextScroller = new Pre(callRecords.stream().map(x -> new H4(
                    "(Destinatario: " + x.getDestinationPhoneNumber() + ", Segundos: " + x.getSeconds() + ", Fecha:"
                            + x.getCallDate() + ")"))
                    .toArray(H4[]::new));

            Pre smsTextScroller = new Pre(smsRecords.stream().map(x -> new H4(
                    "(Destinatario: " + x.getDestinationPhoneNumber() + ", Texto: " + x.getSmsText().substring(0, 30)
                            + ", Fecha: "
                            + x.getSmsDate() + ")"))
                    .toArray(H4[]::new));

            // SCROLLERS
            VerticalLayout dataVL = new VerticalLayout(dataUsageTextScroller);
            dataRecordsScroller = new Scroller(dataVL);
            dataRecordsScroller.addClassName("scroller");
            //
            VerticalLayout callVL = new VerticalLayout(callTextScroller);
            callRecordsScroller = new Scroller(callVL);
            callRecordsScroller.addClassName("scroller");
            //
            VerticalLayout smsVL = new VerticalLayout(smsTextScroller);
            smsRecordsScroller = new Scroller(smsVL);
            smsRecordsScroller.addClassName("scroller");

            bodySubDiv4.setWidth("auto");
            bodySubDiv4.setHeight("auto");
            bodySubDiv4.add(dataRecordsScroller, callRecordsScroller, smsRecordsScroller);
        } else {
            Notification.show("Por favor, rellene todos los campos")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}