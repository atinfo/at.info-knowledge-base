
package com.tools.qaa.entities.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListBucketResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListBucketResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prefix" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Marker" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IsTruncated" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Contents" type="{http://doc.s3.amazonaws.com/2006-03-01}ContentsType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListBucketResultType", namespace = "http://doc.s3.amazonaws.com/2006-03-01", propOrder = {
    "name",
    "prefix",
    "marker",
    "isTruncated",
    "contents"
})
public class ListBucketResultType {

    @XmlElement(name = "Name", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String name;
    @XmlElement(name = "Prefix", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String prefix;
    @XmlElement(name = "Marker", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String marker;
    @XmlElement(name = "IsTruncated", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String isTruncated;
    @XmlElement(name = "Contents", namespace = "http://doc.s3.amazonaws.com/2006-03-01")
    protected List<ContentsType> contents;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the prefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the value of the prefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrefix(String value) {
        this.prefix = value;
    }

    /**
     * Gets the value of the marker property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarker() {
        return marker;
    }

    /**
     * Sets the value of the marker property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarker(String value) {
        this.marker = value;
    }

    /**
     * Gets the value of the isTruncated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTruncated() {
        return isTruncated;
    }

    /**
     * Sets the value of the isTruncated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTruncated(String value) {
        this.isTruncated = value;
    }

    /**
     * Gets the value of the contents property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contents property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentsType }
     * 
     * 
     */
    public List<ContentsType> getContents() {
        if (contents == null) {
            contents = new ArrayList<>();
        }
        return this.contents;
    }

}
