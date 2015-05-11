package repository.mailitem.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import events.model.MailItem;

public interface MailItemMapper {
	  @Select("SELECT m.uniqueItemId, m.Status, m.PreviousStatus, m.eventCode, m.WeightInGrammes, m.LengthInMillimetres, " +
	  		"m.WidthInMillimetres, m.HeightInMillimetres, m.declaredWeightInGrammes, m.DeclaredLengthInMillimetres, m.DeclaredWidthInMillimetres, " +
	  		"m.DeclaredHeightInMillimetres, m.ShapeType, m.DestinationPostCode, m.SourcePostCode, m.CustomerAccountNumber, " +
	  		"a.PropertyNumber, a.Street, a.Locality, a.Town, a.County, a.PostCode, a.Country " +
	  		"FROM mailitem m LEFT OUTER JOIN address a " +
	  		"ON m.recipientaddresid = a.addresid " +
	  		"WHERE m.uniqueitemid = #{uniqueItemId}")
	  MailItem getMailItem(@Param("uniqueItemId") String uniqueItemId);

	  @Insert("INSERT INTO mailitem (uniqueItemId, status, previousStatus, eventCode, weightInGrammes, declaredWeightInGrammes, lengthInMillimetres, " +
		  		"widthInMillimetres, heightInMillimetres, declaredLengthInMillimetres, declaredWidthInMillimetres, " +
		  		"declaredHeightInMillimetres, shapeType, destinationPostCode, sourcePostCode, customerAccountNumber) " +
				"VALUES (#{uniqueItemId}, #{status}, #{previousStatus}, #{eventCode}, #{weightInGrammes}, #{declaredWeightInGrammes}, #{lengthInMillimetres}, "+
				"#{widthInMillimetres}, #{heightInMillimetres}, #{declaredLengthInMillimetres}, #{declaredWidthInMillimetres}, " +
				"#{declaredHeightInMillimetres}, #{shapeType}, #{destinationPostCode}, #{sourcePostCode}, #{customerAccountNumber})") 
	  void insertMailItem(MailItem mailItem);
	  
	  @Delete("DELETE FROM mailItem " +
			  "WHERE uniqueItemId = #{uniqueItemId}")
	  void deleteMailItem(@Param("uniqueItemId") String uniqueItemId);
	  
	  @Update("UPDATE mailitem set status=#{status}, previousStatus=#{previousStatus}, eventCode=#{eventCode}, " +
	  		"weightInGrammes=#{weightInGrammes}, lengthInMillimetres=#{lengthInMillimetres}, widthInMillimetres=#{widthInMillimetres}, " +
	  		"heightInMillimetres=#{heightInMillimetres}, declaredWeightInGrammes=#{declaredWeightInGrammes}, declaredLengthInMillimetres=#{declaredLengthInMillimetres}, " +
	  		"declaredWidthInMillimetres=#{declaredWidthInMillimetres}, declaredHeightInMillimetres=#{declaredHeightInMillimetres}, " +
	  		"shapeType=#{shapeType}, destinationPostCode=#{destinationPostCode}, sourcePostCode=#{sourcePostCode}, " +
	  		"customerAccountNumber=#{customerAccountNumber}")
	  void updateMailItem(MailItem mailItem);
}
