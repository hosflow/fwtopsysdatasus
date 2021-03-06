
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ActInformationAccess.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ActInformationAccess">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="INFA"/>
 *     &lt;enumeration value="INFASO"/>
 *     &lt;enumeration value="INFAO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ActInformationAccess")
@XmlEnum
public enum ActInformationAccess {

    INFA,
    INFASO,
    INFAO;

    public String value() {
        return name();
    }

    public static ActInformationAccess fromValue(String v) {
        return valueOf(v);
    }

}
