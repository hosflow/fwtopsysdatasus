
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de DoseLowDetectedIssueCode.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DoseLowDetectedIssueCode">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="DOSEL"/>
 *     &lt;enumeration value="DOSELINDA"/>
 *     &lt;enumeration value="DOSELINDSA"/>
 *     &lt;enumeration value="DOSELIND"/>
 *     &lt;enumeration value="DOSELINDW"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DoseLowDetectedIssueCode")
@XmlEnum
public enum DoseLowDetectedIssueCode {

    DOSEL,
    DOSELINDA,
    DOSELINDSA,
    DOSELIND,
    DOSELINDW;

    public String value() {
        return name();
    }

    public static DoseLowDetectedIssueCode fromValue(String v) {
        return valueOf(v);
    }

}
