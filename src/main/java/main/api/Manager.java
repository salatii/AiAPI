package main.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Manager {
    String scriptName;
    String args;
    public Manager(String scriptName) { this.scriptName = scriptName; }

    public Manager(String scriptName, String args) {
        this.scriptName = scriptName;
        this.args = args;
    }

    public void setScriptName(String scriptName) { this.scriptName = scriptName; }

    public String getScriptName() {
        return scriptName;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getArgs() {
        return args;
    }

    public List<String> runPython() throws IOException {
        String[] cmd = {
                "python",
                "C:/Users/natha/IdeaProjects/demo/src/main/java/com/example/demo/python/"+scriptName+".py",
                this.args,
        };
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        return  reader.lines().toList();
    }
}
