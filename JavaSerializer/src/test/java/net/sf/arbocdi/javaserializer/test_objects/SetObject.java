package net.sf.arbocdi.javaserializer.test_objects;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class SetObject {
    private Set<Wheel> wheels = new HashSet();
}
