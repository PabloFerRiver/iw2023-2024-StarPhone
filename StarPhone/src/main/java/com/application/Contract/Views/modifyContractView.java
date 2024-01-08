package com.application.Contract.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.Status;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.Fee;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Modificar Contrato")
@Route(value = "/modificarcontrato", layout = menu.class)
public class modifyContractView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4, bodySubDiv5;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI;
    Select<String> contractsFees;
    Select<Status> actualStatus, newStatus;
    Button confirmar;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;
    private final MobileLineService mobileLineService;

    public modifyContractView(ContractService cService, UserService uService, FeeService fService,
            MobileLineService mLService) {
        contractService = cService;
        userService = uService;
        feeService = fService;
        mobileLineService = mLService;

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
        registerForm.setHeight("680px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI");
        DNI.addClassName("modifyformfield");
        DNI.setRequired(true);
        DNI.setHelperText("DNI del Usuario a modificar el Contrato.");
        DNI.setId("DNI");

        contractsFees = new Select<String>();
        contractsFees.setLabel("Contrato con tarifa:");
        contractsFees.addClassName("modifyformfield");
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

        actualStatus = new Select<Status>();
        actualStatus.addClassName("modifyformfield");
        actualStatus.setLabel("Estado del contrato:");
        actualStatus.setId("actualstatus");

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
                    actualStatus.setItems(contractStatus);
                    actualStatus.setValue(contractStatus.get(0));
                }
                contractStatus.clear();
            } else {
                actualStatus.clear();
            }
        });

        newStatus = new Select<Status>();
        newStatus.addClassName("modifyformfield");
        newStatus.setLabel("Nuevo estado:");
        newStatus.setItems(Status.ACTIVO, Status.ENPROCESO, Status.CANCELADO);
        newStatus.setValue(Status.ENPROCESO);
        newStatus.setHelperText("CANCELADO, implica la desvinculación de las líneas móviles de dicho contrato.");
        newStatus.setId("newStatus");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("registerformbutton");
        confirmar.addClickListener(e -> {
            onModifyButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleCreate = new H3("Modificar Contrato");
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
        bodySubDiv2 = new HorizontalLayout(contractsFees);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(actualStatus);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");
        bodySubDiv4 = new HorizontalLayout(newStatus);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysmodify");
        bodySubDiv5 = new HorizontalLayout(confirmar);
        bodySubDiv5.setSpacing(false);
        bodySubDiv5.setPadding(false);
        bodySubDiv5.addClassName("bodysmodify");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4, bodySubDiv5);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);
    }

    public void onModifyButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (contractsFees.getValue().equals("No hay tarifa asociada a este contrato!")) {
            Notification.show("Error! No hay tarifa asociada a este contrato!.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (!actualStatus.isEmpty() && !newStatus.isEmpty()) {
            Fee fee = feeService.getFeeByTitle(contractsFees.getValue());
            Contract c = contractService.getContractByUserIdAndFeeIdAndStatus(user.getId(), fee.getId(),
                    actualStatus.getValue());
            if (newStatus.getValue().equals(Status.ACTIVO) ||
                    newStatus.getValue().equals(Status.ENPROCESO)) {
                c.setStatus(newStatus.getValue());
                c.setEndDate(null);
            } else if (newStatus.getValue().equals(Status.CANCELADO)) {
                mobileLineService.deleteMobileLinesByContractId(c.getId());
                c.setStatus(newStatus.getValue());
                c.setEndDate(LocalDate.now());
            }
            if (contractService.saveContract(c)) {
                Notification.show("Contrato modificado correctamente!").addThemeVariants(
                        NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Error inesperado! Contacte con un administrador.").addThemeVariants(
                        NotificationVariant.LUMO_ERROR);
            }
        } else {
            Notification.show("Error! Rellene todos los campos.").addThemeVariants(
                    NotificationVariant.LUMO_ERROR);
        }
    }
}