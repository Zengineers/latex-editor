package controller.commands;

import model.VersionTrackingManager;

public class DisableVersionsManagementCommand implements Command {

	private VersionTrackingManager versionsManager;
	
	public DisableVersionsManagementCommand(VersionTrackingManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.disable();
	}

}
