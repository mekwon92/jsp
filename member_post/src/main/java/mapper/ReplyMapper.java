package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import dto.ReplyCri;
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
	
	//마이바티스는 이름이 아니라 타입으로 체크함. 파라미터 2개일 경우 명시해줘야 한다
	List<Reply> selectList(@Param("pno") Long pno, @Param("cri") ReplyCri cri);

	List<Reply> selectListByMe(Reply reply);
	
	
	
	
}
