package com.example.dogshop.Controller;

import com.example.dogshop.Entity.Gallery;
import com.example.dogshop.Entity.Product;
import com.example.dogshop.Entity.User;
import com.example.dogshop.Pojo.BookingPojo;
import com.example.dogshop.Pojo.ContactPojo;
import com.example.dogshop.Pojo.UserPojo;
import com.example.dogshop.Service.BookingService;
import com.example.dogshop.Service.GalleryService;
import com.example.dogshop.Service.ProductServices;
import com.example.dogshop.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class  UserController {
    private final UserService userService;
    private final BookingService bookingService;
    private final GalleryService galleryServices;
    private final ProductServices productServices;
    private final ValidationAutoConfiguration validationAutoConfiguration;


    @GetMapping("/viewProduct")
    public String getProductsPage(Model model, Principal principal) {
        List<Product> products = productServices.fetchAll();
        if (principal != null) {
            model.addAttribute("prof", userService.findByEmail(principal.getName()));
        }
        model.addAttribute("product", products.stream().map(product ->
                        Product.builder()
                                .id(product.getId())
                                .imageBase64(getImageBase64(product.getImage()))
                                .name(product.getName())
                                .type(product.getType())
                                .price(product.getPrice())
                                .build()
                )
        );
        return "product";
    }
    @GetMapping("/create")
    public String createUser(Model model) {
        model.addAttribute("user", new UserPojo());
        return "register";

    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.fetchById(id);
        model.addAttribute("prof", new UserPojo(user));
        return "/user/homepage";
    }

//    @PostMapping("/update")
//    public String updateUser(@Valid UserPojo userpojo) {
//        userService.update(userpojo);
//        return "redirect:/user/homepage";
//    }

    @GetMapping("/about")
    public String getAboutPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("prof", userService.findByEmail(principal.getName()));
        }
        return "/about";
    }
    @GetMapping("/profile")
    public String getProfile() {
        return "update";
    }



    @GetMapping("/profile/{id}")
    public String getUserProfile(@PathVariable("id") Integer id, Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login";
        }
        User user = userService.fetchById(id);

        model.addAttribute("user", new UserPojo(user));
        model.addAttribute("prof", user);

        return "update";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteuser(@PathVariable("id") Integer id) {
//        System.out.println("delete");
//        userService.deleteById(id);
//        return "redirect:/user/list";
//
//    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo) throws IOException {
        userService.save(userPojo);
        return "redirect:/login";

    }

    @GetMapping("/contact")
    public String getPage(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("prof", userService.findByEmail(principal.getName()));
        }
        model.addAttribute("contact", new ContactPojo());
        return "contact";
    }

    @GetMapping("/homepage")
    public String gethomepage(Model model, Principal principal, Authentication authentication ) {
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/dashboard";
                }
            }
        }
        if (principal != null) {
            model.addAttribute("prof", userService.findByEmail(principal.getName()));
        }
        return "homepage";
    }

    @GetMapping("/viewGallery")
    public String getViewGallery(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("prof", userService.findByEmail(principal.getName()));
        }
        List<Gallery> gallerys = galleryServices.fetchAll();
        model.addAttribute("gallerylist", gallerys.stream().map(gallery ->
                Gallery.builder()
                        .id(gallery.getId())
                        .title(gallery.getTitle())
                        .imageBase64(getImageBase64(gallery.getImage()))
                        .build()

        ));
        return "gallery";
    }

    @PostMapping("/send-message")
    public String submitMessage(@Valid ContactPojo contactPojo) {
        userService.submitMsg(contactPojo);
        return "redirect:/user/contact";
    }

    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/dogshop/";
        File file = new File(filePath + fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    @GetMapping("/forgotpassword")
    public String forgotpassword(Model model) {
        model.addAttribute("user", new UserPojo());
        return ("forgotpassword");
    }

    @PostMapping("/changepassword")
    public String changepassword(@RequestParam("email") String email, Model model, @Valid UserPojo userPojo) {
        userService.processPasswordResetRequest(userPojo.getEmail());
        model.addAttribute("message", "Your new password has been sent to your email Please " +
                "check your inbox");
        return "redirect:/user/login";
    }
    @GetMapping("/booking")
    public String BookHotel(Model model) {
        model.addAttribute("booking", new BookingPojo());
        return "booking";
    }


    @PostMapping("/savebook")
    public String saveBooking(@Valid BookingPojo bookingPojo) {
        bookingService.save(bookingPojo);
        return "homepage";
    }


}
