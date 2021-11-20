package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionTrackingStrategy;
import model.strategies.VersionTrackingStrategy;
import model.strategies.VolatileVersionTrackingStrategy;

public class VersionTrackingManager {
	private static VersionTrackingManager instance = null;

	private VersionTrackingStrategy versionTrackingStrategy;
	private DocumentManager documentManager;
	private boolean isEnabled;
	private String strategyType;

	private VersionTrackingManager() {
		versionTrackingStrategy = new VolatileVersionTrackingStrategy();
		documentManager = DocumentManager.getInstance();
	}
	
	public static VersionTrackingManager getInstance() {
		if (instance == null)
			instance = new VersionTrackingManager();
		return instance;
	}
	
	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	public void setVersionTrackingStrategy(VersionTrackingStrategy strategy) {
		this.versionTrackingStrategy = strategy;
	}

	public VersionTrackingStrategy getStrategy() {
		return versionTrackingStrategy;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void enable() {
		isEnabled = true;
	}

	public void disable() {
		isEnabled = false;
	}	

	public void  putVersion(Document document) {
		versionTrackingStrategy.putVersion(document);
	}
	
	public void enableStrategy() {
		if(strategyType.equals("volatile") && versionTrackingStrategy instanceof VolatileVersionTrackingStrategy) {
			enable();
		}
		else if(strategyType.equals("stable") && versionTrackingStrategy instanceof VolatileVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new StableVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionTrackingStrategy.getEntireHistory());
			versionTrackingStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && versionTrackingStrategy instanceof StableVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new VolatileVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionTrackingStrategy.getEntireHistory());
			versionTrackingStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("stable") && versionTrackingStrategy instanceof StableVersionTrackingStrategy) {
			enable();
		}
	}

	public void changeStrategy() {
		//String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("stable") && versionTrackingStrategy instanceof VolatileVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new StableVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionTrackingStrategy.getEntireHistory());
			versionTrackingStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && versionTrackingStrategy instanceof StableVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new VolatileVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionTrackingStrategy.getEntireHistory());
			versionTrackingStrategy = newStrategy;
			enable();
		}
	}

	public void rollback() {
		if(isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document doc = versionTrackingStrategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				versionTrackingStrategy.removeVersion();
				documentManager.setCurrentDocument(doc);
			}
		}
	}
	
}
