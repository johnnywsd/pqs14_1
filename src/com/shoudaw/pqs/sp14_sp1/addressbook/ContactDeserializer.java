package com.shoudaw.pqs.sp14_sp1.addressbook;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class ContactDeserializer implements JsonDeserializer<Contact> {

  @Override
  public Contact deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    // TODO Auto-generated method stub
    Gson gson = new Gson();
//    Contact contact = gson.fromJson(json.getAsString(), classOfT)
    return null;
  }
}