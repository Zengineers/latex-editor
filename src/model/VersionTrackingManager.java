package model;

import javax.swing.JOptionPane;

import model.strategies.StableVersionTrackingStrategy;
import model.strategies.VersionTrackingStrategy;
import model.strategies.VolatileVersionTrackingStrategy;
import view.LatexEditorView;

public class VersionTrackingManager {
	private static VersionTrackingManager instance;

	private VersionTrackingStrategy versionsStrategy;
	private LatexEditorView latexEditorView;
	private boolean isEnabled;
	private String strategyType;
	
	static {
		try {
			instance = new VersionTrackingManager();
		} catch (Exception e) {
			throw new RuntimeException("An error occured!", e);
		}
	}

	private VersionTrackingManager() {}
	
	public static VersionTrackingManager getInstance() {
		return instance;
	}
	
	/* TODO remove after LatexEditorView refactor */
	public LatexEditorView getLatexEditorView() {
		return latexEditorView;
	}

	/* TODO remove after LatexEditorView refactor */
	public void setLatexEditorView(LatexEditorView latexEditorView) {
		this.latexEditorView = latexEditorView;
	}
	
	public String getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(String strategyType) {
		this.strategyType = strategyType;
	}

	public void setVersionTrackingStrategy(VersionTrackingStrategy strategy) {
		this.versionsStrategy = strategy;
	}

	public VersionTrackingStrategy getStrategy() {
		return versionsStrategy;
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
	
//	public VersionTrackingManager(VersionTrackingStrategy versionsStrategy, LatexEditorView latexEditorView) {
//		this.versionsStrategy = versionsStrategy;
//		this.latexEditorView = latexEditorView;
//	}

	

	public void  putVersion(Document document) {
		versionsStrategy.putVersion(document);
	}
	
	public void enableStrategy() {
		//String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("volatile") && versionsStrategy instanceof VolatileVersionTrackingStrategy) {
			enable();
		}
		else if(strategyType.equals("stable") && versionsStrategy instanceof VolatileVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new StableVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && versionsStrategy instanceof StableVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new VolatileVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("stable") && versionsStrategy instanceof StableVersionTrackingStrategy) {
			enable();
		}
	}

	public void changeStrategy() {
		//String strategyType = latexEditorView.getStrategy();
		if(strategyType.equals("stable") && versionsStrategy instanceof VolatileVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new StableVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newStrategy;
			enable();
		}
		else if(strategyType.equals("volatile") && versionsStrategy instanceof StableVersionTrackingStrategy) {
			VersionTrackingStrategy newStrategy = new VolatileVersionTrackingStrategy();
			newStrategy.setEntireHistory(versionsStrategy.getEntireHistory());
			versionsStrategy = newStrategy;
			enable();
		}
	}

	public void rollback() {
		if(isEnabled() == false) {
			JOptionPane.showMessageDialog(null, "Strategy is not enabled", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			Document doc = versionsStrategy.getVersion();
			if(doc == null) {
				JOptionPane.showMessageDialog(null, "No version available", "InfoBox", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				versionsStrategy.removeVersion();
				latexEditorView.setCurrentDocument(doc);
			}
		}
	}
	
}
