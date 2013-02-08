/*
* Copyright (c) 2013 xqj.net. All rights reserved.
*/

package net.xqj.prague2013;

import javax.xml.transform.stream.StreamResult;
import javax.xml.xquery.*;

import net.xqj.basex.BaseXXQDataSource;

import java.io.StringWriter;
import java.util.Properties;

public class Adhoc1IME
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

    XQResultSequence rs = xqe.executeQuery(
      "42, xs:date('2012-02-08'), xs:float(3.142), <e>Hello World</e>"
    );

//    XQResultSequence rs = xqe.executeQuery(
//      "fn:collection('books')//publish_date/xs:date(.)"
//    );

    while(rs.next())
    {
      XQItemType itemType = rs.getItemType();
      System.out.println(
        "type="+ itemType + ", value=" +rs.getItemAsString(null)
      );
    }



//    XQResultSequence rs = xqe.executeQuery(
//      "fn:collection('books')[fn:position() = (1 to 3)]"
//    );
//
//    StringWriter buffer = new StringWriter();
//    rs.writeSequenceToResult(
//      new StreamResult(
//        buffer
//      )
//    );
//    System.out.println(buffer.toString());

    xqc.close();
  }
}
