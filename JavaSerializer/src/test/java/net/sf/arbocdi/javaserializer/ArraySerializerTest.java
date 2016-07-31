package net.sf.arbocdi.javaserializer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author arbocdi
 */
public class ArraySerializerTest {

    protected SerializerCollection serializerCollection;

    public ArraySerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        serializerCollection = SerializerCollection.getDefault();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testIntArray() throws Exception {
        System.out.println("=========ArraySerializerTest:testIntArray==========");
        int[] array = {1, 2, 3, 4, 5};
        SerializerUtils.checkWriteRead(serializerCollection, array, array.getClass());
    }

    @Test
    public void testIntegerArray() throws Exception {
        System.out.println("=========ArraySerializerTest:testIntegerArray==========");
        Integer[] array = {1, 2, 3, 4, 5};
        SerializerUtils.checkWriteRead(serializerCollection, array, array.getClass());
    }

    @Test
    public void testStringArray() throws Exception {
        System.out.println("=========ArraySerializerTest:testStringArray==========");
        String[] array = {"1", "2", "3", "4", "5"};
        SerializerUtils.checkWriteRead(serializerCollection, array, array.getClass());
    }

    @Test
    public void testInt2DArray() throws Exception {
        System.out.println("=========ArraySerializerTest:testInt2DArray==========");
        int[][] array = {{1, 2}, {3}, {4, 5, 6}};
        SerializerUtils.checkWriteRead(serializerCollection, array, array.getClass());
    }

}
