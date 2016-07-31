package net.sf.arbocdi.javaserializer;

import java.util.LinkedList;
import net.sf.arbocdi.javaserializer.test_objects.Address;
import net.sf.arbocdi.javaserializer.test_objects.Car;
import net.sf.arbocdi.javaserializer.test_objects.Human;
import net.sf.arbocdi.javaserializer.test_objects.Wheel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author arbocdi
 */
public class ObjectSerializerTest {

    protected SerializerCollection serializerCollection;

    public ObjectSerializerTest() {
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
    public void testReadWriteAddress() throws Exception {
        System.out.println("========ObjectSerializerTest:testReadWriteAddress==========");
        Address addr = new Address();
        addr.setCity("Moscov");
        addr.setStreet("Karpinka");
        addr.setHouseNumber(200);
        SerializerUtils.checkWriteRead(serializerCollection, addr, addr.getClass());
    }

    @Test
    public void testReadWriteAddressNull() throws Exception {
        System.out.println("========ObjectSerializerTest:testReadWriteAddressNull==========");
        Address addr = new Address();
        //addr.setCity("Moscov");
        addr.setStreet("Karpinka");
        addr.setHouseNumber(200);
        SerializerUtils.checkWriteRead(serializerCollection, addr, addr.getClass());
    }

    @Test
    public void testReadWriteCar() throws Exception {
        System.out.println("========ObjectSerializerTest:testReadWriteAddress==========");
        Address addr = new Address();
        addr.setCity("Moscov");
        addr.setStreet("Karpinka");
        addr.setHouseNumber(200);

        Human h = new Human();
        h.setAge(20);
        h.setName("Вася");
        h.setAddr(addr);

        Car car = new Car();
        car.setName("gazenwagen");
        car.setOwner(h);

        SerializerUtils.checkWriteRead(serializerCollection, car, car.getClass());
    }

    @Test
    public void testReadWriteList() throws Exception {
        System.out.println("========ObjectSerializerTest:testReadWriteList==========");
        Human h = new Human();
        h.setAge(20);
        h.setName("Вася");

        Car car = new Car();
        car.setName("gazenwagen");
        car.setOwner(h);

        car.wheels = new LinkedList();
        car.wheels.add(new Wheel(2));
        car.wheels.add(new Wheel(5));
        car.wheels.add(new Wheel(20));

        SerializerUtils.checkWriteRead(serializerCollection, car, car.getClass());
    }

}
