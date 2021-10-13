package learning.languageimplementationpatterns.core;

import br.com.recatalog.util.PropertyList;

public class StandardModuleSymbol extends ScopedSymbol implements MethodSymbol{

	public StandardModuleSymbol(PropertyList properties) {
		super(properties);
	}
}