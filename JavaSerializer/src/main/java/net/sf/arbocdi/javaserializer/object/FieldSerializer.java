package net.sf.arbocdi.javaserializer.object;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import lombok.Data;
import net.sf.arbocdi.javaserializer.SerializerI;

/**
 *
 * @author root
 */
@Data
public class FieldSerializer {

    public void fromStream(SerializerI serializer, InputStream in, Object obj, Field f) throws IOException {
        try {
            Object value = serializer.fromStream(serializer, in, f.getType(),f);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void toStream(SerializerI serializer, OutputStream out, Object obj, Field f) throws IOException {
        try {
            f.setAccessible(true);
            serializer.toStream(serializer, f.get(obj), out);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

}
