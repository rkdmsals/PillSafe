package PillSafe.PillSafeweb.controller;

import PillSafe.PillSafeweb.domain.User;
import PillSafe.PillSafeweb.repository.MemoryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class GoogleLoginController {

    @Autowired
    private MemoryUserRepository userRepository;

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        String googleId = oauth2User.getAttribute("sub");
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // 기존에 같은 Google ID로 저장된 사용자가 있는지 확인
        User user = userRepository.findByGoogleId(googleId);
        if (user == null) {
            // 새로운 사용자라면 저장
            user = new User();
            user.setGoogleId(googleId);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
        }

        model.addAttribute("email", email);
        model.addAttribute("name", name);

        return "loginSuccess"; // 로그인 성공 페이지로 이동
    }
}





