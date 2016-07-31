package net.sf.arbocdi.javaserializer;

import net.sf.arbocdi.javaserializer.SerializationException;
import net.sf.arbocdi.javaserializer.DataOutputStreamSerializer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author arbocdi
 */
public class DataOutputStreamSerializerTest {

    protected DataOutputStreamSerializer serializer;

    public DataOutputStreamSerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        serializer = new DataOutputStreamSerializer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testReadWriteByte() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteByte==========");
        SerializerUtils.checkWriteRead(serializer,(byte) 140, byte.class);
        SerializerUtils.checkWriteRead(serializer,new Byte((byte) 140), Byte.class);

    }

    @Test
    public void testReadWriteBool() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteBool==========");
        SerializerUtils.checkWriteRead(serializer,true, boolean.class);
        SerializerUtils.checkWriteRead(serializer,true, Boolean.class);
    }

    @Test
    public void testReadWriteCharacter() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteCharacter==========");
        SerializerUtils.checkWriteRead(serializer,'A', char.class);
        SerializerUtils.checkWriteRead(serializer,'a', Character.class);
    }

    @Test
    public void testReadWriteDouble() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteDouble==========");
        SerializerUtils.checkWriteRead(serializer,new Double(20.45), double.class);
        SerializerUtils.checkWriteRead(serializer,20.45d, Double.class);
    }

    @Test
    public void testReadWriteFloat() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteFloat==========");
        SerializerUtils.checkWriteRead(serializer,new Float(20.2), float.class);
        SerializerUtils.checkWriteRead(serializer,20.2f, Float.class);
    }

    @Test
    public void testReadWriteInt() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteInt==========");
        SerializerUtils.checkWriteRead(serializer,new Integer(20), int.class);
        SerializerUtils.checkWriteRead(serializer,20, Integer.class);
    }

    @Test
    public void testReadWriteLong() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteLong==========");
        SerializerUtils.checkWriteRead(serializer,new Long(20), long.class);
        SerializerUtils.checkWriteRead(serializer,20L, Long.class);
    }

    @Test
    public void testReadWriteShort() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteShort==========");
        SerializerUtils.checkWriteRead(serializer,new Short((short) 20), short.class);
        SerializerUtils.checkWriteRead(serializer,(short) 20, Short.class);
    }

    @Test
    public void testReadWriteString() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteString==========");
        SerializerUtils.checkWriteRead(serializer,"Hello Вася!", String.class);
    }

    @Test(expected = SerializationException.class)
    public void testNotSupportedClass() throws Exception {
        System.out.println("========DataOutputStreamSerializerTest:testReadWriteNotSupportedClass==========");
        List list = new LinkedList();
        SerializerUtils.checkWriteRead(serializer,list, list.getClass());
    }

}
