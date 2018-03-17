////////////////////////////////////////////////////////////////////////////////
//
// Created by BBruhns on 17.03.2018.
//
// Copyright (c) 2006 - 2018 FORCAM GmbH. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

package mynamespace.web;

import org.apache.olingo.client.api.ODataClient;
import org.apache.olingo.client.api.communication.request.retrieve.EdmMetadataRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntitySetIteratorRequest;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientEntitySet;
import org.apache.olingo.client.api.domain.ClientServiceDocument;
import org.apache.olingo.client.core.ODataClientFactory;
import org.apache.olingo.commons.api.edm.Edm;
import org.apache.olingo.commons.api.edm.EdmComplexType;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmProperty;
import org.apache.olingo.commons.api.edm.EdmSchema;
import org.apache.olingo.commons.api.edm.FullQualifiedName;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Handler {

    // ------------------------------------------------------------------------
    // constants
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // members
    // ------------------------------------------------------------------------

    final ODataClient client = ODataClientFactory.getClient();

    // ------------------------------------------------------------------------
    // constructors
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // methods
    // ------------------------------------------------------------------------

    public void getData() {
        //Build the request URI - Execute the request - Browse the received data
        //For the next step, Olingo provides the class URIBuilder that allows to build a OData v4 compliant URI for data we want to get.
        // This class supports all features of OData v4. We leverage it all along the post to build our queries.

        String serviceRoot = "http://services.odata.org/V4/Northwind/Northwind.svc";
        URI customersUri = client.newURIBuilder(serviceRoot).appendEntitySetSegment("Customers").build();

        //Now we have the URI, we can execute the request using the client instance.
        //        ODataRetrieveResponse<ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity>> response = client.getRetrieveRequestFactory()
        //                                                                                                           .getEntitySetIteratorRequest(customersUri)
        //                                                                                                           .execute();

        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request = client.getRetrieveRequestFactory().getEntitySetIteratorRequest(customersUri);
        // odata4 sample/server limitation not handling metadata=full
        request.setAccept("application/json;odata.metadata=minimal");
//        ODataRetrieveResponse<ClientEntity> response = request.execute();

        //        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> iteratorRequest = response.getBody();

        //        URI absoluteUri = client.newURIBuilder(serviceUri).appendEntitySetSegment(entitySetName).build();
        //        ODataEntitySetIteratorRequest<ClientEntitySet, ClientEntity> request =
        //            client.getRetrieveRequestFactory().getEntitySetIteratorRequest(absoluteUri);
        //        // odata4 sample/server limitation not handling metadata=full
        //        request.setAccept("application/json;odata.metadata=minimal");
        //        ODataRetrieveResponse<ClientEntitySetIterator<ClientEntitySet, ClientEntity>> response = request.execute();
        //        ClientEntitySetIterator<ClientEntitySet, ClientEntity> iterator = response.getBody();
        //
        //        while (iterator.hasNext()) {
        //            ClientEntity ce = iterator.next();
        //            System.out.println("Manufacturer name: " + ce.getProperty("Name").getPrimitiveValue());
        //        }
    }

    public void retrieveServiceDocument() {
        //get the service document
        String serviceRoot = "http://services.odata.org/V4/Northwind/Northwind.svc";
        ODataServiceDocumentRequest req = client.getRetrieveRequestFactory().getServiceDocumentRequest(serviceRoot);
        ODataRetrieveResponse<ClientServiceDocument> res = req.execute();//ODataServiceDocument

        //From this response, we can get an instance of class ODataServiceDocument to actually get the metadata, as described below
        ClientServiceDocument serviceDocument = res.getBody();

        Collection<String> entitySetNames = serviceDocument.getEntitySetNames();
        Map<String, URI> entitySets = serviceDocument.getEntitySets();
        //        Map<String, URI> singletons = serviceDocument.getSingletons();
        //        Map<String, URI> functionImports = serviceDocument.getFunctionImports();
        URI productsUri = serviceDocument.getEntitySetURI("Products");

        //At this level, we can only have access to elements provided and their URIs but we can have details of their structures.
        // //If we want to do a deeper introspection, we need to use a metadata request, as described below:
        serviceRoot = "http://services.odata.org/V4/Northwind/Northwind.svc";
        EdmMetadataRequest request = client.getRetrieveRequestFactory().getMetadataRequest(serviceRoot);
        ODataRetrieveResponse<Edm> response = request.execute();

        //From the response, we get an instance of the class Edm that contains all the metadata. We can first begin to get the schemas, get all contained elements.
        Edm edm = response.getBody();

        List<EdmSchema> schemas = edm.getSchemas();
        for (EdmSchema schema : schemas) {
            String namespace = schema.getNamespace();
            for (EdmComplexType complexType : schema.getComplexTypes()) {
                FullQualifiedName name = complexType.getFullQualifiedName();
            }
            for (EdmEntityType entityType : schema.getEntityTypes()) {
                FullQualifiedName name = entityType.getFullQualifiedName();
            }
        }

        //We can get now details about the element itself. The following code gets details of the entity Customers:
        EdmEntityType customerType = edm.getEntityType(new FullQualifiedName("NorthwindModel", "Customer"));
        List<String> propertyNames = customerType.getPropertyNames();
        for (String propertyName : propertyNames) {
            EdmProperty property = customerType.getStructuralProperty(propertyName);
            FullQualifiedName typeName = property.getType().getFullQualifiedName();
        }
        //Now we have inspected the metadata of the service, we can actually get the corresponding data.
    }

    // ------------------------------------------------------------------------
    // getters/setters
    // ------------------------------------------------------------------------

}
