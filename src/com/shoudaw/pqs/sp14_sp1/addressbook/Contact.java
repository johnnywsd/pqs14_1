package com.shoudaw.pqs.sp14_sp1.addressbook;

public class Contact {
  private String id;
  private String name;
  private String address;
  private String phone;
  private String email;
  private String note;

  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress(String address) {
    this.address = address;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Contact [id=" + id + ", name=" + name + ", address=" + address
        + ", phone=" + phone + ", email=" + email + ", note=" + note + "]";
  }
  
  

}
