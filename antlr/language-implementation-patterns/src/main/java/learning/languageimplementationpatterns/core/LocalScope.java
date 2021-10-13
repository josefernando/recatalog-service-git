package learning.languageimplementationpatterns.core;

import br.com.recatalog.util.PropertyList;

public class LocalScope extends BaseScope {

	public LocalScope(Scope scope, PropertyList properties) {
		super(properties);
	}
}