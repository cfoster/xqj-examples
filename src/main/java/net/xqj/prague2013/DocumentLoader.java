/**
 * Copyright (c) 2013 xqj.net. All rights reserved.
**/

package net.xqj.prague2013;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQItem;
import javax.xml.namespace.QName;

import net.xqj.basex.BaseXXQDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;

import com.xqj2.XQConnection2; // extends javax.xml.xquery.XQConnection

/**
 * Simple Application which loads an XML document into BaseX
 * This makes use of extensions from the XQJ2 API.
**/

public class DocumentLoader
{
  public static void main(String[] args) throws XQException, IOException
  {
    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");
    ds.setProperty("databaseName", "test");

    XQConnection2 xqc = (XQConnection2)ds.getConnection();

    File file = new File(args[5]);

    XQItem item = xqc.createItemFromDocument(
      new FileInputStream(file), null, null);

    xqc.insertItem(file.getName(), item, null);

    xqc.close();
  }
}
