package net.sf.arbocdi.javaserializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author arbocdi
 */
public class MapSerializer implements SerializerI {

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        Map map;
        if (objectClass.equals(Map.class) || objectClass.equals(HashMap.class)) {
            map = new HashMap();
        } else if (objectClass.equals(SortedMap.class) || objectClass.equals(TreeMap.class)) {
            map = new TreeMap();
        } else {
            throw new SerializationException(String.format("Class %s is not supported", objectClass));
        }

        Class keyClass = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
        Class valueClass = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1];

        Object[] keyArray = (Object[]) Array.newInstance(keyClass, 0);
        Object[] valueArray = (Object[]) Array.newInstance(valueClass, 0);

        keyArray = (Object[]) serializer.fromStream(serializer, in, keyArray.getClass(), field);
        valueArray = (Object[]) serializer.fromStream(serializer, in, valueArray.getClass(), field);

        for (int i = 0; i < keyArray.length; i++) {
            map.put(keyArray[i], valueArray[i]);
        }
        return map;
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        Map map = (Map) obj;
        List keys = new LinkedList();
        List values = new LinkedList();
        Set<Map.Entry> entrySet = map.entrySet();
        for (Map.Entry entry : entrySet) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }
        Object[] keyArray = keys.toArray();
        Object[] valueArray = values.toArray();
        serializer.toStream(serializer, keyArray, out);
        serializer.toStream(serializer, valueArray, out);
        return null;
    }

}
