package com.inventarios.controller;

import com.inventarios.model.Category;
import com.inventarios.model.Product;
import com.inventarios.repository.CategoryRepository;
import com.inventarios.service.ProductService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class WebController {

    private final ProductService productService;
    private final CategoryRepository categoryRepo;

    public WebController(ProductService productService,
                         CategoryRepository categoryRepo) {
        this.productService = productService;
        this.categoryRepo   = categoryRepo;
    }

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryRepo.findAll();
    }

    @GetMapping
    public String list(Model model,
                       @RequestParam(required = false) String category) {
        List<Product> items = (category == null || category.isBlank())
            ? productService.findAll()
            : productService.filterByCategory(category);
        model.addAttribute("products", items);
        model.addAttribute("category", category);
        return "product-list";
    }

    @GetMapping("/new")
    public String createForm(Product product) {
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@Valid Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow());
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
