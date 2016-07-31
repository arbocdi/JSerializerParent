package net.sf.arbocdi.javaserializer.test_objects;

import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class Human {
    public String name;
    public int age;
    protected Address addr;
}
