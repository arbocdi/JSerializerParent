package net.sf.arbocdi.javaserializer.object;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import net.sf.arbocdi.javaserializer.SerializationException;
import net.sf.arbocdi.javaserializer.SerializerI;
import net.sf.arbocdi.javaserializer.reflection.ClassUtilsI;

/**
 *
 * @author arbocdi
 */
public class SimpleObjectSerializer implements SerializerI {

    protected FieldSerializer fieldSerializer;
    protected ClassUtilsI classUtils;

    public SimpleObjectSerializer(FieldSerializer fieldSerializer, ClassUtilsI classUtils) {
        this.fieldSerializer = fieldSerializer;
        this.classUtils = classUtils;
    }

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field field) throws IOException, SerializationException {
        try {
            Object obj = objectClass.newInstance();
            List<Field> fieldsList = this.classUtils.getFields(objectClass);
            for (Field f : fieldsList) {
                this.fieldSerializer.fromStream(serializer, in, obj, f);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new SerializationException(ex);
        }
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        List<Field> fieldsList = this.classUtils.getFields(obj.getClass());
        for (Field field : fieldsList) {
            this.fieldSerializer.toStream(serializer, out, obj, field);
        }
        return null;
    }

}
