/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.jsexample;

import net.sf.arbocdi.jsexample.test_objects.Ship;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import lombok.extern.slf4j.Slf4j;
import net.sf.arbocdi.javaserializer.SerializerCollection;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arbocdi
 */
@Slf4j
public class Launcher {

    public static void main(String[] args) {
        OutputStream out = null;
        InputStream in = null;
        try {
            initLogger();
            //reading input file
            Serializer persister = new Persister();
            Ship ship = persister.read(Ship.class, new File(args[0]));
            log.info(String.format("Object from xml\n %s", ship));
            //serializing to binary data
            SerializerCollection collection = SerializerCollection.getDefault();
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(args[1] + ".bin")));
            collection.toStream(collection, ship, out);
            out.close();
            //deserializing object
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(args[1] + ".bin")));
            Ship ship2 = (Ship) collection.fromStream(collection, in, Ship.class, null);
            in.close();
            log.info(String.format("Object from binary\n %s", ship2));
            //print results
            log.info(String.format("Initial and serialized objects match %b", ship.equals(ship2)));

        } catch (Exception ex) {
            log.warn("Something went wrong...", ex);
        } finally {
            log.info("Closing streams");
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
            log.info("Stopping logger");
            LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
            context.stop();
        }
    }

    public static void initLogger() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        InputStream in = null;
        try {
            in = Launcher.class.getResourceAsStream("/logger.xml");
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(context);
            // Call context.reset() to clear any previous configuration, e.g. default 
            // configuration. For multi-step configuration, omit calling context.reset().
            context.reset();
            configurator.doConfigure(in);
        } catch (Exception je) {
            System.out.println(je);
            je.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception ex) {
                }
            }
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);
        log.info("Logger initializated");
    }
}
