package com.application.User.Views;

import com.application.User.Entities.Role;
import com.application.User.Services.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;

@RolesAllowed("ROLE_ADMIN")
@CssImport("./styles/styles.css")
@PageTitle("Gestionar Roles")
@Route(value = "/gestionarroles", layout = menu.class)
public class rolManagementView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv;
    H3 titleRolManagement;
    Select<String> roleActions;
    Select<Role> role;
    ComboBox<String> username;
    Button confirm;

    private final UserService userService;

    public rolManagementView(UserService uService) {
        userService = uService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        roleActions = new Select<String>();
        roleActions.addClassName("activefield");
        roleActions.setLabel("Acción:");
        roleActions.setItems("Asignar", "Eliminar");
        roleActions.setValue("Asignar");
        roleActions.setId("roleActions");

        role = new Select<Role>();
        role.addClassName("activefield");
        role.setLabel("Rol:");
        role.setItems(Role.CUSTOMER, Role.MARKETING, Role.FINANCE, Role.CUSTOMERSUPPORT, Role.ADMIN);
        role.setValue(Role.CUSTOMER);
        role.setId("rol");

        username = new ComboBox<String>();
        username.addClassName("activefield");
        username.setLabel("Nombre de usuario:");
        username.setHelperText("Tras escribir, despliegue para marcar una opción.");
        username.setId("username");

        username.setAllowCustomValue(true);
        username.addCustomValueSetListener(event -> {
            String text = event.getDetail();
            if (text != null && !text.isEmpty()) {
                List<String> usernames = userService.getFullUsernameByPartUsername(text);
                username.setItems(usernames);
                username.setValue(text);
                username.setOpened(true);
            } else {
                username.setItems("");
            }
        });

        confirm = new Button("Confirmar");
        confirm.addClassName("activebutton");
        confirm.addClickListener(e -> onManageRolButton());
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
        titleRolManagement = new H3("Gestionar Roles");
        titleRolManagement.getStyle().set("font-size", "28px");
        titleRolManagement.getStyle().set("color", "white");
        titleDiv.add(titleRolManagement);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(roleActions, role, username, confirm);
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

    public void onManageRolButton() {
        if (roleActions.getValue() != null && role.getValue() != null && username.getValue() != null) {
            if (roleActions.getValue().equals("Asignar")) {
                if (userService.giveRole(username.getValue(), role.getValue())) {
                    Notification.show("Rol añadido con éxito.").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                } else {
                    Notification.show("Error al añadir dicho Rol.").addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            } else if (roleActions.getValue().equals("Eliminar")) {
                if (userService.removeRole(username.getValue(), role.getValue())) {
                    Notification.show("Rol eliminado con éxito.").addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                } else {
                    Notification.show("Error al eliminar dicho Rol.").addThemeVariants(NotificationVariant.LUMO_ERROR);
                }
            } else {
                Notification.show("Debe completar todos los campos.").addThemeVariants(NotificationVariant.LUMO_ERROR);
            }
        }
    }
}
