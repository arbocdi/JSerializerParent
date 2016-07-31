package net.sf.arbocdi.javaserializer.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author arbocdi
 */
//@Data
public class ClassUtils implements Comparator<Field>, ClassUtilsI {

    protected Class stopParent;

    public ClassUtils(Class stopParent) {
        this.stopParent = stopParent;
    }

    public List<Field> getFields(Class clazz, Class stopParent) {
        List<Field> fieldList;
        Class superClass = clazz.getSuperclass();
        if (superClass != null && !superClass.equals(stopParent)) {
            fieldList = this.getFields(superClass, stopParent);
        } else {
            fieldList = new LinkedList();
        }
        for (Field f : clazz.getDeclaredFields()) {
            if (!Modifier.isFinal(f.getModifiers()) && !f.isSynthetic()) {
                fieldList.add(f);
            }
        }
        return fieldList;
    }

    @Override
    public int compare(Field o1, Field o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public List<Field> getFields(Class clazz) {
        List<Field> fields = this.getFields(clazz, stopParent);
        List<Field> outFields = new LinkedList();
        //filter out private superclass fields
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers()) && !field.getDeclaringClass().equals(clazz)) {
                continue;
            }
            outFields.add(field);
        }
        outFields.sort(this);
        return outFields;
    }
}
