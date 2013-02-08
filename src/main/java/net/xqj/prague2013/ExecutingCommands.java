/*
 * Copyright (c) 2013 xqj.net. All rights reserved.
 */

package net.xqj.prague2013;

import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQException;

import java.io.PrintWriter;

import net.xqj.basex.BaseXXQDataSource;

/**
 * Executing commands against BaseX
**/

public class ExecutingCommands
{
  public static void main(String[] args) throws XQException
  {
    XQDataSource ds = new BaseXXQDataSource();

    ds.setProperty("serverName", "localhost");
    ds.setProperty("port", "1984");
    ds.setProperty("user", "admin");
    ds.setProperty("password", "admin");

    XQConnection xqc = ds.getConnection();

    ds.setLogWriter(new PrintWriter(System.out, true));

    XQExpression xqe = xqc.createExpression();

    String passwordAsMD5 =
      xqe.executeQuery("xs:hexBinary(hash:md5('PA55w0rd1'))")
        .getSequenceAsString(null);

    xqe.executeCommand("CREATE DB xmlstore"); // Create Database

    // Create some users
    xqe.executeCommand("CREATE USER jbloggs "+passwordAsMD5);
    xqe.executeCommand("CREATE USER psmith  "+passwordAsMD5);
    xqe.executeCommand("CREATE USER ejones  "+passwordAsMD5);

    // Create permissions
    xqe.executeCommand("GRANT READ ON xmlstore TO jbloggs");
    xqe.executeCommand("GRANT READ ON xmlstore TO psmith");
    xqe.executeCommand("GRANT WRITE ON xmlstore TO ejones");

    // Show the users of xmlstore and their permissions
    xqe.executeCommand("SHOW USERS ON xmlstore");

    // Delete the database
    xqe.executeCommand("DROP DB xmlstore");

    // Drop the Users
    xqe.executeCommand("DROP USER jbloggs");
    xqe.executeCommand("DROP USER psmith");
    xqe.executeCommand("DROP USER ejones");

    // Show all users in BaseX
    xqe.executeCommand("SHOW USERS");

    xqc.close();
  }
}
