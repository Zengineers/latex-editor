package controller.commands;

import model.VersionTrackingManager;

public class ChangeVersionsStrategyCommand implements Command {
	private VersionTrackingManager versionsManager;
	
	public ChangeVersionsStrategyCommand(VersionTrackingManager versionsManager) {
		super();
		this.versionsManager = versionsManager;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		versionsManager.changeStrategy();
	}

}
