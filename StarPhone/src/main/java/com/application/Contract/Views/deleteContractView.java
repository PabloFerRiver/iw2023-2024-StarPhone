package com.application.Contract.Views;

import com.application.User.Entities.User;
import com.application.User.Services.UserService;
import com.application.User.Views.menu;
import com.application.Contract.Entities.Contract;
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
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Eliminar Contrato")
@Route(value = "/eliminarcontrato", layout = menu.class)
public class deleteContractView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3, footerDiv;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleCreate;
    TextField DNI;
    Select<String> contractsfees;
    Button confirmar;
    private final ContractService contractService;
    private final UserService userService;
    private final FeeService feeService;

    public deleteContractView(ContractService cService, UserService uService, FeeService fService) {
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
        registerForm.setWidth("400px");
        registerForm.setHeight("460px");
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

        contractsfees = new Select<String>();
        contractsfees.setLabel("Contrato con tarifa:");
        contractsfees.addClassName("modifyformfield");
        contractsfees.setId("contractsfees");

        List<String> feeTitles = new ArrayList<>();
        DNI.addValueChangeListener(event -> {
            List<Contract> contracts = new ArrayList<>();
            User user = userService.getUserByDNI(event.getValue());
            if (user.getId() != null) {
                contracts = contractService.getContractsByUserId(user.getId());
                for (Contract c : contracts) {
                    feeTitles.add(c.getFee().getTitle());
                }

                if (feeTitles.size() > 0) {
                    contractsfees.setItems(feeTitles);
                    contractsfees.setValue(feeTitles.get(0));
                } else {
                    contractsfees.setItems("No hay tarifa asociada a este contrato!");
                    contractsfees.setValue("No hay tarifa asociada a este contrato!");
                }
            }
        });

        confirmar = new Button("Confirmar");
        confirmar.addClassName("registerformbutton");
        confirmar.addClickListener(e -> {
            onDeleteButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleCreate = new H3("Eliminar Contrato");
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
        bodySubDiv2 = new HorizontalLayout(contractsfees);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(confirmar);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysmodify");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);
    }

    public void onDeleteButtonClick() {
        User user = userService.getUserByDNI(DNI.getValue());
        if (contractsfees.getValue().equals("No hay tarifa asociada a este contrato!")) {
            Notification.show("Error! No hay tarifa asociada a este contrato!.")
                    .addThemeVariants(NotificationVariant.LUMO_ERROR);
            UI.getCurrent().navigate("/menu");
        } else if (user.getId() != null && contractsfees.getValue() != null) {
            Fee fee = feeService.getFeeByTitle(contractsfees.getValue());
            if (contractService.deleteContractByUserIdAndFeeId(user.getId(), fee.getId())) {
                Notification.show("Contrato eliminado correctamente!").addThemeVariants(
                        NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate("/menu");
            } else {
                Notification.show("Error! Rellene todos los campos.").addThemeVariants(
                        NotificationVariant.LUMO_ERROR);
            }
        }
    }
}