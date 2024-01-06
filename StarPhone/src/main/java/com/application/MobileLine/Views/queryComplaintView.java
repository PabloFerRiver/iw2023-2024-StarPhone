package com.application.MobileLine.Views;

import com.application.Contract.Entities.Contract;
import com.application.Contract.Service.ContractService;
import com.application.MobileLine.Entities.MobileLine;
import com.application.MobileLine.Service.MobileLineService;
import com.application.User.Security.AuthenticatedUser;
import com.application.User.Views.menu;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.util.ArrayList;
import java.util.List;
//TODO: TERMINAR VISTA

// TODO: @RolesAllowed("ROLE_CUSTOMER") + import jakarta
@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Reclamaciones")
@Route(value = "/reclamaciones", layout = menu.class)
public class queryComplaintView extends VerticalLayout {
    Select<Integer> lines;
    VerticalLayout bodyDiv, centerDiv, confirmSquare;
    HorizontalLayout titleDiv, footerDiv;
    H3 titleDelete;
    TextArea textArea;

    Button confirmar;
    public Paragraph text = new Paragraph(" ");
    private final MobileLineService mobileService;
    private final AuthenticatedUser authenticatedUser;
    private final ContractService contractService;

    public queryComplaintView(AuthenticatedUser authUser, MobileLineService mService, ContractService cService) {
        this.authenticatedUser = authUser;
        this.mobileService = mService;
        this.contractService = cService;

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        // Campos formulario
        List<Contract> contracts = contractService.getContractsByUser_Id(authenticatedUser.get().get().getId());
        List<MobileLine> mobileLines = new ArrayList<>();
        for (var c : contracts) {
            mobileLines.addAll(mobileService.getMobileLineByContractId(c.getId()));
        }

        List<Integer> phoneNumberlines = new ArrayList<>();
        for (var m : mobileLines) {
            phoneNumberlines.add(m.getPhoneNumber());
        }

        lines = new Select<Integer>();
        lines.addClassName("activefield");
        lines.setLabel("Línea:");
        lines.setItems(phoneNumberlines);

        textArea = new TextArea("Reclamación:");
        textArea.addClassName("activefield");
        textArea.setId("textArea");
        textArea.setRequired(true);
        textArea.setHeight("300px");
        textArea.setWidth("600px");

        confirmar = new Button("Enviar");
        confirmar.addClassName("activebutton");
        confirmar.addClickListener(e -> onData());
        // ---------------------------

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        confirmSquare = new VerticalLayout();
        confirmSquare.setWidth("980px");
        confirmSquare.setHeight("600px");
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
        titleDelete = new H3("Reclamación");
        titleDelete.getStyle().set("font-size", "28px");
        titleDelete.getStyle().set("color", "white");
        titleDiv.add(titleDelete);
        confirmSquare.add(titleDiv);

        bodyDiv = new VerticalLayout(lines, textArea, confirmar);
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

    public void onData() {

    }
}