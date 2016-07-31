package net.sf.arbocdi.javaserializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.reflect.Array;

/**
 *
 * @author arbocdi
 */
public class SerializerUtils {

    public static void checkWriteRead(SerializerI serializer, Object obj, Class clazz) throws Exception {
        System.out.println(String.format("%-10s object being serialized", SerializerUtils.toString(obj)));
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataOutputStream dataOut = new DataOutputStream(bout);
        serializer.toStream(serializer, obj, dataOut);
        System.out.println(byteArrayToString(bout.toByteArray()));
        DataInputStream dataIn = new DataInputStream(new ByteArrayInputStream(bout.toByteArray()));
        Object obj2 = serializer.fromStream(serializer, dataIn, clazz,null);
        System.out.println(String.format("%-10s deserialized object", SerializerUtils.toString(obj2)));
        if (!objectEquals(obj, obj2)) {
            throw new Exception(String.format("%s!=%s serialized and deserialized objects dont match!", SerializerUtils.toString(obj),
                    SerializerUtils.toString(obj2)));
        }
    }

    public static String toString(Object obj) {
        if (obj.getClass().getComponentType() == null) {
            return obj.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            int len = Array.getLength(obj);
            sb.append("{");
            for (int i = 0; i < len; i++) {
                sb.append(toString(Array.get(obj, i)));
                if (i != len - 1) {
                    sb.append(",");
                }
            }
            sb.append("}");
            return sb.toString();
        }
    }

    public static boolean objectEquals(Object obj, Object obj2) {
        if (obj.getClass().getComponentType() == null) {
            return obj.equals(obj2);
        } else {
            int len = Array.getLength(obj);
            int len2 = Array.getLength(obj2);
            if (len != len2) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                if (!objectEquals(Array.get(obj, i), Array.get(obj2, i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public static String byteToString(byte bt) {
        String str = Integer.toBinaryString(bt & 0x00FF | 256);
        return str.substring(1);
    }

    public static String byteArrayToString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(byteToString(data[i]));
            if (i != data.length - 1) {
                sb.append("\t");
            }
        }
        return sb.toString();
    }

}
