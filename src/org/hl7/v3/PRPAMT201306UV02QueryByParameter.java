
package org.hl7.v3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de PRPA_MT201306UV02.QueryByParameter complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conte�do esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="PRPA_MT201306UV02.QueryByParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:hl7-org:v3}InfrastructureRootElements"/>
 *         &lt;element name="queryId" type="{urn:hl7-org:v3}II"/>
 *         &lt;element name="statusCode" type="{urn:hl7-org:v3}CS"/>
 *         &lt;element name="modifyCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         &lt;element name="responseElementGroupId" type="{urn:hl7-org:v3}II" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="responseModalityCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         &lt;element name="responsePriorityCode" type="{urn:hl7-org:v3}CS" minOccurs="0"/>
 *         &lt;element name="initialQuantity" type="{urn:hl7-org:v3}INT" minOccurs="0"/>
 *         &lt;element name="initialQuantityCode" type="{urn:hl7-org:v3}CE" minOccurs="0"/>
 *         &lt;element name="executionAndDeliveryTime" type="{urn:hl7-org:v3}TS" minOccurs="0"/>
 *         &lt;element name="matchCriterionList" type="{urn:hl7-org:v3}PRPA_MT201306UV02.MatchCriterionList" minOccurs="0"/>
 *         &lt;element name="parameterList" type="{urn:hl7-org:v3}PRPA_MT201306UV02.ParameterList"/>
 *         &lt;element name="sortControl" type="{urn:hl7-org:v3}PRPA_MT201306UV02.SortControl" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:hl7-org:v3}InfrastructureRootAttributes"/>
 *       &lt;attribute name="nullFlavor" type="{urn:hl7-org:v3}NullFlavor" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PRPA_MT201306UV02.QueryByParameter", propOrder = {
    "realmCode",
    "typeId",
    "templateId",
    "queryId",
    "statusCode",
    "modifyCode",
    "responseElementGroupId",
    "responseModalityCode",
    "responsePriorityCode",
    "initialQuantity",
    "initialQuantityCode",
    "executionAndDeliveryTime",
    "matchCriterionList",
    "parameterList",
    "sortControl"
})
public class PRPAMT201306UV02QueryByParameter {

    protected List<CS> realmCode;
    protected II typeId;
    protected List<II> templateId;
    @XmlElement(required = true)
    protected II queryId;
    @XmlElement(required = true)
    protected CS statusCode;
    protected CS modifyCode;
    protected List<II> responseElementGroupId;
    protected CS responseModalityCode;
    protected CS responsePriorityCode;
    protected INT initialQuantity;
    protected CE initialQuantityCode;
    protected TS executionAndDeliveryTime;
    @XmlElementRef(name = "matchCriterionList", namespace = "urn:hl7-org:v3", type = JAXBElement.class, required = false)
    protected JAXBElement<PRPAMT201306UV02MatchCriterionList> matchCriterionList;
    @XmlElement(required = true)
    protected PRPAMT201306UV02ParameterList parameterList;
    @XmlElement(nillable = true)
    protected List<PRPAMT201306UV02SortControl> sortControl;
    @XmlAttribute(name = "nullFlavor")
    protected List<String> nullFlavor;

    /**
     * Gets the value of the realmCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the realmCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRealmCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CS }
     * 
     * 
     */
    public List<CS> getRealmCode() {
        if (realmCode == null) {
            realmCode = new ArrayList<CS>();
        }
        return this.realmCode;
    }

    /**
     * Obt�m o valor da propriedade typeId.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getTypeId() {
        return typeId;
    }

    /**
     * Define o valor da propriedade typeId.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setTypeId(II value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the templateId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the templateId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemplateId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getTemplateId() {
        if (templateId == null) {
            templateId = new ArrayList<II>();
        }
        return this.templateId;
    }

    /**
     * Obt�m o valor da propriedade queryId.
     * 
     * @return
     *     possible object is
     *     {@link II }
     *     
     */
    public II getQueryId() {
        return queryId;
    }

    /**
     * Define o valor da propriedade queryId.
     * 
     * @param value
     *     allowed object is
     *     {@link II }
     *     
     */
    public void setQueryId(II value) {
        this.queryId = value;
    }

    /**
     * Obt�m o valor da propriedade statusCode.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getStatusCode() {
        return statusCode;
    }

    /**
     * Define o valor da propriedade statusCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setStatusCode(CS value) {
        this.statusCode = value;
    }

    /**
     * Obt�m o valor da propriedade modifyCode.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getModifyCode() {
        return modifyCode;
    }

    /**
     * Define o valor da propriedade modifyCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setModifyCode(CS value) {
        this.modifyCode = value;
    }

    /**
     * Gets the value of the responseElementGroupId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseElementGroupId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseElementGroupId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link II }
     * 
     * 
     */
    public List<II> getResponseElementGroupId() {
        if (responseElementGroupId == null) {
            responseElementGroupId = new ArrayList<II>();
        }
        return this.responseElementGroupId;
    }

    /**
     * Obt�m o valor da propriedade responseModalityCode.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getResponseModalityCode() {
        return responseModalityCode;
    }

    /**
     * Define o valor da propriedade responseModalityCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setResponseModalityCode(CS value) {
        this.responseModalityCode = value;
    }

    /**
     * Obt�m o valor da propriedade responsePriorityCode.
     * 
     * @return
     *     possible object is
     *     {@link CS }
     *     
     */
    public CS getResponsePriorityCode() {
        return responsePriorityCode;
    }

    /**
     * Define o valor da propriedade responsePriorityCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CS }
     *     
     */
    public void setResponsePriorityCode(CS value) {
        this.responsePriorityCode = value;
    }

    /**
     * Obt�m o valor da propriedade initialQuantity.
     * 
     * @return
     *     possible object is
     *     {@link INT }
     *     
     */
    public INT getInitialQuantity() {
        return initialQuantity;
    }

    /**
     * Define o valor da propriedade initialQuantity.
     * 
     * @param value
     *     allowed object is
     *     {@link INT }
     *     
     */
    public void setInitialQuantity(INT value) {
        this.initialQuantity = value;
    }

    /**
     * Obt�m o valor da propriedade initialQuantityCode.
     * 
     * @return
     *     possible object is
     *     {@link CE }
     *     
     */
    public CE getInitialQuantityCode() {
        return initialQuantityCode;
    }

    /**
     * Define o valor da propriedade initialQuantityCode.
     * 
     * @param value
     *     allowed object is
     *     {@link CE }
     *     
     */
    public void setInitialQuantityCode(CE value) {
        this.initialQuantityCode = value;
    }

    /**
     * Obt�m o valor da propriedade executionAndDeliveryTime.
     * 
     * @return
     *     possible object is
     *     {@link TS }
     *     
     */
    public TS getExecutionAndDeliveryTime() {
        return executionAndDeliveryTime;
    }

    /**
     * Define o valor da propriedade executionAndDeliveryTime.
     * 
     * @param value
     *     allowed object is
     *     {@link TS }
     *     
     */
    public void setExecutionAndDeliveryTime(TS value) {
        this.executionAndDeliveryTime = value;
    }

    /**
     * Obt�m o valor da propriedade matchCriterionList.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201306UV02MatchCriterionList }{@code >}
     *     
     */
    public JAXBElement<PRPAMT201306UV02MatchCriterionList> getMatchCriterionList() {
        return matchCriterionList;
    }

    /**
     * Define o valor da propriedade matchCriterionList.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PRPAMT201306UV02MatchCriterionList }{@code >}
     *     
     */
    public void setMatchCriterionList(JAXBElement<PRPAMT201306UV02MatchCriterionList> value) {
        this.matchCriterionList = value;
    }

    /**
     * Obt�m o valor da propriedade parameterList.
     * 
     * @return
     *     possible object is
     *     {@link PRPAMT201306UV02ParameterList }
     *     
     */
    public PRPAMT201306UV02ParameterList getParameterList() {
        return parameterList;
    }

    /**
     * Define o valor da propriedade parameterList.
     * 
     * @param value
     *     allowed object is
     *     {@link PRPAMT201306UV02ParameterList }
     *     
     */
    public void setParameterList(PRPAMT201306UV02ParameterList value) {
        this.parameterList = value;
    }

    /**
     * Gets the value of the sortControl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sortControl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSortControl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PRPAMT201306UV02SortControl }
     * 
     * 
     */
    public List<PRPAMT201306UV02SortControl> getSortControl() {
        if (sortControl == null) {
            sortControl = new ArrayList<PRPAMT201306UV02SortControl>();
        }
        return this.sortControl;
    }

    /**
     * Gets the value of the nullFlavor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nullFlavor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNullFlavor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNullFlavor() {
        if (nullFlavor == null) {
            nullFlavor = new ArrayList<String>();
        }
        return this.nullFlavor;
    }

}
