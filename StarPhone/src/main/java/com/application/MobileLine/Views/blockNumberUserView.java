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
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;

// TODO: @RolesAllowed("ROLE_CUSTOMER") + import jakarta
@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Bloquear Número")
@Route(value = "/bloquearnumero", layout = menu.class)
public class blockNumberUserView extends VerticalLayout {
    Select<Integer> lines;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;
    IntegerField phoneNumberToBlock;
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

        phoneNumberToBlock = new IntegerField("Número a bloquear:");
        phoneNumberToBlock.addClassName("activefield");
        phoneNumberToBlock.setId("phoneNumberToBlock");

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
        titleDelete = new H3("Bloquear número");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(lines, phoneNumberToBlock, confirmar);
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

    public void onBlockNumberButton() {
        String nText = "";
        if (blockedNumbersService.isBlockedNumberByPhoneNumber(phoneNumberToBlock.getValue(), lines.getValue())) {
            nText = "El número ya está bloqueado!";
            Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
        } else if (lines.getValue() != null && phoneNumberToBlock.getValue() != null) {

            mobileService.blockNumber(phoneNumberToBlock.getValue(), lines.getValue());
            nText = "Número bloqueado con éxito!";
            Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } else {
            nText = "Algo falló! Revise los datos introducidos.";
            Notification.show(nText).addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}