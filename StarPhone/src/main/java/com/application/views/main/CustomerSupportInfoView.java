package com.application.views.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.application.views.main.layouts.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Atención al Cliente	")
@Route(value = "/atencionalcliente")
public class CustomerSupportInfoView extends VerticalLayout {

    header h;
    H3 titleRegister;
    H4 p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12;
    HorizontalLayout titleDiv;
    VerticalLayout centerDiv, container, layoutText;
    Scroller bodyDiv;
    footer f;

    public CustomerSupportInfoView() {
        p1 = new H4(
                "Estimado/a Cliente,\n"
                        + "Es un placer para nosotros en StarPhone atender tus consultas y darte el mejor servicio posible. Agradecemos sinceramente \n"
                        + "tu elección de nuestros servicios y estamos aquí para ayudarte en todo lo que necesites.\n");
        p2 = new H4(
                "Puedes ponerse en contacto llamando directamente, para obtener respuestas inmediatas a algunas de las preguntas \n"
                        + "más comunes. O bien, a través de Whatsapp, Twitter, Instagram o Facebook.");

        p3 = new H4(
                "*** Nuestros Call Centers se encuentran en España y todos los agentes trabajan exclusivamente para StarPhone.");

        p4 = new H4("*** Nuestro horario de atención es de lunes a domingo de 8:00 a 23:45h.");

        p5 = new H4("*** Aquí todos somos Estrellas, y estamos disponibles tanto para clientes como para no clientes.");

        p6 = new H4(
                "Para poner una reclamación, además de utilizar las vías anteriores, también puedes hacerlo en las siguientes:");

        p7 = new H4(
                "*** Escribiendo a nuestro domicilio social en Av. Universidad de Cádiz, 10, 11519 Puerto Real, Cádiz.");
        p8 = new H4("*** A través de la plataforma de resolución de litigios en línea de la Unión Europea.");
        p9 = new H4(
                "*** Si eres una persona física y has reclamado previamente ante StarPhone en materia de protección de datos,"
                        + " puedes acceder al servicio de mediación gratuita gestionado por Autocontrol: Asociación para la Autorregulación de"
                        + " la Comunicación Comercial al que estamos adheridos.");

        p10 = new H4(
                "Gracias por elegir StarPhone. Valoramos tu confianza y estamos aquí para brindarte el mejor servicio posible.");
        p11 = new H4("Atentamente,");
        p12 = new H4("Equipo de Atención al Cliente. StarPhone!");

        setWidthFull();
        setHeightFull();
        addClassName("mainView");
        setPadding(false);
        setSpacing(false);
        getStyle().set("font-family", "Kavoon");

        add(new header());

        centerDiv = new VerticalLayout();
        centerDiv.setWidthFull();
        centerDiv.setAlignItems(Alignment.CENTER);
        centerDiv.setJustifyContentMode(JustifyContentMode.CENTER);

        container = new VerticalLayout();
        container.setWidth("1400px");
        container.setHeight("600px");
        container.setPadding(false);
        container.setSpacing(false);
        container.setAlignItems(Alignment.CENTER);
        container.getStyle().set("border-radius", "12px");

        titleDiv = new HorizontalLayout();
        titleDiv.setWidthFull();
        titleDiv.setHeight("60px");
        titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
        titleDiv.setAlignItems(Alignment.CENTER);
        titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
        titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
        titleRegister = new H3("Atención al Cliente");
        titleRegister.getStyle().set("font-size", "38px");
        titleRegister.getStyle().set("color", "white");
        titleDiv.add(titleRegister);
        container.add(titleDiv);

        layoutText = new VerticalLayout();
        layoutText.add(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12);
        bodyDiv = new Scroller(layoutText);
        bodyDiv.setWidthFull();
        bodyDiv.getStyle().set("border-radius", "0 0 12px 12px");
        bodyDiv.getStyle().set("background-color", "rgb(255, 255, 255)");

        container.add(bodyDiv);
        expand(bodyDiv);

        centerDiv.add(container);
        add(centerDiv);
        expand(centerDiv);

        add(new footer());
    }
}
