package model;

import java.util.HashMap;

import resources.Strings;

public class DocumentManager {
	private HashMap<String, Document> templates;
	
	public DocumentManager() {
		templates = new HashMap<String, Document>();
		
		Document document = new Document();
		document.setContents(getContents("reportTemplate"));
		templates.put("reportTemplate", document);
		
		document = new Document();
		document.setContents(getContents("bookTemplate"));
		templates.put("bookTemplate", document);
		
		document = new Document();
		document.setContents(getContents("articleTemplate"));
		templates.put("articleTemplate", document);
		
		document = new Document();
		document.setContents(getContents("letterTemplate"));
		templates.put("letterTemplate", document);
		
		document = new Document();
		templates.put("emptyTemplate", document);
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
	public String getContents(String type) {
		return Strings.getTemplate(type);
	}
	
}
