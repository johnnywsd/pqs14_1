package com.shoudaw.pqs.sp14_sp1.addressbook;


import org.junit.BeforeClass;
import org.junit.Test;

public class ContastTest {

  private static Contact contact1 = null;
  private static Contact contact2 = null;
  @BeforeClass
  public static void testSetup() throws InvalidPhone, InvalidEmail {

    contact1 = new Contact();
    contact1.setName("Shouda Wang");
    contact1.setPhone("2019931149");
    contact1.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact1.setEmail("shoudaw@gmail.com");
    contact1.setNote("NYU Student");
    contact2 = new Contact();
    contact2.setName("Shouda Wang");
    contact2.setPhone("2019931149");
    contact2.setAddress("40 Newport Pkwy, Jersey City, NJ 07310");
    contact2.setEmail("shoudaw@gmail.com");
    contact2.setNote("NYU Student");
  }

  @Test
  public void testPrint() {
    System.out.println(contact1);
  }
  
  @Test(expected = InvalidPhone.class)
  public void testPhoneExcetion1() throws InvalidPhone{
    contact1.setPhone("abcd");
  }

  @Test
  public void testPhone2() throws InvalidPhone{
    contact1.setPhone("+86 13920540580");
    contact1.setPhone("+1(201)-993-1149");
    contact1.setPhone("+12019931149");
    contact1.setPhone("");
    contact1.setPhone("(201)993-1149");
  }

  @Test(expected = InvalidEmail.class)
  public void testEmailExcetion1() throws InvalidEmail{
    contact1.setEmail("abcd");
  }

}
