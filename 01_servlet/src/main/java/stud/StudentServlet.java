package stud;

import java.io.ByteArrayInputStream;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/1234")
public class StudentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/html; charset=utf-8");
		
		PrintWriter pw = resp.getWriter();
		pw.println("<body>");
		pw.println("<h1>안녕세상aa</h1>");		
		pw.println("</body>");
		
		ByteArrayInputStream bais = new ByteArrayInputStream(new byte[] {10,20,30});
		CharArrayReader car = new CharArrayReader(new char[] {'가','나'});
		StringReader sr = new StringReader("가나다라");
		
		System.out.println((char)sr.read());
		System.out.println((char)sr.read());
		System.out.println((char)sr.read());
		System.out.println((char)sr.read());
		
		
		
	}
	

}
