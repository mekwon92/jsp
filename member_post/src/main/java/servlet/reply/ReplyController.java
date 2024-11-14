package servlet.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import service.ReplyService;
import service.ReplyServiceImpl;
import vo.Reply;

//경로 변수 인식 /reply/list/3 , /reply/5

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private ReplyService service = ReplyServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.replace(req.getContextPath() + "/reply/","");
		System.out.println(uri);
		
		
		Object ret = null; 
		if(uri.startsWith("list")) { //목록조회
			int tmpIdx = uri.lastIndexOf("/");
			Long pno = 0L;
			if(tmpIdx != -1) {
				pno = Long.valueOf(uri.substring(tmpIdx+1));
			}
			ret = service.list(pno);
		}
		else { //단일조회
			Long rno = Long.valueOf(uri);
			ret = service.findBy(rno);
		}
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(new ObjectMapper().writeValueAsString(ret));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("replyController.doPost()");

		char[] chs = new char[req.getContentLength()];
		req.getReader().read(chs);
		String str = new String(chs);
		System.out.println(str);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	

}
