package net.sf.arbocdi.javaserializer.reflection;

import net.sf.arbocdi.javaserializer.reflection.test_objects.Square;
import net.sf.arbocdi.javaserializer.reflection.test_objects.Shape2D;
import net.sf.arbocdi.javaserializer.reflection.test_objects.Shape;
import java.lang.reflect.Field;
import java.util.List;
import net.sf.arbocdi.javaserializer.reflection.test_objects.ListObject;
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
public class ClassUtilsTest {

    ClassUtils classUtils;

    public ClassUtilsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        classUtils = new ClassUtils(null);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetFieldsShape2D() {
        System.out.println("=======ClassUtilsTest:testGetFieldsShape2D=========");
        List<Field> fields = this.classUtils.getFields(Shape2D.class);
        System.out.println(fields);
        Assert.assertEquals("area", fields.get(0).getName());
        Assert.assertEquals("perimeter", fields.get(1).getName());
        Assert.assertEquals("x", fields.get(2).getName());
        Assert.assertEquals("y", fields.get(3).getName());
        Assert.assertEquals(4, fields.size());
    }

    @Test
    public void testGetFieldsShape() {
        System.out.println("=======ClassUtilsTest:testGetFieldsShape=========");
        List<Field> fields = this.classUtils.getFields(Shape.class);
        System.out.println(fields);
        Assert.assertEquals("some_field", fields.get(0).getName());
        Assert.assertEquals(1, fields.size());
    }

    @Test
    public void testGetFieldsShapeSquare() {
        System.out.println("=======ClassUtilsTest:testGetFieldsShapeSquare=========");
        List<Field> fields = this.classUtils.getFields(Square.class);
        System.out.println(fields);
        Assert.assertEquals("a", fields.get(0).getName());
        Assert.assertEquals("area", fields.get(1).getName());
        Assert.assertEquals("b", fields.get(2).getName());
        Assert.assertEquals("perimeter", fields.get(3).getName());
        Assert.assertEquals(4, fields.size());
    }

    @Test
    public void testListObject() {
        System.out.println("=======ClassUtilsTest:testListObject=========");
        List<Field> fields = this.classUtils.getFields(ListObject.class);
        System.out.println(fields);
    }

}
