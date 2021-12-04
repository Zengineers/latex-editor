package controller.commands;

public class CommandFactory {	
	
	public Command createCommand(String type) {
		if (type.equals("addLatex")) {
			return new AddLatexCommand();
		}
		if (type.equals("changeVersionTrackingStrategy")) {
			return new ChangeVersionTrackingStrategyCommand();
		}
		if (type.equals("create")) {
			return new CreateCommand();
		}
		if (type.equals("disableVersionTracking")) {
			return new DisableVersionTrackingCommand();
		}
		if (type.equals("edit")) {
			return new EditCommand();
		}
		if (type.equals("enableVersionTracking")) {
			return new EnableVersionTrackingCommand();
		}
		if (type.equals("load")) {
			return new LoadCommand();
		}
		if (type.equals("rollback")) {
			return new RollbackCommand();
		}
		if (type.equals("save")) {
			return new SaveCommand();
		}
		if (type.equals("exportHtml")) {
			return new ExportHtmlCommand();
		}
		if (type.equals("importHtml")) {
			return new ImportHtmlCommand();
		}
		return null;
	}
}
