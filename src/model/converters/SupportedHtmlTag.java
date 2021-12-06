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
	style {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "<style>";
		}
	},
	style_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</style>";
		}
	},
	html {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "<html";
		}
	}, 
	html_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</html>";
		}
		
	},
	body {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{document}\n";
		}
		@Override
		protected String getTag() {
			return "<body";
		}
	},
	body_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{document}\n";
		}
		@Override
		protected String getTag() {
			return "</body>";
		}
	},
	div {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{abstract}\n";
		}
		@Override
		protected String getTag() {
			return "<div>";
		}
	},
	div_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\n\\end{abstract}\n\n";
		}
		@Override
		protected String getTag() {
			return "</div>";
		}
	},
	br {
		@Override
		protected String convert(String[] words, String word) {
			return "\n";
		}
		@Override
		protected String getTag() {
			return "<br>";
		}	
	},
	title {
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
	title_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</title>";
		}
	},
	class_attribute {
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
	author {
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
	author_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</author>";
		}
	},
	date {
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
	date_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</date>";
		}
	},
	today {
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
	h1 {
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
	h1_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h1>";
		}
	},
	h2 {
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
	h2_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h2>";
		}
	},
	h3 {
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
	h3_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h3>";
		}
	},
	h4 {
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
	h4_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h4>";
		}
	},
	h5 {
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
	h5_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</h5>";
		}
	},
	and {
		@Override
		protected String convert(String[] words, String word) {
			return "\\and ";
		}
		@Override
		protected String getTag() {
			return "&";
		}	
	},
	paragraph {
		@Override
		protected String convert(String[] words, String word) {
			return "\\par\n";
		}
		@Override
		protected String getTag() {
			return "<p>";
		}	
	},
	paragraph_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</p>";
		}	
	},
	unordered_list {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{itemize}\n";
		}
		@Override
		protected String getTag() {
			return "<ul>";
		}	
	},
	unordered_list_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{itemize}\n\n";
		}
		@Override
		protected String getTag() {
			return "</ul>";
		}	
	},
	list_item {
		@Override
		protected String convert(String[] words, String word) {
			return "\\item ";
		}
		@Override
		protected String getTag() {
			return "<li>";
		}	
	},
	list_item_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</li>";
		}	
	},
	ordered_list {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{enumerate}\n";
		}
		@Override
		protected String getTag() {
			return "<ol>";
		}	
	},
	ordered_list_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{enumerate}\n\n";
		}
		@Override
		protected String getTag() {
			return "</ol>";
		}	
	},
	table {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{table}\n";
		}
		@Override
		protected String getTag() {
			return "<table>";
		}	
	},
	table_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{table}\n\n";
		}
		@Override
		protected String getTag() {
			return "</table>";
		}	
	},
	caption {
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
	caption_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "</caption>";
		}	
	},
	label {
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
	label_end {
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
	tbody {
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
	tbody_end {
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
	hr {
		@Override
		protected String convert(String[] words, String word) {
			return "\\hline\n";
		}
		@Override
		protected String getTag() {
			return "<hr>";
		}	
	},
	table_row {
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
	table_row_end {
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
	table_data {
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
	table_data_end {
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
	figure {
		@Override
		protected String convert(String[] words, String word) {
			return "\\begin{figure}\n";
		}
		@Override
		protected String getTag() {
			return "<figure>";
		}	
	},
	figure_end {
		@Override
		protected String convert(String[] words, String word) {
			return "\\end{figure}\n";
		}
		@Override
		protected String getTag() {
			return "</figure>";
		}	
	},
	img {
		@Override
		protected String convert(String[] words, String word) {
			return "\\includegraphics[width=...,height=...]{...}\n";
		}
		@Override
		protected String getTag() {
			return "<img";
		}	
	},
	src_attribute {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "src=";
		}	
	},
	style_attribute {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "style=";
		}	
	},
	alt_attribute {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "alt=";
		}	
	},
	tag_end {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getTag() {
			return "/>";
		}	
	},
	comment {
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
	comment_end {
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
