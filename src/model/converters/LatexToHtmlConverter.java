package model.converters;

import model.Converter;

public class LatexToHtmlConverter {
	
	private static String documentTitle = "";
	private static String documentAuthor = "";
	private static String documentDate = "";
	private static boolean convertingTable = false;
	private static boolean isNewTableRow = true;
	private static boolean convertingComment = false;
	private static boolean shouldBreak = false;
	
	private LatexToHtmlConverter() {}
	
	public static String getDocumentTitle() {
		return documentTitle;
	}

	public static void setDocumentTitle(String documentTitle) {
		LatexToHtmlConverter.documentTitle = documentTitle;
	}

	public static String getDocumentAuthor() {
		return documentAuthor;
	}

	public static void setDocumentAuthor(String documentAuthor) {
		LatexToHtmlConverter.documentAuthor = documentAuthor;
	}

	public static String getDocumentDate() {
		return documentDate;
	}

	public static void setDocumentDate(String documentDate) {
		LatexToHtmlConverter.documentDate = documentDate;
	}

	public static void setConvertingTable(boolean convertingTable) {
		LatexToHtmlConverter.convertingTable = convertingTable;
	}

	public static void setConvertingComment(boolean convertingComment) {
		LatexToHtmlConverter.convertingComment = convertingComment;
	}
	
	public static void setShouldBreak(boolean shouldBreak) {
		LatexToHtmlConverter.shouldBreak = shouldBreak;
	}

	public static void reset() {
		documentTitle = "";
		documentAuthor = "";
		documentDate = "";
		convertingTable = false;
		isNewTableRow = true;
		convertingComment = false;
		shouldBreak = false;
	}
	
	public static String convertLine(String[] words) {
		String lineContents = "";
		for (String word : words) {
			SupportedLatexCommand command = findCommand(word);
			if (command != null) {
				shouldBreak = false;
				lineContents += command.convert(words, word);			
				if (shouldBreak)
					break;
			}		
			else if (convertingTable) {
				lineContents += getTableContent(word);
			}
			else {
				lineContents += word + " ";
			}
		}
		return lineContents;
	}

	public static String checkClosingComment() {
		String str = "";
		if (convertingComment) {
			str += "-->\n";
			convertingComment = false;
		}
		return str;
	}

	public static String createHtmlContents() {
		return "<!DOCTYPE html>\n" +
				"<style>\n" +
				"div { text-align: center; font-size:20px; }\n" +
				"author { text-align: center; font-size:20px; }\n" +
				"date { text-align: center; font-size:20px; }\n" +
				".content { text-align: center; margin: auto; }\n" +
				"</style>\n" +
				"\n<html class=\"content\">\n";
	}
	
	public static String getCommandParams(String command, String[] words) {
		String str = "";
		for (String word : words) {
			if (word.startsWith(command)) {
				word = word.substring(command.length());
			}
			if (word.startsWith("*")) {
				word = Converter.removeFirstChar(word);
			}
			if (word.startsWith("{")) {
				word = Converter.removeFirstChar(word);
			}
			if (word.endsWith("}")) {
				word = Converter.removeLastChar(word);
			}
			if (word.equals("\\today")) {
				word = "DD-MM-YYYY";
			}
			if (word.equals("\\and")) {
				word = "&";
			}
			if (word.contains("\\label")) {
				word = word.replace("\\label", " <label> ");
				word = word.replace("{", "");
				word = word.replace("}", "");
				word += " </label> ";
			}
			str += word + " ";
		}
		return str.trim();
	}
	
	private static SupportedLatexCommand findCommand(String word) {
		for (SupportedLatexCommand command : SupportedLatexCommand.values()) {
			if (word.startsWith(command.getCommand())) {
				return command;
			}
		}
		return null;
	}
	
	private static String getTableContent(String word) {
		String[] columns = word.split("&");
		String tableContents = "";
		if (isNewTableRow) {
			tableContents += "\t<tr>\n";
			isNewTableRow = false;
		}
		for (String column : columns) { 
			if (column.endsWith("\\")) {
				column = column.replace("\\", "");
				isNewTableRow = true;
			}
			if (!column.isBlank()) {
				tableContents += "\t\t<td> " + column + " </td>\n";							
			}
		}
		if (isNewTableRow) {
			tableContents += "\t</tr>\n";
		}
		return tableContents;
	}
	
}
