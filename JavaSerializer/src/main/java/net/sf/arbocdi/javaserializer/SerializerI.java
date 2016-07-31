/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.javaserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 *
 * @author root
 */
public interface SerializerI {

    Object fromStream(SerializerI serializer,InputStream in,Class objectClass,Field field) throws IOException, SerializationException;

    Object toStream(SerializerI serializer,Object obj, OutputStream out) throws IOException, SerializationException;
}
