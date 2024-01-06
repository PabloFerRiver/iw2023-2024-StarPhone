package com.application.MobileLine.Views;

import java.util.List;

import com.application.Contract.Views.contract;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.application.views.main.layouts.footer;
import com.application.views.main.layouts.header;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Tarifas")
@Route(value = "/starphonetarifas")
public class feesView extends VerticalLayout {

    VerticalLayout centerDiv;
    HorizontalLayout contratos;
    contract cont;
    Button butCont;

    public feesView(FeeService feeService) {
        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        add(new header());

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setPadding(false);
        centerDiv.setSpacing(false);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        centerDiv.setAlignItems(Alignment.CENTER);

        contratos = new HorizontalLayout();
        contratos.setWidthFull();
        contratos.setHeight(90, Unit.PERCENTAGE);
        contratos.setPadding(false);
        contratos.setSpacing(false);
        contratos.setJustifyContentMode(JustifyContentMode.CENTER);
        contratos.setAlignItems(Alignment.CENTER);
        contratos.getStyle().set("gap", "60px");

        List<Fee> fees = feeService.getAll();

        for (Fee fee : fees) {
            cont = new contract(fee.getTitle(),
                    fee.getDescriptionFiber(),
                    fee.getDescriptionMobile(),
                    fee.getDescriptionTV(),
                    fee.getMaxMobileLines(),
                    fee.getMonthlyprice());
            contratos.add(cont);
        }

        centerDiv.add(contratos);
        butCont = new Button("Registrarse");
        butCont.setWidth("245px");
        butCont.setHeight("70px");
        butCont.getStyle().set("font-size", "26px");
        butCont.getStyle().set("margin-bottom", "20px");
        butCont.addClickListener(e -> UI.getCurrent().getPage().setLocation("/registro"));
        butCont.addClassName("mainViewButton");
        butCont.getStyle().set("cursor", "pointer");

        centerDiv.add(butCont);
        add(centerDiv);
        expand(centerDiv);

        add(new footer());
    }
}
