package com.temula;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.temula.exception.InvalidInputException;
import com.temula.exception.NullInputException;
import com.temula.exception.RecordExistsException;
import com.temula.person.Person;
import com.temula.person.PersonManager;

@SuppressWarnings("serial")
public class GuestbookServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	  throws IOException {
		
		PrintWriter out = resp.getWriter();
		PersonManager personManager = new PersonManager();
		Person person = new Person();
		person.setFirstName("Vivek App Engine!"+Math.random());
		person.setLastName("Ramaswamy App Engine!"+Math.random());
		try{
			personManager.insert(person);
			out.println("<html><head></head><body>Success! Redirecting in 3 seconds...</body></html>");
			resp.setHeader("Refresh","3; url=/guestbook.jsp");
		}
		catch(NullInputException nullInputException){
			out.println("<html><head></head><body>Failure! Null input!..</body></html>");
		}
		catch(InvalidInputException invalidInputException){
			out.println("<html><head></head><body>Failure! Invalid input!..</body></html>");
		}	    	
		catch(RecordExistsException recordExistsException){
			out.println("<html><head></head><body>Failure! Record Exists!..</body></html>");
		}
	  }
}
/*	    	


DriverManager.registerDriver(new AppEngineDriver());
c = DriverManager.getConnection("jdbc:google:rdbms://temula1:temula/guestbook");
String fname = req.getParameter("fname");
String content = req.getParameter("content");
if (fname == "" || content == "") {
out.println("<html><head></head><body>You are missing either a message or a name! Try again! Redirecting in 3 seconds...</body></html>");
} else {
String statement ="INSERT INTO entries (guestName, content) VALUES( ? , ? )";
PreparedStatement stmt = c.prepareStatement(statement);
stmt.setString(1, fname);
stmt.setString(2, content);
int success = 2;
success = stmt.executeUpdate();
*/
