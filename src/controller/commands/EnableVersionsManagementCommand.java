package controller.commands;

import model.VersionTrackingManager;

public class EnableVersionsManagementCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	
	
	public EnableVersionsManagementCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}


	@Override
	public void execute() {
		versionTrackingManager.enableStrategy();
	}

}
