/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.jsexample.test_objects;

import lombok.Data;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author arbocdi
 */
@Data
@Root
public class User {
    @Element(required = false)
    protected String id;
}
