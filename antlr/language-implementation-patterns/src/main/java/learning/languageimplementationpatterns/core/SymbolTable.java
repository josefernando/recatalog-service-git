package learning.languageimplementationpatterns.core;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.util.PropertyList;

public interface SymbolTable {
	
	
	public Scope getGlobalScope();
    
    public SymbolFactory getSymbolFactory() ;
    
    public PropertyList getProperties();
	
	public ContextData addContextData(ParserRuleContext ctx);
	
	public ContextData getContextData(ParserRuleContext ctx);

}
