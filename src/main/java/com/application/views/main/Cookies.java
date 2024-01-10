package com.application.views.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.application.views.main.layouts.*;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@AnonymousAllowed
@CssImport("./styles/styles.css")
@PageTitle("Política de Cookies")
@Route(value = "/cookies")
public class Cookies extends VerticalLayout {

        header h;
        H3 titleRegister, one, two, three, four, five, six, seven, eight, nine;
        H5 Welcome, Welcome1, subOneOne, subOneTwo, subOneThree, subOneFour, subOneFive, subTwo, subThree,
                        subFour, subFive, subSix, subSeven, subEightOne, subEightTwo, subEightThree,
                        subEightFour, subNine;
        H4 oneTwo;
        HorizontalLayout titleDiv;
        VerticalLayout centerDiv, container, layoutTextCookies;
        Scroller bodyDiv;
        footer f;

        public Cookies() {
                Welcome = new H5(
                                "POLÍTICA DE PRIVACIDAD DEL SITIO WEB DE STARPHONE - www.StarPhone.com");
                Welcome1 = new H5(
                                "En StarPhone valoramos a nuestros clientes y empleados por ello que ofrecemos un entorno seguro y de confianza.");

                one = new H3("1. POLÍTICA DE PRIVACIDAD Y PROTECCIÓN DE DATOS");
                one.getStyle().set("font-weight", "bold");
                oneTwo = new H4("Leyes que incorpora esta política de privacidad");
                subOneOne = new H5(
                                "*** El Reglamento (UE) 2016/679 del Parlamento Europeo y del Consejo, de 27 de abril de 2016, relativo\r\n"
                                                + //
                                                "a la protección de las personas físicas en lo que respecta al tratamiento de datos personales y a la\r\n"
                                                + //
                                                "libre circulación de estos datos (RGPD).");
                subOneTwo = new H5(
                                "*** La Ley Orgánica 3/2018, de 5 de diciembre, de Protección de Datos Personales y garantía de los\r\n"
                                                + //
                                                "derechos digitales (LOPD-GDD).");
                subOneThree = new H5(
                                "*** El Real Decreto 1720/2007, de 21 de diciembre, por el que se aprueba el Reglamento de desarrollo\r\n"
                                                + //
                                                "de la Ley Orgánica 15/1999, de 13 de diciembre, de Protección de Datos de Carácter Personal\r\n"
                                                + //
                                                "(RDLOPD).");
                subOneFour = new H5(
                                "*** La Ley 34/2002, de 11 de julio, de servicios de la sociedad de la información y de comercio\r\n"
                                                + //
                                                "electrónico (LSSI-CE).");
                subOneFive = new H5("*** La Ley 9/2014, de 9 de mayo, General de Telecomunicaciones (LGT).");

                two = new H3("2. REGISTRO DE DATOS DE CARÁCTER PERSONAL");
                two.getStyle().set("font-weight", "bold");
                subTwo = new H5(
                                "En cumplimiento de lo establecido en el RGPD y la LOPD-GDD, le informamos que los datos personales\r\n"
                                                + //
                                                "recabados por StarPhone, mediante los formularios extendidos en sus páginas quedarán incorporados y\r\n"
                                                + //
                                                "serán tratados en nuestro fichero con el fin de poder facilitar, agilizar y cumplir los compromisos\r\n"
                                                + //
                                                "establecidos entre StarPhone y el Usuario o el mantenimiento de la relación que se establezca en los\r\n"
                                                + //
                                                "formularios que este rellene, o para atender una solicitud o consulta del mismo. Asimismo, de conformidad\r\n"
                                                + //
                                                "con lo previsto en el RGPD y la LOPD-GDD, salvo que sea de aplicación la excepción prevista en el artículo\r\n"
                                                + //
                                                "30.5 del RGPD, se mantiene un registro de actividades de tratamiento que especifica, según sus\r\n"
                                                + //
                                                "finalidades, las actividades de tratamiento llevadas a cabo y las demás circunstancias establecidas en el\r\n"
                                                + //
                                                "RGPD.");

                three = new H3("3. BASE LEGAL DEL TRATAMIENTO DE LOS DATOS PERSONALES");
                three.getStyle().set("font-weight", "bold");
                subThree = new H5(
                                "La base legal para el tratamiento de los datos personales es el consentimiento. StarPhone se compromete\r\n"
                                                + //
                                                "a recabar el consentimiento expreso y verificable del Usuario para el tratamiento de sus datos personales\r\n"
                                                + //
                                                "para uno o varios fines específicos.\r\n" + //
                                                "El Usuario tendrá derecho a retirar su consentimiento en cualquier momento. Será tan fácil retirar el\r\n"
                                                + //
                                                "consentimiento como darlo. Como regla general, la retirada del consentimiento no condicionará el uso del\r\n"
                                                + //
                                                "Sitio Web.\r\n" + //
                                                "En las ocasiones en las que el Usuario deba o pueda facilitar sus datos a través de formularios para\r\n"
                                                + //
                                                "realizar consultas, solicitar información o por motivos relacionados con el contenido del Sitio Web, se le\r\n"
                                                + //
                                                "informará en caso de que la cumplimentación de alguno de ellos sea obligatoria debido a que los mismos\r\n"
                                                + //
                                                "sean imprescindibles para el correcto desarrollo de la operación realizada.\r");

                four = new H3("4. FINALIDAD DEL TRATAMIENTO DE LOS DATOS PERSONALES");
                four.getStyle().set("font-weight", "bold");
                subFour = new H5(
                                "Los datos personales del Usuario se recaban con la finalidad de poder prestar los servicios de StarPhone,\r\n"
                                                + //
                                                "entre los que se encuentran: el mantenimiento de la relación contractual con el Usuario; el envío de\r\n"
                                                + //
                                                "información comercial y/o promocional; la realización de estudios estadísticos; el tratamiento de\r\n"
                                                + //
                                                "solicitudes de información; la tramitación de encargos; o la gestión de incidencias.");

                five = new H3("5. PLAZOS DE CONSERVACIÓN DE LOS DATOS PERSONALES");
                five.getStyle().set("font-weight", "bold");
                subFive = new H5(
                                "Los datos personales se conservarán mientras se mantenga la relación comercial o durante el tiempo\r\n"
                                                + //
                                                "necesario para cumplir con las obligaciones legales y no se solicite su supresión por el interesado. Los\r\n"
                                                + //
                                                "datos no se cederán a terceros salvo en los casos en que exista una obligación legal y los trataremos\r\n"
                                                + //
                                                "en base a su consentimiento o ejecución de un contrato.");

                six = new H3("6. DESTINATARIOS DE LOS DATOS PERSONALES");
                six.getStyle().set("font-weight", "bold");
                subSix = new H5(
                                "Los datos personales del Usuario podrán ser compartidos con los siguientes destinatarios o\r\n"
                                                + //
                                                "categorías de destinatarios:\r\n" + //
                                                "*** Los proveedores de servicios que prestan servicios para StarPhone, tales como, servicios de\r\n"
                                                + //
                                                "alojamiento de sitios web, servicios de mensajería instantánea, servicios de envío de correo\r\n"
                                                + //
                                                "electrónico, servicios de análisis de la web, etc. En este sentido, los proveedores de servicios\r\n"
                                                + //
                                                "que tengan acceso a los datos personales necesarios para la prestación de sus servicios, no\r\n"
                                                + //
                                                "los utilizarán para ninguna otra finalidad y StarPhone se asegurará de que todos los\r\n"
                                                + //
                                                "proveedores de servicios con acceso a datos personales están sujetos a las mismas\r\n"
                                                + //
                                                "obligaciones de privacidad que StarPhone.");

                seven = new H3("7. SECRETISMO Y SEGURIDAD DE LOS DATOS PERSONALES");
                seven.getStyle().set("font-weight", "bold");
                subSeven = new H5(
                                "StarPhone se compromete a adoptar las medidas técnicas y organizativas necesarias, según el nivel de\r\n"
                                                + //
                                                "seguridad adecuado al riesgo de los datos recogidos, de forma que se garantice la seguridad de los datos\r\n"
                                                + //
                                                "de carácter personal y se evite la destrucción, pérdida o alteración accidental o ilícita de datos personales\r\n"
                                                + //
                                                "transmitidos, conservados o tratados de otra forma, o la comunicación o acceso no autorizados a dichos\r\n"
                                                + //
                                                "datos.\r\n" + //
                                                "El Sitio Web cuenta con un certificado SSL (Secure Socket Layer), que asegura que los datos personales se\r\n"
                                                + //
                                                "transmiten de forma segura y confidencial, al ser la transmisión de los datos entre el servidor y el Usuario,\r\n"
                                                + //
                                                "y en retroalimentación, totalmente cifrada o encriptada.\r\n" + //
                                                "Sin embargo, debido a que StarPhone no puede garantizar la inexpugnabilidad de internet ni la ausencia\r\n"
                                                + //
                                                "total de hackers u otros que accedan de modo fraudulento a los datos personales, el Responsable del\r\n"
                                                + //
                                                "tratamiento se compromete a comunicar al Usuario sin dilación indebida cuando ocurra una violación de la\r\n"
                                                + //
                                                "seguridad de los datos personales que sea probable que entrañe un alto riesgo para los derechos y\r\n"
                                                + //
                                                "libertades de las personas físicas. Siguiendo lo establecido en el artículo 4 del RGPD, se entiende por\r\n"
                                                + //
                                                "violación de la seguridad de los datos personales toda violación de la seguridad que ocasione la\r\n"
                                                + //
                                                "destrucción, pérdida o alteración accidental o ilícita de datos personales transmitidos, conservados o\r\n"
                                                + //
                                                "tratados de otra forma, o la comunicación o acceso no autorizados a dichos datos.\r\n"
                                                + //
                                                "Los datos personales serán tratados como confidenciales por el Responsable del tratamiento, quien se\r\n"
                                                + //
                                                "compromete a informar de y a garantizar por medio de una obligación legal o contractual que dicha\r\n"
                                                + //
                                                "confidencialidad sea respetada por sus empleados, asociados, y toda persona a la cual le haga accesible la\r\n"
                                                + //
                                                "información");

                eight = new H3("8. DERECHOS DERIVADOS DEL TRATAMIENTO DE LOS DATOS PERSONALES");
                eight.getStyle().set("font-weight", "bold");
                subEightOne = new H5(
                                "El Usuario tiene sobre StarPhone y podrá, por tanto, ejercer frente al Responsable del tratamiento los\r\n"
                                                + //
                                                "siguientes derechos reconocidos en el RGPD y la LOPD-GDD:\r\n");
                subEightTwo = new H5(
                                "*** Derecho de acceso: Es el derecho del Usuario a obtener confirmación de si StarPhone está\r\n"
                                                + //
                                                "tratando o no sus datos personales y, en caso afirmativo, obtener información sobre sus datos\r\n"
                                                + //
                                                "concretos de carácter personal y del tratamiento que StarPhone haya realizado o realice, así\r\n"
                                                + //
                                                "como, entre otra, de la información disponible sobre el origen de dichos datos y los\r\n"
                                                + //
                                                "destinatarios de las comunicaciones realizadas o previstas de los mismos.\r\n");
                subEightThree = new H5(
                                "*** Derecho de rectificación: Es el derecho del Usuario a que se modifiquen sus datos\r\n"
                                                + //
                                                "personales que resulten ser inexactos o, teniendo en cuenta los fines del tratamiento, incompletos.\r\n"
                                                + //
                                                "Sólo podrá satisfacerse en relación a aquella información que se encuentre bajo el control de\r\n"
                                                + //
                                                "StarPhone, por ejemplo, eliminar comentarios publicados en la propia página, imágenes o\r\n"
                                                + //
                                                "contenido web donde consten datos de carácter personal del Usuario.\r\n");
                subEightFour = new H5(
                                "*** Derecho a la limitación del tratamiento: Es el derecho a que se limiten los fines del\r\n"
                                                + //
                                                "tratamiento previstos de forma original por el Responsable del tratamiento.\r\n"
                                                + //
                                                "Cuando se haya obtenido la limitación del tratamiento en virtud de este derecho, se podrá\r\n"
                                                + //
                                                "tratar los datos, a excepción de su conservación, sólo con el consentimiento del Usuario o para\r\n"
                                                + //
                                                "la formulación, el ejercicio o la defensa de reclamaciones, o con miras a la protección de los\r\n"
                                                + //
                                                "derechos de otra persona física o jurídica o por razones de interés público importante de la\r\n"
                                                + //
                                                "Unión Europea o de un determinado Estado miembro.\r\n");

                nine = new H3("9. ACEPTACIÓN Y CAMBIOS EN ESTA POLÍTICA DE PRIVACIDAD");
                nine.getStyle().set("font-weight", "bold");
                subNine = new H5(
                                "Es necesario que el Usuario haya leído y esté conforme con las condiciones sobre la protección de datos\r\n"
                                                + //
                                                "de carácter personal contenidas en esta Política de Privacidad, así como que acepte el tratamiento de sus\r\n"
                                                + //
                                                "datos personales para que el Responsable del tratamiento pueda proceder al mismo en la forma, durante\r\n"
                                                + //
                                                "los plazos y para las finalidades indicadas. El uso del Sitio Web implicará la aceptación de la Política de\r\n"
                                                + //
                                                "Privacidad del mismo.\r\n" + //
                                                "StarPhone se reserva el derecho a modificar su Política de Privacidad, de acuerdo a su propio criterio, o\r\n"
                                                + //
                                                "motivado por un cambio legislativo, jurisprudencial o doctrinal de la Agencia Española de Protección de\r\n"
                                                + //
                                                "Datos. Los cambios o actualizaciones de esta Política de Privacidad no serán notificados de forma explícita\r\n"
                                                + //
                                                "al Usuario. Se recomienda al Usuario consultar esta página de forma periódica para estar al tanto de los\r\n"
                                                + //
                                                "últimos cambios o actualizaciones.\r\n" + //
                                                "Esta Política de Privacidad fue actualizada para adaptarse al Reglamento (UE) 2016/679 del Parlamento\r\n"
                                                + //
                                                "Europeo y del Consejo, de 27 de abril de 2016, relativo a la protección de las personas físicas en lo que\r\n"
                                                + //
                                                "respecta al tratamiento de datos personales y a la libre circulación de estos datos (RGPD) y a la Ley\r\n"
                                                + //
                                                "Orgánica 3/2018, de 5 de diciembre, de Protección de Datos Personales y garantía de los derechos\r\n"
                                                + //
                                                "digitales.\r\n");

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
                titleDiv.setHeight("80px");
                titleDiv.setJustifyContentMode(JustifyContentMode.CENTER);
                titleDiv.setAlignItems(Alignment.CENTER);
                titleDiv.getStyle().set("border-radius", "12px 12px 0 0");
                titleDiv.getStyle().set("background-color", "rgb(135, 206, 235)");
                titleRegister = new H3("Política de Cookies");
                titleRegister.getStyle().set("font-size", "38px");
                titleRegister.getStyle().set("color", "white");
                titleDiv.add(titleRegister);
                container.add(titleDiv);

                layoutTextCookies = new VerticalLayout();
                layoutTextCookies.add(Welcome, Welcome1,
                                one, oneTwo, subOneOne, subOneTwo, subOneThree, subOneFour, subOneFive,
                                two, subTwo, three, subThree, four, subFour, five, subFive,
                                six, subSix, seven, subSeven, eight, subEightOne, subEightTwo,
                                subEightThree, subEightFour, nine, subNine);
                bodyDiv = new Scroller(layoutTextCookies);
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