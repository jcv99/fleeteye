package herramienta;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PageRanges;
import javax.print.attribute.standard.Sides;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
import org.apache.pdfbox.printing.PDFPageable;

public class ImprimirManager {
	
	public static void imprimirDocumentoConOpciones(PDDocument document) throws PrinterException
	    {
	        PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPageable(new PDFPageable(document));

	        PrintRequestAttributeSet attr = new HashPrintRequestAttributeSet();
	        attr.add(new PageRanges(1, 1)); 
	        
	        PDViewerPreferences vp = document.getDocumentCatalog().getViewerPreferences();
	        if (vp != null && vp.getDuplex() != null)
	        {
	            String dp = vp.getDuplex();
	            if (PDViewerPreferences.DUPLEX.DuplexFlipLongEdge.toString().equals(dp))
	            {
	                attr.add(Sides.TWO_SIDED_LONG_EDGE);
	            }
	            else if (PDViewerPreferences.DUPLEX.DuplexFlipShortEdge.toString().equals(dp))
	            {
	                attr.add(Sides.TWO_SIDED_SHORT_EDGE);
	            }
	            else if (PDViewerPreferences.DUPLEX.Simplex.toString().equals(dp))
	            {
	                attr.add(Sides.ONE_SIDED);
	            }
	        }

	        if (job.printDialog(attr))
	        {
	            job.print(attr);
	        }
	    }
	
	 public ImprimirManager() {}
	
	

}
