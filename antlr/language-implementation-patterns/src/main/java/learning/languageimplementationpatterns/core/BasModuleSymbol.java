package learning.languageimplementationpatterns.core;

import br.com.recatalog.util.PropertyList;

public class BasModuleSymbol extends ScopeSymbol implements ModuleSymbol{

	public BasModuleSymbol(PropertyList properties) {
		super(properties);
	}
}