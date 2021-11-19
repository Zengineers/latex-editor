package model;

import java.util.HashMap;

import resources.Strings;

public class DocumentManager {
	
	private HashMap<String, Document> templates = new HashMap<String, Document>();
	private String[] templateNames = {
			"reportTemplate", "bookTemplate",
			"articleTemplate", "letterTemplate",
			"emptyTemplate"
	};
	
	public DocumentManager() {
		for (String name : templateNames) {
			Document document = new Document();
			document.setContents(Strings.getTemplate(name));
			templates.put(name, document);
		}
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
}
