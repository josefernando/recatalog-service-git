package learning.languageimplementationpatterns.core;

import org.antlr.v4.runtime.ParserRuleContext;

import br.com.recatalog.util.PropertyList;

public class SymbolFactoryVisualBasic6 implements SymbolFactory{
	public static final String VARIABLE = "VARIABLE";
	public static final String PRIMITIVE_TYPE = "PRIMITIVE_TYPE";

	@Override
	public Symbol getSymbol(PropertyList _properties) {
		String symType = (String)_properties.getProperty("SYMBOL_TYPE").toString();
		switch (symType){
	        case PRIMITIVE_TYPE:
	    	return createPrimitiveTypeSymbol(_properties);	
//	        case CLASS:
//	    	return createClassSymbol(_properties);
	        case VARIABLE:
	    	return createVariableSymbol(_properties);
//	        case FUNCTION:
//	    	return createFunctionSymbol(_properties);	    	
//	        case LIBRARY:
//	    	return createLibrarySymbol(_properties);	
//	        case IMPLICIT:
//	    	return createImplicitSymbol(_properties);
//			case MODULE:
//				return createModuleSymbol(_properties);
//			case MODULE_FRM:
//				return createModuleFrmSymbol(_properties);
//			case MODULE_BAS:
//				return createModuleBasSymbol(_properties);
//			case MODULE_CLS:
//				return createModuleClsSymbol(_properties);				
//			case BUILTIN:
//				return createBuiltinSymbol(_properties);
//			case TYPE:
//				return createTypeSymbol(_properties);
//			case ENUM:
//				return createEnumSymbol(_properties);
//			case GUI:
//				return createGuiSymbol(_properties);
//			case GUI_ATTRIBUTE:
//				return createGuiAttributeSymbol(_properties);	
//			case GUI_PROPERTY:
//				return createGuiPropertySymbol(_properties);				
//			case METHOD:
//				return createMethodSymbol(_properties);
//			case LABEL:
//				return createLabelSymbol(_properties);				
//			case STORED_PROCEDURE:
//				return createStoredProcedureSymbol(_properties);				
//			case GLOBAL_SCOPE: // è tratadp como symbol...
//				return createGlobalScope(_properties);				
//			case VISIBILITY_SCOPE: // è tratadp como symbol...
//				return createVisibilityScope(_properties);				
		default:
			throw new RuntimeException("** Error*** - Invalid Symbol type to create: "  
					+ ((ParserRuleContext)_properties.getProperty("CONTEXT")).getClass().getSimpleName()
					+ " SymbolType: " + symType);			
		}
	}
	
	private Symbol createVariableSymbol(PropertyList propreties) {
		return new VariableSymbol(propreties);
	}
	
	private Symbol createPrimitiveTypeSymbol(PropertyList propreties) {
		return new PrimitiveTypeSymbol(propreties);
	}
}