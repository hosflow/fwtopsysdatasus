
package org.hl7.v3;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ParameterizedDataTypeParametricProbabilityDistribution.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ParameterizedDataTypeParametricProbabilityDistribution">
 *   &lt;restriction base="{urn:hl7-org:v3}cs">
 *     &lt;enumeration value="PPD&lt;T>"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ParameterizedDataTypeParametricProbabilityDistribution")
@XmlEnum
public enum ParameterizedDataTypeParametricProbabilityDistribution {

    @XmlEnumValue("PPD<T>")
    PPD_T("PPD<T>");
    private final String value;

    ParameterizedDataTypeParametricProbabilityDistribution(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ParameterizedDataTypeParametricProbabilityDistribution fromValue(String v) {
        for (ParameterizedDataTypeParametricProbabilityDistribution c: ParameterizedDataTypeParametricProbabilityDistribution.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
