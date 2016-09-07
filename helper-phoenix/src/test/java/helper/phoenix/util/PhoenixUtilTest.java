//package helper.phoenix.util;
//
//import org.junit.Test;
//
//import common.query.QueryParam;
//import helper.phoenix.model.TestModel;
//
//public class PhoenixUtilTest {
//	@Test
//    public void testUtil() throws Exception {
//           TestModel tester = new TestModel(); // MyClass is tested
//           tester.setId(1L);
//           QueryParam<TestModel> param = new QueryParam<TestModel>(TestModel.class);
//           param.setModelParam(tester);
//           param.setLimit(1L);
//           System.out.println(PhoenixUtil.createCountSQL(param)); 
//           System.out.println(PhoenixUtil.createGetSQL(param)); 
//       
//    }
//}