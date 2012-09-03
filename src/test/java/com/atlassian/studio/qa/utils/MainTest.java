package com.atlassian.studio.qa.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import junit.framework.TestCase;

public class MainTest extends TestCase
{

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    private SelectorThread threadSelector;

    private WebResource r;

    public MainTest(String testName)
    {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        threadSelector = Main.startServer();

        Client c = Client.create();
        r = c.resource(Main.BASE_URI);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();

        threadSelector.stopEndpoint();
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    public void _testMyResource()
    {
        String responseMsg = r.path("myresource").get(String.class);
        assertEquals("Got it!", responseMsg);
    }

    /**sdfsafsafsdfsdfsd
     * Test if a WADL document is available at the relative path
     * "application.wadl".
     * 
     * @throws InterruptedException
     */
    public void testApplicationWadl() throws InterruptedException
    {
        for (int i = 0; i < 1; i++)
        {
            log.info("I am just sleeping yeah yeah...again oh siesta time yeaaah");
            TimeUnit.SECONDS.sleep(5);
        }

        String serviceWadl = r.path("application.wadl").accept(MediaTypes.WADL).get(String.class);

        assertTrue(serviceWadl.length() > 0);
    }

    public void testRubbishGeneration()
    {
        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter("*.rubbish");
        File[] files = dir.listFiles(fileFilter);
        for (int i = 0; i < files.length; i++) {
           FileUtils.deleteQuietly(files[i]);
        }
        
        File file = new File(RandomStringUtils.randomAlphabetic(9) + ".rubbish");
        try
        {
            FileWriterWithEncoding fileWriterWithEncoding = new FileWriterWithEncoding(file, Charset.defaultCharset());
            for (int i = 0; i < 100; i++)
            {
                fileWriterWithEncoding.append(RandomStringUtils.randomAlphabetic(1024 * 1024));
            }
            fileWriterWithEncoding.close();
        } catch (IOException e)
        {
            log.error("Rubbish generation failed buddy, pls try again.", e);
        }
    }

    //super test dudes
    public void testRubbishGeneration2()
    {
        File dir = new File(".");
        FileFilter fileFilter = new WildcardFileFilter("*.rubbish");
        File[] files = dir.listFiles(fileFilter);
        for (int i = 0; i < files.length; i++) {
           FileUtils.deleteQuietly(files[i]);
        }
        
        File file = new File(RandomStringUtils.randomAlphabetic(9) + ".rubbish");
        try
        {
            FileWriterWithEncoding fileWriterWithEncoding = new FileWriterWithEncoding(file, Charset.defaultCharset());
            for (int i = 0; i < 100; i++)
            {
                fileWriterWithEncoding.append(RandomStringUtils.randomAlphabetic(1024 * 1024));
            }
            fileWriterWithEncoding.close();
        } catch (IOException e)
        {
            log.error("Rubbish generation failed dude.", e);
        }
    }




}
