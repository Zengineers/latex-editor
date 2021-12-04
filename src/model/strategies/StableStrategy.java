package model.strategies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Document;

public class StableStrategy implements VersionTrackingStrategy{
	private String versionID = "";
	
	@Override
	public void putVersion(Document document) {
		String filename = document.getVersionID() + ".tex";
		document.save(filename);
		versionID = document.getVersionID();
	}

	@Override
	public Document getVersion() {
		if(versionID.equals(""))
			return null;
		
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(versionID + ".tex"));
			while(scanner.hasNextLine()) {
				fileContents = fileContents + scanner.nextLine() + "\n";
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Document document = new Document();
		document.setVersionID(versionID);
		document.setContents(fileContents);
		return document;
	}

	@Override
	public void setEntireHistory(List<Document> documents) {
		for (Document document : documents) {
			document.save(document.getVersionID() + ".tex");
		}
		if(documents.size() > 0)
			versionID = documents.get(documents.size()-1).getVersionID();
		else
			versionID = "";
	}

	@Override
	public List<Document> getEntireHistory() {
		List<Document> history = new ArrayList<Document>();
		if(versionID.equals(""))
			return history;
		int n = Integer.parseInt(versionID);
		for(int i = 0; i <= n; i++) {
			String fileContents = "";
			try {
				Scanner scanner = new Scanner(new FileInputStream(i + ".tex"));
				while(scanner.hasNextLine()) {
					fileContents = fileContents + scanner.nextLine() + "\n";
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Document document = new Document();
			document.setContents(fileContents);
			document.setVersionID(String.valueOf(i));
			history.add(document);
		}
		return history;
	}

	@Override
	public void removeVersion() {
		String filename = System.getProperty("user.dir") + "\\" + versionID + ".tex";
		File versionFile = new File(filename);
		// File deletion does not work
		// Probably has to do with FileInputStream or scanner
		versionFile.delete();
		int n = Integer.parseInt(versionID);
		if(n == 0)
			versionID = "";
		else
			versionID = (n-1) + "";
	}
	
	@Override
	public void clearHistory() {
		int versionsCount = Integer.parseInt(versionID);
		for (int id=0; id<versionsCount; id++) {
			String filename = System.getProperty("user.dir") + "\\" + String.valueOf(id) + ".tex";
			File versionFile = new File(filename);
			versionFile.delete();
		}
	}

}
