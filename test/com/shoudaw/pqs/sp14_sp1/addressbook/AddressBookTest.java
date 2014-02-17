/**
 * 
 */
package com.shoudaw.pqs.sp14_sp1.addressbook;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonSyntaxException;

/**
 * @author johnny
 *
 */
public class AddressBookTest {

  private static AddressBook addressBook = new AddressBook();
  private static Contact contact1 = new Contact();
  private static Contact contact2 = new Contact();

  @Before
  public void testSetup() throws InvalidPhone, InvalidEmail {

    contact1 = new Contact();
    contact1.setName("Shouda Wang");
    contact1.setPhone("2019931149");
    contact1.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact1.setEmail("shoudaw@gmail.com");
    contact1.setNote("NYU Student");
    addressBook.addContact(contact1);
    contact2 = new Contact();
    contact2.setName("Shouda Wang");
    contact2.setPhone("+12019931149");
    contact2.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact2.setEmail("shoudaw@gmail.com");
    contact2.setNote("NYU Student");
    addressBook.addContact(contact2);
  }
  @After
  public void clean(){
    addressBook.clear();
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
  
  @Test
  public void testSearchByName(){
    String name = "Shouda Wang";
    ArrayList<Contact> c = addressBook.findAllContactByName(name);
    assertEquals("FindByName, size:", c.size(), 2);

    for (Contact item : c){
      assertEquals("Name:", item.getName(), name);
    }
  }

  @Test
  public void testRemove(){
    addressBook.deleteContactById(contact1.getId());
    assertEquals("testRemove, size:", addressBook.getNumOfContacts(), 1);
    addressBook.deleteContactById(contact1.getId());
    assertEquals("testRemove, size:", addressBook.getNumOfContacts(), 1);

    addressBook.deleteContactById(contact2.getId());
    assertEquals("testRemove, size:", addressBook.getNumOfContacts(), 0);
  }

  @Test(expected = JsonSyntaxException.class)
  public void testLoadError() throws IOException{
    String filename = "./data_err_1.json";
    AddressBook ab = new AddressBook();
    ab.load(filename);
  }

  @Test(expected = JsonSyntaxException.class)
  public void testLoadError2() throws IOException{
    String filename = "./data_err_2.json";
    AddressBook ab = new AddressBook();
    ab.load(filename);
    assertEquals("load, size:", addressBook.getNumOfContacts(), 2);
  }

}
