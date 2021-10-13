package learning.languageimplementationpatterns.core;

import java.util.stream.Stream;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public  abstract class ScopedSymbol extends Symbol implements Scope {
	SymbolTable symbolTable;
	Scope globalScope;
	Scope enclosingScope;
	
	Scope nextUpScope;
	ScopeSymbolList symbols;
	
	public ScopedSymbol(PropertyList _properties) {
		super(_properties);
		this.symbolTable = (SymbolTable) properties.getProperty("SYSTEM_TABLE");
		this.symbols = new ScopeSymbolList();
	}
	
	@Override
	public void setEnclosingScope(Scope _enclosingScope) {
		this.enclosingScope = _enclosingScope;
	}
	
	@Override
	public Scope getNextUpScope() {
		if(this.nextUpScope != null) {
			return this.nextUpScope;
		}
		else return enclosingScope;
	}
	
	@Override
	public void setNextUpScope(Scope _nextUpScope) {
		this.nextUpScope = _nextUpScope;
	}	
	
	
	@Override
	public Scope getEnclosingScope() {
		return this.enclosingScope;
	}
	

	@Override
	public Symbol resolve(PropertyList _properties) {
		String nameToResolve = (String)_properties.getProperty("NAME_TO_RESOLVE");
		Stream<Symbol> symStream = symbols.get(nameToResolve);
		if(symStream == null) {
			return getNextUpScope().resolve(_properties);
		}
		if(symStream.toArray().length > 1) {
			ParserRuleContext ctx = (ParserRuleContext)_properties.getProperty("CONTEXT");
			int line = ctx.getStart().getLine();
			int positionInLine = ctx.getStart().getCharPositionInLine();
			String moduleName = (String) _properties.mustProperty("MODULE_NAME");
			BicamSystem.printLog("ERROR", "Duplicated symbol not resolved: " 
			                     + nameToResolve + " module name: " 
					             + moduleName + " line: " + line
					             + " position: " + positionInLine);
		}
		return symStream.findFirst().get();
	}

	@Override
	public void define( Symbol _sym) {
		symbols.add(_sym);
		_sym.setScope(this);
	}
	
	@Override
	public SymbolTable getSymbolTable() {
		return symbolTable;
	}
	
	
	@Override
	public Scope getGlobalScope() {
		return globalScope;
	}
}