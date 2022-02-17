package io.simpolor.jdbc.config;

/*
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

   @Bean
    public DataSource dataSource(Environment environment) throws SQLException, ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriver(new com.mysql.jdbc.Driver());
        //dataSource.setDriverClass(((Class<Driver>) Class.forName("com.mysql.jdbc.Driver")));
        dataSource.setUrl("jdbc:mysql://localhost:3306/demo_db");
        dataSource.setUsername("demo");
        dataSource.setPassword("demo");

        return dataSource;
    }

	// @Bean
	// public DataSource dataSource(){
    //         return DataSourceBuilder
    //         		.create()
    //         		.driverClassName("com.mysql.jdbc.Driver")
    //        		.url("jdbc:mysql://localhost:3306/demo_db")
    //        		.username("demo")
    //        		.password("demo")
    //    		.build();
    // }

    @Bean
    public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") final DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
*/