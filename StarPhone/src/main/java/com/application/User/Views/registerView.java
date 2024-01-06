package com.application.User.Views;

import com.application.User.Services.UserService;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.application.User.Entities.User;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Pre;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;

@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Registro")
@Route(value = "/registro")
public class registerView extends VerticalLayout {

    HorizontalLayout titleDiv, centerDiv, bodySubDiv1, bodySubDiv2, bodySubDiv3,
            bodySubDiv4, bodySubDiv5, footerDiv;
    VerticalLayout center, bodyDiv, registerForm;
    H3 titleRegister;
    TextField name, surname, username, country, DNI, city;
    DatePicker birthdate;
    EmailField email;
    IntegerField phoneNumber;
    PasswordField password, repeatPassword;
    Select<String> fees;
    Button confirmar;

    private final BeanValidationBinder<User> binder;
    private final UserService userService;

    public registerView(UserService uService, FeeService feeService) {
        userService = uService;

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
        registerForm.setWidth("1200px");
        registerForm.setHeight("700px");
        registerForm.setPadding(false);
        registerForm.setSpacing(false);
        registerForm.setAlignItems(Alignment.CENTER);
        registerForm.getStyle().set("border-radius", "12px");

        // Campos formulario ------------------------------
        DNI = new TextField("DNI:");
        DNI.addClassName("registerformfield");
        DNI.setMinLength(9);
        DNI.setMaxLength(9);
        DNI.setId("DNI");

        name = new TextField("Nombre:");
        name.addClassName("registerformfield");
        name.setId("name");

        surname = new TextField("Apellido:");
        surname.addClassName("registerformfield");
        surname.setId("surname");

        username = new TextField("Nombre de Usuario:");
        username.addClassName("registerformfield");
        username.setId("username");

        city = new TextField("Ciudad:");
        city.addClassName("registerformfield");
        city.setId("city");

        country = new TextField("País:");
        country.addClassName("registerformfield");
        country.setId("country");

        birthdate = new DatePicker("Fecha de Nacimiento:");
        birthdate.addClassName("registerformfield");
        birthdate.setId("birthdate");

        phoneNumber = new IntegerField("Teléfono:");
        phoneNumber.addClassName("registerformfield");
        phoneNumber.setHelperText("Si no posee uno, deje el 0. " +
                "En caso contrario, omita el prefijo internacional.");
        phoneNumber.setId("phoneNumber");

        email = new EmailField("Correo Electrónico:");
        email.addClassName("registerformfield");
        email.setId("email");

        password = new PasswordField("Contraseña:");
        password.addClassName("registerformfield");
        password.setId("password");

        repeatPassword = new PasswordField("Repetir Contraseña:");
        repeatPassword.addClassName("registerformfield");
        repeatPassword.setId("repeatPassword");

        List<Fee> feesList = feeService.getAll();
        List<String> titles = new ArrayList<>();
        fees = new Select<>();
        fees.addClassName("registerformfield");
        fees.setLabel("Tarifa:");
        for (Fee fee : feesList) {
            titles.add(fee.getTitle());
        }
        fees.setItems(titles);
        fees.setValue("Tarifa 1");

        confirmar = new Button("Registrarse");
        confirmar.addClassName("registerformbutton");
        confirmar.addClickListener(e -> {
            onRegisterButtonClick();
        });

        // -------------------------------------------------

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("80px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleRegister = new H3("Hazte Cliente");
        titleRegister.getStyle().set("font-size", "38px");
        titleRegister.getStyle().set("color", "white");
        titleDiv.add(titleRegister);
        registerForm.add(titleDiv);

        bodyDiv = new VerticalLayout();
        bodyDiv.setWidthFull();
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        bodySubDiv1 = new HorizontalLayout(name, surname, DNI);
        bodySubDiv1.setSpacing(false);
        bodySubDiv1.setPadding(false);
        bodySubDiv1.addClassName("bodysregister");
        bodySubDiv1.getStyle().set("margin-top", "30px");
        bodySubDiv2 = new HorizontalLayout(username, birthdate, fees);
        bodySubDiv2.setSpacing(false);
        bodySubDiv2.setPadding(false);
        bodySubDiv2.addClassName("bodysregister");
        bodySubDiv3 = new HorizontalLayout(email, password, repeatPassword);
        bodySubDiv3.setSpacing(false);
        bodySubDiv3.setPadding(false);
        bodySubDiv3.addClassName("bodysregister");
        bodySubDiv4 = new HorizontalLayout(phoneNumber, city, country);
        bodySubDiv4.setSpacing(false);
        bodySubDiv4.setPadding(false);
        bodySubDiv4.addClassName("bodysregister");
        bodySubDiv5 = new HorizontalLayout(confirmar);
        bodySubDiv5.setSpacing(false);
        bodySubDiv5.setPadding(false);
        bodySubDiv5.addClassName("bodysregister");

        bodyDiv.add(bodySubDiv1, bodySubDiv2, bodySubDiv3, bodySubDiv4, bodySubDiv5);
        registerForm.add(bodyDiv);

        expand(bodyDiv);

        center.add(registerForm);
        add(center);
        expand(center);

        // Registro USUARIO
        binder = new BeanValidationBinder<>(User.class);
        binder.bindInstanceFields(this);
        binder.setBean(new User());
    }

    public void onRegisterButtonClick() {

        if (binder.validate().isOk() && password.getValue().equals(repeatPassword.getValue())) {
            Button closeButton = new Button(new Icon("lumo", "cross"));
            closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            closeButton.getElement().setAttribute("aria-label", "Close");
            Notification not = new Notification();
            if (userService.registerUser(binder.getBean())) {
                closeButton.addClickListener(event -> {
                    not.close();
                    UI.getCurrent().getPage().setLocation("/activaruser");
                });
                Pre text = new Pre("Genial. Por favor, revisa tu email!\nClick en la cruz para continuar.");
                HorizontalLayout layout = new HorizontalLayout(text, closeButton);
                layout.setAlignItems(Alignment.CENTER);
                not.add(layout);
                not.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                not.open();
            } else {
                closeButton.addClickListener(event -> {
                    not.close();
                });
                Pre text = new Pre("Algo falló. Revise los datos, si el fallo persiste, \n" +
                        "contacte con nosotros.\n Click en la cruz para continuar.");
                HorizontalLayout layout = new HorizontalLayout(text, closeButton);
                layout.setAlignItems(Alignment.CENTER);
                not.add(layout);
                not.addThemeVariants(NotificationVariant.LUMO_ERROR);
                not.open();
            }

        } else {
            Notification.show("Por favor, revise los datos introducidos.");
        }

    }
}