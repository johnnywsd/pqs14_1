package com.shoudaw.pqs.sp14_sp1.addressbook;

import com.google.gson.annotations.Expose;

public class Contact {
  @Expose
  private static final String PHONE_PATTERN = "[0-9\\s\\-()\\+]*";

  @Expose
  private static final String EMAIL_PATTERN = "\\S+@\\S+\\.\\S+";

  String id;
  private String name;
  private String address;
  private String phone;
  private String email;
  private String note;
  
  /**
   * The available properties.
   */
  public enum Properties {
    ID, NAME, EMAIL, PHONE, ADDRESS, NOTE
  }
  
  /**
   * 
   * @return ID of that contact. It is an UUID. Only it has been added to
   * an AddressBook object, it has an ID.
   */
  public String getId() {
    return id;
  }
  
  /**
   * 
   * @return the name of the contact
   */
  public String getName() {
    return name;
  }
  
  /**
   * 
   * @param name name to set
   */
  public void setName(String name) {
    this.name = name.trim();
  }
  
  /**
   * 
   * @return the address of the contact
   */
  public String getAddress() {
    return address;
  }
  
  /**
   * 
   * @param address address to set
   */
  public void setAddress(String address) {
    this.address = address.trim();
  }
  
  /**
   * 
   * @return phone of the contact
   */
  public String getPhone() {
    return phone;
  }
  
  /**
   * 
   * @param phone Phone to set.
   * @throws InvalidPhone If it does not match the pattern of a phone number. 
   */
  public void setPhone(String phone) throws InvalidPhone {
    if (phone.matches(PHONE_PATTERN)){
      this.phone = phone.trim();
    }else{
      throw new InvalidPhone();
    }
  }
  
  /**
   * 
   * @return the email of that contact
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * 
   * @param email email to set
   * @throws InvalidEmail if it does not match the pattern of a email.
   */
  public void setEmail(String email) throws InvalidEmail {
    email = email.trim();
    if (email.matches(EMAIL_PATTERN)){
      this.email = email;
    }else{
      throw new InvalidEmail();
    }
  }
  
  /**
   * 
   * @return the note of that contact
   */
  public String getNote() {
    return note;
  }

  /**
   * 
   * @param note note to set
   */
  public void setNote(String note) {
    this.note = note.trim();
  }


  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", address=" + address
        + ", phone=" + phone + ", email=" + email + ", note=" + note + "]";
  }
  
}
