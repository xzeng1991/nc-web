package mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;

public class TestMybatis {
	private SqlSessionFactory factory;
	
	@Before
	public void init() throws IOException{
		String configFile = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(configFile);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		factory = builder.build(inputStream);
	}
}
