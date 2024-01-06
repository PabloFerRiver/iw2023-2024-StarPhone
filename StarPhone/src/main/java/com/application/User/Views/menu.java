package com.application.User.Views;

import com.application.MobileLine.Views.blockNumberUserView;
import com.application.MobileLine.Views.consultUserView;
import com.application.MobileLine.Views.createFeeView;
import com.application.MobileLine.Views.deleteFeeView;
import com.application.MobileLine.Views.feesViewMenu;
import com.application.MobileLine.Views.modifyFeeView;
import com.application.MobileLine.Views.shareDataAndRoamingView;
import com.application.User.Entities.User;
import com.application.User.Security.AuthenticatedUser;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;
import com.application.views.main.layouts.header;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

@CssImport("./styles/styles.css")
@PageTitle("Menu")
public class menu extends AppLayout {
    // TODO: Cambiar diferentes LineAwesomeIcon por cada navegación

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

        if (accessChecker.hasAccess(adminRegisterUserView.class)) {
            navItem = new SideNavItem("Registrar Usuario", adminRegisterUserView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(modifyDataUserView.class)) {
            navItem = new SideNavItem("Modificar Usuario", modifyDataUserView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(adminDeleteUserView.class)) {
            navItem = new SideNavItem("Borrar Usuario", adminDeleteUserView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(rolManagementView.class)) {
            navItem = new SideNavItem("Gestionar Roles", rolManagementView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(createFeeView.class)) {
            navItem = new SideNavItem("Crear Tarifa", createFeeView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(modifyFeeView.class)) {
            navItem = new SideNavItem("Modificar Tarifa", modifyFeeView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(deleteFeeView.class)) {
            navItem = new SideNavItem("Eliminar Tarifa", deleteFeeView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(blockNumberUserView.class)) {
            navItem = new SideNavItem("Números Desconocidos", blockNumberUserView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(shareDataAndRoamingView.class)) {
            navItem = new SideNavItem("Roaming & Datos Compartidos", shareDataAndRoamingView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(changePasswordView.class)) {
            navItem = new SideNavItem("Cambiar Credenciales", changePasswordView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(feesViewMenu.class)) {
            navItem = new SideNavItem("Tarifas", feesViewMenu.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
            navItem.addClassName("sideNavItem");
            nav.addItem(navItem);
        }

        if (accessChecker.hasAccess(feesViewMenu.class)) {
            navItem = new SideNavItem("Consultar Consumos Línea", consultUserView.class,
                    LineAwesomeIcon.GLOBE_SOLID.create());
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
}