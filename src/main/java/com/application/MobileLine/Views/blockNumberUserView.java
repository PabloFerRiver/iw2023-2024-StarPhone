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
    HorizontalLayout titleDiv;
    H3 titleDelete;
    Select<String> actions;
    IntegerField phoneNumberToBlockUnblock;
    Button confirm;

    private final MobileLineService mobileLineService;
    private final AuthenticatedUser authenticatedUser;
    private final ContractService contractService;

    public blockNumberUserView(AuthenticatedUser authUser, MobileLineService mLService, ContractService cService) {
        this.authenticatedUser = authUser;
        this.mobileLineService = mLService;
        this.contractService = cService;

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

        List<Contract> contracts = contractService.getContractsByUserId(authenticatedUser.get().get().getId());
        List<MobileLine> mobileLines = new ArrayList<>();
        for (var c : contracts) {
            mobileLines.addAll(mobileLineService.getMobileLinesByContractId(c.getId()));
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

        confirm = new Button("Confirmar");
        confirm.addClassName("activebutton");
        confirm.addClickListener(e -> onBlockNumberButton());
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

        bodyDiv = new VerticalLayout(actions, lines, phoneNumberToBlockUnblock, confirm);
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
                if (mobileLineService.isBlockedNumberByPhoneNumber(phoneNumberToBlockUnblock.getValue(),
                        lines.getValue())) {
                    Notification.show("El número ya está bloqueado!").addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    if (mobileLineService.blockNumber(phoneNumberToBlockUnblock.getValue(), lines.getValue())) {
                        Notification.show("Número bloqueado con éxito!")
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    } else {
                        Notification.show("Error! No se ha podido bloquear el número.")
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }
            } else if (actions.getValue().equals("Desbloquear")) {
                if (!mobileLineService.isBlockedNumberByPhoneNumber(phoneNumberToBlockUnblock.getValue(),
                        lines.getValue())) {
                    Notification.show("El número no está bloqueado!").addThemeVariants(NotificationVariant.LUMO_ERROR);
                } else {
                    if (mobileLineService.unblockNumber(phoneNumberToBlockUnblock.getValue(), lines.getValue())) {
                        Notification.show("Número desbloqueado con éxito!")
                                .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    } else {
                        Notification.show("Error! No se ha podido desbloquear el número.")
                                .addThemeVariants(NotificationVariant.LUMO_ERROR);
                    }
                }
            }
        } else {
            Notification.show("Error! Revise los datos introducidos.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}