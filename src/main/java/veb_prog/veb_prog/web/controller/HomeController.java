package veb_prog.veb_prog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.WebContext;
import veb_prog.veb_prog.service.CategoryService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    private final CategoryService categoryService;

    public HomeController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String getHomePage(Model model, HttpServletRequest req){
        model.addAttribute("ipAddress", req.getRemoteAddr());
        model.addAttribute("clientAgent", req.getHeader("User-Agent"));
        model.addAttribute("categories", this.categoryService.listCategories());
        model.addAttribute("bodyContent","categories");
        return "master-template";
    }

    @PostMapping
    public String addNewCategory(@RequestParam String name,
                                 @RequestParam String description){
        this.categoryService.create(name, description);
        return "redirect:/home";
    }
    @GetMapping("access_denied")
    public String accessDeniedPage(Model model){
        model.addAttribute("bodyContent","access_denied");
        return "master-template";
    }
}
