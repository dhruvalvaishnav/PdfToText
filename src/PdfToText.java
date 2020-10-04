import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

public class PdfToText extends PDFTextStripper {

	static String str;
	static File myObj;
	static FileWriter myWriter;

	public PdfToText() throws IOException {
		super.setSortByPosition(true);
	}

	public static void main(String[] args) {
		try {
			PDDocument doc = PDDocument.load(new File("src/2020-05612.pdf"));

			PdfToText stripper = new PdfToText();

			System.out.println();

			stripper.setStartPage(1);
			stripper.setEndPage(doc.getNumberOfPages());
			stripper.setSortByPosition(true);

			String st = stripper.getText(doc);

			System.out.println(st);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// for extract bold and capital text from pdf
	@Override
	public void processTextPosition(TextPosition text) {

		// StringBuilder to store the extracted text
		StringBuilder sb = new StringBuilder();

		boolean isBold;

		if (text != null) {

			if (text.getFont().getFontDescriptor() != null) {

				if (text.getFont().getFontDescriptor().isForceBold()
						|| text.getFont().getFontDescriptor().getFontWeight() > 680) {
					isBold = true;

					// here my file is having "Helvetica-Bold" bold and capital fonts : customized
					// as per yours
					if (text.getFont().getFontDescriptor().getFontName().equals("Helvetica-Bold")) {
//						System.out.print(text);
						sb.append(text);
						str = sb.toString();

						System.out.print(str);
					}

				}

			}

		}

	}
}