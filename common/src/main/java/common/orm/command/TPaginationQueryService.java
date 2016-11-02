package common.orm.command;
//package common.service;
//
//import java.util.List;
//
//import common.CommonInterface;
//import common.form.RequestForm;
//import common.form.PaginateResponse;
//import common.query.QueryParam;
//
//public interface TPaginationQueryService<T> extends Query<List<T>, QueryParam<T>>, CommonInterface<T> {
//	default List<T> query(RequestForm<T> param) throws Exception {
//		return query(new QueryParam<T>(getParameterizedType(0), param.getData(), param.getPage(), param.getLimit()));
//	}
//	default Long count(RequestForm<T> param) throws Exception {
//		return count(new QueryParam<T>(getParameterizedType(0), param.getData()));
//	}
//	default PaginateResponse<T> getAllWithPagination(RequestForm<T> param) throws Exception{
//		return new PaginateResponse<T>(query(param), count(param));
//	}
//	default T getOne(QueryParam<T> param) throws Exception{
//		param.setLimit(1L);
//		List<T> result = query(param);
//		if(result.size() > 0) {
//			return result.get(0);
//		} else {
//			return null;
//		}
//	}
//}
