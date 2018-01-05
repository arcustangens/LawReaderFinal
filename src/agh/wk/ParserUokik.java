package agh.wk;

import java.io.*;
import java.util.Scanner;

import static agh.wk.ElementType.*;

public class ParserUokik {
    private Options options;
    private Scanner scanner;
    protected Element uokik = new Element(Ustawa);

    private String regexUseless1 = "^.*Kancelaria Sejmu.*$";
    private String regexUseless2 = "^\\d{4}-\\d{2}-\\d{2}$";

    private String regexBeginning = "^USTAWA$";

    private String regexLitera =  "^[a-z]\\) .*$";
    private String regexPunkt =  "^\\d+[a-z]?\\) .*$";
    private String regexUstep = "^\\d+[a-z]?\\. .*$";
    private String regexArtykul = "^Art\\. \\d+[a-z]?\\..*$";
    private String regexArtykulUstep = "^Art\\. \\d+[a-z]?\\. \\d+[a-z]?\\..*$";
    private String regexRozdzial = "^Rozdział \\d+[a-z]?.*$";
    private String regexDzial = "^DZIAŁ [IVX]+[A-Z]?.*$";

    public ParserUokik(Options options){
        this.options = options;
        try{
            scanner = new Scanner(new File(this.options.getPath()));
        }
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void write(){
        System.out.print(uokik.toString());
    }

    public void writeList(){
        System.out.print(uokik.toStringList());
    }

    public void parse() {
        Element currentElement = uokik;
        Element newElement = new Element(Ustawa);
        String line;
        while (scanner.hasNext()){
            line = scanner.nextLine();
            if(line.matches(regexUseless1) || line.matches(regexUseless2)) {
                continue;
            }
            if(line.matches(regexBeginning)) currentElement.addContent("\n");
            if( !(line.matches(regexDzial) || line.matches(regexRozdzial) || line.matches(regexArtykulUstep) || line.matches(regexArtykul) || line.matches(regexUstep) || line.matches(regexPunkt) || line.matches(regexLitera))) {
                if(currentElement.getContent().equals("") && (currentElement.getType().equals(Dzial) || currentElement.getType().equals(Rozdzial) || currentElement.getType().equals(Artykul)))
                    currentElement.addContent("\n");
                currentElement.addContent(line+" ");
            }
            else {
                currentElement.addContent("\n");
                if(line.matches(regexDzial)){
                    newElement = new Element(Dzial);
                    newElement.setTitle(line);
                    newElement.setListTitle("Dz. " + line.replaceAll("^DZIAŁ ([IVX]+[A-Z]?).*$", "$1"));
                }
                if(line.matches(regexRozdzial)){
                    newElement = new Element(Rozdzial);
                    newElement.setTitle(line);
                    newElement.setListTitle("Roz. " + line.replaceAll("^Rozdział (\\d+[a-z]?).*$", "$1"));
                }
                if(line.matches(regexArtykulUstep)){
                    newElement = new Element(Artykul);
                    newElement.setTitle(line.replaceAll("^(Art\\. \\d+[a-z]?\\.) \\d+[a-z]?\\..*$", "$1"));
                    newElement.setListTitle(line.replaceAll("^(Art\\. \\d+[a-z]?\\.) \\d+[a-z]?\\..*$", "$1"));
                    newElement.addContent("\n");

                    while (!currentElement.isHigherThan(newElement)) currentElement = currentElement.getParent();
                    newElement.setParent(currentElement);
                    currentElement.addChild(newElement);
                    currentElement = newElement;

                    newElement = new Element(Ustep);
                    newElement.setTitle(line.replaceAll("^Art\\. \\d+[a-z]?\\. (\\d+[a-z]?\\.) .*$", "$1"));
                    newElement.addContent(line.replaceAll("^Art\\. \\d+[a-z]?\\. \\d+[a-z]?\\.( .*)$", "$1")+" ");
                    newElement.setListTitle("Ust. " + line.replaceAll("^Art\\. \\d+[a-z]?\\. (\\d+[a-z]?\\.) .*$", "$1"));
                }
                else {
                    if (line.matches(regexArtykul)) {
                        newElement = new Element(Artykul);
                        newElement.setTitle(line.replaceAll("^(Art\\. \\d+[a-z]?\\.) .*$", "$1"));
                        newElement.addContent("\n"+line.replaceAll("^Art\\. \\d+[a-z]?\\. (.*)$", "$1")+" ");
                        newElement.setListTitle(line.replaceAll("^(Art\\. \\d+[a-z]?\\.).*$", "$1"));
                    }
                }
                if(line.matches(regexUstep)){
                    newElement = new Element(Ustep);
                    newElement.setTitle(line.replaceAll("^(\\d+[a-z]?\\.) .*$", "$1"));
                    newElement.addContent(line.replaceAll("^\\d+[a-z]?\\.( .*)$", "$1")+" ");
                    newElement.setListTitle("Ust. " + line.replaceAll("^(\\d+[a-z]?\\.) .*$", "$1"));
                }
                if(line.matches(regexPunkt)){
                    newElement = new Element(Punkt);
                    newElement.setTitle(line.replaceAll("^(\\d+[a-z]?\\)) .*$", "$1"));
                    newElement.addContent(line.replaceAll("^\\d+[a-z]?\\)( .*)$", "$1")+" ");
                    newElement.setListTitle("Pkt. " + line.replaceAll("^(\\d+[a-z]?\\)) .*$", "$1"));
                }
                if(line.matches(regexLitera)){
                    newElement = new Element(Litera);
                    newElement.setTitle(line.replaceAll("^([a-z]\\)) .*$", "$1"));
                    newElement.addContent(line.replaceAll("^[a-z]\\)( .*)$", "$1")+" ");
                    newElement.setListTitle("Lit. " + line.replaceAll("^([a-z]\\)) .*$", "$1"));
                }
                while (!currentElement.isHigherThan(newElement)) currentElement = currentElement.getParent();
                newElement.setParent(currentElement);
                currentElement.addChild(newElement);
                currentElement = newElement;
            }

        }
    }
}
