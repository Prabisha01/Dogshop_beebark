package com.example.dogshop.Controller;

import com.example.dogshop.Entity.*;
import com.example.dogshop.Pojo.BookingPojo;
import com.example.dogshop.Pojo.GalleryPojo;
import com.example.dogshop.Pojo.ProductPojo;
import com.example.dogshop.Service.BookingService;
import com.example.dogshop.Service.GalleryService;
import com.example.dogshop.Service.ProductServices;
import com.example.dogshop.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private  final ProductServices productServices;
    private  final BookingService bookingService;


    private  final GalleryService galleryServices;
    @GetMapping("/dashboard")
    public String getDashboardPage() {
        return "admin";
    }


    @GetMapping("/addGallery")
    public String createGallery(Model model) {
        model.addAttribute("gallery", new GalleryPojo());
        return "adminGallery";
    }
    @PostMapping("/save")
    public String saveGallery(@Valid GalleryPojo galleryPojo,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:admin/gallerytable";
        }

        galleryServices.saveGallery(galleryPojo);
        redirectAttributes.addFlashAttribute("successMsg", "Gallery saved successfully");

        return "redirect:/admin/list";
    }

    @GetMapping("/list")
    public String displayGallery( Model model) {
        List<Gallery> galleries = galleryServices.fetchAll();
        model.addAttribute("gallerylist", galleries.stream().map(gallery ->
                Gallery.builder()
                        .id(gallery.getId())
                        .title(gallery.getTitle())
                        .imageBase64(getImageBase64(gallery.getImage()))
                        .build()

        ));
        return "gallerytable";
    }
    @GetMapping("/editGallery/{id}")
    public String editGallery(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Gallery gallery = galleryServices.fetchById(id);
        model.addAttribute("gallery", new GalleryPojo(gallery));
        return "adminGallery";
    }


    @GetMapping("/deleteGallery/{id}")
    public String deleteGallery(@PathVariable("id") Integer id) {
        galleryServices.deleteById(id);
        return "redirect:/admin/list";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/dogshop/";
        File file = new File(filePath + fileName);
        byte[] bytes ;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
    @GetMapping("/addproduct")
    public String createProduct(Model model) {
        model.addAttribute("product", new ProductPojo());
        return "/adminProduct";

    }


    @PostMapping("/saveProduct")
    public String saveProduct(@Valid ProductPojo productPojo,
        BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

            Map<String, String> requestError = validateRequest(bindingResult);
            if (requestError != null) {
                redirectAttributes.addFlashAttribute("requestError", requestError);
                return "redirect:admin/producttable";
            }

            productServices.saveProduct(productPojo);
            redirectAttributes.addFlashAttribute("successMsg", "Gallery saved successfully");

            return "redirect:/admin/listproduct";
        }


    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Product product = productServices.fetchById(id);
        model.addAttribute("product", new ProductPojo(product));
        return "adminProduct";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Integer id) {
        productServices.deleteById(id);
        return "redirect:/admin/listproduct";
    }
    @GetMapping("/profile")
    public String getProfile(Model model) {
        return "update";
    }
    @GetMapping("/listproduct")
    public String displayProduct( Model model) {
        List<Product> products = productServices.fetchAll();
        model.addAttribute("productlist", products.stream().map(product ->
                Product.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .type(product.getType())
                        .price(product.getPrice())
                        .imageBase64(getImageBase64(product.getImage()))
                        .build()

        ));
        return "producttable";
    }
    @GetMapping("/deleteContact/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        userService.CdeleteById(id);
        return "redirect:/admin/contactlist";
    }
    @GetMapping("/contactlist")
    public String getContactList(Model model) {
        List<Contact> contacts = userService.fetchAllContact();
        model.addAttribute("contact", contacts);
        return "contacttable";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        bookingService.userById(id);
        return "redirect:/admin/userlist";
    }
    @GetMapping("/userlist")
    public String getUserList(Model model) {
        List<User> users = userService.fetchAllUser();
        model.addAttribute("userlist", users);
        return "usertable";
    }







    @GetMapping("/alllist")
    public String getAllBooking(Model model) {
        List<Booking> bookings = bookingService.fetchAll();
        model.addAttribute("bookinglist", bookings);
        return "AllBooking";
    }





    @GetMapping("/newbooking")
    public String BookHotel(Model model) {
        model.addAttribute("newBooking", new BookingPojo());
        return "newbookings";
    }



    @PostMapping("/savenewbook")
    public String saveBooking(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "redirect:/admin/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteDog(@PathVariable("id") Integer id) {
        bookingService.deleteById(id);
        return "redirect:/admin/alllist";
    }

}