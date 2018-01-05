package agh.wk;

public class Reader {

    public static void main(String args[])  throws IllegalArgumentException{
        OptionParser optionParser = new OptionParser();
        Options options = optionParser.parseArgs(args);
        switch(options.getDocument()){
            case "konstytucja":
                ParserKonstytucja parserK = new ParserKonstytucja(options);
                parserK.parse();
                if(options.getContentOrTable()){
                    if(options.getWhatToDisplay().equals("całość"))
                        parserK.write();
                    if(options.getWhatToDisplay().equals("element")){
                        Element element = parserK.konstytucja;
                        for(String target : options.getTargetDirectory()){
                            element=element.getElementTitled(target);
                            if(element==null) break;
                        }
                        if(element!=null){
                            System.out.print(element.toString());
                        }
                        else{
                            throw new IllegalArgumentException("Bledne dane.");
                        }
                    }
                    if(options.getWhatToDisplay().equals("przedział")){
                        System.out.print(parserK.konstytucja.toStringBetween(options.getTargetDirectory().get(0),options.getTargetDirectory().get(1)));
                    }
                }
                else{
                    if(options.getWhatToDisplay().equals("całość"))
                        parserK.writeList();
                    if(options.getWhatToDisplay().equals("element")){
                        Element element = parserK.konstytucja;
                        for(String target : options.getTargetDirectory()){
                            element=element.getElementTitled(target);
                            if(element==null) break;
                        }
                        if(element!=null){
                            System.out.print(element.toStringList());
                        }
                        else{
                            throw new IllegalArgumentException("Bledne dane.");
                        }
                    }
                    if(options.getWhatToDisplay().equals("przedział")){
                        System.out.print(parserK.konstytucja.toStringBetweenList(options.getTargetDirectory().get(0),options.getTargetDirectory().get(1)));
                    }
                }
                break;
            case "uokik":
                ParserUokik parserU = new ParserUokik(options);
                parserU.parse();
                if(options.getContentOrTable()){
                    if(options.getWhatToDisplay().equals("całość"))
                        parserU.write();
                    if(options.getWhatToDisplay().equals("element")){
                        Element element = parserU.uokik;
                        for(String target : options.getTargetDirectory()){
                            element=element.getElementTitled(target);
                            if(element==null) break;
                        }
                        if(element!=null){
                            System.out.print(element.toString());
                        }
                        else{
                            throw new IllegalArgumentException("Bledne dane.");
                        }
                    }
                    if(options.getWhatToDisplay().equals("przedział")){
                        System.out.print(parserU.uokik.toStringBetween(options.getTargetDirectory().get(0),options.getTargetDirectory().get(1)));
                    }
                }
                else{
                    if(options.getWhatToDisplay().equals("całość"))
                        parserU.writeList();
                    if(options.getWhatToDisplay().equals("element")){
                        Element element = parserU.uokik;
                        for(String target : options.getTargetDirectory()){
                            element=element.getElementTitled(target);
                            if(element==null) break;
                        }
                        if(element!=null){
                            System.out.print(element.toStringList());
                        }
                        else{
                            throw new IllegalArgumentException("Bledne dane.");
                        }
                    }
                    if(options.getWhatToDisplay().equals("przedział")){
                        System.out.print(parserU.uokik.toStringBetweenList(options.getTargetDirectory().get(0),options.getTargetDirectory().get(1)));
                    }
                }
                break;
            default: break;
        }
    }
}