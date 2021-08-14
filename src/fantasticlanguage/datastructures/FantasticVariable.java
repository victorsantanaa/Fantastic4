package fantasticlanguage.datastructures;

public class FantasticVariable extends FantasticSymbol {

    public static final int NUMBER=0;
    public static final int TEXT  =1;

    private int type;
    private String value;
    private boolean init = false;

    public FantasticVariable(String name, int type, String value) {
        super(name);
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    @Override
    public String toString() {
        return "FantasticVariable [name=" + name + ", type=" + type + ", value=" + value + "]";
    }

    public String generateJavaCode() {
        String str;
        if (type == NUMBER) {
            str = "double ";
        }
        else {
            str = "String ";
        }
        return str + " "+super.name+";";
    }



}
