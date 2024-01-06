package com.application.MobileLine.Views;

import java.util.List;

import com.application.Contract.Views.contract;
import com.application.MobileLine.Entities.Fee;
import com.application.MobileLine.Service.FeeService;
import com.application.User.Views.menu;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@CssImport("./styles/styles.css")
@PageTitle("Tarifas")
@Route(value = "/tarifas", layout = menu.class)
public class feesViewMenu extends VerticalLayout {

    VerticalLayout centerDiv;
    HorizontalLayout contratos;
    contract cont;

    public feesViewMenu(FeeService feeService) {
        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
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
        add(centerDiv);
        expand(centerDiv);
    }
}
