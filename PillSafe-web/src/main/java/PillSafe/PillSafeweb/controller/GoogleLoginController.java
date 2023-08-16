import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleLoginController {

    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User, Model model) {
        // OAuth2User를 통해 사용자 정보를 얻어옵니다.
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        model.addAttribute("email", email);
        model.addAttribute("name", name);

        return "loginSuccess"; // 로그인 성공 페이지로 이동
    }
}