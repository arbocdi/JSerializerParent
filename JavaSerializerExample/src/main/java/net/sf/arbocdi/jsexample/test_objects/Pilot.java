/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.jsexample.test_objects;

import lombok.Data;
import lombok.ToString;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author arbocdi
 */
@Data
@Root
@ToString(callSuper = true)
public class Pilot extends User {
    @Element
    private String name;
    @Element
    private int skillpoints;
}
