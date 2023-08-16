package PillSafe.PillSafeweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CameraController {

    @GetMapping("/camera")
    public String cameraPage() {
        return "camera"; // 카메라 기능이 있는 HTML 페이지를 반환합니다.
    }
}

