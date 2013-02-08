/*
* Copyright (c) 2013 xqj.net. All rights reserved.
*/

package net.xqj.prague2013;

import javax.xml.xquery.*;

import com.xqj2.XQConnection2;
import net.xqj.basex.BaseXXQDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Load sample data
 **/

public class LoadSampleData
{
  public static void main(String[] args) throws XQException, IOException
  {
    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");
    ds.setProperty("databaseName", "books"); // db must already exist

    File[] files = new File("sample-data").listFiles();

    XQConnection2 xqc = (XQConnection2)ds.getConnection();

    for(File file : files)
    {
      XQItem xqItem = xqc.createItemFromDocument(
        new FileInputStream(file),
        null,null
      );

      xqc.insertItem(file.getName(), xqItem, null);
    }

    xqc.close();

    System.out.println("sample data loaded.");
  }
}
