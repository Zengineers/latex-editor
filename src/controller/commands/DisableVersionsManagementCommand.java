package controller.commands;

import model.VersionTrackingManager;

public class DisableVersionsManagementCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	
	
	public DisableVersionsManagementCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}

	
	@Override
	public void execute() {
		versionTrackingManager.disable();
	}

}
