package agh.wk;

import java.util.ArrayList;

public class Element {
    private ElementType type;
    private String title="";
    private String listTitle="";
    private String content="";
    private Element parent = null;
    private ArrayList<Element> children = new ArrayList<>();
    private boolean flag;

    public Element(ElementType type){
        this.type=type;
        if(this.type.equals(ElementType.Ustawa))
            this.listTitle="Ustawa";
    }

    public int depth(){
        if(parent == null) return 0;
        return this.parent.depth()+1;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }

    public void setListTitle(String title){
        this.listTitle = title;
    }

    public String getListTitle(){
        return this.listTitle;
    }

    public void addChild(Element child){
        children.add(child);
    }

    public ArrayList<Element> getChildren(){
        return this.children;
    }

    public void setType(ElementType type){
        this.type = type;
    }

    public ElementType getType(){
        return this.type;
    }

    public void setParent(Element parent){
        this.parent=parent;
    }

    public Element getParent(){
        return this.parent;
    }

    public void addContent(String content){
        this.content += content;
    }

    public String getContent(){
        return this.content;
    }

    public Element getElementTitled(String title){
        if(this.listTitle.equals(title)) return this;
        Element tmp = null;
        for(Element child : children){
            tmp = child.getElementTitled(title);
            if(tmp!=null) return tmp;
        }
        return tmp;
    }

    /*public String toStringBetween(String fromTitle, String toTitle){
        String result="";

    }*/

    public boolean isHigherThan(Element element){
        return this.getType().isHigherThan(element.getType());
    }

    @Override
    public String toString() {
        String result="";
        result += this.title;
        result += this.content;
        for(Element child : children)
            result += child.toString();
        return result;
    }

    public String toStringList(){
        String result="";
        for(int i = 0; i<depth(); i++) result += "-";
        result += listTitle + "\n";
        for(Element child : children) result += child.toStringList();
        return result;
    }
}
