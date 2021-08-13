package fantasticlanguage.datastructures;

public abstract class FantasticSymbol {

    protected String name;

    public abstract String generateJavaCode();
    public FantasticSymbol(String name) {
        this.name = name;

    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "FantasticSymbol [name=" + name + "]";
    }




}
