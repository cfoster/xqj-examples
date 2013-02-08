package net.xqj.prague2013;

import net.xqj.basex.BaseXXQDataSource;

import javax.xml.xquery.*;
import java.io.PrintWriter;

/**
 * Altering Static Context
 */

public class StaticContext
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource ds = new BaseXXQDataSource();
    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");

    XQConnection xqc = ds.getConnection();

    XQStaticContext ctx = xqc.getStaticContext();

    ctx.declareNamespace("ns", "java rules");

    xqc.setStaticContext(ctx);

    XQExpression xqe = xqc.createExpression();

    // declare namespace ns = 'xquery rules more';
    XQResultSequence rs = xqe.executeQuery(
      "<ns:e />"
    );

    rs.writeSequence(System.out, null);

    xqc.close();
  }

}
