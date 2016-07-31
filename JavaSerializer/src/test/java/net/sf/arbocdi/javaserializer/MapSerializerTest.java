package net.sf.arbocdi.javaserializer;

import net.sf.arbocdi.javaserializer.test_objects.MapObject;
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
public class MapSerializerTest {

    SerializerCollection collection;

    public MapSerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        collection = SerializerCollection.getDefault();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testReadWrite() throws Exception {
        System.out.println("========MapSerializerTest:testReadWrite=========");
        MapObject mo = new MapObject();
        mo.users.put("u1", "arbocdi");
        mo.users.put("u2", "maya");
        mo.users.put("u3", "gaal");
        
        SerializerUtils.checkWriteRead(collection, mo, mo.getClass());
    }

}
