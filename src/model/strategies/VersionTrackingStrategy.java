package model.strategies;

import java.util.List;

import model.Document;

public interface VersionTrackingStrategy {
	public void putVersion(Document document);
	public Document getVersion();
	public void setEntireHistory(List<Document> documents);
	public List<Document> getEntireHistory();
	public void removeVersion();
}
