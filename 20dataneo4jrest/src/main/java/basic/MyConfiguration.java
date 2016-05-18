package basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableNeo4jRepositories("basic.repo") //name of package containing repositories here
@EnableTransactionManagement
public class MyConfiguration extends SpringBootServletInitializer {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyConfiguration.class, args);
    }


}
