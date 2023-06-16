package main.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import main.api.Manager;
import main.api.data.Article;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class AiController {
    @RequestMapping("/main")
    public String getMain() {
        Manager manager = new Manager("main", "arg1", "arg2", "arg3");
        try {
            List<String> result = manager.runPython();
            result.forEach(entry -> System.out.println(entry));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "main";
    }

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
    public String getTranslation(@RequestParam("language") Optional<String> t_language, Article body) {
        if( t_language.isPresent()){
            Manager manager = new Manager("translation_de_en", body.getContent());
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
    public String getKeywords(Article body) {
        return "keywords";
    }

    @RequestMapping("/summary")
    public String getSummary(Article body) {
        return "summary";
    }
}
