package net.sf.arbocdi.javaserializer.test_objects;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author arbocdi
 */
@Data
public class MapObject {
    public Map<String,String> users = new HashMap();
}
