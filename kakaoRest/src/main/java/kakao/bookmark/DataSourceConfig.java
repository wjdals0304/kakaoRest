package kakao.bookmark;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;


@Configuration
@MapperScan(basePackages="kakao/bookmark/dao")
public class DataSourceConfig {


	@Bean
	public DataSource dataSource()
	{ 
		BasicDataSource dataSource =new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//dataSource.setUrl("jdbc:mysql://localhost:3306/bookmark");
		dataSource.setUrl("jdbc:mysql://211.253.9.184:3306/bookmark");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
		return dataSource;
		
		
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)throws Exception{
		SqlSessionFactoryBean sqlSessionFactory=new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/bookmark.xml")); 
		sqlSessionFactory.setTypeAliasesPackage("kakao.bookmark.domain");
		//sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate SqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception 
	{ 
		return new SqlSessionTemplate(sqlSessionFactory); 
		
	}

}
