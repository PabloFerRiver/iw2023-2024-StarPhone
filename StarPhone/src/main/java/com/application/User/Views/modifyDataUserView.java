package com.application.User.Views;

import com.application.User.Services.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.application.User.Entities.Role;
import com.application.User.Entities.User;
import com.application.User.Security.AuthenticatedUser;
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
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.annotation.security.RolesAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@RolesAllowed({ "ROLE_ADMIN", "ROLE_CUSTOMERSUPPORT" })
@CssImport("./styles/styles.css")
@PageTitle("Modificar Usuario")
@Route(value = "/modificarusuario", layout = menu.class)
public class modifyDataUserView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4, footerDiv;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleRegister;
    TextField username, DNI;
    EmailField email;
    IntegerField phoneNumber;
    PasswordField password, repeatPassword;
    Select<Role> role;
    Button confirmar;
    private final UserService userService;
    private final AuthenticatedUser authenticatedUser;
    private final PasswordEncoder passwordEncoder;

    public modifyDataUserView(UserService service, AuthenticatedUser authUser, PasswordEncoder pwEncoder) {
        userService = service;
        authenticatedUser = authUser;
        passwordEncoder = pwEncoder;
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
        registerForm.setHeight("550px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI:");
        DNI.addClassName("modifyformfield");
        DNI.setMinLength(9);
        DNI.setMaxLength(9);
        DNI.setRequired(true);
        DNI.setId("DNI");

        username = new TextField("Nombre de Usuario:");
        username.addClassName("modifyformfield");
        username.setId("username");

        phoneNumber = new IntegerField("Teléfono:");
        phoneNumber.addClassName("modifyformfield");
        phoneNumber.setHelperText("Si no posee uno, deje el 0. " +
                "En caso contrario, omita el prefijo internacional.");
        phoneNumber.setId("phoneNumber");

        email = new EmailField("Correo Electrónico:");
        email.addClassName("modifyformfield");
        email.setId("email");

        password = new PasswordField("Contraseña:");
        password.addClassName("modifyformfield");
        password.setId("password");

        repeatPassword = new PasswordField("Repetir Contraseña:");
        repeatPassword.addClassName("modifyformfield");
        repeatPassword.setId("repeatPassword");

        role = new Select<>();
        role.addClassName("registerformfield");
        role.setLabel("Rol inicial:");
        role.setItems(Role.CUSTOMER, Role.MARKETING, Role.FINANCE, Role.CUSTOMERSUPPORT, Role.ADMIN);
        role.setId("rol");
        if (authenticatedUser.get().get().getRoles().contains(Role.CUSTOMERSUPPORT))
            role.setEnabled(false);
        if (authenticatedUser.get().get().getRoles().contains(Role.ADMIN))
            role.setEnabled(true);

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
        titleRegister = new H3("Modificar Datos Usuario");
        titleRegister.getStyle().set("font-size", "28px");
        titleRegister.getStyle().set("color", "white");
        titleDiv.add(titleRegister);
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
        bodySubDiv2 = new HorizontalLayout(username, phoneNumber, role);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysmodify");
        bodySubDiv3 = new HorizontalLayout(email, password, repeatPassword);
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
        User u = userService.getUserByDNI(DNI.getValue());
        if (!username.getValue().isEmpty())
            u.setUsername(username.getValue());

        if (phoneNumber.getValue() != null && !phoneNumber.getValue().equals(0))
            u.setPhoneNumber(phoneNumber.getValue());

        if (!email.getValue().isEmpty())
            u.setEmail(email.getValue());

        if (!password.getValue().isEmpty() && !repeatPassword.getValue().isEmpty() &&
                password.getValue().equals(repeatPassword.getValue()))
            u.setPassword(passwordEncoder.encode(password.getValue()));
        if (role.getValue() != null) {
            u.getRoles().clear();
            u.addRole(role.getValue());
        }

        if (userService.saveUser(u)) {
            String text = new String("Genial. Datos modificados correctamente!!");
            Notification.show(text).addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            UI.getCurrent().navigate("/menu");
        } else {
            String text = new String("Algo falló! Revise los datos.");
            Notification.show(text).addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
