
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de x_BasicConfidentialityKind.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="x_BasicConfidentialityKind">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="V"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "x_BasicConfidentialityKind")
@XmlEnum
public enum XBasicConfidentialityKind {

    N,
    R,
    V;

    public String value() {
        return name();
    }

    public static XBasicConfidentialityKind fromValue(String v) {
        return valueOf(v);
    }

}
