package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dto.ReplyCri;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mapper.ReplyMapper;
import utils.MybatisInit;
import vo.Reply;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // 기본생성자 프라이빗으로 만들기 - 싱글턴 
public class ReplyServiceImpl implements ReplyService{
	@Getter //getinstance static은 필드 머리에 붙여야함..?
	private static ReplyService instance = new ReplyServiceImpl();

	@Override
	public int write(Reply reply) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.insert(reply);
		}
	}

	@Override
	public int modify(Reply reply) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.update(reply);
		}
	}

	@Override
	public int remove(Long rno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.delete(rno);
		}
	}

	@Override
	public int removeAll(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.deleteAll(pno);
		}
	}

	@Override
	public Reply findBy(Long rno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.selectOne(rno);
		}
	}

	@Override
	public List<Reply> list(Long pno, ReplyCri cri) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			ReplyMapper mapper = session.getMapper(ReplyMapper.class);
			return mapper.selectList(pno, cri);
		}
	}
}
