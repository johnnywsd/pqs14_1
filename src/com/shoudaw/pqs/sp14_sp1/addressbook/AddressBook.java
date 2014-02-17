package com.shoudaw.pqs.sp14_sp1.addressbook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
   * Add a contact to AddressBook
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
   * @return
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
   * @return An ArrayList of Contact objects. 
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
   * Find all contacts by its name
   * @param name Contact's name
   * @return An ArrayList of Contact objects. 
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
  

  protected String toJson(){
    String jsonStr = gson.toJson(data);
    return jsonStr;
  }
  
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
