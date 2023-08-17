package PillSafe.PillSafeweb.controller;

import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Controller
public class TextDetectionController {

    public String secKey(){
        String s_value = null;
        String line = null;
        String variableName = null;
        try {
            FileReader fileReader = new FileReader("secret_key.init");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                // 여기서 line을 파싱하여 변수 추출
                // 예를 들어, "변수명=값" 형태로 저장된 텍스트 파일이라면,
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    variableName = parts[0];
                    s_value = parts[1];
                }
            }
            bufferedReader.close();
        }
        catch(IOException e){
                e.getStackTrace();
            }
        finally {
            return s_value;
        }

    }


    @GetMapping("/")
    public String mainWeb(){
        return "mainWeb";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        // 화이트리스트에 허용된 파일 확장자들을 정의합니다.
        List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png");

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file to upload.");
            return "redirect:/upload"; // 업로드 페이지로 리다이렉트
        }

        // 업로드된 파일의 확장자를 추출합니다.
        String originalFilename = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);

        if (fileExtension != null && allowedExtensions.contains(fileExtension.toLowerCase())) {
            try {
                String result = DetectText.detectTextFromImage(file.getBytes());
                redirectAttributes.addAttribute("textResult", result); // 결과를 파라미터로 추가
            } catch (IOException e) {
                redirectAttributes.addFlashAttribute("error", "An error occurred while processing the image.");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "Only images with allowed extensions are allowed.");
        }

        return "redirect:/getDrugInfo"; // /getDrugInfo 엔드포인트로 리다이렉트
    }

    @PostMapping("/detect-text")
    @ResponseBody
    public String detectText(@RequestParam("imageData") String imageData) throws IOException {
        // imageData를 byte[]로 변환 (데이터 URL 형식 제거)
        byte[] imageBytes = java.util.Base64.getDecoder().decode(imageData.split(",")[1]);

        return DetectText.detectTextFromImage(imageBytes);
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search";
    }

    @PostMapping("/search")
    public String search(@RequestParam("textResult") String text, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("textResult", text); // 결과를 파라미터로 추가

        return "redirect:/getSearchInfo"; // /getDrugInfo 엔드포인트로 리다이렉트
    }




    @GetMapping("/getDrugInfo")
    public String getDrugInfo(@RequestParam("textResult") String textResult, Model model) {

            String apiUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
            String secretkey = secKey();

            // Decode the URL-encoded textResult parameter
            String decodedTextResult = UriUtils.decode(textResult, "UTF-8");

            // Make the API call and fetch data
            String apiResponse = makeApiCall(apiUrl, secretkey, textResult);

            jsonResult(apiResponse, model);

            return "apiResult"; // 리다이렉트 요청
        }

        @GetMapping("/getSearchInfo")
        public String getSearchInfo (@RequestParam("textResult") String textResult, Model model){
            String apiUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
            String serviceKey = secKey();

            // Decode the URL-encoded textResult parameter
            String decodedTextResult = UriUtils.decode(textResult, "UTF-8");

            // Make the API call and fetch data
            String apiResponse = makeApiCall(apiUrl, serviceKey, textResult);

            jsonResult(apiResponse, model);

            return "searchResult"; // 리다이렉트 요청
        }

        private void jsonResult (String apiString, Model model){

            // Parse JSON result and obtain items
            JSONParserExample parserExample = new JSONParserExample();
            JSONObject jsonObject;
            try {
                jsonObject = parserExample.parseJson(apiString);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JSONArray items = parserExample.getItems(jsonObject);

            model.addAttribute("items", items);
        }

        private String makeApiCall (String apiUrl, String serviceKey, String itemName){
//        StringBuffer result = new StringBuffer();
            StringBuilder result = new StringBuilder();
            try {
                String encodedItemName = URLEncoder.encode(itemName, StandardCharsets.UTF_8); // URL 인코딩 적용

                String urlstr = apiUrl + "?ServiceKey=" + serviceKey + "&itemName=" + encodedItemName + "&type=json";

                URL url = new URL(urlstr);
                HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
                urlconnection.setRequestMethod("GET");

                BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8));

                String returnLine;
                while ((returnLine = br.readLine()) != null) {
                    result.append(returnLine);
                }
                br.close();
                urlconnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();
        }

    }