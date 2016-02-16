package basic;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableNeo4jRepositories("basic.repo") //name of package containing repositories here
@EnableTransactionManagement
public class MyConfiguration extends Neo4jConfiguration {

    @Autowired 
    private Environment env;

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory("basic.model"); //name of package containing domain objects here
    }

    @Override
    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer(env.getProperty("neo4j.url"));
    }

    @Override
    @Bean
    //@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session getSession() throws Exception {
        return super.getSession();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyConfiguration.class, args);
    }


}
