/**
 * 
 */
package com.shoudaw.pqs.sp14_sp1.addressbook;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author johnny
 *
 */
public class AddressBookTest2 {

  private static AddressBook addressBook = new AddressBook();
  private static Contact contact1 = new Contact();
  private static Contact contact2 = new Contact();
  @BeforeClass
  public static void testSetup() {

    Contact contact1 = new Contact();
    contact1.setName("Shouda Wang");
    contact1.setPhone("2019931149");
    contact1.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact1.setEmail("shoudaw@gmail.com");
    contact1.setNote("NYU Student");
    addressBook.addContact(contact1);
    Contact contact2 = new Contact();
    contact2.setName("Shouda Wang");
    contact2.setPhone("2019931149");
    contact2.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact2.setEmail("shoudaw@gmail.com");
    contact2.setNote("NYU Student");
    addressBook.addContact(contact2);
  }
  
  @AfterClass
  public static void testCleanup() {
    // Teardown for data used by the unit tests
  }
  
  @Test
  public void testPrintAddressBook() {
    System.out.println(addressBook);
  }
  
  @Test
  public void testToJson(){
    System.out.println(addressBook.toJson());
  }
  
  @Test
  public void testLoadJsonStr(){
    AddressBook ab = new AddressBook();
    String jsonStr = addressBook.toJson();
    ab.fromJson(jsonStr);
    System.out.println(ab);
  }
  
  @Test
  public void testSave() throws IOException{
    String filename = "data.json";
    addressBook.save(filename);
  }
  
  @Test
  public void testLoadFile() throws IOException{
    String filename = "data.json";
    addressBook.save(filename);
    AddressBook ab = new AddressBook();
    ab.load(filename);
    System.out.println(ab);
    
  }

}
