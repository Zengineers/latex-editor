package model.converters;

import model.Converter;

public enum SupportedLatexCommand {
	
	DOCUMENTCLASS {
        @Override
        protected String convert(String[] words, String word) {
			return "<!--" + word + "-->\n\n";	
					
        }
		@Override
		protected String getCommand() {
			return "\\documentclass";
		} 
    },
    BEGIN_DOCUMENT {
		@Override
		protected String convert(String[] words, String word) {
			return "<body class=\"content\">\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{document}";
		}    	
    },
    END_DOCUMENT {
		@Override
		protected String convert(String[] words, String word) {
			return "</body>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{document}";
		}
    },
    BEGIN_ABSTRACT {
		@Override
		protected String convert(String[] words, String word) {
			return "<div>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{abstract}";
		}
    },
    END_ABSTRACT {
		@Override
		protected String convert(String[] words, String word) {
			return "\n</div>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{abstract}";
		}
    },
    BEGIN_LETTER {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String letterHeader = LatexToHtmlConverter.getCommandParams("\\begin{letter}", words);
			return "<div>\n" + letterHeader + "\n<br><br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{letter}";
		} 	
    },
    END_LETTER {
    	@Override
		protected String convert(String[] words, String word) {
			return "</div>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{letter}";
		} 	
    },
    TITLE {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String documentTitle = LatexToHtmlConverter.getCommandParams("\\title", words);
			LatexToHtmlConverter.setDocumentTitle(documentTitle);
			return "<title> " + documentTitle + " </title>\n";
		}
		@Override
		protected String getCommand() {
			return "\\title";
		}
    },
    AUTHOR {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String documentAuthor = LatexToHtmlConverter.getCommandParams("\\author", words);
			LatexToHtmlConverter.setDocumentAuthor(documentAuthor);
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\author";
		}
    },
    DATE {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String documentDate = LatexToHtmlConverter.getCommandParams("\\date", words);
			LatexToHtmlConverter.setDocumentDate(documentDate);
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\date";
		}
    },
    MAKETITLE {
		@Override
		protected String convert(String[] words, String word) {
			return "<h1> " + LatexToHtmlConverter.getDocumentTitle() + " </h1>\n" +
					"<author> " + LatexToHtmlConverter.getDocumentAuthor() + " </author><br>\n" +
					"<date> " + LatexToHtmlConverter.getDocumentDate() + " </date><br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\maketitle";
		}
    },
    FRONTMATTER {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\frontmatter";
		}   	
    },
    MAINMATTER {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\mainmatter";
		}   	
    },
    BACKMATTER {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\backmatter";
		}   	
    },
    CHAPTER {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String chapterHeader = LatexToHtmlConverter.getCommandParams("\\chapter", words);
			return "<h2> " + chapterHeader + " </h2>\n";
		}
		@Override
		protected String getCommand() {
			return 	"\\chapter";
		}
    },
    SECTION {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String sectionHeader = LatexToHtmlConverter.getCommandParams("\\section", words);
			return "<h3> " + sectionHeader + " </h3>\n";
		}
		@Override
		protected String getCommand() {
			return "\\section";
		}	
    },
    SUBSECTION {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String subsectionHeader = LatexToHtmlConverter.getCommandParams("\\subsection", words);
			return "<h4> " + subsectionHeader + " </h4>\n";
		}
		@Override
		protected String getCommand() {
			return "\\subsection";
		}	    	
    },
    SUBSUBSECTION {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String subsubsectionHeader = LatexToHtmlConverter.getCommandParams("\\subsubsection", words);
			return "<h5> " + subsubsectionHeader + " </h5>\n";
		}
		@Override
		protected String getCommand() {
			return "\\subsubsection";
		}
    },
    USEPACKAGE {
		@Override
		protected String convert(String[] words, String word) {
			return "<!--" + word + "-->\n\n";	
		}
		@Override
		protected String getCommand() {
			return "\\usepackage";
		}    	
    },
    SIGNATURE {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String signatureText = LatexToHtmlConverter.getCommandParams("\\signature", words);
			return "<p> " + signatureText + " </p>\n";
		}
		@Override
		protected String getCommand() {
			return "\\signature";
		}	
    },
    ADDRESS {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String addressText = LatexToHtmlConverter.getCommandParams("\\address", words);
			return "<p> " + addressText + " </p>\n";
		}
		@Override
		protected String getCommand() {
			return "\\address";
		} 	
    },
    OPENING {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String openingText = LatexToHtmlConverter.getCommandParams("\\opening", words);
			return "<br>\n<p> " + openingText + " </p>\n<br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\opening";
		}	
    },
    CLOSING {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String closingText = LatexToHtmlConverter.getCommandParams("\\closing", words);
			return "\n<br>\n<p> " + closingText + " </p>\n<br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\closing";
		}	
    },
    PS {
		@Override
		protected String convert(String[] words, String word) {
			return "";
		}
		@Override
		protected String getCommand() {
			return "\\ps";
		}   	
    },
    ENCL {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String enclText = LatexToHtmlConverter.getCommandParams("\\encl", words);
			return "\n<br>\n<p> " + enclText + " </p>\n<br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\encl";
		}	
    },
    BEGIN_ITEMIZE {
		@Override
		protected String convert(String[] words, String word) {
			return "<ul>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{itemize}";
		} 	
    },
    END_ITEMIZE {
		@Override
		protected String convert(String[] words, String word) {
			return "</ul>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{itemize}";
		}	
    },
    ITEM {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String itemText = LatexToHtmlConverter.getCommandParams("\\item", words);
			return "<li> " + itemText + " </li>\n";
		}
		@Override
		protected String getCommand() {
			return "\\item";
		} 	
    },
    BEGIN_ENUMERATE {
		@Override
		protected String convert(String[] words, String word) {
			return "<ol>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{enumerate}";
		}    	
    },
    END_ENUMERATE {
		@Override
		protected String convert(String[] words, String word) {
			return "</ol>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{enumerate}";
		}	
    },
    BEGIN_TABLE {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setConvertingTable(true);
			return "<table><br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{table}";
		}
    },
    END_TABLE {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setConvertingTable(false);
			return "</table><br>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{table}";
		}	    	
    },
    CAPTION {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setShouldBreak(true);
			String captionText = LatexToHtmlConverter.getCommandParams("\\caption", words);
			return "<caption> " + captionText + " </caption>\n";
		}
		@Override
		protected String getCommand() {
			return "\\caption";
		}
    },
    BEGIN_TABULAR {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setConvertingTable(true);
			return "<tbody>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{tabular}";
		}
    },
    END_TABULAR {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setConvertingTable(false);
			return "</tbody>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{tabular}";
		}    	
    },
    HLINE {
		@Override
		protected String convert(String[] words, String word) {
			return "<hr>\n";
		}
		@Override
		protected String getCommand() {
			return "\\hline";
		}
    },
    BEGIN_FIGURE {
		@Override
		protected String convert(String[] words, String word) {
			return"<figure>\n";
		}
		@Override
		protected String getCommand() {
			return "\\begin{figure}";
		} 	
    },
    END_FIGURE {
		@Override
		protected String convert(String[] words, String word) {
			return "</figure>\n";
		}
		@Override
		protected String getCommand() {
			return "\\end{figure}";
		}	
    },
    INCLUDEGRAPHICS {
		@Override
		protected String convert(String[] words, String word) {
			return "<img src=\"myimage.png\" style=\"width:5cm\" alt=\"image\" />\n";
		}
		@Override
		protected String getCommand() {
			return "\\includegraphics";
		}
    },
    COMMENT {
		@Override
		protected String convert(String[] words, String word) {
			LatexToHtmlConverter.setConvertingComment(true);
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
