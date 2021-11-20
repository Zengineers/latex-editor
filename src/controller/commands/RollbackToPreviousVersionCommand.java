package controller.commands;

import model.VersionTrackingManager;

public class RollbackToPreviousVersionCommand implements Command {
	private VersionTrackingManager versionTrackingManager;
	
	
	public RollbackToPreviousVersionCommand() {
		versionTrackingManager = VersionTrackingManager.getInstance();
	}


	@Override
	public void execute() {
		versionTrackingManager.rollback();
	}

}
