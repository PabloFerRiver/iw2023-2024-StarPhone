package com.application.MobileLine.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.StatusContract;
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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Crear Línea")
@Route(value = "/crearlinea", layout = menu.class)
public class createMobileLineView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI;
    IntegerField phoneNumber;
    RadioButtonGroup<Boolean> roaming, shareData;
    Select<String> contractsFees;
    Select<StatusContract> status;
    Button confirm;
    private final MobileLineService mobileLineService;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;
    private final BeanValidationBinder<MobileLine> binder;

    public createMobileLineView(MobileLineService mService, ContractService cService, UserService uService,
            FeeService fService) {
        mobileLineService = mService;
        contractService = cService;
        userService = uService;
        feeService = fService;

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
        registerForm.setWidth("1150px");
        registerForm.setHeight("440px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI");
        DNI.addClassName("modifyformfield");
        DNI.setRequired(true);
        DNI.setHelperText("DNI CON letra del Usuario a modificar el Contrato.");
        DNI.setId("DNI");

        contractsFees = new Select<String>();
        contractsFees.setLabel("Contrato con tarifa:");
        contractsFees.addClassName("registerformfield");
        contractsFees.setId("contractsFees");

        List<String> feeTitles = new ArrayList<>();
        DNI.addValueChangeListener(event -> {
            List<Contract> contracts = new ArrayList<>();
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                contracts = contractService.getContractsByUserId(user.getId());
                for (Contract c : contracts) {
                    if (!c.getStatus().equals(StatusContract.CANCELADO))
                        feeTitles.add(c.getFee().getTitle());
                }

                if (feeTitles.size() > 0) {
                    contractsFees.setItems(feeTitles);
                    contractsFees.setValue(feeTitles.get(0));
                } else {
                    contractsFees.setItems("No hay contratos asociados a este DNI!");
                    contractsFees.setValue("No hay contratos asociados a este DNI!");
                }
            }
        });

        status = new Select<StatusContract>();
        status.addClassName("modifyformfield");
        status.setLabel("Estado del contrato anterior:");
        status.setId("actualstatus");

        List<StatusContract> contractStatus = new ArrayList<>();
        contractsFees.addValueChangeListener(event -> {
            List<Contract> contracts = new ArrayList<>();
            User user = userService.getUserByDNI(DNI.getValue());
            Fee fee = feeService.getFeeByTitle(event.getValue());
            if (user.getId() != null) {
                contracts = contractService.getContractsByUserIdAndFeeId(user.getId(), fee.getId());
                for (Contract c : contracts) {
                    if (!c.getStatus().equals(StatusContract.CANCELADO))
                        contractStatus.add(c.getStatus());
                }

                if (contractStatus.size() > 0) {
                    status.setItems(contractStatus);
                    status.setValue(contractStatus.get(0));
                }
                contractStatus.clear();
            }
        });

        phoneNumber = new IntegerField("Número de teléfono");
        phoneNumber.addClassName("registerformfield");
        phoneNumber.setRequired(true);

        roaming = new RadioButtonGroup<>();
        roaming.addClassName("registerformfield");
        roaming.setLabel("Roaming:");
        roaming.setItems(true, false);
        roaming.setValue(false);
        roaming.setId("roaming");

        shareData = new RadioButtonGroup<>();
        shareData.addClassName("registerformfield");
        shareData.setLabel("Compartir datos:");
        shareData.setItems(true, false);
        shareData.setValue(false);
        shareData.setId("shareData");

        confirm = new Button("Confirmar");
        confirm.addClassName("registerformbutton");
        confirm.addClickListener(e -> {
            onCreateButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleCreate = new H3("Crear Línea Móvil");
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

        bodySubDiv1 = new HorizontalLayout(DNI, contractsFees, status);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysregister");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(phoneNumber, roaming, shareData);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysregister");
        bodySubDiv3 = new HorizontalLayout(confirm);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysregister");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);

        // Registro CONTRATO
        binder = new BeanValidationBinder<>(MobileLine.class);
        binder.bindInstanceFields(this);
        binder.setBean(new MobileLine());
    }

    public void onCreateButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (contractsFees.getValue().equals("No hay contratos asociados a este DNI!")) {
            Notification.show("No hay contratos asociados a este DNI!")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (DNI.getValue().isEmpty() || user.getId() == null) {
            Notification.show("Error! Usuario inexistente. Revise los datos introducidos.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (binder.validate().isOk() && phoneNumber.getValue() >= 100000000
                && phoneNumber.getValue() <= 999999999) {
            Fee fee = feeService.getFeeByTitle(contractsFees.getValue());
            Contract cont = contractService.getContractByUserIdAndFeeIdAndStatus(user.getId(), fee.getId(),
                    status.getValue());
            List<MobileLine> mLinesOfContract = mobileLineService.getMobileLinesByContractId(cont.getId());
            if (mLinesOfContract.size() < fee.getMaxMobileLines()) {
                MobileLine mL = new MobileLine();
                mL.setUser(user);
                mL.setContract(cont);
                mL.setPhoneNumber(phoneNumber.getValue());
                mL.setRoaming(roaming.getValue());
                mL.setShareData(shareData.getValue());
                if (mobileLineService.saveMobileLine(mL)) {
                    Notification.show("Línea Móvil creada correctamente!")
                            .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                    UI.getCurrent().navigate("/menu");
                } else {
                    Notification.show("Algo falló! Revise los datos.").addThemeVariants(
                            NotificationVariant.LUMO_ERROR);
                }
            } else {
                Notification.show("Error! Número máximo de líneas móviles alcanzado.").addThemeVariants(
                        NotificationVariant.LUMO_ERROR);
                UI.getCurrent().navigate("/menu");
            }
        } else {
            Notification.show("Error inesperado! Contacte con un adminsitrador.").addThemeVariants(
                    NotificationVariant.LUMO_ERROR);
        }
    }
}
