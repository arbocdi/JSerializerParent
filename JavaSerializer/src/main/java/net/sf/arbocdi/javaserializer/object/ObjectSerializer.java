package net.sf.arbocdi.javaserializer.object;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import net.sf.arbocdi.javaserializer.SerializationException;
import net.sf.arbocdi.javaserializer.SerializerI;
import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class ObjectSerializer implements SerializerI {

    protected FieldSerializer fieldSerializer;
    protected NullFieldsSerializer nullFieldsSerializer;

    public ObjectSerializer(FieldSerializer fieldSerializer, NullFieldsSerializer nullFieldsSerializer) {
        this.fieldSerializer = fieldSerializer;
        this.nullFieldsSerializer = nullFieldsSerializer;
    }

    @Override
    public Object fromStream(SerializerI serializer, InputStream in, Class fieldClass,Field f) throws IOException, SerializationException {
        try {
            Object obj = fieldClass.newInstance();
            List<Field> fieldsList = (List<Field>) this.nullFieldsSerializer.fromStream(serializer, in, fieldClass,f);
            for (Field field : fieldsList) {
                this.fieldSerializer.fromStream(serializer, in, obj, field);
            }
            return obj;
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new SerializationException(ex);
        }
    }

    @Override
    public Object toStream(SerializerI serializer, Object obj, OutputStream out) throws IOException, SerializationException {
        List<Field> fieldsList = (List<Field>)this.nullFieldsSerializer.toStream(serializer, obj, out);
        for(Field field:fieldsList){
            this.fieldSerializer.toStream(serializer, out, obj, field);
        }
        return null;
    }

}
