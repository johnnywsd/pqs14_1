package com.shoudaw.pqs.sp14_sp1.addressbook;


public class AddressBookTest {
  public static void main(String[] args){
    AddressBook ab = new AddressBook();

    Contact c = new Contact();
    c.setName("Shouda Wang");
    c.setPhone("2019931149");
    c.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    c.setEmail("shoudaw@gmail.com");
    c.setNote("NYU Student");
    System.out.println(c);
    ab.addContact(c);
    
    System.out.println(ab);
    String jsonStr = ab.toJson();
    System.out.println(jsonStr);
    
    AddressBook d = new AddressBook();
    d.fromJson(jsonStr);
    System.out.println(d);

  }

}
