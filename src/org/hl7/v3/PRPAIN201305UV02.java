
package org.hl7.v3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de anonymous complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{urn:hl7-org:v3}PRPA_IN201305UV02.MCCI_MT000100UV01.Message">
 *       &lt;attribute name="ITSVersion" use="required" type="{http://www.w3.org/2001/XMLSchema}string" fixed="XML_1.0" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "PRPA_IN201305UV02")
public class PRPAIN201305UV02
    extends PRPAIN201305UV02MCCIMT000100UV01Message
{

    @XmlAttribute(name = "ITSVersion", required = true)
    protected String itsVersion;

    /**
     * Obt�m o valor da propriedade itsVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getITSVersion() {
        if (itsVersion == null) {
            return "XML_1.0";
        } else {
            return itsVersion;
        }
    }

    /**
     * Define o valor da propriedade itsVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setITSVersion(String value) {
        this.itsVersion = value;
    }

}
