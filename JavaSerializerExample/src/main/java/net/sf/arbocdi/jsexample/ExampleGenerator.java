/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sf.arbocdi.jsexample;

import net.sf.arbocdi.jsexample.test_objects.Module;
import net.sf.arbocdi.jsexample.test_objects.Pilot;
import net.sf.arbocdi.jsexample.test_objects.Ship;
import java.io.File;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 *
 * @author arbocdi
 */
public class ExampleGenerator {

    public static final File INPUT_XML = new File("input.xml");

    public static void main(String[] args) throws Exception {

        Pilot arboc = new Pilot();
        arboc.setId("01");
        arboc.setName("Arboc Digambara");
        arboc.setSkillpoints(1123);

        Pilot maya = new Pilot();
        //arboc.setId("02");
        maya.setName("Maya Lightbringer");
        maya.setSkillpoints(1156);

        Ship ship = new Ship();
        
        ship.setPrice(1020);
        
        ship.getPilots().add(arboc);
        ship.getPilots().add(maya);

        Module lazer = new Module();
        lazer.setName("Super Mega Cannon Lazer");
        lazer.setDescription("Голактега опасносте!11");

        Module projectile = new Module();
        projectile.setName("1400 Artillery II");
        projectile.setDescription("Minmatar rulez!");

        ship.getModules().put("lazer", lazer);
        ship.getModules().put("cannon", projectile);
        ship.getModules().put("forgot to install", null);

        Serializer persister = new Persister();
        persister.write(ship, INPUT_XML);

    }
}
