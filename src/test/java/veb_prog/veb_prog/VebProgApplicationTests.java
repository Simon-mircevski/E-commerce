package veb_prog.veb_prog;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import veb_prog.veb_prog.model.Category;
import veb_prog.veb_prog.model.Manufacturer;
import veb_prog.veb_prog.model.Product;
import veb_prog.veb_prog.model.enumeration.Role;
import veb_prog.veb_prog.service.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class VebProgApplicationTests {

    MockMvc mockMvc;

    @Autowired
    AuthService authService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    private static Category c1;
    private static Manufacturer m1;
    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
         initData();
    }

    private void initData(){
        if(!dataInitialized){
            c1 = categoryService.create("c1","c1");
            categoryService.create("c2","c2");

            m1 = manufacturerService.save("m1","m1").get();
            manufacturerService.save("m2","m2");

            String user = "user";
            String admin = "admin";

            authService.register(user,user,user,user,user, Role.ROLE_USER);
            authService.register(admin,admin,admin,admin,admin,Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetProducts() throws Exception {
        MockHttpServletRequestBuilder productsRequest = MockMvcRequestBuilders.get("/products");
        this.mockMvc.perform(productsRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("products"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent","products"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));

    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = this.productService.save("test",2.0,2,c1.getId(), m1.getId()).get();
        MockHttpServletRequestBuilder productDeleteRequest = MockMvcRequestBuilders
                .delete("/products/delete/" + product.getId());
        this.mockMvc.perform(productDeleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/products"));
    }

}
