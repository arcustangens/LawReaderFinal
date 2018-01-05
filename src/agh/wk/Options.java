package agh.wk;

import java.util.ArrayList;

public class Options {
    private String path;
    private String document;
    private boolean contentOrTable=true;
    private String whatToDisplay;
    private String targetToDisplay;
    private ArrayList<String> targetDirectory = new ArrayList<>();

    public void setDocument(String document){ this.document = document; }
    public String getDocument(){ return this.document; }

    public void setPath(String path){ this.path = path; }
    public String getPath(){ return this.path; }

    public void setContentOrTable(boolean choice){
        this.contentOrTable=choice;
    }
    public boolean getContentOrTable(){
        return this.contentOrTable;
    }

    public void setWhatToDisplay(String whatToDisplay){
        this.whatToDisplay=whatToDisplay;
    }

    public String getWhatToDisplay(){
        return this.whatToDisplay;
    }

    public void setTargetDirectory(ArrayList<String> directory){
        this.targetDirectory=directory;
    }

    public ArrayList<String> getTargetDirectory() {
        return targetDirectory;
    }
}
