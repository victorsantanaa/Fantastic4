package fantasticlanguage.ast;


import fantasticlanguage.datastructures.FantasticVariable;

public class CommandLeitura extends AbstractCommand {

    private String id;
    private FantasticVariable var;

    public CommandLeitura (String id, FantasticVariable var) {
        this.id = id;
        this.var = var;
    }
    @Override
    public String generateJavaCode() {
        // TODO Auto-generated method stub
        return id +"=" + (var.getType()== FantasticVariable.NUMBER? "Double.parseDouble(_key.nextLine());" : "_key.nextLine();");
    }
    @Override
    public String toString() {
        return "CommandLeitura [id=" + id + "]";
    }

}
