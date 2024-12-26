package net.engineeringdigest.journalApp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class JournalApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JournalApp.class, args);
        ConfigurableEnvironment environment=context.getEnvironment();
        System.out.println(environment.getActiveProfiles()[0]);
    }

    @Bean
    public PlatformTransactionManager falana(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}