package controller.commands;

import model.VersionTrackingManager;

public class DisableVersionTrackingCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	
	
	public DisableVersionTrackingCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}

	
	@Override
	public void execute() {
		versionTrackingManager.disable();
	}

}
