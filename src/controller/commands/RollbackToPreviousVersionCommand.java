package controller.commands;

import model.VersionTrackingManager;

public class RollbackToPreviousVersionCommand implements Command {
	private VersionTrackingManager versionsManager;
	
	
	public RollbackToPreviousVersionCommand(VersionTrackingManager versionsManager) {
		this.versionsManager = versionsManager;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.rollback();
	}

}
