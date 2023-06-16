package main.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Manager {
    String scriptName = "default";
    String arg1 = "";
    String arg2 = "";
    String arg3 = "";

    public Manager(String scriptName) { this.scriptName = scriptName; }

    public Manager(String scriptName, String arg1) {
        this.scriptName = scriptName;
        this.arg1 = arg1;
    }
    public Manager(String scriptName, String arg1, String arg2) {
        this.scriptName = scriptName;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public Manager(String scriptName, String arg1, String arg2, String arg3) {
        this.scriptName = scriptName;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public void setScriptName(String scriptName) { this.scriptName = scriptName; }

    public String getScriptName() {
        return scriptName;
    }

    public void setArgs(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg1() {
        return this.arg1;
    }

    public List<String> runPython() throws IOException {
        String[] cmd = {
                "python",
                "C:/Users/natha/Documents/GitHub/AiAPI/python/python_scripts/"+scriptName+".py",
                this.arg1,
                this.arg2,
                this.arg3,
        };
        Process process = Runtime.getRuntime().exec(cmd);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()));
        return  reader.lines().toList();
    }
}
