/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ledes.hidra.services;

import junit.framework.TestCase;

/**
 *
 * @author pedro
 */
public class HidraServicesTest extends TestCase {
    
    public HidraServicesTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.err.println("Iniciando Teste");
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.err.println("Finalizando teste");
    }

    /**
     * Test of start method, of class HidraServices.
     *
    public void testStart() {
        System.out.println("start");
        String localPath = "/home/danielli/LocalTeste";
        HidraServices instance = new HidraServices();
        boolean expResult = true;
        boolean result = instance.start(localPath);
        assertEquals(expResult, result);
        
    }
    * /
    /**
     * Test of addOn method, of class HidraServices.
     *
    public void testAddOn() {
        System.out.println("addOn");
        String fileName = "";
        HidraServices instance = new HidraServices();
        boolean expResult = false;
        boolean result = instance.addOn(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * /
    /**
     * Test of remove method, of class HidraServices.
     *
    public void testRemove() {
        System.out.println("remove");
        String filename = "";
        HidraServices instance = new HidraServices();
        boolean expResult = false;
        boolean result = instance.remove(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * /
    /**
     * Test of commit method, of class HidraServices.
     *
    public void testCommit() {
        System.out.println("commit");
        String message = "";
        HidraServices instance = new HidraServices();
        boolean expResult = false;
        boolean result = instance.commit(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */

    public void testClone(){
        System.out.println("Clone");
        String message = "";
        HidraServices instance = new HidraServices();
        boolean expResult = true;
        boolean result = instance.clone("https://github.com/pedroSouzaJunior/ArquivosTeste.git", "/home/danielli/CLONE");
        assertEquals(expResult, result);
        
        
        
    }
}
