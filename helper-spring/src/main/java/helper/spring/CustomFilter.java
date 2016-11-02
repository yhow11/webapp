package helper.spring;

import java.io.IOException;
import java.util.List;

import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

public class CustomFilter implements TypeFilter {

	private List<String> classes;
	
	public CustomFilter(List<String> classes) {
		// TODO Auto-generated constructor stub
		this.classes = classes;
	}
	
	@Override
	public boolean match(MetadataReader arg0, MetadataReaderFactory arg1) throws IOException {
		// TODO Auto-generated method stub
		String className = arg0.getClassMetadata().getClassName().substring(arg0.getClassMetadata().getClassName().lastIndexOf(".")+1, arg0.getClassMetadata().getClassName().length());
		System.out.println(className);
		return classes.contains(className);
	}
}
