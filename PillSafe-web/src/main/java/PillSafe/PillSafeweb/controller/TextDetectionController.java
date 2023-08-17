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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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


@Controller
public class TextDetectionController {

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
                System.out.println(result);
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



    @GetMapping("/getDrugInfo")
    public String getDrugInfo(@RequestParam("textResult") String textResult, Model model) {
        String apiUrl = "http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList";
        String serviceKey = "qAKbXRR4042vBsi3b39VoNv8bKlELiGAo046m1w5E3%2FifqQqoz%2B%2Fp9cel5cGeKAtD0HhA9RDU65b8NIGvd4DqQ%3D%3D";

        // Decode the URL-encoded textResult parameter
        String decodedTextResult = UriUtils.decode(textResult, "UTF-8");

        // Make the API call and fetch data
        String apiResponse = makeApiCall(apiUrl, serviceKey, textResult);

//        model.addAttribute("apiResponse", apiResponse);

        Parse(apiResponse);
        jsonResult(apiResponse, model);

        return "apiResult"; // 리다이렉트 요청
    }

    private void jsonResult(String apiString, Model model) {
        String result = apiString; // JSON 결과 문자열

        // Parse JSON result and obtain items
        JSONParserExample parserExample = new JSONParserExample();
        JSONObject jsonObject = null;
        try {
            jsonObject = parserExample.parseJson(result);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        JSONArray items = parserExample.getItems(jsonObject);

        model.addAttribute("items", items);
    }

    private void Parse(String result){
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject)jsonParser.parse(result);

            JSONObject parse_object = (JSONObject) object.get("body");
            JSONArray array = (JSONArray) parse_object.get("items");

            for(int i=0; i<array.size(); i++) {

                System.out.println("항목[" + (i + 1) + "] ===========================================");

                //배열 안에 있는것도 JSON형식 이기 때문에 JSON Object 로 추출
                JSONObject tmp = (JSONObject) array.get(i);

                //JSON name으로 추출
                System.out.println("업체명 ==> " + tmp.get("entpName"));
                System.out.println("제품명 ==> " + tmp.get("itemName"));
                System.out.println("효능 ==> " + tmp.get("efcyQesitm"));
                System.out.println("사용법 ==> " + tmp.get("useMethodQesitm"));
                System.out.println("주의사항 경고 ==> " + tmp.get("atpnWarnQesitm"));
                System.out.println("이미지 경로 ==>" + tmp.get("itemImage"));
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    private String makeApiCall(String apiUrl, String serviceKey, String itemName){
//        StringBuffer result = new StringBuffer();
        StringBuilder result = new StringBuilder();
        try{
            String encodedItemName = URLEncoder.encode(itemName, "UTF-8"); // URL 인코딩 적용

            String urlstr = apiUrl + "?ServiceKey=" + serviceKey + "&itemName=" + encodedItemName + "&type=json";
//            String urlstr = apiUrl + "?ServiceKey=" + serviceKey + "&itemName=" + itemName;

            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), StandardCharsets.UTF_8));

            // StringBuffer 일때
//            String returnLine;
//            while((returnLine = br.readLine()) != null){
//                result.append(returnLine);
//            }
//            urlconnection.disconnect();

            // String Builder 일때
            String returnLine;
            while((returnLine = br.readLine()) != null){
                result.append(returnLine);
            }
            br.close();
            urlconnection.disconnect();
            System.out.println(result.toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
