package io.github.tavernaextras.biocatalogue.integration.service_panel;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Icon;
import javax.wsdl.Operation;
import javax.wsdl.WSDLException;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import io.github.tavernaextras.biocatalogue.model.SoapOperationIdentity;
import org.apache.taverna.activities.wsdl.WSDLActivityHealthChecker;
import org.apache.taverna.servicedescriptions.AbstractConfigurableServiceProvider;
import org.apache.taverna.servicedescriptions.impl.ServiceDescriptionRegistryImpl;
import org.apache.taverna.wsdl.parser.UnknownOperationException;
import org.apache.taverna.wsdl.parser.WSDLParser;

/**
 * Service provider for WSDL operations added to the Service Panel through the
 * BioCatalogue perspective.
 * 
 * @author Alex Nenadic
 */
public class BioCatalogueWSDLOperationServiceProvider extends
	AbstractConfigurableServiceProvider<WSDLOperationFromBioCatalogueServiceDescription> {

	public BioCatalogueWSDLOperationServiceProvider(
			WSDLOperationFromBioCatalogueServiceDescription wsdlOperationDescription) {
		super(wsdlOperationDescription);
	}

	public BioCatalogueWSDLOperationServiceProvider() {
		super(new WSDLOperationFromBioCatalogueServiceDescription(new SoapOperationIdentity("", "", "")));
	}
	
	public static final String PROVIDER_NAME = "Service Catalogue - selected services";
	  
	private static final URI providerId = URI
	.create("http://taverna.sf.net/2010/service-provider/servicecatalogue/wsdl");
	
	private static Logger logger = Logger.getLogger(BioCatalogueWSDLOperationServiceProvider.class);

	@Override
	protected List<? extends Object> getIdentifyingData() {
		return getConfiguration().getIdentifyingData();
	}

	@Override
	public void findServiceDescriptionsAsync(
			FindServiceDescriptionsCallBack callBack) {
	    callBack.status("Starting Service Catalogue WSDL Service Provider");
		registerWSDLOperation(getConfiguration(), callBack);
	}

	@Override
	public Icon getIcon() {
		return getConfiguration().getIcon();
	}

	@Override
	public String getId() {
		return providerId.toString();
	}

	@Override
	public String getName() {
		return "Service Catalogue WSDL";
	}
	
	@Override
	public String toString() {
		return "Service Catalogue WSDL service " + getConfiguration().getName();
	}
	
	public static boolean registerWSDLOperation(
			WSDLOperationFromBioCatalogueServiceDescription wsdlOperationDescription,
			FindServiceDescriptionsCallBack callBack)	{
		
		if (callBack == null) {
			// We are not adding service through Taverna service registry's callback and
			// findServiceDescriptionsAsync() -
			// we are adding directly from the BioCatalogue perspective.
			ServiceDescriptionRegistryImpl serviceDescriptionRegistry = ServiceDescriptionRegistryImpl
					.getInstance();
			serviceDescriptionRegistry
					.addServiceDescriptionProvider(new BioCatalogueWSDLOperationServiceProvider(
							wsdlOperationDescription));
			return true;
		} else {
			// Add the WSDL operation to the Service Panel through the callback
			callBack.partialResults(Collections
					.singletonList(wsdlOperationDescription));
			callBack.finished();
			return (true);
		}
	}

	/**
	 * Adds a SOAP/WSDL service and all of its operations into the Taverna's Service Panel.
	 */
	public static boolean registerWSDLService(String wsdlURL, FindServiceDescriptionsCallBack callBack)
	{
		String errorMessage = null;
		Exception ex = null;
		
		List<Operation> operations = null;
		List<WSDLOperationFromBioCatalogueServiceDescription> items = null;
		
		// Do the same thing as in the WSDL service provider
		WSDLParser parser = null;
		try {
			parser = new WSDLParser(wsdlURL);
			operations = parser.getOperations();
			items = new ArrayList<WSDLOperationFromBioCatalogueServiceDescription>();
			for (Operation operation : operations) {
				WSDLOperationFromBioCatalogueServiceDescription item;
				try {
					String operationName = operation.getName();
					String operationDesc = parser.getOperationDocumentation(operationName);
					String use = parser.getUse(operationName);
					String style = parser.getStyle();
					if (!WSDLActivityHealthChecker.checkStyleAndUse(style, use)) {
						logger.warn("Unsupported style and use combination " + style + "/" + use + " for operation " + operationName + " from " + wsdlURL);
						continue;
					}
					item = new WSDLOperationFromBioCatalogueServiceDescription(wsdlURL, operationName, operationDesc);
					items.add(item);					
				} catch (UnknownOperationException e) {
					errorMessage = "Encountered an unexpected operation name:"
							+ operation.getName();
					ex = e;
				}
			}
		} catch (ParserConfigurationException e) {
			errorMessage = "Error configuring the WSDL parser";
			ex = e;
		} catch (WSDLException e) {
			errorMessage = "There was an error with the wsdl: " + wsdlURL;
			ex = e;
		} catch (IOException e) {
			errorMessage = "There was an IO error parsing the wsdl: " + wsdlURL
					+ " Possible reason: the wsdl location was incorrect.";
			ex = e;
		} catch (SAXException e) {
			errorMessage = "There was an error with the XML in the wsdl: "
					+ wsdlURL;
			ex = e;
		} catch (IllegalArgumentException e) { // a problem with the wsdl url
			errorMessage = "There was an error with the wsdl: " + wsdlURL + " "
					+ "Possible reason: the wsdl location was incorrect.";
			ex = e;
		} catch (Exception e) { // anything else we did not expect
			errorMessage = "There was an error with the wsdl: " + wsdlURL;
			ex = e;
		}
		
		if (callBack == null) {
			if (errorMessage != null){
				logger.error(errorMessage, ex);
				return false;
			}
			else{
				// We are not adding service through Taverna service registry's callback and
				// findServiceDescriptionsAsync() -
				// we are adding directly from the BioCatalogue perspective.
				ServiceDescriptionRegistryImpl serviceDescriptionRegistry = ServiceDescriptionRegistryImpl
						.getInstance();
				for (WSDLOperationFromBioCatalogueServiceDescription item : items) {
					serviceDescriptionRegistry
							.addServiceDescriptionProvider(new BioCatalogueWSDLOperationServiceProvider(
									item));
				}
				return true;
			}
		} else {
			if (errorMessage != null){
				callBack.fail(errorMessage, ex);
				return false;
			}
			else{
				callBack.status("Found " + operations.size() + " WSDL operations of service "
						+ wsdlURL);
				callBack.partialResults(items);
				callBack.finished();
				return true;
			}
		}   
	}
}
