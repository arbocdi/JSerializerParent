package net.sf.arbocdi.javaserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serializes list as object array.
 *
 * @author arbocdi
 */
public class ListSerializer implements SerializerI {

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        List list;
        if (objectClass.equals(List.class) || objectClass.equals(Collection.class) || objectClass.equals(Queue.class) || objectClass.equals(LinkedList.class)) {
            list = new LinkedList();
        } else if (objectClass.equals(ArrayList.class)) {
            list = new ArrayList();
        } else {
            throw new SerializationException(String.format("Class %s is not supported", objectClass));
        }
        
        Class clazz = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        Object[] array = (Object[]) Array.newInstance(clazz, 0);
        array = (Object[]) serializer.fromStream(serializer, in, array.getClass(), field);
        for (Object o : array) {
            list.add(o);
        }
        return list;
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        List list = (List) obj;
        serializer.toStream(null, list.toArray(), out);
        return null;
    }

}
