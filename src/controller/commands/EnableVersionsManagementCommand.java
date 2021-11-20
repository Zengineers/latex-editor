package controller.commands;

import model.VersionTrackingManager;

public class EnableVersionsManagementCommand implements Command {
	private VersionTrackingManager versionsManager;
	
	public EnableVersionsManagementCommand(VersionTrackingManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}


	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.enableStrategy();
	}

}
