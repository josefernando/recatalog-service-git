package learning.languageimplementationpatterns.core;

import br.com.recatalog.util.PropertyList;

public class SubSymbol extends ScopeSymbol implements MethodSymbol {

	public SubSymbol(PropertyList _properties) {
		super(_properties);
	}
}
