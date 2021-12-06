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
	
	private static enum SupportedCommand {
	    documentclass {
	        @Override
	        protected String convert(String[] words, String word) {
	        	shouldBreak = false;
				return "<!--" + word + "-->\n\n";	
						
	        }
			@Override
			protected String getCommand() {
				return "\\documentclass";
			} 
	    },
	    begin_document {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<body class=\"content\">\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{document}";
			}    	
	    },
	    end_document {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "</body>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{document}";
			}
	    },
	    begin_abstract {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<div>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{abstract}";
			}
	    },
	    end_abstract {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "\n</div>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{abstract}";
			}
	    },
	    begin_letter {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String letterHeader = getCommandParams("\\begin{letter}", words);
				return "<div>\n" + letterHeader + "\n<br><br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{letter}";
			} 	
	    },
	    end_letter {
	    	@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "</div>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{letter}";
			} 	
	    },
	    title {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				documentTitle = getCommandParams("\\title", words);
				return "<title> " + documentTitle + " </title>\n";
			}
			@Override
			protected String getCommand() {
				return "\\title";
			}
	    },
	    author {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				documentAuthor = getCommandParams("\\author", words);
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\author";
			}
	    },
	    date {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				documentDate = getCommandParams("\\date", words);
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\date";
			}
	    },
	    maketitle {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<h1> " + documentTitle + " </h1>\n" +
						"<author> " + documentAuthor + " </author><br>\n" +
						"<date> " + documentDate + " </date><br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\maketitle";
			}
	    },
	    frontmatter {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\frontmatter";
			}   	
	    },
	    mainmatter {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\mainmatter";
			}   	
	    },
	    backmatter {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\backmatter";
			}   	
	    },
	    chapter {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String chapterHeader = getCommandParams("\\chapter", words);
				return "<h2> " + chapterHeader + " </h2>\n";
			}
			@Override
			protected String getCommand() {
				return 	"\\chapter";
			}
	    },
	    section {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String sectionHeader = getCommandParams("\\section", words);
				return "<h3> " + sectionHeader + " </h3>\n";
			}
			@Override
			protected String getCommand() {
				return "\\section";
			}	
	    },
	    subsection {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String subsectionHeader = getCommandParams("\\subsection", words);
				return "<h4> " + subsectionHeader + " </h4>\n";
			}
			@Override
			protected String getCommand() {
				return "\\subsection";
			}	    	
	    },
	    subsubsection {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String subsubsectionHeader = getCommandParams("\\subsubsection", words);
				return "<h5> " + subsubsectionHeader + " </h5>\n";
			}
			@Override
			protected String getCommand() {
				return "\\subsubsection";
			}
	    },
	    usepackage {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<!--" + word + "-->\n\n";	
			}
			@Override
			protected String getCommand() {
				return "\\usepackage";
			}    	
	    },
	    signature {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String signatureText = getCommandParams("\\signature", words);
				return "<p> " + signatureText + " </p>\n";
			}
			@Override
			protected String getCommand() {
				return "\\signature";
			}	
	    },
	    address {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String addressText = getCommandParams("\\address", words);
				return "<p> " + addressText + " </p>\n";
			}
			@Override
			protected String getCommand() {
				return "\\address";
			} 	
	    },
	    opening {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String openingText = getCommandParams("\\opening", words);
				return "<br>\n<p> " + openingText + " </p>\n<br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\opening";
			}	
	    },
	    closing {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String closingText = getCommandParams("\\closing", words);
				return "\n<br>\n<p> " + closingText + " </p>\n<br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\closing";
			}	
	    },
	    ps {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "";
			}
			@Override
			protected String getCommand() {
				return "\\ps";
			}   	
	    },
	    encl {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String enclText = getCommandParams("\\encl", words);
				return "\n<br>\n<p> " + enclText + " </p>\n<br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\encl";
			}	
	    },
	    begin_itemize {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<ul>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{itemize}";
			} 	
	    },
	    end_itemize {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "</ul>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{itemize}";
			}	
	    },
	    item {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String itemText = getCommandParams("\\item", words);
				return "<li> " + itemText + " </li>\n";
			}
			@Override
			protected String getCommand() {
				return "\\item";
			} 	
	    },
	    begin_enumerate {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<ol>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{enumerate}";
			}    	
	    },
	    end_enumerate {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "</ol>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{enumerate}";
			}	
	    },
	    begin_table {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				convertingTable = true;
				return "<table><br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{table}";
			}
	    },
	    end_table {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				convertingTable = false;
				return "</table><br>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{table}";
			}	    	
	    },
	    caption {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = true;
				String captionText = getCommandParams("\\caption", words);
				return "<caption> " + captionText + " </caption>\n";
			}
			@Override
			protected String getCommand() {
				return "\\caption";
			}
	    },
	    begin_tabular {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				convertingTable = true;
				return "<tbody>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{tabular}";
			}
	    },
	    end_tabular {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				convertingTable = false;
				return "</tbody>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{tabular}";
			}    	
	    },
	    hline {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<hr>\n";
			}
			@Override
			protected String getCommand() {
				return "\\hline";
			}
	    },
	    begin_figure {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return"<figure>\n";
			}
			@Override
			protected String getCommand() {
				return "\\begin{figure}";
			} 	
	    },
	    end_figure {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "</figure>\n";
			}
			@Override
			protected String getCommand() {
				return "\\end{figure}";
			}	
	    },
	    includegraphics {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				return "<img src=\"myimage.png\" style=\"width:5cm\" alt=\"image\" />\n";
			}
			@Override
			protected String getCommand() {
				return "\\includegraphics";
			}
	    },
	    comment {
			@Override
			protected String convert(String[] words, String word) {
				shouldBreak = false;
				convertingComment = true;
				word = Converter.removeFirstChar(word);
				return "<!--" + word + " ";
			}
			@Override
			protected String getCommand() {
				return "%";
			}
	    };
		
	    protected abstract String convert(String[] words, String word);
	    protected abstract String getCommand();
	}
		
	public static String convertLine(String[] words) {
		String lineContents = "";
		for (String word : words) {
			SupportedCommand command = findCommand(word);
			if (command != null) {
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
	
	public static void reset() {
		documentTitle = "";
		documentAuthor = "";
		documentDate = "";
		convertingTable = false;
		isNewTableRow = true;
		convertingComment = false;
		shouldBreak = false;
	}
	
	private static SupportedCommand findCommand(String word) {
		for (SupportedCommand command : SupportedCommand.values()) {
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

	private static String getCommandParams(String command, String[] words) {
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
	
}
