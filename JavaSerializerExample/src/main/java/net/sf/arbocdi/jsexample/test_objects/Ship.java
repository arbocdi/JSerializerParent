/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.jsexample.test_objects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementMap;
import org.simpleframework.xml.Root;

/**
 *
 * @author arbocdi
 */
@Data
@Root
public class Ship {

    @ElementList
    private List<Pilot> pilots = new LinkedList();
    @ElementMap
    private Map<String, Module> modules = new HashMap();
    @Element
    private int price;
}
