<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.google.appengine.api.rdbms.AppEngineDriver" %>
<%
String delete = request.getParameter("delete");
if(delete!=null && delete.equals("true")){
	Connection conn = DriverManager.getConnection("jdbc:google:rdbms://temula1:temula/guestbook");
	PreparedStatement ps = conn.prepareStatement("delete from space");
	ps.execute();
	conn.commit();
	ps.close();
	ps=null;
	conn.close();
}
%>
<html>
 <body>
 <form action='testdelete.jsp'>
	<input type='hidden' name='delete' value='true'>
	<input type='submit' value='DELETE ALL SPACE ROWS'> 
  </form>
  </body>
</html>