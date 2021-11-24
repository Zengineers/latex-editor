package model;

import java.util.HashMap;

import resources.Strings;

public class DocumentManager {
	private static DocumentManager instance = null;
	
	private Document currentDocument;
	private HashMap<String, Document> templates;
	private String editedContents;
	private String[] templateNames = { "reportTemplate", "bookTemplate",
							"articleTemplate", "letterTemplate", "emptyTemplate" };
	
	private DocumentManager() {
		templates = new HashMap<String, Document>();
		for (String name : templateNames) {
			Document document = new Document();
			document.setContents(Strings.getTemplate(name));
			templates.put(name, document);
		}
	}
	
	public static DocumentManager getInstance() {
		if (instance == null)
			instance = new DocumentManager();
		return instance;
	}
	
	public Document createDocument(String type) {
		return templates.get(type).clone();
	}
	
	public Document getCurrentDocument() {
		return currentDocument;
	}
	
	public void setCurrentDocument(Document currentDocument) {
		this.currentDocument = currentDocument;
	}

	public String getEditedContents() {
		return editedContents;
	}

	public void setEditedContents(String editedContents) {
		this.editedContents = editedContents;
	}
	
	public void dispose() {
		instance = null;
	}
}
