package main.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.api.Manager;
import main.api.data.Article;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AiController {
    @RequestMapping("/rss")
    public String getRss() {
        Manager manager = new Manager("rss");
        JSONArray nodes = new JSONArray();
        try {
            List<String> result = manager.runPython();
            result.forEach(entry -> {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode node = mapper.readTree(entry.replace("\"", "").replace('\'', '\"'));
                    nodes.put(node);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes.toString();
    }

    @RequestMapping(value="/translation", method = RequestMethod.GET)
    public String getTranslation(@RequestParam("language") Optional<String> t_language, @RequestBody Article body) {
        if( t_language.isPresent()){
            Manager manager = new Manager("translation", t_language.get(), body.getContent());
            JSONArray nodes = new JSONArray();
            try {
                List<String> result = manager.runPython();
                result.forEach(line -> nodes.put(line));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return nodes.toString();
        }  else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("HTTP Status will be CREATED (CODE 201)\n").toString();
        }
    }

    @RequestMapping("/keywords")
    public String getKeywords(@RequestBody Article body) {
        Manager manager = new Manager("keywords", body.getContent());
        JSONArray nodes = new JSONArray();
        try {
            List<String> result = manager.runPython();
            result.forEach(line -> nodes.put(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes.toString();
    }

    @RequestMapping("/summary")
    public String getSummary(@RequestBody Article body) {
        Manager manager = new Manager("summary", body.getContent());
        JSONArray nodes = new JSONArray();
        try {
            List<String> result = manager.runPython();
            result.forEach(line -> nodes.put(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes.toString();
    }

    @RequestMapping("/sentiment")
    public String getSentiment(@RequestBody Article body) {
        Manager manager = new Manager("sentiment", body.getContent());
        JSONArray nodes = new JSONArray();
        try {
            List<String> result = manager.runPython();
            result.forEach(line -> nodes.put(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes.toString();
    }

    @RequestMapping("/language")
    public String getLng(@RequestBody Article body) {
        Manager manager = new Manager("detectLanguage", body.getContent());
        JSONArray nodes = new JSONArray();
        try {
            List<String> result = manager.runPython();
            result.forEach(line -> nodes.put(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nodes.toString();
    }
}
