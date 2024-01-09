package com.application.User.Views;

import com.application.Contract.Views.consultPersonalContractsView;
import com.application.MobileLine.Views.blockNumberUserView;
import com.application.MobileLine.Views.consultPersonalMobileLinesView;
import com.application.MobileLine.Views.feesViewMenu;
import com.application.User.Entities.Role;
import com.application.User.Entities.User;
import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import org.vaadin.lineawesome.LineAwesomeIcon;
import com.application.views.main.layouts.header;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

import java.util.Optional;
import java.util.Set;

@CssImport("./styles/styles.css")
@PageTitle("Menu")
public class menu extends AppLayout {

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public menu(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        addToNavbar(true, toggle, new header());
    }

    private void addDrawerContent() {
        H1 aName = new H1("StarPhone");
        aName.getStyle().set("font-size", "32px");
        aName.getStyle().set("font-weight", "bold");

        HorizontalLayout appName = new HorizontalLayout(aName);
        appName.setWidthFull();
        appName.setHeight("60px");
        appName.setJustifyContentMode(JustifyContentMode.CENTER);
        appName.setAlignItems(Alignment.CENTER);
        appName.add(aName);

        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();
        SideNavItem navItem;

        if (accessChecker.hasAccess(menuUserEntityView.class)) {
            SvgIcon icon = LineAwesomeIcon.USER_COG_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Usuario", menuUserEntityView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(menuFeeEntityView.class)) {
            SvgIcon icon = LineAwesomeIcon.FILE_INVOICE_DOLLAR_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Tarifa", menuFeeEntityView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(menuContractEntityView.class)) {
            SvgIcon icon = LineAwesomeIcon.FILE_ALT_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Contrato", menuContractEntityView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(menuMobileLineEntityView.class)) {
            SvgIcon icon = LineAwesomeIcon.PHONE_VOLUME_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Línea", menuMobileLineEntityView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(personalDataView.class)) {
            SvgIcon icon = LineAwesomeIcon.USER_CIRCLE_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Datos Personales", personalDataView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(modifyPersonalDataView.class)) {
            SvgIcon icon = LineAwesomeIcon.USER_EDIT_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Modificar Datos Personales", modifyPersonalDataView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(changePasswordView.class)) {
            SvgIcon icon = LineAwesomeIcon.KEY_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Cambiar Credenciales", changePasswordView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(blockNumberUserView.class)) {
            SvgIcon icon = LineAwesomeIcon.PHONE_SLASH_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Números Desconocidos", blockNumberUserView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(feesViewMenu.class) && hasRole(authenticatedUser.get().get(), Role.CUSTOMER)) {
            SvgIcon icon = LineAwesomeIcon.SEARCH_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Consultar Tarifas", feesViewMenu.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(consultPersonalContractsView.class)) {
            SvgIcon icon = LineAwesomeIcon.SEARCH_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Consultar Contratos Personales", consultPersonalContractsView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(consultPersonalMobileLinesView.class)) {
            SvgIcon icon = LineAwesomeIcon.SEARCH_SOLID.create();
            icon.addClassName("icons");
            navItem = new SideNavItem("Consultar Líneas Personales", consultPersonalMobileLinesView.class,
                    icon);
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        return nav;
    }

    private HorizontalLayout createFooter() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setHeight("60px");
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");
            avatar.setWidth("35px");
            avatar.setHeight("35px");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            HorizontalLayout infosesion = new HorizontalLayout();
            infosesion.add(avatar);
            infosesion.add(user.getUsername());
            infosesion.add(new Icon("lumo", "dropdown"));
            infosesion.getElement().getStyle().set("display", "flex");
            infosesion.getElement().getStyle().set("align-items", "center");
            infosesion.getElement().getStyle().set("gap", "15px");
            infosesion.getElement().getStyle().set("font-size", "20px");
            userName.add(infosesion);
            userName.getSubMenu().addItem("CerrarSesión", e -> {
                authenticatedUser.logout();
            });
            layout.add(userMenu);
        }
        return layout;
    }

    public boolean hasRole(User user, Role role) {
        Set<Role> roles = user.getRoles();
        for (Role r : roles) {
            if (r.equals(role)) {
                return true;
            }
        }
        return false;
    }
}