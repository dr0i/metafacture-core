/* Copyright 2017  hbz, Pascal Christoph. Licensed under the EPL 2.0 */

import java.io.File;

import org.metafacture.biblio.marc21.MarcXmlEncoder;
import org.metafacture.biblio.marc21.MarcXmlHandler;
import org.metafacture.io.FileOpener;
import org.metafacture.io.ObjectWriter;
import org.metafacture.metamorph.Metamorph;
import org.metafacture.xml.XmlDecoder;

/**
 * mach deine eigenen tests und sieh den output
 * 
 * @author Pascal Christoph (dr0i)
 * 
 */
public final class HelpDebugMetafacture {

	static final String PATH="/home/pc/git/metafacture-sandbox/enrich_marcxml/"; 
	public static void main(String... args) {
		String inputPath = PATH+"example.xml";
		final FileOpener opener = new FileOpener();
		if (inputPath.toLowerCase().endsWith("bz2")) {
			opener.setCompression("BZIP2");
		} else if (inputPath.toLowerCase().endsWith("gz"))
			opener.setCompression("GZIP");
		opener.setReceiver(new XmlDecoder())
				.setReceiver(new MarcXmlHandler())
				.setReceiver(new Metamorph(PATH+"morph1.xml"))
				.setReceiver(new MarcXmlEncoder())
				.setReceiver(new ObjectWriter<String>(PATH+"out.txt"));
		opener.process(new File(inputPath).getAbsolutePath());
		opener.closeStream();
	}
}
