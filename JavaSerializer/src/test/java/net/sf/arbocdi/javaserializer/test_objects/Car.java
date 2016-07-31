package net.sf.arbocdi.javaserializer.test_objects;

import java.util.LinkedList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class Car {
    public String name;
    private Human owner;
    public List<Wheel> wheels;
}
