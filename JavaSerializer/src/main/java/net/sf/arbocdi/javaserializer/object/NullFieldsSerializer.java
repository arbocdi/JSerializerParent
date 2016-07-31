package net.sf.arbocdi.javaserializer.object;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.sf.arbocdi.javaserializer.SerializationException;
import net.sf.arbocdi.javaserializer.SerializerI;
import net.sf.arbocdi.javaserializer.reflection.ClassUtilsI;
import lombok.Data;

/**
 * Writes\reads short array of null field numbers;
 *
 * @author arbocdi
 */
@Data
public class NullFieldsSerializer implements SerializerI {

    //config=============
    protected ClassUtilsI classUtils;
    //work===============

    public NullFieldsSerializer(ClassUtilsI classUtils) {
        this.classUtils = classUtils;
    }

    /**
     * Reads array which contains null field numbers from stream. Excludes null
     * fields from deserialization process.
     *
     * @param serializer
     * @param in
     * @param objectClass
     * @return
     * @throws IOException
     * @throws SerializationException
     */
    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class objectClass, Field f) throws IOException, SerializationException {
        Short[] nullFieldsArray = new Short[0];
        nullFieldsArray = (Short[]) serializer.fromStream(null, in, nullFieldsArray.getClass(), f);
        List<Field> fieldsList = this.classUtils.getFields(objectClass);
        List<Field> notNullFieldsList = new LinkedList();
        for (short i = 0; i < fieldsList.size(); i++) {
            if (Arrays.binarySearch(nullFieldsArray, i) < 0) {
                notNullFieldsList.add(fieldsList.get(i));
            }
        }
        return notNullFieldsList;
    }

    /**
     * Writes array which contains null field numbers to stream. Excludes null
     * fields from serialization process.
     *
     * @param serializer
     * @param obj
     * @param out
     * @return
     * @throws IOException
     * @throws SerializationException
     */
    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        List<Field> fieldList = this.classUtils.getFields(obj.getClass());
        List<Short> nullFields = new LinkedList();
        List<Field> notNullFields = new LinkedList();
        try {
            for (short i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                field.setAccessible(true);
                if (field.get(obj) == null) {
                    nullFields.add(i);
                } else {
                    notNullFields.add(field);
                }
            }
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
        Short[] nullFieldsArray = nullFields.toArray(new Short[nullFields.size()]);
        serializer.toStream(null, nullFieldsArray, out);
        return notNullFields;
    }

}
