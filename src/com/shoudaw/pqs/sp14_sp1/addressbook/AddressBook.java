package com.shoudaw.pqs.sp14_sp1.addressbook;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.shoudaw.SDWUtils.file.*;


public class AddressBook {
//  private List<Contact> contacts;
//  private HashMap<String, Contact> data = null;
  private HashMap<String, Contact> data = 
      new HashMap<String, Contact>();
  
  public AddressBook(){
//    data = new HashMap<String, Contact>();
    
  }
  
  

  public HashMap<String, Contact> getData() {
    return data;
  }



  public void setData(HashMap<String, Contact> data) {
    this.data = data;
  }



  public String addContact(Contact contact){
    UUID uuid = null;
    String id = null;
    do{
      uuid = UUID.randomUUID();
//      id = String.valueOf(uuid);
      id = uuid.toString();
    }while(data.containsKey(id));
//    id = "1";
    contact.setId(id);
    data.put(id, contact);
    return id;
  }
  
  public void deleteContactById(String id){
    if(data.containsKey(id)){
      data.remove(id);
    }else{
      
    }
  }
  
  public Contact getContactById(String id){
    return data.get(id);
  }
  
  public ArrayList<Contact> findAllContactByName(String name){
    ArrayList<Contact> ret = new ArrayList<Contact>();
    Iterator<Contact> it = data.values().iterator();
    while (it.hasNext()){
      Contact item = it.next();
      if (item.getName() == name){
        ret.add(item);
      }
    }
    return ret;
    
  }
  

  public void save(String filename) throws IOException{
    FileWriter file = new FileWriter(filename);
    file.write(this.toJson());
    file.flush();
    file.close();
  }
  
  public void load(String filename) throws IOException{
    String jsonStr = com.shoudaw.SDWUtils.SDWFileUtils.readFileAsString(filename);
    this.fromJson(jsonStr);
  }
  
  public String toJson(){
    Gson gson = new Gson();
    String jsonStr = gson.toJson(data);
    return jsonStr;
  }
  
  public void fromJson(String jsonStr){
    Gson gson = new Gson();
    HashMap<String, Contact> data = gson.fromJson(jsonStr, new TypeToken<HashMap<String, Contact>>(){}.getType());
    this.data = data;
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
