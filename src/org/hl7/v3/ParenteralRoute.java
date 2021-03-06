
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ParenteralRoute.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ParenteralRoute">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="PARENTINJ"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ParenteralRoute")
@XmlEnum
public enum ParenteralRoute {

    PARENTINJ;

    public String value() {
        return name();
    }

    public static ParenteralRoute fromValue(String v) {
        return valueOf(v);
    }

}
