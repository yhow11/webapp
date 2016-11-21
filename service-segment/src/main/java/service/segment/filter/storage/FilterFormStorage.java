package service.segment.filter.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import common.orm.query.Storage;
import common.orm.query.param.Param;
import service.segment.filter.form.FilterForm;

@Service("FilterFormStorage")
public class FilterFormStorage implements Storage<FilterForm> {

	public List<FilterForm> get(Param<FilterForm> param) throws Exception {
		// TODO Auto-generated method stub
		List<FilterForm> filterForms = new ArrayList<>();
		FilterForm filterForm = new FilterForm();
		filterForm.setName("Equals");
		filterForm.setType("EQ");
		filterForms.add(filterForm);
		filterForm = new FilterForm();
		filterForm.setName("Not Equals");
		filterForm.setType("NE");
		filterForms.add(filterForm);
		filterForm = new FilterForm();
		filterForm.setName("Greater Than");
		filterForm.setType("GT");
		filterForms.add(filterForm);
		filterForm = new FilterForm();
		filterForm.setName("Less Than");
		filterForm.setType("LT");
		filterForms.add(filterForm);
		
		if(param.getModel().getType() != null && param.getModel().getType() != ""){
			return filterForms.stream().filter(form -> form.getType().equals(param.getModel().getType())).collect(Collectors.toList());
		}
		if(!param.getModel().getMetricType().equals("KEYSUM")){
			return filterForms.stream().filter(form -> form.getType().equals("EQ") || form.getType().equals("NE")).collect(Collectors.toList());
		}
		return filterForms;
	}

	public void remove(FilterForm model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	public FilterForm save(FilterForm model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

	public void createTable() throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Not Supported.");
	}

}
