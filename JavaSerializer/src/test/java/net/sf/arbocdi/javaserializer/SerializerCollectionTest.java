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
public class SerializerCollectionTest {
    
    SerializerCollection collection;
    
    public SerializerCollectionTest() {
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
    public void testGetArraySerializer() throws Exception {
        SerializerI serializer = this.collection.getSerializer("[Ljava.lang.Integer");
        System.out.println(serializer);
        
    }
    
}
