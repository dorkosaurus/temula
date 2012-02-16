<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.google.appengine.api.rdbms.AppEngineDriver" %>

<html>
 <body>

<%
Connection c = null;
c = DriverManager.getConnection("jdbc:google:rdbms://temula1:temula/guestbook");
ResultSet rs = c.createStatement().executeQuery("SELECT * FROM person"); %>

<table style="border: 1px solid black">
<tbody>
<tr>
<th width="35%" style="background-color: #CCFFCC; margin: 5px">Name</th>
<th style="background-color: #CCFFCC; margin: 5px">Message</th>
<th style="background-color: #CCFFCC; margin: 5px">ID</th>
</tr> 
<%
while (rs.next()){
    int id = rs.getInt("prsn_id");
    String fname = rs.getString("prsn_fname");
    String lname = rs.getString("prsn_lname"); 
%>

<tr>
<td><%= id %></td>
<td><%= fname %></td>
<td><%= lname %></td>
</tr>

<% }
c.close(); %>

</tbody>
</table>
<br />
No more messages!
<p><strong>Sign the guestbook!</strong></p>
<form action="/sign" method="post">
    <div>First Name: <input type="text" name="fname"></input></div>
    <div>Message:
    <br /><textarea name="content" rows="3" cols="60"></textarea>
    </div>
    <div><input type="submit" value="Post Greeting" /></div>
    <input type="hidden" name="guestbookName" />
  </form>
  </body>
</html>