package fantasticlanguage.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class FantasticSymbolTable {

    private HashMap<String, FantasticVariable> map;

    public FantasticSymbolTable() {
        map = new HashMap<String, FantasticVariable>();

    }

    public void add(FantasticVariable symbol) {
        map.put(symbol.getName(), symbol);
    }

    public boolean exists(String symbolName) {
        return map.get(symbolName) != null;
    }

    public FantasticVariable get(String symbolName) {
        return map.get(symbolName);
    }

    public ArrayList<FantasticSymbol> getAll(){
        ArrayList<FantasticSymbol> lista = new ArrayList<FantasticSymbol>();
        for (FantasticSymbol symbol : map.values()) {
            lista.add(symbol);
        }
        return lista;
    }

    public void replace(String id, FantasticVariable newSymbol){
        map.replace(id, newSymbol);
    }

    public Collection<FantasticVariable> values() {
        return map.values();
    }



}
