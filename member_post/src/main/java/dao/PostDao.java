package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.DBConn;
import vo.Member;
import vo.Post;

public class PostDao {

	public int insert(Post post) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String sql = "insert into tbl_post (pno, title, writer, content, view_count) values (?,?,?,?,?)";
			
			conn = DBConn.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, post.getPno());
			pstmt.setString(idx++, post.getTitle());
			pstmt.setString(idx++, post.getWriter());
			pstmt.setString(idx++, post.getContent());
			pstmt.setLong(idx++, post.getViewCount());
		
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
	
	public static void main(String[] args) {
		PostDao dao = new PostDao();
	
		Post p = dao.selectOne(1);
		System.out.println(p);
	}
	
	
	
	private Post selectOne(int pno) {
		Post post = null;
		String sql = "select * from tbl_post where pno = ?";
		
		try(Connection conn = DBConn.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setLong(1, pno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				
			}
			rs.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return post;
	}
	
	
}

