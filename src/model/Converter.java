package model;

import java.util.Optional;

import model.converters.LatexToHtmlConverter;

public class Converter {
	private static Converter instance = null;
	
	private Converter() {}
	
	public static Converter getInstance() {
		if (instance == null)
			instance = new Converter();
		return instance;
	}

	public String convertLatexToHtml(String contents) {
		LatexToHtmlConverter.reset();
		String[] lines = splitContentsToLines(contents);
		String htmlContents = LatexToHtmlConverter.createHtmlContents();
		for (String line : lines) {
			htmlContents += LatexToHtmlConverter.checkClosingComment();
			String[] words = splitLineToWords(line);
			htmlContents += LatexToHtmlConverter.convertLine(words);
		}
		return htmlContents + "\n</html>\n";
	}

	public String convertHtmlToLatex(String contents) {
		// TODO
		return contents;
	}
	
	public static String removeFirstChar(String s) {
		return Optional.ofNullable(s)
				.filter(str -> str.length() != 0)
				.map(str -> str.substring(1))
				.orElse(s);
	}

	public static String removeLastChar(String s) {
		return Optional.ofNullable(s)
			  .filter(str -> str.length() != 0)
			  .map(str -> str.substring(0, str.length() - 1))
			  .orElse(s);
	}

	private static String[] splitContentsToLines(String contents) {
		return contents.split("\\r?\\n");
	}
	
	private static String[] splitLineToWords(String line) {
		return line.split("\\s+");
	}


}
