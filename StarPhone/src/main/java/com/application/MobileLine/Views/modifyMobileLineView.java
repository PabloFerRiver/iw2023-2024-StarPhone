package com.application.MobileLine.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.Status;
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
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Modificar Línea Móvil")
@Route(value = "/modificarlineamovil", layout = menu.class)
public class modifyMobileLineView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI;
    Select<String> contractsFees;
    Select<Status> status;
    Select<Integer> lines;
    RadioButtonGroup<Boolean> roaming, shareData;
    Button confirmar;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;
    private final MobileLineService mobileLineService;

    public modifyMobileLineView(ContractService cService, UserService uService, FeeService fService,
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
        registerForm.setWidth("800px");
        registerForm.setHeight("560px");
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

        List<Integer> phoneNumberlines = new ArrayList<>();
        lines = new Select<Integer>();
        lines.addClassName("modifyformfield");
        lines.setLabel("Líneas:");
        lines.setItems(phoneNumberlines);

        DNI.setValueChangeMode(ValueChangeMode.EAGER);
        DNI.addValueChangeListener(event -> {
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                List<MobileLine> mLines = mobileLineService.getMobileLinesByUserId(user.getId());
                for (MobileLine mL : mLines) {
                    phoneNumberlines.add(mL.getPhoneNumber());
                }

                if (phoneNumberlines.size() > 0) {
                    lines.setItems(phoneNumberlines);
                    lines.setValue(phoneNumberlines.get(0));
                }
            } else {
                lines.clear();
            }
        });

        contractsFees = new Select<String>();
        contractsFees.setLabel("Contrato con tarifa:");
        contractsFees.addClassName("modifyformfield");
        contractsFees.setHelperText("No rellenar si se desea modificar solo el romaing y/o compartir datos.");
        contractsFees.setId("contractsFees");

        List<String> feeTitles = new ArrayList<>();
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
                } else {
                    contractsFees.setItems("No hay tarifa asociada a este contrato!");
                }
            } else {
                contractsFees.clear();
            }
        });

        status = new Select<Status>();
        status.addClassName("modifyformfield");
        status.setLabel("Estado del contrato:");
        status.setHelperText("No rellenar si se desea modificar solo el romaing y/o compartir datos.");
        status.setId("actualstatus");

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
                }
                contractStatus.clear();
            } else {
                status.clear();
            }
        });

        roaming = new RadioButtonGroup<>();
        roaming.addClassName("modifyformfield");
        roaming.setLabel("Roaming:");
        roaming.setItems(true, false);
        roaming.setValue(false);
        roaming.setId("roaming");

        shareData = new RadioButtonGroup<>();
        shareData.addClassName("modifyformfield");
        shareData.setLabel("Compartir datos:");
        shareData.setItems(true, false);
        shareData.setValue(false);
        shareData.setId("shareData");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("modifyformbutton");
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
        titleCreate = new H3("Modificar Línea Móvil");
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

        bodySubDiv1 = new HorizontalLayout(DNI, lines);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysmodify");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(contractsFees, status);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(roaming, shareData);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");
        bodySubDiv4 = new HorizontalLayout(confirmar);
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

    public void onModifyButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (user.getId() != null) {
            if (contractsFees.getValue() != null
                    && contractsFees.getValue().equals("No hay tarifa asociada a este contrato!")) {
                Notification.show("Error! No hay tarifa asociada a este contrato!.")
                        .addThemeVariants(NotificationVariant.LUMO_ERROR);
                UI.getCurrent().navigate("/menu");
            } else {
                MobileLine mLine = mobileLineService.getMobileLineByPhoneNumber(lines.getValue());
                if (!contractsFees.isEmpty() && !status.isEmpty()) {
                    Fee fee = feeService.getFeeByTitle(contractsFees.getValue());
                    Contract c = contractService.getContractByUserIdAndFeeIdAndStatus(user.getId(), fee.getId(),
                            status.getValue());
                    mLine.setContract(c);
                }

                mLine.setRoaming(roaming.getValue());
                mLine.setShareData(shareData.getValue());
                if (mobileLineService.saveMobileLine(mLine)) {
                    Notification.show("Contrato modificado correctamente!").addThemeVariants(
                            NotificationVariant.LUMO_SUCCESS);
                    UI.getCurrent().navigate("/menu");
                } else {
                    Notification.show("Error inesperado! Contacte con un administrador.")
                            .addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            }
        } else {
            Notification.show("Error! El DNI introducido no está registrado.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);

        }
    }
}
