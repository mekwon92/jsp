package vo;

import java.util.Date;

import lombok.Data;

@Data
public class Post {
	private Long pno;
	private String title;
	private String writer;
	private String content;
	private Long viewCount;
	private Date regdate;
	private Date updatedate;
	
	
}
