package learning.languageimplementationpatterns.core.visualbasic6;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import br.com.recatalog.languageimplementationpatterns.parser.visualbasic6.VisualBasic6CompUnitParser;
import br.com.recatalog.languageimplementationpatterns.parser.visualbasic6.VisualBasic6CompUnitParserBaseListener;
import br.com.recatalog.util.BicamSystem;
import br.com.recatalog.util.PropertyList;

public class VisualBasic6Module extends VisualBasic6CompUnitParserBaseListener {
	 PropertyList properties;
	 private String moduleName;
	 
	 public VisualBasic6Module(PropertyList _properties) {
		 this.properties = _properties;
	 }
	 
	 public VisualBasic6Module() {
		 this.properties = new PropertyList();
	 }
	 
	 public PropertyList getProperties() {
		 return properties;
	 }
	 
	 public String getModuleName() {
		 return (String)properties.mustProperty("NAME");
	 }
	
	public void enterAttributeStmt(VisualBasic6CompUnitParser.AttributeStmtContext ctx) {
		if(ctx.Name.getText().equalsIgnoreCase("VB_NAME")) {
			moduleName = ctx.Values.getText().replace("\"", "");
		}
	}
	
	public void exitStartRule(VisualBasic6CompUnitParser.StartRuleContext ctx) {
		if(moduleName == null) {
			BicamSystem.printLog("ERROR", "Module name not found on source file: " 
		                          + properties.getProperty("FILE_PATH"));
		}
		else {
			properties.addProperty("NAME", moduleName);
		}
		
		if(((String)properties.mustProperty("FILE_PATH")).toUpperCase().endsWith("CLS")) {
			properties.addProperty("MODULE_TYPE", "CLS");
		}
		if(((String)properties.mustProperty("FILE_PATH")).toUpperCase().endsWith("FRM")) {
			properties.addProperty("MODULE_TYPE", "FRM");
		}
		if(((String)properties.mustProperty("FILE_PATH")).toUpperCase().endsWith("BAS")) {
			properties.addProperty("MODULE_TYPE", "BAS");
		}
	}
	
	public static void main(String[] args) {
		// Parsing source file e generates AST
		PropertyList properties = new PropertyList();
		properties.addProperty("FILE_PATH", "C:\\workspace\\antlr\\parser.visualbasic6\\src\\main\\resources\\R1PAB001\\R1FAB001.FRM");
		VisualBasic6ParserCompUnit parser = new VisualBasic6ParserCompUnit(properties);
		System.err.println("Total time: " + parser.getElapsedTime());
		System.err.println("Total Errors: " + parser.getNumErrors());

		// walking AST
//		ParseTree tree = parser.getAstree();
		ParseTree tree = (ParseTree) parser.getProperties().mustProperty("ASTREE");
        ParseTreeWalker walker = new ParseTreeWalker();
      
        VisualBasic6Module visualBasic6module = new VisualBasic6Module(properties);
        walker.walk(visualBasic6module, tree);        // walk parse tree 
        
        System.err.println("Module name: " + visualBasic6module.getModuleName());
        System.err.println(properties.toString());
	}
}