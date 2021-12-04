package model;

import java.util.Optional;

public class HtmlConverter {
	private String[] supportedLatexCommands = {
			"\\documentclass", "\\begin{document}",
			"\\title", "\\author",
			"\\and", "\\date", "\\today",
			"\\maketitle"
	};
	public static String convertLatexToHtml(String contents) {
		boolean convertingTable = false;
		boolean isNewTableRow = true;
		String[] lines = splitContentsToLines(contents);
		String title = "";
		String author = "";
		String date = "";
		String htmlContents = "<!DOCTYPE html>\n" +
				"<style>\n" +
//				"h1 {text-align: left;}\n" +
//				"h2 {text-align: left;}\n" +
//				"h3 {text-align: left;}\n" +
//				"h4 {text-align: left;}\n" +
//				"h5 {text-align: left;}\n" +
//				"h6 {text-align: left;}\n" +
				"p {text-align: left;}\n" +
				"div {text-align: left; font-size:20px;}\n" +
//				"li {text-align: left;}\n" +
//				"ul {text-align: left;}\n" +
//				".content { max-width: 500px; margin: auto; }" +
//				"</style>\n <div class=\"content\">";
				"</style>\n";
		for (String line : lines) {
			String[] words = splitLineToWords(line);
			for (String word : words) {
				if (word.startsWith("\\documentclass")) {
					String latexParams = word.substring("\\documentclass".length());
					htmlContents += "<!--Latex Parameters-->\n";	
					htmlContents += "<!--" + latexParams + "-->\n\n";	
				}
				else if (word.startsWith("\\begin{document}")) {
					htmlContents += "<html>\n";
				}
				else if (word.startsWith("\\end{document}")) {
					htmlContents += "</html>\n";
				}
				else if (word.startsWith("\\begin{abstract}")) {
					htmlContents += "<div>\n";
				}
				else if (word.startsWith("\\end{abstract}")) {
					htmlContents += "</div>\n";
				}
				else if (word.startsWith("\\begin{letter}")) {
					String letterHeader = getCommandParams("\\begin{letter}", words);
					htmlContents += "<div>\n" + letterHeader + "<br><br>\n";
				}
				else if (word.startsWith("\\end{letter}")) {
					htmlContents += "</div>\n";
				}
				else if (word.startsWith("\\title")) {
					title = getCommandParams("\\title", words);
					htmlContents += "<title>\n" + title + "\n</title>\n";
					break;
				}
				else if (word.startsWith("\\author")) {
					author = getCommandParams("\\author", words);
					break;
				}
				else if (word.startsWith("\\date")) {
					date = getCommandParams("\\date", words);
					break;
				}
				else if (word.startsWith("\\maketitle")) {
					htmlContents += "<h1> " + title + " <h1>\n";
					htmlContents += "<div> " + author + " <div>\n";
					htmlContents += "<div> " + date + " <div>\n";
				}
				else if (word.startsWith("\\frontmatter")) {
					continue;
				}
				else if (word.startsWith("\\mainmatter")) {
					continue;
				}
				else if (word.startsWith("\\backmatter")) {
					continue;
				}
				else if (word.startsWith("\\chapter")) {
					String chapterHeader = getCommandParams("\\chapter", words);
					htmlContents += "<h2> " + chapterHeader + " <h2>\n";
					break;
				}
				else if (word.startsWith("\\section")) {
					String sectionHeader = getCommandParams("\\section", words);
					htmlContents += "<h3> " + sectionHeader + " <h3>\n";
					break;
				}
				else if (word.startsWith("\\subsection")) {
					String subsectionHeader = getCommandParams("\\subsection", words);
					htmlContents += "<h4> " + subsectionHeader + " <h4>\n";
					break;
				}
				else if (word.startsWith("\\subsubsection")) {
					String subsubsectionHeader = getCommandParams("\\subsubsection", words);
					htmlContents += "<h5> " + subsubsectionHeader + " <h5>\n";
					break;
				}
				else if (word.startsWith("\\usepackage")) {
					htmlContents += "<!--" + word + "-->\n\n";	
				}
				else if (word.startsWith("\\signature")) {
					String signature = getCommandParams("\\signature", words);
					htmlContents += "<div> " + signature + " <div>\n";
				}
				else if (word.startsWith("\\address")) {
					String address = getCommandParams("\\address", words);
					htmlContents += "<div> " + address + " <div>\n";
				}
				else if (word.startsWith("\\opening")) {
					String opening = getCommandParams("\\opening", words);
					htmlContents += "<br><div> " + opening + " <div><br>\n";
				}
				else if (word.startsWith("\\closing")) {
					String closing = getCommandParams("\\closing", words);
					htmlContents += "<br><div> " + closing + " <div><br>\n";
				}
				else if (word.startsWith("\\ps")) {
					continue;
				}
				else if (word.startsWith("\\encl")) {
					String encl = getCommandParams("\\encl", words);
					htmlContents += "<br><div> " + encl + " <div><br>\n";
				}
				else if (word.startsWith("\\begin{itemize}")) {
					htmlContents += "<ul>\n";
				}
				else if (word.startsWith("\\end{itemize}")) {
					htmlContents += "</ul>\n";
				}
				else if (word.startsWith("\\item")) {
					String item = getCommandParams("\\item", words);
					htmlContents += "<li> " + item + " </li>\n";
				}
				else if (word.startsWith("\\begin{enumerate}")) {
					htmlContents += "<ol>\n";
				}
				else if (word.startsWith("\\end{enumerate}")) {
					htmlContents += "</ol>\n";
				}
				else if (word.startsWith("\\begin{table}")) {
					htmlContents += "<table><br>\n";
					convertingTable = true;
				}
				else if (word.startsWith("\\end{table}")) {
					htmlContents += "</table><br>\n";
					convertingTable = false;
				}
				else if (word.startsWith("\\caption")) {
					String caption = getCommandParams("\\caption", words);
					htmlContents += "<caption> " + caption + "</caption>\n";
				}
				else if (word.startsWith("\\begin{tabular}")) {
					htmlContents += "<tbody>\n";
					convertingTable = true;
				}
				else if (word.startsWith("\\end{tabular}")) {
					htmlContents += "</tbody>\n";
					convertingTable = false;
				}
				else if (word.startsWith("\\hline")) {
					htmlContents += "<hr>\n";
				}
				else if (word.startsWith("\\begin{figure}")) {
					htmlContents += "<figure>\n";
				}
				else if (word.startsWith("\\end{figure}")) {
					htmlContents += "</figure>\n";
				}
				else if (word.startsWith("\\includegraphics")) {
					htmlContents += "<p><img src=\"myimage.png\" style=\"width:5cm\" alt=\"image\" /></p>";
				}		
				else if (convertingTable) {
					if (isNewTableRow) {
						htmlContents += "\t<tr>\n";
						isNewTableRow = false;
					}
					String[] columns = word.split("&");
					for (String column : columns) { 
						if (column.endsWith("\\")) {
							column = column.replace("\\", "");
							isNewTableRow = true;
						}
						if (!column.isBlank()) {
							htmlContents += "\t\t<td> " + column + " </td>\n";							
						}
					}
					if (isNewTableRow) {
						htmlContents += "\t</tr>\n";
					}
				}
				else if (word.startsWith("%")) {
					htmlContents += "<!--" + word + "-->\n";
				}
				else {
					htmlContents += word + " ";
				}
			}
		}
		return htmlContents;
	}

	private static String getCommandParams(String command, String[] words) {
		String str = "";
		for (String word : words) {
			if (word.startsWith(command)) {
				word = word.substring(command.length());
			}
			if (word.startsWith("*")) {
				word = removeFirstChar(word);
			}
			if (word.startsWith("{")) {
				word = removeFirstChar(word);
			}
			if (word.endsWith("}")) {
				word = removeLastChar(word);
			}
			if (word.equals("\\today")) {
				word = "DD-MM-YYYY";
			}
			if (word.equals("\\and")) {
				word = "&";
			}
			if (word.contains("\\label")) {
				word = word.replace("\\label", "<label>");
				word = word.replace("{", "");
				word = word.replace("}", "");
				word += "</label>";
			}
			str += word + " ";
		}
		return str.trim();
	}

	private static String[] splitContentsToLines(String contents) {
		return contents.split("\\r?\\n");
	}

	private static String[] splitLineToWords(String line) {
		return line.split("\\s+");
	}

	private static String removeLastChar(String s) {
	    return Optional.ofNullable(s)
	      .filter(str -> str.length() != 0)
	      .map(str -> str.substring(0, str.length() - 1))
	      .orElse(s);
	    }
	
	private static String removeFirstChar(String s) {
		return Optional.ofNullable(s)
				.filter(str -> str.length() != 0)
				.map(str -> str.substring(1))
				.orElse(s);
	}
	
	public String convertHtmlToLatex(String contents) {
		// TODO
		return contents;
	}
	
}
