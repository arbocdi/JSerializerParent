/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.javaserializer;

import net.sf.arbocdi.javaserializer.object.FieldSerializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import net.sf.arbocdi.javaserializer.object.ObjectSerializer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import lombok.Data;
import net.sf.arbocdi.javaserializer.object.NullFieldsSerializer;
import net.sf.arbocdi.javaserializer.object.SimpleObjectSerializer;
import net.sf.arbocdi.javaserializer.reflection.ClassUtils;
import net.sf.arbocdi.javaserializer.reflection.ClassUtilsI;

/**
 *
 * @author root
 */
@Data
public class SerializerCollection implements SerializerI {

    private Map<String, SerializerI> serializers = new HashMap();
    private SerializerI defaultSerializer;
    private SerializerI arraySerializer;
    private SerializerI nullSerializer;

    public void addSerializer(String name, SerializerI serializer) {
        this.serializers.put(name, serializer);
    }

    public SerializerI getSerializer(String name) {
        if (name.startsWith("[")) {
            //generic array serializer
            return this.arraySerializer;
        } else {
            SerializerI serializer = this.serializers.get(name);
            if (serializer != null) {
                return serializer;
            } else {
                return this.defaultSerializer;
            }
        }
    }

    public static SerializerCollection getDefault() {
        SerializerCollection cfg = new SerializerCollection();

        cfg.setNullSerializer(new NullSerializer());

        cfg.setArraySerializer(new ArraySerializer());
        //primitive types+String+wrappers
        DataOutputStreamSerializer dOutSerializer = new DataOutputStreamSerializer();
        cfg.addSerializer(boolean.class.getName(), dOutSerializer);
        cfg.addSerializer(Boolean.class.getName(), dOutSerializer);

        cfg.addSerializer(byte.class.getName(), dOutSerializer);
        cfg.addSerializer(Byte.class.getName(), dOutSerializer);

        cfg.addSerializer(short.class.getName(), dOutSerializer);
        cfg.addSerializer(Short.class.getName(), dOutSerializer);

        cfg.addSerializer(int.class.getName(), dOutSerializer);
        cfg.addSerializer(Integer.class.getName(), dOutSerializer);

        cfg.addSerializer(long.class.getName(), dOutSerializer);
        cfg.addSerializer(Long.class.getName(), dOutSerializer);

        cfg.addSerializer(char.class.getName(), dOutSerializer);
        cfg.addSerializer(Character.class.getName(), dOutSerializer);

        cfg.addSerializer(float.class.getName(), dOutSerializer);
        cfg.addSerializer(Float.class.getName(), dOutSerializer);

        cfg.addSerializer(double.class.getName(), dOutSerializer);
        cfg.addSerializer(Double.class.getName(), dOutSerializer);

        cfg.addSerializer(String.class.getName(), dOutSerializer);
        //default
        //cfg.setDefaultSerializer(new ObjectSerializer(new FieldSerializer(),new NullFieldsSerializer(new ClassUtils(Object.class))));
        cfg.setDefaultSerializer(new SimpleObjectSerializer(new FieldSerializer(), new ClassUtils(null)));
        //lists
        cfg.addSerializer(List.class.getName(), new ListSerializer());
        cfg.addSerializer(Queue.class.getName(), new ListSerializer());
        cfg.addSerializer(Collection.class.getName(), new ListSerializer());
        cfg.addSerializer(LinkedList.class.getName(), new ListSerializer());
        cfg.addSerializer(ArrayList.class.getName(), new ListSerializer());
        //sets
        cfg.addSerializer(Set.class.getName(), new SetSerializer());
        cfg.addSerializer(HashSet.class.getName(), new SetSerializer());
        cfg.addSerializer(LinkedHashSet.class.getName(), new SetSerializer());
        cfg.addSerializer(SortedSet.class.getName(), new SetSerializer());
        cfg.addSerializer(TreeSet.class.getName(), new SetSerializer());
        //maps
        cfg.addSerializer(Map.class.getName(), new MapSerializer());
        cfg.addSerializer(HashMap.class.getName(), new MapSerializer());
        cfg.addSerializer(SortedMap.class.getName(), new MapSerializer());
        cfg.addSerializer(TreeMap.class.getName(), new MapSerializer());

        return cfg;
    }

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        int nullValue = (int) this.nullSerializer.fromStream(serializer, in, objectClass, field);
        if (nullValue == NullSerializer.NULL) {
            return null;
        } else {
            return this.getSerializer(objectClass.getName()).fromStream(this, in, objectClass, field);
        }
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        int nullValue = (int) this.nullSerializer.toStream(serializer, obj, out);
        if (nullValue == NullSerializer.NULL) {
            return null;
        } else {
            return this.getSerializer(obj.getClass().getName()).toStream(this, obj, out);
        }
    }

}
