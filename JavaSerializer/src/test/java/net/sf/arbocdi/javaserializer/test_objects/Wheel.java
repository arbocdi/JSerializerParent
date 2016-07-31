package net.sf.arbocdi.javaserializer.test_objects;

import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class Wheel {

    private int diameter;

    public Wheel() {
    }

    public Wheel(int diameter) {
        this.diameter = diameter;
    }

}
