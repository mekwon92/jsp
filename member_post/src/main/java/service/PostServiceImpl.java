package service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import dao.PostDao;
import dto.Criteria;
import mapper.PostMapper;
import utils.MybatisInit;
import vo.Post;

public class PostServiceImpl implements PostService{
//	private PostDao dao = new PostDao();
	
	@Override
	public int write(Post post) { //mb 자원관리때문에 메서드안에 메서드마다 넣어야함....ㅠ
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.insert(post);
		}
	}
	
	@Override
	public int modify(Post post) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.update(post);
		}
	}
	
	@Override
	public int remove(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.delete(pno);
		}
	}

	@Override
	public Post findBy(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.selectOne(pno);
		}
	}
	
	@Override
	public Post view(Long pno) { //부하가 심한 코드임 -> 테이블이 아니라 시퀀스 등으로 처리하거나 nosql로 ...처리?
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			mapper.increaseViewCount(pno);
			return mapper.selectOne(pno);
		}
	}
	
	@Override
	public List<Post> list(Criteria cri){
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.selectList(cri);
		}
	}

	@Override
	public int count(Criteria cri) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.getCount(cri);
		}
	}
	
}
