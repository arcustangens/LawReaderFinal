package agh.wk;

import java.io.*;
import java.util.Scanner;

import static agh.wk.ElementType.*;

public class ParserKonstytucja {

    private Options options;
    private Scanner scanner;
    protected Element konstytucja = new Element(Ustawa);

    private String regexUseless1 = "^.*Kancelaria Sejmu.*$";
    private String regexUseless2 = "^\\d{4}-\\d{2}-\\d{2}$";
    private String regexUseless3 = "^.$";

    private String regexEndOfLineDash = "^.*\\S-$";

    private String regexPunkt = "^\\d+\\) .*$";
    private String regexUstep = "^\\d+\\. .*$";
    private String regexArtykul = "^Art\\. \\d+\\.$";
    private String regexSekcja = "^[A-Z]+([A-Z,]|\\s)*$";
    private String regexRozdzial = "^Rozdział [IVX]+$";

    public ParserKonstytucja(Options options){
        this.options = options;
        try{
            scanner = new Scanner(new File(this.options.getPath()));
        }
        catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void write(){
        System.out.print(konstytucja.toString());
    }

    public void writeList(){
        System.out.print(konstytucja.toStringList());
    }

    public void parse() {
        Element currentElement = konstytucja;
        Element newElement = new Element(Ustawa);
        String line;
        boolean deletedDash;
        int sectionCounter = 1;
        while (scanner.hasNext()){
            deletedDash=false;
            line = scanner.nextLine();
            if(line.matches(regexUseless1) || line.matches(regexUseless2) || line.matches(regexUseless3)) {
                continue;
            }
            if(line.matches(regexEndOfLineDash)){
                line=line.replaceAll("^(.*\\S)-$", "$1");
                deletedDash=true;
            }
            if( !(line.matches(regexRozdzial) || line.matches(regexSekcja) || line.matches(regexArtykul) || line.matches(regexUstep) || line.matches(regexPunkt))) {
                if(currentElement.getContent().equals("") && (currentElement.getType().equals(Artykul) || currentElement.getType().equals(Sekcja) || currentElement.getType().equals(Rozdzial)))
                    currentElement.addContent("\n");
                if(deletedDash){
                    currentElement.addContent(line);
                }
                else{
                    currentElement.addContent(line+" ");
                }
            }
            else {
                if(!currentElement.getType().equals(Ustawa))
                    currentElement.addContent("\n");
                if(line.matches(regexRozdzial)){
                    newElement = new Element(Rozdzial);
                    newElement.setTitle(line);
                    newElement.setListTitle("Roz. " + line.replaceAll("^Rozdział ([IVX]+)$", "$1"));
                }
                if(line.matches(regexSekcja)){
                    newElement = new Element(Sekcja);
                    newElement.setTitle(line);
                    newElement.setListTitle("Sek. " + sectionCounter + ".");
                    sectionCounter += 1;
                }
                if(line.matches(regexArtykul)){
                    newElement = new Element(Artykul);
                    newElement.setTitle(line);
                    newElement.setListTitle(line.replaceAll("^(Art\\. \\d+\\.)$", "$1"));
                }
                if(line.matches(regexUstep)){
                    newElement = new Element(Ustep);
                    newElement.setTitle(line.replaceAll("^(\\d+\\. ).*$", "$1"));
                    newElement.addContent(line.replaceAll("^\\d+\\. (.*)$", "$1"));
                    newElement.setListTitle("Ust. " + line.replaceAll("^(\\d+\\.).*$", "$1"));
                }
                if(line.matches(regexPunkt)){
                    newElement = new Element(Punkt);
                    newElement.setTitle(line.replaceAll("^(\\d+\\) ).*$", "$1"));
                    newElement.addContent(line.replaceAll("^\\d+\\) (.*)$", "$1"));
                    newElement.setListTitle("Pkt. " + line.replaceAll("^(\\d+\\)).*$", "$1"));
                }
                while (!currentElement.isHigherThan(newElement)) currentElement = currentElement.getParent();
                newElement.setParent(currentElement);
                currentElement.addChild(newElement);
                currentElement = newElement;
            }

        }
    }
}
