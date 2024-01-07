package com.application.Contract.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
import com.application.Contract.Entities.Status;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import jakarta.annotation.security.RolesAllowed;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Crear Contrato")
@Route(value = "/crearcontrato", layout = menu.class)
public class createContractView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI;
    EmailField email;
    Select<String> fees;
    Select<Status> status;
    Button confirmar;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;
    private final BeanValidationBinder<Contract> binder;

    public createContractView(ContractService cService, UserService uService, FeeService fService) {
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
        registerForm.setWidth("1100px");
        registerForm.setHeight("450px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI");
        DNI.addClassName("registerformfield");
        DNI.setRequired(true);
        DNI.setHelperText("DNI del Usuario a realizar con el Contrato.");
        DNI.setId("DNI");

        email = new EmailField("Email");
        email.addClassName("registerformfield");
        email.setReadOnly(true);
        email.setId("email");

        DNI.addValueChangeListener(event -> {
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                email.setValue(user.getEmail());
            } else {
                email.setValue("");
            }
        });

        List<Fee> f = feeService.getAll();
        List<String> titlesFee = new ArrayList<>();
        for (Fee fee : f) {
            titlesFee.add(fee.getTitle());
        }
        fees = new Select<String>();
        fees.addClassName("registerformfield");
        fees.setLabel("Tarifa:");
        if (titlesFee.size() > 0) {
            fees.setItems(titlesFee);
            fees.setValue(titlesFee.get(0));
        } else {
            fees.setItems("No hay tarifas disponibles!");
            fees.setValue("No hay tarifas disponibles!");
        }
        fees.setId("fees");

        status = new Select<Status>();
        status.addClassName("registerformfield");
        status.setLabel("Estado:");
        status.setItems(Status.ACTIVO, Status.ENPROCESO, Status.CANCELADO);
        status.setValue(Status.ENPROCESO);
        status.setId("status");

        confirmar = new Button("Confirmar");
        confirmar.addClassName("registerformbutton");
        confirmar.addClickListener(e -> {
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
        titleCreate = new H3("Crear Contrato");
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
        bodySubDiv1.addClassName("bodysregister");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(email, fees, status);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysregister");
        bodySubDiv3 = new HorizontalLayout(confirmar);
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
        binder = new BeanValidationBinder<>(Contract.class);
        binder.bindInstanceFields(this);
        binder.setBean(new Contract());
    }

    public void onCreateButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (fees.getValue().equals("No hay tarifas disponibles!")) {
            Notification.show("Error! No hay tarifas disponibles.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (DNI.getValue().isEmpty() || email.getValue().isEmpty() || user.getId() == null) {
            Notification.show("Error! Usuario no existente.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (!status.isEmpty()) {
            Fee fee = feeService.getFeeByTitle(fees.getValue());
            binder.getBean().setUser(user);
            binder.getBean().setFee(fee);
            binder.getBean().setStatus(status.getValue());
            binder.getBean().setStartDate(LocalDate.now());
            if (contractService.saveContract(binder.getBean())) {
                Notification.show("Genial. Contrato creado correctamente!!")
                        .addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Algo falló! Revise los datos.").addThemeVariants(
                        NotificationVariant.LUMO_ERROR);
            }
        } else {
            Notification.show("Error inesperado! Contacte con un adminsitrador.").addThemeVariants(
                    NotificationVariant.LUMO_ERROR);
        }
    }
}