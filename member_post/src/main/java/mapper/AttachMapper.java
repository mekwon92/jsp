package mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import vo.Attach;

public interface AttachMapper {
	@Insert("insert into tbl_attach values(#{uuid}, #{origin}, #{path}, #{image}, #{pno})")
	int insert(Attach attach);	
	
	@Select("select * from tbl_attach where pno= #{pno}")
	List<Attach> selectList(Long pno);
	
	@Delete("delete from tbl_attach where pno = #{pno}")
	int delete(Long pno);
	
}
