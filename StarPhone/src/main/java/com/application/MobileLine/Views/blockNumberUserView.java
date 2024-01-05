package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.BlockedNumbersService;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Security.AuthenticatedUser;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Números Desconocidos")
@Route(value = "/numerosdesconocidos", layout = menu.class)
public class blockNumberUserView extends VerticalLayout {
    Select<Integer> lines;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;
    Select<String> actions;
    IntegerField phoneNumberToBlockUnblock;
    Button confirmar;

    private final MobileLineService mobileService;
    private final AuthenticatedUser authenticatedUser;
    private final ContractService contractService;
    private final BlockedNumbersService blockedNumbersService;

    public blockNumberUserView(AuthenticatedUser authUser, MobileLineService mService, ContractService cService,
            BlockedNumbersService blockedNumbersService) {
        this.authenticatedUser = authUser;
        this.mobileService = mService;
        this.contractService = cService;
        this.blockedNumbersService = blockedNumbersService;

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
        actions.setItems("Bloquear", "Desbloquear");
        actions.setValue("Bloquear");
        actions.setId("actions");

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

        phoneNumberToBlockUnblock = new IntegerField("Número de teléfono:");
        phoneNumberToBlockUnblock.addClassName("activefield");
        phoneNumberToBlockUnblock.setId("phoneNumberToBlockUnblock");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onBlockNumberButton());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("380px");
        confirmSquare.setHeight("450px");
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
        titleDelete = new H3("Números Desconocidos");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(actions, lines, phoneNumberToBlockUnblock, confirmar);
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

    public void onBlockNumberButton() {
        if (lines.getValue() != null && phoneNumberToBlockUnblock.getValue() != null) {
            if (actions.getValue().equals("Bloquear")) {
                String nText = "";
                if (blockedNumbersService.isBlockedNumberByPhoneNumber(phoneNumberToBlockUnblock.getValue(),
                        lines.getValue())) {
                    nText = "El número ya está bloqueado!";
                    Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    mobileService.blockNumber(phoneNumberToBlockUnblock.getValue(), lines.getValue());
                    nText = "Número bloqueado con éxito!";
                    Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            } else if (actions.getValue().equals("Desbloquear")) {
                String nText = "";
                if (!blockedNumbersService.isBlockedNumberByPhoneNumber(phoneNumberToBlockUnblock.getValue(),
                        lines.getValue())) {
                    nText = "El número no está bloqueado!";
                    Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    mobileService.unblockNumber(phoneNumberToBlockUnblock.getValue(), lines.getValue());
                    nText = "Número desbloqueado con éxito!";
                    Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                }
            }
        } else {
            Notification.show("Algo falló! Revise los datos introducidos.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}