package controller.commands;

import model.VersionTrackingManager;

public class ChangeVersionsStrategyCommand implements Command {
	private VersionTrackingManager  versionTrackingManager;
	
	
	public ChangeVersionsStrategyCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}

	
	@Override
	public void execute() {
		versionTrackingManager.changeStrategy();
	}

}
