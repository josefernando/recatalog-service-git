package learning.languageimplementationpatterns.core;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.core.ContextTreeData;
import br.com.recatalog.core.Symbol;
import br.com.recatalog.util.PropertyList;

public class SymbolTable  {
	PropertyList properties;
	Scope  globalScope;
	Language language;
	
	IdentityHashMap<ParserRuleContext, ContextTreeData> ctdMap;
	
	SymbolFactory symbolFactory;
	
	IdentityHashMap<Symbol, ArrayList<ParserRuleContext>> whereUsed; 
	IdentityHashMap<Symbol, ArrayList<ContextTreeData>> whereUsedCdt; 
	IdentityHashMap<ParserRuleContext, Symbol> ModuleMap;


	public SymbolTable() {
		symbolFactory = new SymbolFactoryVisualBasic6();
		properties = new PropertyList();
		PropertyList prop = new PropertyList();
		prop.addProperty("NAME", "GLOBAL");
		prop.addProperty("SYMBOL_TABLE", this);
		prop.addProperty("SCOPE", null);
		prop.addProperty("SYMBOL_FACTORY", symbolFactory);


		this.globalScope = new GlobalScope(prop);

		PropertyList prop1 = new PropertyList();
		prop1.addProperty("CASE_SENSITIVE", false);
		prop1.addProperty("SYMBOL_TABLE", this);

		language = new LanguageVb6(prop1);
		
		this.properties.addProperty("LANGUAGE", language);

		ctdMap = new IdentityHashMap<ParserRuleContext, ContextTreeData>();
		whereUsed   = new IdentityHashMap<Symbol, ArrayList<ParserRuleContext>>();
		whereUsedCdt = new IdentityHashMap<Symbol, ArrayList<ContextTreeData>>(); 
		ModuleMap = new IdentityHashMap<ParserRuleContext, Symbol>();

//		init();
	}
	
//	private void init() {
//		setGlobalScope();
//	}
	
	
	public Scope getGlobalScope() {
		return globalScope;
	}
	
//    public void  setGlobalScope() {
//    	PropertyList prop = new PropertyList();
//    	prop.addProperty("NAME", "GLOBAL");
//    	prop.addProperty("LANGUAGE", language);
//    	prop.addProperty("SCOPE", null);
//    	globalScope = new GlobalScope(prop);
//    }
    
    public SymbolFactory getSymbolFactory() {
    	return symbolFactory;
    }
    
    public String toString() {
    	return globalScope.toString();
    }
    
    public static void main(String[] args) {
    	PropertyList propx = new PropertyList();
    	SymbolTable st = new SymbolTable();
//    	PropertyList properties = new PropertyList();
//    	properties.addProperty("NAME", "VB6");
//    	properties.addProperty("CASE_SENSITIVE", false);
//    	Language languageVb6 = new LanguageVb6(properties);
//    	properties.clear();
//    	properties.addProperty("LANGUAGE", languageVb6);
    	
    	System.err.println(st.toString());
    }
}