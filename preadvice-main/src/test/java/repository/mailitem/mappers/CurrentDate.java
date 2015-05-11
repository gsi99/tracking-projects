package repository.mailitem.mappers;

import org.apache.ibatis.annotations.Select;

public interface CurrentDate {
	  @Select("SELECT CURDATE() FROM Dual ")
	  String getCurrentDate();
}
