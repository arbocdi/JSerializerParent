package net.sf.arbocdi.javaserializer;

import java.util.LinkedList;
import java.util.List;
import net.sf.arbocdi.javaserializer.reflection.test_objects.ListObject;
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
public class ListSerializerTest {

    SerializerCollection collection;

    public ListSerializerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.collection = SerializerCollection.getDefault();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testToFromStream() throws Exception {
        System.out.println("======ListSerializerTest:testToFromStream=======");
        List<Byte> data = new LinkedList();
        data.add((byte)1);
        data.add((byte)2);
        ListObject lo = new ListObject();
        lo.data = data;
        SerializerUtils.checkWriteRead(collection, lo, lo.getClass());
    }

}
