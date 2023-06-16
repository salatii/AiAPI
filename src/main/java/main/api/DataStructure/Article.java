package main.api.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class Article {
    private String title;
    private String content;
    private String task;
    List<String> keywords = new ArrayList<>();
    String summary;

    public Article(String title, String content, String task) {
        this.task = task;
        this.title = title;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getTask() {
        return task;
    }

    public String getTitle() {
        return title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSummary() {
        return summary;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void addKeyword(String keyword) {
        keywords.add(keyword);
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", task='" + task + '\'' +
                '}';
    }
}
