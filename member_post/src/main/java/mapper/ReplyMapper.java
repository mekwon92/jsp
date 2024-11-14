package mapper;

import java.util.List;

import vo.Post;
import vo.Reply;

public interface ReplyMapper {
	int insert(Reply reply);
	int update(Reply reply);
	int delete(Long rno);
	int deleteAll(Long pno);
	
	Reply selectOne(Long rno);
	List<Reply> selectList(Long pno);
	//id에 파라미터에있으면 내가 쓴 댓글
	
	
}
