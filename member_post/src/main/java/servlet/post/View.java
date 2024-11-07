package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PostService;
import service.PostServiceImpl;

@WebServlet("/post/view")
public class View extends HttpServlet{
	private PostService service = new PostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pnoString = req.getParameter("pno");
		Long bno = pnoString == null ? 1L : Long.valueOf(pnoString);
		
		req.setAttribute("post", service.findBy(bno));
		req.getRequestDispatcher("/WEB-INF/jsp/post/view.jsp").forward(req, resp);
		
		//리디렉션하는 방법도 있음
	}
	
}
