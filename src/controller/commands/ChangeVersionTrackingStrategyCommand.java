package controller.commands;

import model.VersionTrackingManager;

public class ChangeVersionTrackingStrategyCommand implements Command {
	private VersionTrackingManager  versionTrackingManager;
	
	
	public ChangeVersionTrackingStrategyCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}

	
	@Override
	public void execute() {
		versionTrackingManager.changeStrategy();
	}

}
