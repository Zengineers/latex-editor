package controller.commands;

import model.VersionTrackingManager;

public class EnableVersionTrackingCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	
	
	public EnableVersionTrackingCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}


	@Override
	public void execute() {
		versionTrackingManager.enableStrategy();
	}

}
