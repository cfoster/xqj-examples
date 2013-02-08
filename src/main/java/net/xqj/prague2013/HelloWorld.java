/*
* Copyright (c) 2013 xqj.net. All rights reserved.
*/

package net.xqj.prague2013;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQException;

import net.xqj.basex.BaseXXQDataSource;

/**
* The archetypal Hello World application.
**/

public class HelloWorld
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");

    XQConnection xqc = ds.getConnection();
    XQExpression xqe = xqc.createExpression();
    XQResultSequence rs = xqe.executeQuery("'Hello World'");
    rs.writeSequence(System.out, null);
    xqc.close();
  }
}
