package core.tests.com;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Assert;

import org.testng.annotations.Test;

public class WebServiceTest {

	@Test
	public void testForWS()throws Exception {

		SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
				.newInstance();
		SOAPConnection soapConnection = soapConnectionFactory
				.createConnection();

		// Send SOAP Message to SOAP Server
		String url = "http://helpqaspb.com:8080/ws/";

		String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "
				+ "xmlns:gs=\"http://spring.io/guides/gs-producing-web-service\"> <soapenv:Header/>"
				+ "<soapenv:Body>"
                  + "<gs:getCountryRequest>"
                      + "<gs:name>Spain</gs:name>"
                      + "</gs:getCountryRequest>"
                  + "</soapenv:Body>" + "</soapenv:Envelope>";
		SOAPMessage soapResponse = soapConnection.call(
				getSoapMessageFromString(xml), url);

		// Process the SOAP Response
		String responce = printSOAPResponse(soapResponse);
		//Check responce
		Assert.assertTrue(responce.contains("<ns2:currency>EUR</ns2:currency>"));
		Assert.assertTrue(responce.contains("<ns2:population>46704314</ns2:population>"));


	}

	public static SOAPMessage getSoapMessageFromString(String xml)
			throws SOAPException, IOException {
		MessageFactory factory = MessageFactory.newInstance();
		SOAPMessage message = factory
				.createMessage(
						new MimeHeaders(),
						new ByteArrayInputStream(xml.getBytes(Charset
								.forName("UTF-8"))));
		return message;
	}

	/**
	 * Method used to print the SOAP Response
	 */
	private static String printSOAPResponse(SOAPMessage soapResponse)
			throws Exception {
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.print("\nResponse SOAP Message = ");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);

		StringWriter writer = new StringWriter();
        StreamResult result1 = new StreamResult(writer);
        TransformerFactory tFactory = TransformerFactory.newInstance();
        Transformer transformer1 = tFactory.newTransformer();
        transformer1.transform(sourceContent,result1);
        return writer.toString();
	}

}

                  