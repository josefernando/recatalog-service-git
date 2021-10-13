package learning.languageimplementationpatterns.core;

import java.util.ArrayList;
import java.util.List;

import br.com.recatalog.util.PropertyList;

public class LanguageVb6 extends Language {

	public LanguageVb6(PropertyList _properties) {
		super(_properties);
		properties.addProperty("NAME", "VB6");
	}
	
	protected void setSymbols() {
		setPrimitiveTypes();
	}
	
	protected void setPrimitiveTypes() {
		List<String> types = new ArrayList<String>() {
			{   add("Any");
			    add("Numeric");
				add("Byte");
				add("Integer");
				add("Long");
				add("String");
				add("Single");
				add("Double");
				add("Currency");
				add("Date");
				add("Boolean");
				add("Object");
				add("Variant");
				add("Void");
			}
		};
		
		PropertyList prop1 = new PropertyList();
		prop1.addProperty("SYMBOL_TABLE", symbolTable);
		prop1.addProperty("SYMBOL_TYPE", "PRIMITIVE_TYPE");
		prop1.addProperty("CONTEXT", null);
		prop1.addProperty("TYPE", null);
		prop1.addProperty("LANGUAGE", properties.getProperty("LANGUAGE"));
		prop1.addProperty("SCOPE", symbolTable.getGlobalScope());
		
		for(String typeName : types) {
			prop1.addProperty("NAME", typeName);
				symbolTable.getSymbolFactory().getSymbol(prop1);
			}
	}
}