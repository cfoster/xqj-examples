/*
 * Copyright (c) 2013 xqj.net. All rights reserved.
 */

package net.xqj.prague2013;

import javax.xml.xquery.*;

import javax.xml.namespace.QName;

import net.xqj.basex.BaseXXQDataSource;

import java.io.PrintWriter;

/**
 * Basic Example showing how to bind Java values to XQuery external variables
**/
public class BindingVariables
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");

    // ds.setLogWriter(new PrintWriter(System.out, true));
    // ds.setProperty("logLevel","TRACE");

    XQConnection xqc = ds.getConnection();

    XQPreparedExpression xqpe = xqc.prepareExpression(
      "xquery version '3.0';"+
      "declare variable $str as xs:string external := 'default';\n"+
      "declare variable $doc as document-node(element(e)) external;\n"+
      "fn:concat('String: ', $str),\n"+
      "fn:concat('Document: ', $doc)"
    );

    // ... Bind String ...
    xqpe.bindString(new QName("str"), "Hello World", null);

    // ... Bind Document ...
    xqpe.bindDocument(new QName("doc"), "<e>Hello World</e>", null, null);

/**
    XQPreparedExpression xqpe = xqc.prepareExpression(
      "declare context item as xs:string external := 'my default value';" +
      "."
    );

    xqpe.bindString(
      XQConstants.CONTEXT_ITEM, "overridden value", null
    );
**/

    XQResultSequence rs = xqpe.executeQuery();

    while(rs.next())
      System.out.println(rs.getItemAsString(null));

    xqc.close();
  }
}