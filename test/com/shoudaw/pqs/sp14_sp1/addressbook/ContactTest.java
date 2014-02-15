package com.shoudaw.pqs.sp14_sp1.addressbook;

public class ContactTest {

  public static void main(String[] args){
    Contact c = new Contact();
    c.setName("Shouda Wang");
    c.setPhone("2019931149");
    c.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    c.setEmail("shoudaw@gmail.com");
    c.setNote("NYU Student");
    
    System.out.println(c);
  }

}
