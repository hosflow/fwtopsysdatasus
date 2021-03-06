
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Grandparent.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="Grandparent">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="GRPRN"/>
 *     &lt;enumeration value="GRFTH"/>
 *     &lt;enumeration value="GRMTH"/>
 *     &lt;enumeration value="MGRFTH"/>
 *     &lt;enumeration value="MGRMTH"/>
 *     &lt;enumeration value="MGRPRN"/>
 *     &lt;enumeration value="PGRFTH"/>
 *     &lt;enumeration value="PGRMTH"/>
 *     &lt;enumeration value="PGRPRN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "Grandparent")
@XmlEnum
public enum Grandparent {

    GRPRN,
    GRFTH,
    GRMTH,
    MGRFTH,
    MGRMTH,
    MGRPRN,
    PGRFTH,
    PGRMTH,
    PGRPRN;

    public String value() {
        return name();
    }

    public static Grandparent fromValue(String v) {
        return valueOf(v);
    }

}
