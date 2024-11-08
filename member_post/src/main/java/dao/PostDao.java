package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DBConn;
import vo.Post;

public class PostDao {
	public int insert(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			String sql = "insert into tbl_post (title, writer, content) values (?,?,?)";

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			int idx = 1;

			pstmt.setString(idx++, post.getTitle());
			pstmt.setString(idx++, post.getWriter());
			pstmt.setString(idx++, post.getContent());
			return pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}

		return 0;

	}
	

	public Post selectOne(Long pno) {
		Post post = null;
		String sql = "select pno, title, writer, content, view_count, regdate, updatedate from tbl_post where pno = ?";

		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, pno);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int idx = 1;
				post = Post.builder()
						.pno(rs.getLong(idx++))
						.title(rs.getString(idx++))
						.writer(rs.getString(idx++))
						.content(rs.getString(idx++))
						.viewCount(rs.getLong(idx++))
						.regdate(rs.getDate(idx++))
						.updatedate(rs.getDate(idx++))
						.build();
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return post;
	}

	public List<Post> selectList() {
		List<Post> posts = new ArrayList<>();
		String sql = "select pno, title, writer, view_count, regdate from tbl_post order by 1 desc";

		try (Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int idx = 1;
				Post post = Post.builder()
						.pno(rs.getLong(idx++))
						.title(rs.getString(idx++))
						.writer(rs.getString(idx++))
						.viewCount(rs.getLong(idx++))
						.regdate(rs.getDate(idx++))
						.build();
				posts.add(post);
			}
			rs.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	public int update(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update tbl_post set title = ?, content = ?, updatedate = now() where pno = ?";

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			int idx = 1;
			
			pstmt.setString(idx++, post.getTitle());
			pstmt.setString(idx++, post.getContent());
			pstmt.setLong(idx++, post.getPno());
			return pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {}
		}
		return 0;
	}
	
	public int increaseViewCount(Long pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			String sql = "update tbl_post set view_count = view_count + 1 where pno = ?";

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			int idx = 1;
			
			pstmt.setLong(idx++, pno);
			return pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}
		return 0;
	}

	public int delete(Long pno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {

			String sql = "delete from tbl_post where pno = ?";

			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, pno);
			return pstmt.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException ignore) {
			}
		}

		return 0;
	}
	
	public static void main(String[] args) {	
		PostDao dao = new PostDao();
		
		//10개씩 삽입
//		for(int i = 0; i < 10; i++) {
//			dao.insert(Post.builder().writer("mekwon").title("제목 +" + (i + 1)).content("내용").build());			
//		}
		//목록조회
		dao.selectList().forEach(System.out::println);
		
		
		//단일조회
//		System.out.println(dao.selectOne(6L));
		
		//삭제
//		System.out.println(dao.delete(6L));
//		System.out.println(dao.delete(6L));
	
		//수정
//		Post post = dao.selectOne(10L);
//		
//		System.out.println(post);
//		
//		post = Post.builder().pno(post.getPno()).title("수정된 제목").content("수정된 내용").build();
//		
//		dao.update(post);
//		
//		post = dao.selectOne(10L);
//		
//		System.out.println(post);
	
	}

}