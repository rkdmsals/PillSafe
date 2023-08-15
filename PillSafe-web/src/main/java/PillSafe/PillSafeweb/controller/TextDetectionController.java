package PillSafe.PillSafeweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Controller
public class TextDetectionController {

    @GetMapping("/")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("error", "Please select a file to upload.");
            return "upload";
        }

        try {
            String result = DetectText.detectText(file.getBytes());
            model.addAttribute("result", result);
        } catch (IOException e) {
            model.addAttribute("error", "An error occurred while processing the image.");
        }

        return "result";
    }

    @GetMapping("/getDrugInfo")
    public String getDrugInfo(@RequestParam("textResult") String textResult, Model model) {
        String apiUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
        String serviceKey = "qAKbXRR4042vBsi3b39VoNv8bKlELiGAo046m1w5E3%2FifqQqoz%2B%2Fp9cel5cGeKAtD0HhA9RDU65b8NIGvd4DqQ%3D%3D";

        // Make the API call and fetch data
        String apiResponse = makeApiCall(apiUrl, serviceKey, textResult);

        model.addAttribute("apiResponse", apiResponse);
        return "apiResult";
    }

    private String makeApiCall(String apiUrl, String serviceKey, String itemName) {
        RestTemplate restTemplate = new RestTemplate();

        // Construct the API request URL with required parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("serviceKey", serviceKey)
                .queryParam("itemName", itemName);

        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
