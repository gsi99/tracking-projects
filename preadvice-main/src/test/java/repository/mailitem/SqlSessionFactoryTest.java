package repository.mailitem;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repository.mailitem.mappers.CurrentDate;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class SqlSessionFactoryTest {
	
	@Resource
	SqlSessionFactory sessionFactory;
	
	@Resource
	CurrentDate currentDate;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		SqlSession session = sessionFactory.openSession();
		assertNotNull("Unable to open Sql session to database. Ensure mySQL server is started.", session);

		String currDate = currentDate.getCurrentDate();
		assertNotNull("Expected date to be returned from database", currDate);
	}

}
