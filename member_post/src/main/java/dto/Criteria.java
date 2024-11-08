package dto;
// 페이지에 관한 규정, 표준
// 쿼리스트링

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
	//변할수 있게 (포스트 멤버는 final 해야됨)
	private int page = 1;
	private int amount = 10;
}
