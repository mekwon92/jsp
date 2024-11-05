package servlet.member;

import java.io.IOException;
import java.net.HttpCookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import service.MemberServiceImpl;
import vo.Member;


@WebServlet("/signin")
public class Signin extends HttpServlet{
	private MemberService service = new MemberServiceImpl();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/member/signin.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		System.out.println(id);
		System.out.println(pw);
	
//		service.register(member);
		
		if(service.login(id, pw)) {
			
			//쿠키 생성
			Cookie cookie = new Cookie("rememberMe","yes");
			resp.addCookie(cookie);

			//로그인 성공
			//세션이 로그인 동안 유지되도록
			HttpSession session = req.getSession();
			session.setAttribute("member", service.findBy(id));
			resp.sendRedirect(req.getContextPath()+"/index"); 
			//req.getContextPath()+"/" 이것은 프로젝트이름 /member을 기대하는 것
			
		}
		else {
			resp.sendRedirect("login?msg=fail");
		}
		
	}
}
