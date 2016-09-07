package helper.phoenix.model;

import helper.phoenix.annotation.entity.PhoenixColumn;
import helper.phoenix.annotation.entity.PhoenixID;
import helper.phoenix.annotation.entity.PhoenixSequence;
import helper.phoenix.annotation.entity.PhoenixTable;
import helper.phoenix.annotation.query.PhoenixDistinctColumn;
import helper.phoenix.annotation.query.PhoenixPaginated;

@PhoenixTable(table="testTable")
public class TestModel{

	@PhoenixID
	@PhoenixSequence
	@PhoenixColumn(type="BIGINT")
	private Long id;
	@PhoenixDistinctColumn
	@PhoenixColumn(type="INTEGER")
	private int count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
