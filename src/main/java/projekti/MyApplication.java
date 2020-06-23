package projekti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableCaching
@SpringBootApplication
public class MyApplication {
//lisätty exception ja args välitys eteenpäin
    public static void main(String[] args) throws Exception {
        SpringApplication.run(MyApplication.class, args);
    }

}
