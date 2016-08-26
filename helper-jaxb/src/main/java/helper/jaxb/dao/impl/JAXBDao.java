package helper.jaxb.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import com.sun.xml.txw2.Document;

public abstract class JAXBDao {
	
	/*public JAXBDao(String packageName, String outputDirectory, String... xsdURLs) throws Exception {
		// TODO Auto-generated constructor stub
        System.setProperty("http.agent", "Chrome");
		 for(String url: xsdURLs){
			 // Setup schema compiler
	         SchemaCompiler sc = XJC.createSchemaCompiler();
	         sc.forcePackageName(packageName);

	         // Setup SAX InputSource
	         
	         InputStream iStream = new URL(url).openStream();
	         File tempFile = File.createTempFile("temp", ".xsd");
	         tempFile.deleteOnExit();
	         FileOutputStream out = new FileOutputStream(tempFile);
	         IOUtils.copy(iStream, out);
	         out.close();
	         
	       //  is.setSystemId(schemaFile.getAbsolutePath());

	         // Parse & build
	         InputSource is = new InputSource(tempFile.toURI().toString());
	         sc.parseSchema(is);
	         S2JJAXBModel model = sc.bind();
	         JCodeModel jCodeModel = model.generateCode(null, null);
	         
	         jCodeModel.build(new File(outputDirectory));
		 }
        
	}*/
	public <T> T get(Class<T> clazz, String xmlURL) throws Exception{
		JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		HttpURLConnection httpcon = (HttpURLConnection) new URL(xmlURL).openConnection(); 
		 httpcon.addRequestProperty("User-Agent", "Mozilla/4.76"); 
		return  (T) jaxbUnmarshaller.unmarshal(httpcon.getInputStream());
	}

}
