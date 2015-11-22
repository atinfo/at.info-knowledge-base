
package com.tools.qaa.entities.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ContentsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContentsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Key" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Generation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MetaGeneration">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="4"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LastModified" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ETag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Size" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Owner" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentsType", namespace = "http://doc.s3.amazonaws.com/2006-03-01", propOrder = {
    "key",
    "generation",
    "metaGeneration",
    "lastModified",
    "eTag",
    "size",
    "owner"
})
public class ContentsType {

    @XmlElement(name = "Key", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String key;
    @XmlElement(name = "Generation", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String generation;
    @XmlElement(name = "MetaGeneration", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String metaGeneration;
    @XmlElement(name = "LastModified", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String lastModified;
    @XmlElement(name = "ETag", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String eTag;
    @XmlElement(name = "Size", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String size;
    @XmlElement(name = "Owner", namespace = "http://doc.s3.amazonaws.com/2006-03-01", required = true)
    protected String owner;

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * Gets the value of the generation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneration() {
        return generation;
    }

    /**
     * Sets the value of the generation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneration(String value) {
        this.generation = value;
    }

    /**
     * Gets the value of the metaGeneration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetaGeneration() {
        return metaGeneration;
    }

    /**
     * Sets the value of the metaGeneration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetaGeneration(String value) {
        this.metaGeneration = value;
    }

    /**
     * Gets the value of the lastModified property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModified() {
        return lastModified;
    }

    /**
     * Sets the value of the lastModified property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModified(String value) {
        this.lastModified = value;
    }

    /**
     * Gets the value of the eTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETag() {
        return eTag;
    }

    /**
     * Sets the value of the eTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETag(String value) {
        this.eTag = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

}
