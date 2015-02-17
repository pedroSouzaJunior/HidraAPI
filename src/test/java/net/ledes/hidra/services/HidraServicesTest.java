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
     * @return 
     */
    public HidraServices testStart() {
        System.out.println("iniciando repositorio local...");
        String localPath = "/home/pedro/LOCALTESTE";
        boolean expResult = true;
        
        HidraServices instance = new HidraServices();
        
        boolean result = instance.start(localPath);
        
        assertEquals(expResult, result);
        return instance;
    }
    
    /**
     * Test of addOn method, of class HidraServices.
     */
    
    public void testAddOn() {
        System.out.println("addOn");
        String fileName = "arquivo.txt";
        
        
        boolean expResult = true;
        boolean result = testStart().addOn(fileName);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of remove method, of class HidraServices.
     */
    /*
    public void testRemove() {
        System.out.println("remove");
        String filename = "texte.txt";
        
        boolean expResult = false;
        boolean result = testStart().remove(filename);
        assertEquals(expResult, result);
    }
    */
    
    /**
     * Test of commit method, of class HidraServices.
     */
    /*
    public void testCommit() {
        System.out.println("commit");
        String message = "";
        
        boolean expResult = true;
        boolean result = testStart().commit(message);
        assertEquals(expResult, result);
        
    }
    */
    
    /*
    public void testClone(){
        System.out.println("Clone");
        String message = "";
        HidraServices instance = new HidraServices();
        boolean expResult = true;
        boolean result = instance.clone("https://github.com/pedroSouzaJunior/ArquivosTeste.git", "/home/danielli/CLONE");
        assertEquals(expResult, result);
        
        
        
    }
    */
    
    public void testStatus(){
        System.out.println("Status");
        
        boolean expResult = true;
        boolean result = testStart().status();
        assertEquals(expResult, result);
    }

    /**
     * Test of clone method, of class HidraServices.
     */
    /*
    public void testClone() {
        System.out.println("clone");
        String remotePath = "";
        String localPath = "";
        HidraServices instance = new HidraServices();
        boolean expResult = false;
        boolean result = instance.clone(remotePath, localPath);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */    
    
    
    public void testLogs(){
    
        System.out.println("Logs");
        
        boolean expResult = true;
        boolean result = testStart().Logs();
        assertEquals(expResult, result);
    }
    
    
    public void testBranch(){
        System.out.println("Branch");
        
        boolean expResult = true;
        boolean result = testStart().Branch();
        assertEquals(expResult, result);
    }
    
    public void testCreateBranch(){
    
        System.out.println("CreateBranch");
        
        boolean expResult = true;
        boolean result = testStart().createBranch("NovoBranch");
        assertEquals(expResult, result);
    }
}
