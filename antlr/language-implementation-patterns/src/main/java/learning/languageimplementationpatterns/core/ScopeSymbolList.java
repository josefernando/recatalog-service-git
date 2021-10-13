	package learning.languageimplementationpatterns.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

public class ScopeSymbolList {
	// Map<symbolName, Map<symbolCategory,symbol>
	Map<String, Map<String,Symbol>> symbols;
	
	public ScopeSymbolList() {
		symbols = new HashMap<String,Map<String,Symbol>>();
	}
	
	public void add(Symbol _sym) {
		Map<String,Symbol> symMap = symbols.get(_sym.getName());
		if(symMap == null) {
			symMap = new HashMap<String,Symbol>();
			symbols.put(_sym.getName(), symMap);
		}
		symMap.put(_sym.getClass().getSimpleName(), _sym);
	}
	
	public Stream<Symbol> get(String _name, String... _category) {
		Map<String,Symbol> symMap = symbols.get(_name);
		if(symMap.size() > 0) return symMap.values().stream();
		return null;
	}
	
	public Map<String, Map<String,Symbol>> getSymbols(){
		return symbols;
	}
	
	public Set<Entry<String, Map<String, Symbol>>> getEntries(){
		return symbols.entrySet();
	}
}