package learning.languageimplementationpatterns.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.recatalog.util.PropertyList;

public class LanguageVb6 extends Language {
	
	PropertyList symbolFactoryProperties;
	PropertyList symbolProperties;

	public LanguageVb6(PropertyList _properties) {
		super(_properties);
		properties.addProperty("NAME", "VB6");
	}
	
	protected void setSymbols() {
		setPrimitiveTypes();
		setLibs();
	}
	
	private void setLibs() {
		setSystemLib(); 
		setOthersLib();
	}
	
	protected void setPrimitiveTypes() {
		List<String> symbols = new ArrayList<String>() {
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
		
		symbolFactoryProperties = new PropertyList();
		symbolFactoryProperties.addProperty("SYMBOL_TABLE", symbolTable);
		symbolFactoryProperties.addProperty("SYMBOL_TYPE", "PRIMITIVE_TYPE");
		symbolFactoryProperties.addProperty("CONTEXT", null);
		symbolFactoryProperties.addProperty("TYPE", null);
		symbolFactoryProperties.addProperty("LANGUAGE", properties.getProperty("LANGUAGE"));
		symbolFactoryProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		
		symbolProperties = new PropertyList();
		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		symbolProperties.addProperty("CONTEXT", null);
		symbolProperties.addProperty("TYPE", null);
		symbolProperties.addProperty("DEF_MODE", "BUILTIN");     // IMPLICIT, EXPLICIT, BUILTIN
		symbolProperties.addProperty("CATEGORY", "TYPE");        // TYPE, LIB, CLASS
		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");  // SYSTEM
		
		symbolFactoryProperties.addProperty("SYMBOL_PROPERTIES", symbolProperties);
		for(String name : symbols) {
			symbolProperties.addProperty("NAME", name);
				symbolTable.getSymbolFactory().getSymbol(symbolFactoryProperties);
			}
	}
	
	protected void setSystemLib() {

		Map<String,List<String>> libClasses = new HashMap<String,List<String>>();

// Classes in VB Lib ===================================================
		List<String> vbClasses = new ArrayList<String>() {
			{ add("Form");	
			  add("Image");
			  add("TextBox");
			  add("Label");
			}
		};
		libClasses.put("VB",vbClasses);	
		
		symbolProperties = new PropertyList(); // usado para cria os simbolos
		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		symbolProperties.addProperty("CONTEXT", null);
		symbolProperties.addProperty("TYPE", null);
		symbolProperties.addProperty("DEF_MODE", "BUILTIN");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
		symbolProperties.addProperty("CATEGORY", "LIB");          // 
		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
		symbolProperties.addProperty("NAME", "VB");
		
		Scope parentScope = (Scope) symbolFactory("LIB",  symbolProperties);

		for(String clazz : vbClasses) {
			symbolProperties = new PropertyList(); // usado para cria os simbolos
			symbolProperties.addProperty("SCOPE", parentScope);
			symbolProperties.addProperty("CONTEXT", null);
			symbolProperties.addProperty("TYPE", null);
			symbolProperties.addProperty("DEF_MODE", "BUILTIN");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
			symbolProperties.addProperty("CATEGORY", "CLASS");       // 
			symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
			symbolProperties.addProperty("NAME", clazz);
			
			symbolFactory("FORM_CONTROL",  symbolProperties);
		}
		

// ====================================================================
		
// Classes in Threed Lib
		List<String> threedClasses = new ArrayList<String>() {
			{ add("SSCommand");	
			  add("SSPanel");
			  add("SSOption");
			  add("SSCheck");
			}
		};
		libClasses.put("Threed",threedClasses);	
		
		symbolProperties = new PropertyList(); // usado para cria os simbolos
		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		symbolProperties.addProperty("CONTEXT", null);
		symbolProperties.addProperty("TYPE", null);
		symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
		symbolProperties.addProperty("CATEGORY", "LIB");          // 
		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
		symbolProperties.addProperty("NAME", "Threed");
		
		parentScope = (Scope) symbolFactory("LIB",  symbolProperties);

		for(String clazz : threedClasses) {
			symbolProperties = new PropertyList(); // usado para cria os simbolos
			symbolProperties.addProperty("SCOPE", parentScope);
			symbolProperties.addProperty("CONTEXT", null);
			symbolProperties.addProperty("TYPE", null);
			symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
			symbolProperties.addProperty("CATEGORY", "CLASS");       // 
			symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
			symbolProperties.addProperty("NAME", clazz);
			
			symbolFactory("FORM_CONTROL",  symbolProperties);
		}		
		
// ====================================================================
		
// Classes in MSGrid Lib
		List<String> mSGridClasses = new ArrayList<String>() {
			{ add("Grid");	
			}
		};
		libClasses.put("MSGrid",mSGridClasses);		

		symbolProperties = new PropertyList(); // usado para cria os simbolos
		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		symbolProperties.addProperty("CONTEXT", null);
		symbolProperties.addProperty("TYPE", null);
		symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
		symbolProperties.addProperty("CATEGORY", "LIB");          // 
		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
		symbolProperties.addProperty("NAME", "MSGrid");
		
		parentScope = (Scope) symbolFactory("LIB",  symbolProperties);

		for(String clazz : mSGridClasses) {
			symbolProperties = new PropertyList(); // usado para cria os simbolos
			symbolProperties.addProperty("SCOPE", parentScope);
			symbolProperties.addProperty("CONTEXT", null);
			symbolProperties.addProperty("TYPE", null);
			symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
			symbolProperties.addProperty("CATEGORY", "CLASS");       // 
			symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
			symbolProperties.addProperty("NAME", clazz);
			
			symbolFactory("FORM_CONTROL",  symbolProperties);		
		}
// ====================================================================
		
// Classes in MSFlexGridLib Lib
		List<String> mSFlexGridLibClasses = new ArrayList<String>() {
			{ add("MSFlexGrid");	
			}
		};
		libClasses.put("MSFlexGridLib",mSFlexGridLibClasses);					

		symbolProperties = new PropertyList(); // usado para cria os simbolos
		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
		symbolProperties.addProperty("CONTEXT", null);
		symbolProperties.addProperty("TYPE", null);
		symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
		symbolProperties.addProperty("CATEGORY", "LIB");          // 
		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
		symbolProperties.addProperty("NAME", "MSFlexGridLib");
		
		parentScope = (Scope) symbolFactory("LIB",  symbolProperties);

		for(String clazz : mSFlexGridLibClasses) {
			symbolProperties = new PropertyList(); // usado para cria os simbolos
			symbolProperties.addProperty("SCOPE", parentScope);
			symbolProperties.addProperty("CONTEXT", null);
			symbolProperties.addProperty("TYPE", null);
			symbolProperties.addProperty("DEF_MODE", "INCLUDED");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
			symbolProperties.addProperty("CATEGORY", "CLASS");       // 
			symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
			symbolProperties.addProperty("NAME", clazz);
			
			symbolFactory("FORM_CONTROL",  symbolProperties);		
		}		
	}
	
	protected void setOthersLib() {
//		List<String> libs = new ArrayList<String>() {
//			{   add("Threed");
////		    add("MSGrid");
////		    add("MSFlexGridLib");
////		    add("MSMask");
//			}
//		};
//		
//		List classes = new ArrayList<String>() {
//			{ add("SSPanel");	
//			  add("SSCommand");
//			  add("SSFrame");
//			  add("SSOption");
//			  add("SSCheck");
//
//			}
//		};
//		
//		Map<String,List<String>> libClasses = new HashMap<String,List<String>>() {
//			{ put("Threed",classes);	
//			}
//		};
//		
//		symbolFactoryProperties = new PropertyList();
//		symbolFactoryProperties.addProperty("SYMBOL_TABLE", symbolTable);
//		symbolFactoryProperties.addProperty("SYMBOL_TYPE", "LIB");
//		symbolFactoryProperties.addProperty("CONTEXT", null);
//		symbolFactoryProperties.addProperty("TYPE", null);
//		symbolFactoryProperties.addProperty("LANGUAGE", properties.getProperty("LANGUAGE"));
//		symbolFactoryProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
//		
//		symbolProperties = new PropertyList(); // usado para cria os simbolos
//		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
//		symbolProperties.addProperty("CONTEXT", null);
//		symbolProperties.addProperty("TYPE", null);
//		symbolProperties.addProperty("DEF_MODE", "IMPLICIT");     // CRIA CLASSES E OBJECTOS IMPLICITAMENTO
//		symbolProperties.addProperty("CATEGORY", "LIB");         // 
//		symbolProperties.addProperty("SUB_CATEGORY", "SYSTEM");
//		
//		symbolFactoryProperties.addProperty("SYMBOL_PROPERTIES", symbolProperties);
//		
//		for(String name : libClasses.keySet()) {
//			symbolProperties.addProperty("NAME", name);
//				Scope parentScope = (Scope) symbolTable.getSymbolFactory().getSymbol(symbolFactoryProperties);
//
//	            PropertyList symbolFactoryPropClasses = new PropertyList();
//				
//				for(String className : libClasses.get(name).stream().collect(Collectors.toList())) {
//					System.err.println(name + " - " + className);
//	            	symbolFactoryPropClasses.addProperty("SYMBOL_TYPE", "FORM_CONTROL");
//
//	            	PropertyList
//					symbolPropClasses = new PropertyList();
//	            	symbolPropClasses.addProperty("NAME", className);
//	            	symbolPropClasses.addProperty("SCOPE", parentScope);
//	            	symbolPropClasses.addProperty("PARENT_SCOPE", parentScope);
//	            	symbolPropClasses.addProperty("CONTEXT", null);
//	            	symbolPropClasses.addProperty("TYPE", null);
//	            	symbolPropClasses.addProperty("DEF_MODE", "IMPLICIT");     // IMPLICIT, EXPLICIT, BUILTIN
//	            	symbolPropClasses.addProperty("CATEGORY", "CLASS");         // 
//	            	symbolPropClasses.addProperty("SUB_CATEGORY", "FORM");
//	
//	            	symbolFactoryPropClasses.addProperty("SYMBOL_PROPERTIES", symbolPropClasses);
//	    			
//	            	symbolTable.getSymbolFactory().getSymbol(symbolFactoryPropClasses);
//				}
//			}		
	}
	
	private Symbol symbolFactory(String symbolType, PropertyList _symbolProperties) {
		PropertyList symbolFactoryProp = new PropertyList();
		symbolFactoryProp.addProperty("SYMBOL_TYPE", symbolType);
		symbolFactoryProp.addProperty("SYMBOL_PROPERTIES", _symbolProperties);
		return symbolTable.getSymbolFactory().getSymbol(symbolFactoryProp);
	}
	
//	protected void setOthersLib() {
//		List<String> libs = new ArrayList<String>() {
//			{   add("Threed");
//			    add("MSGrid");
//			    add("MSFlexGridLib");
//			    add("MSMask");
//			}
//		};
//		
//		Map libClasses = new HashMap<String, List<String>>();
//        
//		libClasses.put("Threed", new ArrayList<String>());
//		((List)libClasses.get("Threed")).add("SSPanel");
//		((List)libClasses.get("Threed")).add("SSFrame");
//		((List)libClasses.get("Threed")).add("SSCommand");
//		((List)libClasses.get("Threed")).add("SSOption");
//		((List)libClasses.get("Threed")).add("SSCheck");
//		
//		libClasses.put("MSGrid", new ArrayList<String>());
//		((List)libClasses.get("MSGrid")).add("Grid");
//		
//		libClasses.put("MSMask", new ArrayList<String>());
//		((List)libClasses.get("MSMask")).add("MaskEdBox");
//
//		libClasses.put("MSFlexGridLib", new ArrayList<String>());
//		((List)libClasses.get("MSFlexGridLib")).add("MSFlexGrid");
//		
//		symbolFactoryProperties = new PropertyList();
//		symbolFactoryProperties.addProperty("SYMBOL_TABLE", symbolTable);
//		symbolFactoryProperties.addProperty("SYMBOL_TYPE", "LIB");
//		symbolFactoryProperties.addProperty("CONTEXT", null);
//		symbolFactoryProperties.addProperty("TYPE", null);
//		symbolFactoryProperties.addProperty("LANGUAGE", properties.getProperty("LANGUAGE"));
//		symbolFactoryProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
//		
//		symbolProperties = new PropertyList();
//		symbolProperties.addProperty("SCOPE", symbolTable.getGlobalScope());
//		symbolProperties.addProperty("CONTEXT", null);
//		symbolProperties.addProperty("TYPE", null);
//		symbolProperties.addProperty("DEF_MODE", "IMPLICIT");     // IMPLICIT, EXPLICIT, BUILTIN
//		symbolProperties.addProperty("CATEGORY", "LIB");         // 
//		symbolProperties.addProperty("SUB_CATEGORY", "OTHERS");
//		
//		symbolFactoryProperties.addProperty("SYMBOL_PROPERTIES", symbolProperties);
//		
//		for(String libName : libs) {
//			symbolProperties.addProperty("NAME", libName);
//			Scope parentScope = (Scope)symbolTable.getSymbolFactory().getSymbol(symbolFactoryProperties);
//
//// inicio
//            List<String> classList =  libClasses.entrySet().
//            ArrayList<String>  classes = new ArrayList<String>(classList);
//
//			
//            PropertyList symbolFactoryPropClasses = new PropertyList();
//			symbolFactoryPropClasses.addProperty("SYMBOL_TABLE", symbolTable);
//			symbolFactoryPropClasses.addProperty("SYMBOL_TYPE", "FORM");  
//			
//            for(String className : classes)	{	
//            	symbolFactoryPropClasses.addProperty("NAME", className);
//				
//            	PropertyList
//				symbolPropClasses = new PropertyList();
//            	symbolPropClasses.addProperty("SCOPE", parentScope);
//            	symbolPropClasses.addProperty("PARENT_SCOPE", parentScope);
//            	symbolPropClasses.addProperty("CONTEXT", null);
//            	symbolPropClasses.addProperty("TYPE", null);
//            	symbolPropClasses.addProperty("DEF_MODE", "IMPLICIT");     // IMPLICIT, EXPLICIT, BUILTIN
//            	symbolPropClasses.addProperty("CATEGORY", "CLASS");         // 
//            	symbolPropClasses.addProperty("SUB_CATEGORY", "FORM");
//
//            	symbolFactoryPropClasses.addProperty("SYMBOL_PROPERTIES", symbolPropClasses);
//    			
//            	symbolTable.getSymbolFactory().getSymbol(symbolFactoryPropClasses);
//
//            }
//// fim				
//		}
//	}
}