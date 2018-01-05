package agh.wk;

public enum ElementType {
    Ustawa,
    Dzial,
    Rozdzial,
    Sekcja,
    Artykul,
    Ustep,
    Punkt,
    Litera;

    public ElementType tierUp(){
        switch(this){
            case Litera: return Punkt;
            case Punkt: return Ustep;
            case Ustep: return Artykul;
            case Artykul: return Sekcja;
            case Sekcja: return Rozdzial;
            case Rozdzial: return Dzial;
            case Dzial: return Ustawa;
            case Ustawa: return null;
            default: return null;
        }
    }

    public boolean isHigherThan(ElementType type){
        while(type.tierUp()!=null){
            if(type.tierUp()==this)
                return true;
            type=type.tierUp();
        }
        return false;
    }
}
