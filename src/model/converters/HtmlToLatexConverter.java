package model.converters;


public class HtmlToLatexConverter {

	private static boolean shouldConvert = false;
	private static boolean openBracket = false;
	private static boolean convertingTable = false;
	private static boolean isFirstColumn = true;
	
	private HtmlToLatexConverter() {}
	
	public static void setShouldConvert(boolean shouldConvert) {
		HtmlToLatexConverter.shouldConvert = shouldConvert;
	}

	public static void setOpenBracket(boolean openBracket) {
		HtmlToLatexConverter.openBracket = openBracket;
	}

	public static void setConvertingTable(boolean convertingTable) {
		HtmlToLatexConverter.convertingTable = convertingTable;
	}

	public static boolean isFirstColumn() {
		return isFirstColumn;
	}

	public static void setIsFirstColumn(boolean isFirstColumn) {
		HtmlToLatexConverter.isFirstColumn = isFirstColumn;
	}

	public static void reset() {
		shouldConvert = false;
		openBracket = false;
		convertingTable = false;
		isFirstColumn = true;
	}	
	
	public static String convertLine(String[] words) {
		String lineContents = "";
		for (String word : words) {
			SupportedHtmlTag tag = findTag(word);
			if (tag != null) {
				lineContents += tag.convert(words, word);
			}
			else {
				lineContents += word + " ";
			}
		}
		if (!shouldConvert)
			return "";
		return lineContents.trim();
	}
	
	public static String checkClosingBracket() {
		String str = "";
		if (openBracket) {
			str += "}";
			openBracket = false;
		}
		return str;
	}
	
	public static String checkNewLine() {
		String str = " ";
		if (!convertingTable)
			str = "\n";
		return str;
	}
		
	private static SupportedHtmlTag findTag(String word) {
		for (SupportedHtmlTag tag : SupportedHtmlTag.values()) {
			if (word.startsWith(tag.getTag())) {
				return tag;
			}
		}
		return null;
	}
	
}
