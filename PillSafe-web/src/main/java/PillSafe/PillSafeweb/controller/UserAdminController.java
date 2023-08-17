package PillSafe.PillSafeweb.controller;

import PillSafe.PillSafeweb.Entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/users")
public class UserAdminController {
    private UserService userService;

    @Autowired
    public void UserController(UserService userService) {
        this.userService = userService;
    }

    public UserAdminController() {
        userService = null;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList"; // userList.html (Thymeleaf 템플릿 파일)
    }

    // 다른 CRUD 관련 메서드들도 추가 가능
}
