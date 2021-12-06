package model.converters;

public enum SupportedHtmlTag {
	
	DOCTYPE {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "<!DOCTYPE";
		}
	},
	STYLE {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "<style>";
		}
	},
	STYLE_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</style>";
		}
	},
	HTML {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "<html";
		}
	}, 
	HTML_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</html>";
		}
		
	},
	BODY {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{document}\n";
		}
		@Override
		protected String getTag() {
			return "<body";
		}
	},
	BODY_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{document}\n";
		}
		@Override
		protected String getTag() {
			return "</body>";
		}
	},
	DIV {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{abstract}\n";
		}
		@Override
		protected String getTag() {
			return "<div>";
		}
	},
	DIV_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\n\\end{abstract}\n\n";
		}
		@Override
		protected String getTag() {
			return "</div>";
		}
	},
	BR {
		@Override
		protected String convert(String[] words, String word) {
			return "\n";
		}
		@Override
		protected String getTag() {
			return "<br>";
		}	
	},
	TITLE {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setShouldConvert(true);
			return "\\title{";
		}
		@Override
		protected String getTag() {
			return "<title>";
		}
	},
	TITLE_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</title>";
		}
	},
	CLASS_ATTRIBUTE {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setShouldConvert(true);
			return "";
		}
		@Override
		protected String getTag() {
			return "class=\"";
		}
	},
	AUTHOR {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\author{";
		}
		@Override
		protected String getTag() {
			return "<author>";
		}
	},
	AUTHOR_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</author>";
		}
	},
	DATE {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\date{";
		}
		@Override
		protected String getTag() {
			return "<date>";
		}
	},
	DATE_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</date>";
		}
	},
	TODAY {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\today";
		}
		@Override
		protected String getTag() {
			return "DD-MM-YYYY";
		}
	},
	H1 {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\title{";
		}
		@Override
		protected String getTag() {
			return "<h1>";
		}
	},
	H1_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h1>";
		}
	},
	H2 {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\chapter{";
		}
		@Override
		protected String getTag() {
			return "<h2>";
		}
	},
	H2_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h2>";
		}
	},
	H3 {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\section{";
		}
		@Override
		protected String getTag() {
			return "<h3>";
		}
	},
	H3_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h3>";
		}
	},
	H4 {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\subsection{";
		}
		@Override
		protected String getTag() {
			return "<h4>";
		}
	},
	H4_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h4>";
		}
	},
	H5 {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\subsubsection{";
		}
		@Override
		protected String getTag() {
			return "<h5>";
		}
	},
	H5_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h5>";
		}
	},
	AND {
		@Override
		protected String convert(String[] words, String word) {
			return "\\and ";
		}
		@Override
		protected String getTag() {
			return "&";
		}	
	},
	PARAGRAPH {
		@Override
		protected String convert(String[] words, String word) {
			return "\\par\n";
		}
		@Override
		protected String getTag() {
			return "<p>";
		}	
	},
	PARAGRAPH_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</p>";
		}	
	},
	UNORDERED_LIST {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{itemize}\n";
		}
		@Override
		protected String getTag() {
			return "<ul>";
		}	
	},
	UNORDERED_LIST_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{itemize}\n\n";
		}
		@Override
		protected String getTag() {
			return "</ul>";
		}	
	},
	LIST_ITEM {
		@Override
		protected String convert(String[] words, String word) {
			return "\\item ";
		}
		@Override
		protected String getTag() {
			return "<li>";
		}	
	},
	LIST_ITEM_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</li>";
		}	
	},
	ORDERED_LIST {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{enumerate}\n";
		}
		@Override
		protected String getTag() {
			return "<ol>";
		}	
	},
	ORDERED_LIST_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{enumerate}\n\n";
		}
		@Override
		protected String getTag() {
			return "</ol>";
		}	
	},
	TABLE {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{table}\n";
		}
		@Override
		protected String getTag() {
			return "<table>";
		}	
	},
	TABLE_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{table}\n\n";
		}
		@Override
		protected String getTag() {
			return "</table>";
		}	
	},
	CAPTION {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\caption{";
		}
		@Override
		protected String getTag() {
			return "<caption>";
		}	
	},
	CAPTION_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</caption>";
		}	
	},
	LABEL {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\label{";
		}
		@Override
		protected String getTag() {
			return "<label>";
		}	
	},
	LABEL_END {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "";
		}
		@Override
		protected String getTag() {
			return "</label>";
		}	
	},
	TBODY {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\begin{tabular}{...}\n";
		}
		@Override
		protected String getTag() {
			return "<tbody>";
		}	
	},
	TBODY_END {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setOpenBracket(true);
			return "\\end{tabular}\n\n";
		}
		@Override
		protected String getTag() {
			return "</tbody>";
		}	
	},
	HR {
		@Override
		protected String convert(String[] words, String word) {
			return "\\hline\n";
		}
		@Override
		protected String getTag() {
			return "<hr>";
		}	
	},
	TABLE_ROW {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setConvertingTable(true);
			return "";
		}
		@Override
		protected String getTag() {
			return "<tr>";
		}	
	},
	TABLE_ROW_END {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setConvertingTable(false);
			HtmlToLatexConverter.setIsFirstColumn(true);
			return "\\\\";
		}
		@Override
		protected String getTag() {
			return "</tr>";
		}	
	},
	TABLE_DATA {
		@Override
		protected String convert(String[] words, String word) {
			if (!HtmlToLatexConverter.isFirstColumn()) {
				return " & ";				
			}
			return "";
		}
		@Override
		protected String getTag() {
			return "<td>";
		}	
	},
	TABLE_DATA_END {
		@Override
		protected String convert(String[] words, String word) {
			HtmlToLatexConverter.setIsFirstColumn(false);
			return "";
		}
		@Override
		protected String getTag() {
			return "</td>";
		}	
	},
	FIGURE {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{figure}\n";
		}
		@Override
		protected String getTag() {
			return "<figure>";
		}	
	},
	FIGURE_END {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{figure}\n";
		}
		@Override
		protected String getTag() {
			return "</figure>";
		}	
	},
	IMG {
		@Override
		protected String convert(String[] words, String word) {
			return "\\includegraphics[width=...,height=...]{...}\n";
		}
		@Override
		protected String getTag() {
			return "<img";
		}	
	},
	SRC_ATTRIBUTE {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "src=";
		}	
	},
	STYLE_ATTRIBUTE {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "style=";
		}	
	},
	ALT_ATTRIBUTE {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "alt=";
		}	
	},
	TAG_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "/>";
		}	
	},
	COMMENT {
		@Override
		protected String convert(String[] words, String word) {
			if (word.startsWith("<!--\\documentclass")) {
				HtmlToLatexConverter.setShouldConvert(true);
				word = word.replace("<!--", "");
				word = word.replace("-->", "");
				return word + "\n\n";
			}
			else if (word.startsWith("<!--\\usepackage")) {
				word = word.replace("<!--", "");
				word = word.replace("-->", "");
				return word + "\n\n";
			}
			else {
				return "% ";
			}
		}
		@Override
		protected String getTag() {
			return "<!--";
		}	
	},
	COMMENT_END {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "-->";
		}
	};
	
	protected abstract String convert(String[] words, String word);
    protected abstract String getTag();
}
