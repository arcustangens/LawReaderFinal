package agh.wk;

import java.util.ArrayList;

public class OptionParser{
    public Options parseArgs(String[] args) throws IllegalArgumentException{
        Options parsedOptions = new Options();
        if(args[0].equals("konstytucja") || args[0].equals("uokik")){
            parsedOptions.setDocument(args[0]);
            parsedOptions.setPath(".\\"+args[0]+".txt");
            if(args[1].equals("treść")){
                parsedOptions.setContentOrTable(true);
            }
            else if(args[1].equals("spis")){
                parsedOptions.setContentOrTable(false);
            }
            else{
                throw new IllegalArgumentException("Bledne dane: " + args[1]);
            }
            if(args[2].equals("całość") || args[2].equals("element")){
                parsedOptions.setWhatToDisplay(args[2]);
                if(args[2].equals("element")){
                    ArrayList<String> directory = new ArrayList<>();
                    for (int i = 3; i < args.length; i++) {
                        directory.add(args[i]);
                    }
                    parsedOptions.setTargetDirectory(directory);
                }
            }
            else{
                throw new IllegalArgumentException("Bledne dane: " + args[2]);
            }
        }
        else{
            throw new IllegalArgumentException("Bledne dane: " + args[0]);
        }
        return parsedOptions;
    }
}
