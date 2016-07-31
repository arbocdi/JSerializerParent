package net.sf.arbocdi.javaserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author arbocdi
 */
public class SetSerializer implements SerializerI {

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        Set set;
        if (objectClass.equals(Set.class) || objectClass.equals(HashSet.class)) {
            set = new HashSet();
        } else if (objectClass.equals(LinkedHashSet.class)) {
            set = new LinkedHashSet();
        } else if (objectClass.equals(SortedSet.class)||objectClass.equals(TreeSet.class)) {
            set = new TreeSet();
        } 
        else {
            throw new SerializationException(String.format("Class %s is not supported", objectClass));
        }

        Class clazz = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        Object[] array = (Object[]) Array.newInstance(clazz, 0);
        array = (Object[]) serializer.fromStream(serializer, in, array.getClass(), field);
        for (Object o : array) {
            set.add(o);
        }
        return set;
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        Set set = (Set) obj;
        serializer.toStream(null, set.toArray(), out);
        return null;
    }

}
