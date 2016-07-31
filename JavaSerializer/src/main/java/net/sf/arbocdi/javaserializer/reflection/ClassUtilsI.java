package net.sf.arbocdi.javaserializer.reflection;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author arbocdi
 */
public interface ClassUtilsI {
    List<Field> getFields(Class clazz);
}
