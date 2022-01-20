package veb_prog.veb_prog.bootstrap;

import lombok.Getter;
import org.springframework.stereotype.Component;
import veb_prog.veb_prog.model.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Getter
@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> userList = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    //@PostConstruct
    //public void init(){
    //    categories.add(new Category("Food", "Category"));
    //    categories.add(new Category("Books", "Category"));
    //    categories.add(new Category("Mangas", "Category"));
    //
    //    userList.add(new User("simon.mircevski", "sm", "Simon", "Mircevski"));
    //    userList.add(new User("iva.mircevska", "im", "Iva", "Mircevska"));
    //    userList.add(new User("martin.janevski", "mj", "Martin", "Janevski"));
    //
    //    Manufacturer manufacturer = new Manufacturer("Nike","NY NY");
    //    Manufacturer manufacturer2 = new Manufacturer("Apple","LA LA");
    //    manufacturers.add(manufacturer);
    //    manufacturers.add(manufacturer2);
    //
    //    Category category = new Category("Sport","Sport category");
    //    categories.add(category);
    //
    //    products.add(new Product("Ball 3", 1000.0, 7, category, manufacturer));
    //    products.add(new Product("Ball 1", 1000.0, 7, category, manufacturer));
    //    products.add(new Product("Ball 2", 1000.0, 7, category, manufacturer));
    //}
}
