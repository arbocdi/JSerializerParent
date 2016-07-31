/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.javaserializer;

import java.io.IOException;

/**
 *
 * @author root
 */
public class SerializationException extends IOException {

    public SerializationException(String msg) {
        super(msg);
    }

    public SerializationException(Exception ex) {
        super(ex);
    }
}
