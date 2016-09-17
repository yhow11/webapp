package service.pagecount.impl;

import java.util.List;

import org.apache.spark.sql.SQLContext;

import common.query.QueryParam;
import helper.spark.sql.SparkSQLTemplate;
import helper.spark.sql.util.SparkSQLUtil;
import service.pagecount.PageCountService;
import service.pagecount.model.PageCountModel;

public class PageCountSparkSQLServiceImpl extends SparkSQLTemplate implements PageCountService{

	public PageCountSparkSQLServiceImpl(SQLContext sqlContext, String zookepers, Class<?> modelClass) throws Exception {
		super(sqlContext, zookepers, SparkSQLUtil.getTableName(modelClass));
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<PageCountModel> getAll(QueryParam<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.search(param);
	}

	@Override
	public Long getCount(QueryParam<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.count(param);
	}

	@Override
	public PageCountModel get(String id) throws Exception {
		// TODO Auto-generated method stub
		QueryParam<PageCountModel> param = new QueryParam<PageCountModel>(PageCountModel.class);
		param.getModel().setVISITORID(id);
		return super.searchOne(param);
	}

	@Override
	public PageCountModel save(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		return super.insert(model);
	}

	@Override
	public void remove(PageCountModel model) throws Exception {
		// TODO Auto-generated method stub
		throw new Exception("Delete record not supported.");
	}

	@Override
	public PageCountModel get(QueryParam<PageCountModel> param) throws Exception {
		// TODO Auto-generated method stub
		return super.searchOne(param);
	}

}
