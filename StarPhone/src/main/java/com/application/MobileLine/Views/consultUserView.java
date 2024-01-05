package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.BlockedNumbers;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Security.AuthenticatedUser;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO: @RolesAllowed("") + import jakarta
@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Consultar información Linea")
@Route(value = "/consultar", layout = menu.class)
public class consultUserView extends VerticalLayout {
    Select<Integer> lines;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;

    Button confirmar;
    public Paragraph text = new Paragraph(" ");
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

        confirmar = new Button("Consultar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onData());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("380px");
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
        titleDelete = new H3("Consultar información de linea");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(lines, confirmar, text);
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

    public void onData() {
        if (lines.getValue() != null) {
            MobileLine mobileLine = mobileService.getMobileLineByPhoneNumber(lines.getValue());
            String text1;
            String text2;
            if (mobileLine.getShareData() == true) {
                text1 = new String(" Activado");
            } else {
                text1 = new String(" Desactivado");
            }

            if (mobileLine.getRoaming() == true) {
                text2 = new String(" Activado");
            } else {
                text2 = new String(" Desactivado");
            }
            List<BlockedNumbers> blocked = mobileLine.getBlockedNumbers();
            List<Integer> listabloqueo = new ArrayList<>();
            for (var m : blocked) {
                listabloqueo.add(m.getBlockedNumber());
            }
            String cadenaDeNumeros = listabloqueo.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            text.setText("Total llamadas: " + mobileLine.getTotalCallsToday() + "\n" +
                    "Total datos: " + mobileLine.getTotalDataToday() + "\n" +
                    "Total SMS: " + mobileLine.getTotalSMSToday() + "\n" +
                    "Compartir datos: " + text1 + "\n" +
                    "Numeros bloqueados: " + cadenaDeNumeros + "\n" +
                    "Roaming: " + text2 + "\n" +
                    "Contrato: " + mobileLine.getContract().getId());
            text.getStyle().set("white-space", "pre-line");
            text.getStyle().set("font-size", "20px");
            text.getStyle().set("font-weight", "bold");

        } else {
            Notification.show("Por favor, seleccione una línea", 3000, Notification.Position.MIDDLE)
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}