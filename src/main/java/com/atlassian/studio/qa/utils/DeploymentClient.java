package com.atlassian.studio.qa.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeploymentClient
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        final Logger log = LoggerFactory.getLogger(DeploymentClient.class);

        Client c = Client.create();
        WebResource r = c.resource("http://localhost:9998/myresource");

        ClientResponse response = r.get(ClientResponse.class);
        log.debug("Response status=" + response.getStatus());
        log.debug("Content-Type=" + response.getHeaders().get("Content-Type"));
        String entity = response.getEntity(String.class);
        log.debug("Server responded: " + entity);
    }
}
