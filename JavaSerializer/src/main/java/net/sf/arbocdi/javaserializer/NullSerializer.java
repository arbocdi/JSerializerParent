package net.sf.arbocdi.javaserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

/**
 *
 * @author arbocdi
 */
public class NullSerializer implements SerializerI {
    
    public static final int NULL = 0x0000;
    public static final int NOT_NULL = 0x000F;
    
    
    
    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        int value = in.read() & 0x00ff;
        switch (value) {
            case NULL:
                return NULL;
            case NOT_NULL:
                return NOT_NULL;
            default:
                throw new SerializationException(String.format("Unexpected byte %s", SerializerUtils.byteToString((byte) value)));
        }
    }
    
    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        if (obj == null) {
            out.write(NULL);
            return NULL;
        } else {
            out.write(NOT_NULL);
            return NOT_NULL;
        }
    }
    
}
