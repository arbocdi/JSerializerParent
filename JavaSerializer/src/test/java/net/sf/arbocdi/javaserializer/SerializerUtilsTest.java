package net.sf.arbocdi.javaserializer;

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
public class SerializerUtilsTest {
    
    public SerializerUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testByteToString() {
        System.out.println("=========SerializerUtilsTest:testByteToString=============");
        byte data=0xF;
        String btString = SerializerUtils.byteToString(data);
        System.out.println(btString);
        Assert.assertEquals("00001111", btString);
        
        data= 0x3F;
        btString = SerializerUtils.byteToString(data);
        System.out.println(btString);
        Assert.assertEquals("00111111", btString);
        
    }
    
}
