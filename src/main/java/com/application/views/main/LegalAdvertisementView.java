package com.application.views.main;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H3;
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
@PageTitle("Aviso Legal")
@Route(value = "/avisolegal")
public class LegalAdvertisementView extends VerticalLayout {

        header h;
        H3 titleRegister, GeneralCond, ResponWarranty, PropIndusIntel, LegisAplic, InfoLPD;
        H5 CondGenUso, RespGar1, RespGar2, RespGar3, RespGar4, RespGar5, PropIndInt, LegApli, LPD1, LPD2, LPD3, LPD4;
        HorizontalLayout titleDiv;
        VerticalLayout centerDiv, container, layoutTextAL;
        Scroller bodyDiv;
        footer f;

        public LegalAdvertisementView() {
                GeneralCond = new H3("CONDICIONES GENERALES DE USO");
                CondGenUso = new H5(
                                "Este sitio web (en adelante, \"el Sitio\") es propiedad de StarPhone, S.A. (en adelante,"
                                                +
                                                " \"StarPhone\"). El acceso y uso del Sitio están sujetos a las siguientes condiciones de uso y a todas las "
                                                +
                                                "leyes y regulaciones aplicables. Al acceder y utilizar el Sitio, usted acepta estas condiciones de uso "
                                                +
                                                "sin modificaciones. Si no están de acuerdo con estas condiciones de uso, no debe utilizar el Sitio.");
                ResponWarranty = new H3("RESPONSABILIDAD Y GARANTÍA DE GRUPO StarPhone");
                RespGar1 = new H5(
                                "StarPhone se reserva el derecho de modificar estas condiciones de uso en cualquier momento y "
                                                +
                                                "sin previo aviso. Su uso continuado del Sitio después de la publicación de cualquier modificación de "
                                                +
                                                "estas condiciones de uso constituirá su aceptación de dichas modificaciones.");
                RespGar2 = new H5(
                                "El contenido del Sitio se proporciona \"tal cual\" sin garantía de ningún tipo, " +
                                                "ya sea expresa o implícita. StarPhone no garantiza la exactitud, confiabilidad o integridad de cualquier "
                                                +
                                                "información, texto, gráficos, enlaces u otros elementos contenidos en el Sitio. StarPhone no será "
                                                +
                                                "responsable de ningún error u omisión en el contenido del Sitio.");
                RespGar3 = new H5(
                                "El uso del Sitio y del contenido del mismo es bajo su propia responsabilidad. " +
                                                "StarPhone no será responsable de ningún daño o perjuicio de cualquier tipo derivado del uso o la "
                                                +
                                                "imposibilidad de uso del Sitio o de cualquier sitio web vinculado a él.");
                RespGar4 = new H5(
                                "Este Sitio puede contener enlaces a sitios web de terceros. Estos enlaces se " +
                                                "proporcionan únicamente como una conveniencia y no implican la aprobación por parte de StarPhone de los "
                                                +
                                                "sitios web de terceros ni una responsabilidad por el contenido de dichos sitios web. StarPhone no controla "
                                                +
                                                "ni es responsable del contenido de los sitios web de terceros. El uso de cualquier sitio web de "
                                                +
                                                "terceros es bajo su propia responsabilidad.");
                RespGar5 = new H5(
                                "StarPhone se reserva el derecho de denegar el acceso al Sitio en cualquier momento " +
                                                "y sin previo aviso.");
                PropIndusIntel = new H3("PROPIEDAD INDUSTRIAL E INTELECTUAL");
                PropIndInt = new H5(
                                "El Sitio y todo el contenido, incluyendo texto, gráficos, imágenes y software, son "
                                                +
                                                "propiedad de StarPhone o de sus proveedores de contenido y están protegidos por las leyes de propiedad "
                                                +
                                                "intelectual de España y otras leyes y tratados internacionales. El contenido del Sitio no puede ser "
                                                +
                                                "copiado, reproducido, distribuido, transmitido, exhibido, publicado ni utilizado de ninguna otra forma "
                                                +
                                                "sin el consentimiento previo por escrito de StarPhone.");
                LegisAplic = new H3("LEGISLACIÓN APLICABLE");
                LegApli = new H5(
                                "Las presentes condiciones generales se regirán por la legislación española.");
                H3 InfoLPD = new H3("INFORMACIÓN SOBRE PROTECCIÓN DE DATOS");
                LPD1 = new H5(
                                "StarPhone se compromete a proteger la privacidad y la seguridad de sus clientes y a cumplir"
                                                +
                                                " con todas las leyes y regulaciones aplicables en materia de protección de datos personales.");
                LPD2 = new H5(
                                "StarPhone recopila y procesa ciertos datos personales de sus clientes con el fin " +
                                                "de proporcionar servicios financieros y realizar actividades comerciales. Estos datos pueden incluir "
                                                +
                                                "nombre, dirección, número de identificación, información de contacto y datos financieros. StarPhone "
                                                +
                                                "también puede recopilar y procesar datos personales de otras fuentes, como agencias de informes "
                                                +
                                                "crediticios y otras entidades financieras.");
                LPD3 = new H5(
                                "StarPhone solo utiliza los datos personales de sus clientes de acuerdo con los propósitos "
                                                +
                                                "para los que se han recopilado y en conformidad con las leyes y regulaciones aplicables. StarPhone también toma "
                                                +
                                                "medidas de seguridad razonables para proteger los datos personales de sus clientes contra el uso no autorizado,"
                                                +
                                                " la pérdida, el acceso no autorizado, la divulgación, la alteración y la destrucción.");
                LPD4 = new H5(
                                "Los clientes de StarPhone tienen ciertos derechos en relación con sus datos personales, "
                                                +
                                                "como el derecho a acceder a sus datos, solicitar una copia de los mismos, solicitar su rectificación o "
                                                +
                                                "eliminación y oponerse al tratamiento de sus datos. Los clientes también tienen el derecho a presentar una "
                                                +
                                                "queja ante la autoridad de protección de datos competente si creen que StarPhone ha violado sus derechos en "
                                                +
                                                "materia de protección de datos.");
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
                titleRegister = new H3("Aviso Legal");
                titleRegister.getStyle().set("font-size", "38px");
                titleRegister.getStyle().set("color", "white");
                titleDiv.add(titleRegister);
                container.add(titleDiv);

                layoutTextAL = new VerticalLayout();
                layoutTextAL.add(GeneralCond, CondGenUso, ResponWarranty,
                                RespGar1, RespGar2, RespGar3, RespGar4, RespGar5,
                                PropIndusIntel, PropIndInt, LegisAplic, LegApli,
                                InfoLPD, LPD1, LPD2, LPD3, LPD4);
                bodyDiv = new Scroller(layoutTextAL);
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
