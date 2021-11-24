package model;

import javax.swing.JOptionPane;

import model.strategies.StableStrategy;
import model.strategies.StrategyFactory;
import model.strategies.VersionTrackingStrategy;
import model.strategies.VolatileStrategy;

public class VersionTrackingManager {
	private static VersionTrackingManager instance = null;

	private VersionTrackingStrategy versionTrackingStrategy;
	private StrategyFactory strategyFactory;
	private DocumentManager documentManager;
	private boolean isEnabled;
	private boolean isRollbackSuccessful;
	private String strategyType;


	private VersionTrackingManager() {
		strategyFactory = new StrategyFactory();
		versionTrackingStrategy = strategyFactory.createStrategy("volatileStrategy");
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

	public boolean isRollbackSuccessful() {
		return isRollbackSuccessful;
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
		if(isVolatileType() && isVolatileInstance()) {
			enable();
		}
		else if(isStableType() && isVolatileInstance()) {
			copyHistoryFromPreviousStrategy("stableStrategy");
			enable();
		}
		else if(isVolatileType() && isStableInstance()) {
			copyHistoryFromPreviousStrategy("volatileStrategy");
			enable();
		}
		else if(isStableType() && isStableInstance()) {
			enable();
		}
	}

	public void changeStrategy() {
		if(isStableType() && isVolatileInstance()) {
			copyHistoryFromPreviousStrategy("stableStrategy");
			enable();
		}
		else if(isVolatileType() && isStableInstance()) {
			copyHistoryFromPreviousStrategy("volatileStrategy");
			enable();
		}
	}

	public void rollback() {
		isRollbackSuccessful = false;
		if(!isEnabled) {
			showMessageDialog("Strategy is not enabled");
		}
		else {
			Document previousDocumentVersion = versionTrackingStrategy.getVersion();
			if(previousDocumentVersion == null) {
				showMessageDialog("No version available");
			}
			else {
				isRollbackSuccessful = true;
				documentManager.setCurrentDocument(previousDocumentVersion);
				versionTrackingStrategy.removeVersion();
			}
		}
	}
	
	private void copyHistoryFromPreviousStrategy(String newStrategyType) {
		VersionTrackingStrategy newStrategy = strategyFactory.createStrategy(newStrategyType);
		newStrategy.setEntireHistory(versionTrackingStrategy.getEntireHistory());
		versionTrackingStrategy = newStrategy;
	}
	
	private boolean isStableInstance() {
		return versionTrackingStrategy instanceof StableStrategy;
	}

	private boolean isVolatileInstance() {
		return versionTrackingStrategy instanceof VolatileStrategy;
	}

	private boolean isStableType() {
		return strategyType.equals("stable");
	}

	private boolean isVolatileType() {
		return strategyType.equals("volatile");
	}
	
	private void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(null, message, "InfoBox", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
