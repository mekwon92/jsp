package servlet.reply;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.ReplyCri;
import service.ReplyService;
import service.ReplyServiceImpl;
import vo.Reply;

//경로 변수 인식 /reply/list/3 , /reply/5

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private ReplyService service = ReplyServiceImpl.getInstance();
	private Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd-HH:mm:ss").create();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.replace(req.getContextPath() + "/reply/","");
		//System.out.println(uri);
		Object ret = null; 
		if(uri.startsWith("list")) { //목록조회
			//reply/list/#{pno}
			//reply/list/#{pno}//#{lastRno}
			//reply/list/#{pno}//#{lastRno}/#{amount}
			ReplyCri cri = new ReplyCri();
			int tmpIdx = uri.indexOf("/");
			Long pno = 0L;
			if(tmpIdx != -1) {
				String tmp = uri.substring(tmpIdx+1);
				//System.out.println(tmp);
				String[] tmpArr = tmp.split("/");
				//System.out.println(Arrays.toString(tmpArr));
				switch(tmpArr.length) {
				case 3:
					cri.setAmount(Integer.parseInt(tmpArr[2]));
				case 2:
					cri.setLastRno(Long.parseLong(tmpArr[1]));
				case 1:
					pno = Long.valueOf(tmpArr[0]);
				default:
					break;
				}
				//System.out.println(cri);
				//무조건하는작업
//				pno = Long.valueOf(uri.substring(tmpIdx+1));
				
			}
			ret = service.list(pno, cri, req.getSession().getAttribute("member")); //마이바티스에서 해석가능하도록 cri도 보내주기
		}
		else { //단일조회
			Long rno = Long.valueOf(uri);
			ret = service.findBy(rno);
		}
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().print(gson.toJson(ret));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("replyController.doPost()");

//		char[] chs = new char[req.getContentLength()];
//		req.getReader().read(chs);
//		String str = new String(chs);
//		System.out.println(str);
//		JsonNode node = mapper.readTree(req.getReader());
		Reply reply = gson.fromJson(req.getReader(), Reply.class);		
		service.write(reply);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		char[] chs = new char[req.getContentLength()]; ->이거는 문자열문제 발생함 \"content\"
//		req.getReader().read(chs);
//		String str = new String(chs);
//		System.out.println(str); 
		
//		JsonNode node = mapper.readTree(req.getReader());
		Reply reply = gson.fromJson(req.getReader(), Reply.class);
//		System.out.println(reply);
		
		service.modify(reply);
	
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		uri = uri.replace(req.getContextPath() + "/reply/","");
		
		
		if(uri.startsWith("list")) { //목록조회
			int tmpIdx = uri.lastIndexOf("/");
			Long pno = 0L;
			if(tmpIdx != -1) {
				pno = Long.valueOf(uri.substring(tmpIdx+1));
			}
			service.removeAll(pno);
		}
		else { //단일조회
			Long rno = Long.valueOf(uri);
			service.remove(rno);
		}
	
	}
	

}
