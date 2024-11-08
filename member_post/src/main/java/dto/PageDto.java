package dto;

import lombok.Data;

@Data
public class PageDto {
	private Criteria cri;
	private int total;
	private int startPage;
	private int endPage;
	
	private int pageCount;
	
	public PageDto(int total) {
		this(new Criteria(), total);
	}
	
	public PageDto(Criteria cri, int total) {
		this(cri, total, 10);
	}
	
	
	public PageDto(Criteria cri, int total, int pageCount) {
		this.cri = cri;
		this.total = total;
		this.pageCount = pageCount;
			
		endPage = (cri.getPage() + pageCount - 1) / pageCount * pageCount ; //밑에 보여주는..
		startPage = endPage - pageCount + 1;
		
		int realEnd = (total + cri.getAmount() - 1) / cri.getAmount();
		System.out.println(realEnd);
		//page start end
		//   1    1   10
		//   2    1   10
		//   3    1   10
		//   4    1   10
		//  11   11   20
		
		if(realEnd < endPage) {
			endPage = realEnd;
		}
	}
	
	
	
	public static void main(String[] args) {
		PageDto dto = new PageDto(255);
		System.out.println(dto);
	}
}
	

