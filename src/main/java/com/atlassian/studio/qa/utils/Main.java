
package com.atlassian.studio.qa.utils;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import javax.ws.rs.core.UriBuilder;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import org.slf4j.LoggerFactory;


public class Main {
    
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static final URI BASE_URI = UriBuilder.fromUri("http://localhost/").port(9998).build();

    protected static SelectorThread startServer() throws IOException {
        final Map<String, String> initParams = new HashMap<String, String>();

        initParams.put("com.sun.jersey.config.property.packages", 
                "com.atlassian.studio.qa.utils");

        log.info("Starting grizzly...");
        SelectorThread threadSelector = GrizzlyWebContainerFactory.create(BASE_URI, initParams);
        return threadSelector;
    }
    
    public static void main(String[] args) throws IOException, InterruptedException {
        SelectorThread threadSelector = startServer();
        log.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nWill quit in 2 minutes...",
                BASE_URI));
        for (int i = 0; i < 60; i++)
        {
            log.info("I am just sleeping for 5 seconds...");
            TimeUnit.SECONDS.sleep(5);
        }

        threadSelector.stopEndpoint();
    }    
}
