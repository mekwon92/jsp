package servlet.post;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Criteria;
import service.PostService;
import service.PostServiceImpl;
import utils.Commons;

@WebServlet("/post/view")
public class View extends HttpServlet{
	private PostService service = new PostServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Criteria cri = new Criteria(req); //수집
		
		String pnoStr = req.getParameter("pno");
//		Long pno = pnoStr == null ? 1L : Long.valueOf(pnoStr);
		
		if(pnoStr == null) {
			Commons.printMsg("비정상적인 접근입니다", "list", resp);
			return;
		}
		Long pno = Long.valueOf(pnoStr);
		
		req.setAttribute("post", service.view(pno));
		req.setAttribute("cri", cri); //사용하기위한..
		req.getRequestDispatcher("/WEB-INF/jsp/post/view.jsp").forward(req, resp);
		
		//리디렉션하는 방법도 있음
	}
	
}
