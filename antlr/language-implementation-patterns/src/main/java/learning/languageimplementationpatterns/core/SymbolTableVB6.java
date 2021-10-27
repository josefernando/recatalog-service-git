package learning.languageimplementationpatterns.core;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.core.ContextTreeData;
import br.com.recatalog.core.Symbol;
import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public class SymbolTableVB6 implements SymbolTable {
	PropertyList properties;
	Scope  globalScope;
	Language language;
	
	IdentityHashMap<ParserRuleContext, ContextData> contextDataMap;
	
	SymbolFactory symbolFactory;
	
	IdentityHashMap<Symbol, ArrayList<ParserRuleContext>> whereUsed; 
	IdentityHashMap<Symbol, ArrayList<ContextData>> whereUsedCdt; 
	IdentityHashMap<ParserRuleContext, Symbol> ModuleMap;

	public SymbolTableVB6() {
		symbolFactory = new SymbolFactoryVb6();
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

		contextDataMap = new IdentityHashMap<ParserRuleContext, ContextData>();
		whereUsed   = new IdentityHashMap<Symbol, ArrayList<ParserRuleContext>>();
		whereUsedCdt = new IdentityHashMap<Symbol, ArrayList<ContextData>>(); 
		ModuleMap = new IdentityHashMap<ParserRuleContext, Symbol>();
	}
	
	public Scope getGlobalScope() {
		return globalScope;
	}
    
    public SymbolFactory getSymbolFactory() {
    	return symbolFactory;
    }

	@Override
	public PropertyList getProperties() {
		return properties;
	}
	
	@Override
	public ContextData addContextData(ParserRuleContext ctx) {
		if(getContextData(ctx) != null) {
			BicamSystem.printLog("ERROR", String.format("CTD already exists in line %d at position %d", ctx.start.getLine(), ctx.start.getCharPositionInLine()));
		}
		contextDataMap.put(ctx, new ContextData(ctx));
        return getContextData(ctx);
	}
	
	@Override
	public ContextData getContextData(ParserRuleContext ctx) {
		return contextDataMap.get(ctx);
	}
    
    public String toString() {
    	return globalScope.toString();
    }
    
    public static void main(String[] args) {
    	SymbolTableVB6 st = new SymbolTableVB6();
    	System.err.println(st.toString());
    }
}