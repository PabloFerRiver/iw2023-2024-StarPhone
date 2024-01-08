package com.application.Contract.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.Status;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.FeeService;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Entities.User;
import com.application.User.Services.UserService;
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
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT", "ROLE_MARKETING", "ROLE_FINANCE" })
@CssImport("./styles/styles.css")
@PageTitle("Consultar Contratos Usuario")
@Route(value = "/consultarcontratosusuario", layout = menu.class)
public class consultUserContractsView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv;
    H3 titleDelete;
    H4 feeText, statusText, startDateText, linesNumbersText;
    TextField DNI;
    Select<String> contractsFees;
    Select<Status> status;
    Select<Integer> lines;
    Button confirmar;
    private final MobileLineService mobileService;
    private final UserService userService;
    private final ContractService contractService;
    private final FeeService feeService;

    public consultUserContractsView(UserService uService, MobileLineService mService,
            ContractService cService, FeeService fService) {
        userService = uService;
        mobileService = mService;
        contractService = cService;
        feeService = fService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        DNI = new TextField("DNI:");
        DNI.addClassName("activefield");
        DNI.setLabel("DNI:");
        DNI.setId("DNI");

        contractsFees = new Select<String>();
        contractsFees.addClassName("activefield");
        contractsFees.setLabel("Contrato con Tarifa:");
        contractsFees.setId("contractsFees");

        List<String> feeTitles = new ArrayList<>();
        DNI.setValueChangeMode(ValueChangeMode.EAGER);
        DNI.addValueChangeListener(event -> {
            List<Contract> contracts = new ArrayList<>();
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                contracts = contractService.getContractsByUserId(user.getId());
                for (Contract c : contracts) {
                    if (!c.getStatus().equals(Status.CANCELADO))
                        feeTitles.add(c.getFee().getTitle());
                }

                if (feeTitles.size() > 0) {
                    contractsFees.setItems(feeTitles);
                    contractsFees.setValue(feeTitles.get(0));
                } else {
                    contractsFees.setItems("No hay tarifa asociada a este contrato!");
                    contractsFees.setValue("No hay tarifa asociada a este contrato!");
                }
            } else {
                contractsFees.clear();
            }
        });

        status = new Select<Status>();
        status.addClassName("activefield");
        status.setLabel("Estado:");
        status.setId("status");

        List<Status> contractStatus = new ArrayList<>();
        contractsFees.addValueChangeListener(event -> {
            List<Contract> contracts = new ArrayList<>();
            User user = userService.getUserByDNI(DNI.getValue());
            Fee fee = feeService.getFeeByTitle(event.getValue());
            if (user.getId() != null) {
                contracts = contractService.getContractsByUserIdAndFeeId(user.getId(), fee.getId());
                for (Contract c : contracts) {
                    if (!c.getStatus().equals(Status.CANCELADO))
                        contractStatus.add(c.getStatus());
                }

                if (contractStatus.size() > 0) {
                    status.setItems(contractStatus);
                    status.setValue(contractStatus.get(0));
                }
                contractStatus.clear();
            } else {
                status.clear();
            }
        });

        confirmar = new Button("Confirmar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onGetContract());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("440px");
        confirmSquare.setHeight("520px");
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
        titleDelete = new H3("Consultar Contratos Usuario");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        feeText = new H4("");
        feeText.getStyle().set("font-size", "22px");
        statusText = new H4("");
        statusText.getStyle().set("font-size", "22px");
        startDateText = new H4("");
        startDateText.getStyle().set("font-size", "22px");
        linesNumbersText = new H4("");
        linesNumbersText.getStyle().set("font-size", "22px");

        bodyDiv = new VerticalLayout(DNI, contractsFees, status, confirmar);
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

    public void onGetContract() {
        if (contractsFees.getValue() != null && status.getValue() != null) {
            confirmSquare.setHeight("auto");
            User user = userService.getUserByDNI(DNI.getValue());
            Fee fee = feeService.getFeeByTitle(contractsFees.getValue());
            Contract cont = contractService.getContractByUserIdAndFeeIdAndStatus(user.getId(), fee.getId(),
                    status.getValue());

            List<MobileLine> lines = mobileService.getMobileLinesByContractId(cont.getId());
            List<Integer> linesNumbers = new ArrayList<>();
            for (MobileLine m : lines) {
                linesNumbers.add(m.getPhoneNumber());
            }
            feeText.setText("Tarifa: " + cont.getFee().getTitle());
            statusText.setText("Estado: " + cont.getStatus().toString());
            startDateText.setText("Fecha de inicio: " + cont.getStartDate().toString());
            String linesNumbersString = linesNumbers.toString();
            linesNumbersText.setText("Líneas asociadas: " + linesNumbersString);
            bodyDiv.add(feeText, statusText, startDateText, linesNumbersText);
        } else {
            Notification.show("Por favor, rellene todos los campos")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
        }

    }
}
