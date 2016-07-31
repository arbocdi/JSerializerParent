package net.sf.arbocdi.javaserializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import lombok.Data;

/**
 * Writes or reads the size of an array and then its contents.
 *
 * @author arbocdi
 */
@Data
public class ArraySerializer implements SerializerI {

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class clazz,Field field) throws IOException, SerializationException {
        Class componentClass = clazz.getComponentType();
        if (componentClass == null) {
            throw new SerializationException(String.format("Class %s is not supported", clazz));
        }
        DataInputStream dataIn = (DataInputStream) in;
        int len = dataIn.readShort();
        Object array = Array.newInstance(componentClass, len);
        for (int i = 0; i < len; i++) {
            Array.set(array, i, serializer.fromStream(serializer, in, componentClass,field));
        }
        return array;
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        Class componentClass = obj.getClass().getComponentType();
        //System.out.println(componentClass);
        //System.out.println(obj.getClass());
        if (componentClass == null) {
            throw new SerializationException(String.format("Class %s is not supported", obj.getClass()));
        }
        int len = Array.getLength(obj);
        DataOutputStream dataOut = (DataOutputStream) out;
        dataOut.writeShort(len);
        for (int i = 0; i < len; i++) {
            serializer.toStream(serializer, Array.get(obj, i), out);
        }
        return null;
    }

}
