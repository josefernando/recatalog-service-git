package learning.languageimplementationpatterns.core;

import br.com.recatalog.util.PropertyList;

public class ClassSymbol extends ScopedSymbol implements Scope {

    ClassSymbol superClass;

	public ClassSymbol(PropertyList properties) {
		super(properties);
	}
	
    public Scope getParentScope() {
        if ( superClass==null ) return enclosingScope; // globals
        return superClass; // if not root object, return super
    }


	@Override
	public Scope getEnclosingScope() {
		return enclosingScope;
	}

	@Override
	public Symbol resolve(PropertyList properties) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void define(PropertyList properties, Symbol sym) {
		// TODO Auto-generated method stub
	}	
}