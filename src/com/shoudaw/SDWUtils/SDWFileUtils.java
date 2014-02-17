package com.shoudaw.SDWUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;




public class SDWFileUtils {
  
  // Suppress default constructor for nonistantiability
  private SDWFileUtils(){ }

  public static String readFileAsString(String path) 
      throws IOException {
    return readFileAsString(path, StandardCharsets.UTF_8);
  }

  public static String readFileAsString(String path, Charset encoding) 
      throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return encoding.decode(ByteBuffer.wrap(encoded)).toString();
  }

}
