package PillSafe.PillSafeweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApiResponseController {

    @GetMapping("/show-api-response")
    public String showApiResponse() {
        return "apiResult"; // apiResult.html로 리다이렉트
    }
}