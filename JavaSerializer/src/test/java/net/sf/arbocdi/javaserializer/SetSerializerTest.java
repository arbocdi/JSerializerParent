package net.sf.arbocdi.javaserializer;

import net.sf.arbocdi.javaserializer.test_objects.SetObject;
import net.sf.arbocdi.javaserializer.test_objects.Wheel;
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
public class SetSerializerTest {
    
     protected SerializerCollection serializerCollection;
    
    public SetSerializerTest() {
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
    public void testReadWrite() throws Exception {
        System.out.println("=========SetSerializerTest:testReadWrite==========");
        SetObject object = new SetObject();
        object.getWheels().add(new Wheel(10));
        object.getWheels().add(new Wheel(1));
        object.getWheels().add(new Wheel(3));
        SerializerUtils.checkWriteRead(serializerCollection, object, object.getClass());
    }
    
}
