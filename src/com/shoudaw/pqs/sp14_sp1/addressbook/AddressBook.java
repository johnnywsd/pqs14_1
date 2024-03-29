package com.shoudaw.pqs.sp14_sp1.addressbook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class AddressBook {
  private HashMap<String, Contact> data = 
      new HashMap<String, Contact>();
  private Gson gson = new Gson();
  
  /**
   * Constructor. Initial a empty AddressBook
   */
  public AddressBook(){ }
 

  /**
   * Constructor. Initial a AddressBook from a file
   * @param filename The data file
   * @throws IOException
   */
  public AddressBook(String filename) throws IOException{
     load(filename);
  }
  
  /**
   * 
   * @return number of contacts in this AddressBook object
   */
  public int getNumOfContacts(){
    return data.size();
  }
  

  /**
   * Add a contact to AddressBook. AddressBook instance keeps references
   * of the Contact instances. An ID will be generated and this ID is also
   * store in the Contact instance. You can get this ID by contact.getId()
   * @param contact Contact object to add
   * @return The ID of that contact 
   */
  public String addContact(Contact contact){
    UUID uuid = null;
    String id = contact.getId();

    //Check whether this contact has already been added into this addressbook
    if (data.containsKey(id) ){
      return id;
    }

    do{
      uuid = UUID.randomUUID();
      id = uuid.toString();
    }while(data.containsKey(id));
    contact.id = id;
    data.put(id, contact);
    return id;
  }
  
  /**
   * Update(Overwrite) a contact in this AddressBook instance.
   * @param id The ID of the contact object in this AddressBook instance.
   * @param contact The new contact object.
   * @return
   */
  public boolean updateContact(String id, Contact contact){
    Contact old = data.get(id);
    if (old != null){
      contact.id = old.getId();
      data.put(id, contact);
      return true;
    }else{
      return false;
    }
  }
  
  /**
   * Set new value for a specific property. 
   * @param id The ID of the contact in this AddressBook instant
   * @param property The properties to search. NAME, EMAIL, ADDRESS, etc. 
   * @param value The new Value
   * @return True, if the contact is found and successfully updated.
   * False, if no contacts with this ID are found.
   * @throws InvalidPhone
   * @throws InvalidEmail
   */
  public boolean updataContact(
      String id,
      Contact.Properties property,
      String value) throws InvalidPhone, InvalidEmail{
    
    Contact contact = data.get(id);
    if (contact != null){
      if (property == Contact.Properties.ADDRESS){
        contact.setAddress(value);
      }
      if(property == Contact.Properties.NAME){
        contact.setAddress(value);
      }else if(property == Contact.Properties.PHONE){
        contact.setPhone(value);
      }else if(property == Contact.Properties.EMAIL){
        contact.setEmail(value);
      }else if (property == Contact.Properties.ADDRESS){
        contact.setAddress(value);
      }else if(property == Contact.Properties.NOTE){
        contact.setNote(value);
      }else{
        return false;
      }
      return true;
    }else{
      return false;
    }
  }
  
  /**
   * Delete a contact by its ID. If there is no associated with this ID. 
   * @param id The ID of the contact to delete.
   * @return the previous value associated with id, 
   * or null if there was no mapping for id. 
   */
  public Contact deleteContactById(String id){
    if(data.containsKey(id)){
      return data.remove(id);
    }else{
      return null;
    }
  }
  
  /**
   * Get a contact by its ID
   * @param id
   * @return A reference of Contact object in this AddreeBook instance
   */
  public Contact getContactById(String id){
    return data.get(id);
  }
  
  /**
   * Find all contacts by the property provided. 
   * The property must be exactly same as the key word provided
   * You can also use findAllContactByName(...), 
   * findAllContactByEmail(..), findAllContactByAddress, etc
   * @param by The properties to search. NAME, EMAIL, ADDRESS, etc.
   * @param target The search key word.
   * @return An ArrayList of Contact objects
   * (references of the Contact objects in this AddressBook instance.)
   */
  public ArrayList<Contact> findAllContact(
      Contact.Properties by, String target){

    ArrayList<Contact> ret = new ArrayList<Contact>();
    Iterator<Contact> it = data.values().iterator();
    while (it.hasNext()){
      Contact item = it.next();
      String ori = null;

      if(by == Contact.Properties.NAME){
        ori = item.getName();
      }else if(by == Contact.Properties.PHONE){
        ori = item.getPhone();
      }else if(by == Contact.Properties.EMAIL){
        ori = item.getEmail();
      }else if (by == Contact.Properties.ADDRESS){
        ori = item.getAddress();
      }else if(by == Contact.Properties.ID){
        ori = item.getId();
      }else if(by == Contact.Properties.NOTE){
        ori = item.getNote();
      }else{
        return ret;
      }

      if (ori.equals(target)){
        ret.add(item);
      }
    }
    return ret;
  }
  
  /**
   * Find all contacts by all Fields
   * The property must be exactly same as the key word provided
   * @param target The search key word.
   * @return An ArrayList of Contact objects 
   * (references of the Contact objects in this AddressBook instance.)
   */
  public ArrayList<Contact> findAllContact(String target){
    ArrayList<Contact> ret = null;
    HashSet<Contact> set = new HashSet<Contact>();
    for (Contact.Properties property : Contact.Properties.values()){
      ArrayList<Contact> tmp = this.findAllContact(property, target);
      for (Contact contact: tmp){
        set.add(contact);
      }
    }
    ret = new ArrayList<Contact>(set);
    return ret;
  }
  
  
  /**
   * Find all contacts by its name
   * @param name Contact's name
   * @return An ArrayList of Contact objects. 
   * (references of the Contact objects in this AddressBook instance) 
   */
  public ArrayList<Contact> findAllContactByName(String name){
    ArrayList<Contact> ret = 
        this.findAllContact(Contact.Properties.NAME, name);
    return ret;
  }

  /**
   * Find all contacts by its phone
   * @param name Contact's phone
   * @return An ArrayList of Contact objects.
   * (references of the Contact objects in this AddressBook instance) 
   */
  public ArrayList<Contact> findAllContactByPhone(String phone){
    ArrayList<Contact> ret = 
        this.findAllContact(Contact.Properties.PHONE, phone);
    return ret;
  }

  /**
   * Find all contacts by its address
   * @param name Contact's address
   * @return An ArrayList of Contact objects. 
   * (references of the Contact objects in this AddressBook instance) 
   */
  public ArrayList<Contact> findAllContactByAddress(String address){
    ArrayList<Contact> ret = 
        this.findAllContact(Contact.Properties.ADDRESS, address);
    return ret;
  }
  
  /**
   * Find all contacts by its email
   * @param name Contact's email
   * @return An ArrayList of Contact objects. 
   * (references of the Contact objects in this AddressBook instance) 
   */
  public ArrayList<Contact> findAllContactByEmail(String email){
    ArrayList<Contact> ret = 
        this.findAllContact(Contact.Properties.EMAIL, email);
    return ret;
  }

  /**
   * Find all contacts by its note
   * @param name Contact's note
   * @return An ArrayList of Contact objects. 
   * (references of the Contact objects in this AddressBook instance) 
   */
  public ArrayList<Contact> findAllContactByNote(String note){
    ArrayList<Contact> ret = 
        this.findAllContact(Contact.Properties.NOTE, note);
    return ret;
  }

  /**
   * Save AddressBook object to file. It use JSON style.
   * @param filename
   * @throws IOException
   */
  public void save(String filename) throws IOException{
    FileWriter file = new FileWriter(filename);
    file.write(this.toJson());
    file.flush();
    file.close();
  }
  
  /**
   * Load AddressBook data from file.
   * @param filename. Path to the data file
   * @throws JsonSyntaxException If the data file structure is not correct
   * @throws IOException
   */
  public void load(String filename) throws JsonSyntaxException, IOException{
    String jsonStr = 
        com.shoudaw.SDWUtils.SDWFileUtils.readFileAsString(filename);
    this.fromJson(jsonStr);
  }
  
  /**
   * Removes all of the contact from this address book. 
   * The address book will be empty after this call returns.
   */
  public void clear(){
    this.data.clear();
  }
  

  /**
   * Convert the contacts data to JSON string.
   * @return a JSON string
   */
  protected String toJson(){
    String jsonStr = gson.toJson(data);
    return jsonStr;
  }
  
  /**
   * Load contacts data from JSON string
   * @param jsonStr The input JSON string
   * @throws JsonSyntaxException When JSON string cannot be parsed
   */
  protected void fromJson(String jsonStr) throws JsonSyntaxException{
    try{
      HashMap<String, Contact> data = 
          gson.fromJson(
              jsonStr, 
              new TypeToken<HashMap<String, Contact>>(){}.getType());
      this.data = data;
    }catch(JsonSyntaxException e){
      throw e;
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("AddressBook {\n");
    sb.append(String.format("\tNumber of contacts:%d\n", data.size()));
    sb.append("\tItems: [\n");
    String itemStr = null;
    Iterator<Contact> it = data.values().iterator();
    while (it.hasNext()){
      Contact item = it.next();
      itemStr = String.format("\t\t%s\n", item.toString());
      sb.append(itemStr);
    }
    sb.append("\t]\n");
    sb.append("}");

    return sb.toString();
  }
  

}
