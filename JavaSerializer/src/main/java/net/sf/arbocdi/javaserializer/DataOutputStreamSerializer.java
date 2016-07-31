package net.sf.arbocdi.javaserializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import lombok.Data;

/**
 * This class reads and writes primitives and Strings using JDK DataStreams.
 * @author arbocdi
 */
@Data
public class DataOutputStreamSerializer implements SerializerI {

    @Override
    public Object fromStream(SerializerI serializer,InputStream in, Class clazz,Field field) throws IOException, SerializationException {
        DataInputStream dataIn = (DataInputStream) in;
        if (clazz.equals(Byte.class) || clazz.equals(byte.class)) {
            return dataIn.readByte();
        } else if (clazz.equals(Boolean.class) || clazz.equals(boolean.class)) {
            return (boolean) dataIn.readBoolean();
        } else if (clazz.equals(Character.class) || clazz.equals(char.class)) {
            return dataIn.readChar();
        } else if (clazz.equals(Double.class) || clazz.equals(double.class)) {
            return dataIn.readDouble();
        } else if (clazz.equals(Float.class) || clazz.equals(float.class)) {
            return dataIn.readFloat();
        } else if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return dataIn.readInt();
        } else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return dataIn.readLong();
        } else if (clazz.equals(Short.class) || clazz.equals(short.class)) {
            return dataIn.readShort();
        } else if (clazz.equals(String.class)) {
            return dataIn.readUTF();
        } else {
            throw new SerializationException(String.format("Class %s is not supported", clazz));
        }
    }

    @Override
    public Object toStream(SerializerI serializer,Object obj, OutputStream out) throws IOException, SerializationException {
        Class classToBeWritten = obj.getClass();
        DataOutputStream dataOut = (DataOutputStream) out;
        if (classToBeWritten.equals(Byte.class) || classToBeWritten.equals(byte.class)) {
            dataOut.write((byte) obj);
        } else if (classToBeWritten.equals(Boolean.class) || classToBeWritten.equals(boolean.class)) {
            dataOut.writeBoolean((boolean) obj);
        } else if (classToBeWritten.equals(Character.class) || classToBeWritten.equals(char.class)) {
            dataOut.writeChar((char)obj);
        } else if (classToBeWritten.equals(Double.class) || classToBeWritten.equals(double.class)) {
            dataOut.writeDouble((double) obj);
        } else if (classToBeWritten.equals(Float.class) || classToBeWritten.equals(float.class)) {
            dataOut.writeFloat((float) obj);
        } else if (classToBeWritten.equals(Integer.class) || classToBeWritten.equals(int.class)) {
            dataOut.writeInt((int) obj);
        } else if (classToBeWritten.equals(Long.class) || classToBeWritten.equals(long.class)) {
            dataOut.writeLong((long) obj);
        } else if (classToBeWritten.equals(Short.class) || classToBeWritten.equals(short.class)) {
            dataOut.writeShort((short) obj);
        } else if (classToBeWritten.equals(String.class)) {
            dataOut.writeUTF((String) obj);
        } else {
            throw new SerializationException(String.format("Class %s is not supported", classToBeWritten));
        }
        return null;
    }

}
