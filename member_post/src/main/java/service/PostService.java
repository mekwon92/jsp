package service;

import java.util.List;

import vo.Post;


public interface PostService {
	//메서드명이 동사 파라미터가 목적어 i write post.
	int write(Post post);
	
	int modify(Post post);
	
	int remove(Long pno);

	//pno로 부터 post(목적어)를 찾음 i find post by pno.
	Post findBy(Long pno);

	Post view(Long pno);
	
	List<Post> list();
}
