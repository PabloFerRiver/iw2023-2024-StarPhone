package com.application.User.Views;

import com.application.User.Services.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@PageTitle("Activar Usuario")
@Route(value = "/activaruser")
@CssImport("./styles/styles.css")
public class activateUserView extends VerticalLayout {

    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv;
    H3 confirmTitle;
    EmailField email;
    TextField confirmCode;
    Button confirm;
    private final UserService userService;

    public activateUserView(UserService uService) {
        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        email = new EmailField("Email:");
        email.addClassName("activefield");
        email.setId("email");
        email.setRequired(true);

        confirmCode = new TextField("Código de activación:");
        confirmCode.addClassName("activefield");
        confirmCode.setId("confirmCode");
        confirmCode.setRequired(true);

        confirm = new Button("Confirmar");
        confirm.addClassName("activebutton");
        confirm.addClickListener(e -> onActivateuserClick());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("350px");
        confirmSquare.setHeight("350px");
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
        confirmTitle = new H3("Activar Usuario");
        confirmTitle.getStyle().set("font-size", "28px");
        confirmTitle.getStyle().set("color", "white");
        titleDiv.add(confirmTitle);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(email, confirmCode, confirm);
        bodyDiv.setWidthFull();
        bodyDiv.setJustifyContentMode(JustifyContentMode.START);
        bodyDiv.setAlignItems(Alignment.CENTER);
        bodyDiv.setPadding(false);
        bodyDiv.setSpacing(false);
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        confirmSquare.add(bodyDiv);
        expand(bodyDiv);

        centerDiv.add(confirmSquare);
        add(centerDiv);
        expand(centerDiv);

        userService = uService;
    }

    public void onActivateuserClick() {
        if (!email.getValue().isEmpty() && !confirmCode.getValue().isEmpty()) {
            confirm.setEnabled(false);
            if (userService.isActivated(email.getValue())) {
                UI.getCurrent().getPage().setLocation("/");
            } else if (userService.activateUserCode(email.getValue(), confirmCode.getValue())) {
                UI.getCurrent().getPage().setLocation("/menu");
            } else {
                Notification.show("Código de activación incorrecto").addThemeVariants(NotificationVariant.LUMO_ERROR);
                UI.getCurrent().getPage().setLocation("/activaruser");
            }
        } else {
            Notification.show("Rellene todos los campos!").addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}