package com.example.CodeGames.Controller;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.azure.openai.AzureOpenAiChatClient;
import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/web/create")
public class ChatGptController {


    @Value("${openai.endpoint}")
    private String endpoint;
    
    
    @Value("${openai.api.key}")
    private String apiKey;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private OllamaChatClient chatClient;

    @Autowired
    private AzureOpenAiChatClient openAiChatClient;

    private static final Logger logger = LoggerFactory.getLogger(ChatGptController.class);

    

    @GetMapping("/ai/generate")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> generate(@RequestParam(value = "message") String message) {
        if(!message.contains("10 Steps")){
            message+="in 10 Steps";
        }
        try (XWPFDocument document = new XWPFDocument()) {
            // Create a paragraph
            String response = chatClient.call(message);
            String str[] = response.split("\n");
            for(int i=0;i<str.length;i++){
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(str[i]);
            }
            // Write the document to a byte array
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                document.write(byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                // Set headers
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated-document.docx");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                // Return the ResponseEntity with the document bytes and headers
                return new ResponseEntity<>(bytes, HttpStatus.OK);
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/openai/generate")
    @CrossOrigin(origins = "*")
    public ResponseEntity<byte[]> openAiGenerate(@RequestParam(value = "message") String message) {
        if(!message.contains("10 Steps")){
            message+="in 10 Steps";
        }
        try (XWPFDocument document = new XWPFDocument()) {
            // Create a paragraph
            String response = openAiChatClient.call(message);
            String str[] = response.split("\n");
            for(int i=0;i<str.length;i++){
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun run = paragraph.createRun();
                run.setText(str[i]);
            }
            // Write the document to a byte array
            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                document.write(byteArrayOutputStream);
                byte[] bytes = byteArrayOutputStream.toByteArray();
                // Set headers
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=generated-document.docx");
                headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                // Return the ResponseEntity with the document bytes and headers
                return new ResponseEntity<>(bytes, HttpStatus.OK);
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
