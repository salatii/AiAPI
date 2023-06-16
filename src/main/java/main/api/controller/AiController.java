package main.api.controller;

import main.api.data.Article;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AiController {
    @GetMapping("/rss")
    public String getRss() {
        return "rss";
    }

    @GetMapping("/translation")
    public String getTranslation(Article body) {
        return "translation";
    }

    @GetMapping("/keywords")
    public String getKeywords(Article body) {
        return "keywords";
    }

    @GetMapping("/summary")
    public String getSummary(Article body) {
        return "summary";
    }
}
