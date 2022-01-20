package veb_prog.veb_prog.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.model.Manufacturer;
import veb_prog.veb_prog.model.User;
import veb_prog.veb_prog.model.enumeration.Role;
import veb_prog.veb_prog.service.AuthService;
import veb_prog.veb_prog.service.CategoryService;
import veb_prog.veb_prog.service.ManufacturerService;
import veb_prog.veb_prog.service.ProductService;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {
    @Autowired
    AuthService authService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    private HtmlUnitDriver driver;

    private static Category c1;
    private static Manufacturer m1;
    private static Category c2;
    private static Manufacturer m2;
    private static User regularUser;
    private static User regularAdmin;
    private static boolean dataInitialized = false;

    @BeforeEach
    private void setup(){
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if(this.driver!=null){
            this.driver.close();
        }
    }

    private void initData(){
        if(!dataInitialized){
            c1 = categoryService.create("c1","c1");
            c2 = categoryService.create("c2","c2");

            m1 = manufacturerService.save("m1","m1").get();
            m2 = manufacturerService.save("m2","m2").get();

            String user = "user";
            String admin = "admin";

            authService.register(user,user,user,user,user, Role.ROLE_USER);
            authService.register(admin,admin,admin,admin,admin,Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception{

    }

}
