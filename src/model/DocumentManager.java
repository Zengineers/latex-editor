package model;

import java.util.HashMap;

import resources.Strings;

public class DocumentManager {
	private static DocumentManager instance;
	
	private HashMap<String, Document> templates = new HashMap<String, Document>();
	private String[] templateNames = { "reportTemplate", "bookTemplate",
							"articleTemplate", "letterTemplate", "emptyTemplate" };
	
	static {
		try {
			instance = new DocumentManager();
		} catch (Exception e) {
			throw new RuntimeException("An error occured!", e);
		}
	}
	
	private DocumentManager() {
		for (String name : templateNames) {
			Document document = new Document();
			document.setContents(Strings.getTemplate(name));
			templates.put(name, document);
		}
	}
	
	public static DocumentManager getInstance() {
		return instance;
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
}
