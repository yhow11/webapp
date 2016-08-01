package plugin.highchart.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import plugin.highchart.dao.ChartDao;
import plugin.highchart.enums.ChartCategoryEnum;

public class ChartDaoImpl implements ChartDao {
	private MongoTemplate mongoTemplate;

	public ChartDaoImpl() {
			// TODO Auto-generated constructor stub
		}

	public ChartDaoImpl(MongoTemplate mongoTemplate) {
			// TODO Auto-generated constructor stub
	    	this.mongoTemplate = mongoTemplate;
		}

	@Override
	public <T> List<T> getCompletion(Class<T> clazz, String time, String type, String value) {
		// TODO Auto-generated method stub
		
		/*{ $unwind : "$r309" }*/
		final DBObject unwind = new BasicDBObject("$unwind", "$r309");
		
		
		/*{ $project: {
		    name: "$name",
		    area: "$area",
		    group: "$group",
		    lcode: "$lcode",
		    dcode: "$dcode",
		    startDate: "$r309.weekStartDate",
		    endDate: "$r309.weekEndDate",
		    status: {
		        $ifNull: ["$r309.date", null]
		        }
		    },
		    
		}*/
		DBObject project1 = (DBObject) JSON.parse("{ $project: {\r\n    id: \"$_id\",\r\n    name: \"$name\",\r\n    area: \"$area\",\r\n    group: \"$group\",\r\n    lcode: \"$lcode\",\r\n    dcode: \"$dcode\",\r\n    startDate: \"$r309.weekStartDate\",\r\n    endDate: \"$r309.weekEndDate\",\r\n    status: {\r\n        $ifNull: [\"$r309.date\", null]\r\n        }\r\n    },\r\n    \r\n}");
		
		/*{ $project: {
		    name: "$name",
		    area: "$area",
		    group: "$group",
		    lcode: "$lcode",
		    dcode: "$dcode",
		    status:{
		                 $cond: { if: { $ne: [ "$status", null ] }
		                        , then: {
		                            $cond: { if: { $gte: [ "$status", "$startDate"  ] }
		                                , then: {
		                                    $cond: { if: { $lte: [  "$status" , "$endDate"] }, then: "PRESENT", else: "ABSENT" }
		                                }
		                                , else: "ABSENT" }
		                       }, else: {
		                            $cond: { if: { $lte: [  new Date() , "$endDate"] }, then: "N/A", else: "ABSENT" }
		                        }
		                       }
		                       }
		    }
		}*/
		List<Object> ltes = new ArrayList<>();
		ltes.add(new Date());
		ltes.add("$endDate");
		DBObject lte = new BasicDBObject("$lte",ltes);
		
		 com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
		 
		 
		DBObject project2 = (BasicDBObject) JSON.parse("{ $project: {\r\n    id: \"$_id\",\r\n    name: \"$name\",\r\n    area: \"$area\",\r\n    group: \"$group\",\r\n    lcode: \"$lcode\",\r\n    dcode: \"$dcode\",\r\n    endDate: \"$endDate\",\r\n    status:{\r\n                 $cond: { \r\n                     if: { $ne: [ \"$status\", null ] }\r\n                   , then: {\r\n                            $cond: { \r\n                                if: { $gte: [ \"$status\", \"$startDate\"  ] }\r\n                              , then: {\r\n                                    $cond: { \r\n                                        if: { $lte: [  \"$status\" , \"$endDate\"] }\r\n                                      , then: \"PRESENT\"\r\n                                      , else: \"ABSENT\" }\r\n                                }\r\n                              , else: \"ABSENT\" }\r\n                       }\r\n                   , else: {\r\n                            $cond: { \r\n                                if: "+lte+"\r\n                              , then: \"N/A\"\r\n                              , else: \"ABSENT\" }\r\n                        }\r\n                   }\r\n           }\r\n    }\r\n}");
		DBObject projectnode = (BasicDBObject) project2.get("$project");
		DBObject status = (BasicDBObject) projectnode.get("status");
		DBObject cond = (BasicDBObject) status.get("$cond");
		DBObject elses = (BasicDBObject) cond.get("else");
		cond = (BasicDBObject) elses.get("$cond");
		DBObject ifs = (BasicDBObject) cond.get("if");
//		DBObject ltea = (BasicDBObject) ifs.get("$lte");
		/*{
		    $group : {
		       _id : { 
		            name: "$name",
		            area: "$area",
		            group: "$group",
		            lcode: "$lcode",
		            dcode: "$dcode" },
		       count: { $sum: {$cond: { if: { $eq: [  "$status" , "ABSENT"] }, then: 1, else: 0 } }}
		    }
		}*/
		DBObject group = (DBObject) JSON.parse("{\r\n    $group : {\r\n       _id : { \r\n            id: \"$id\",\r\n            name: \"$name\",\r\n            area: \"$area\",\r\n            group: \"$group\",\r\n            lcode: \"$lcode\",\r\n            dcode: \"$dcode\" },\r\n       lastDate: {\"$last\": \"$endDate\"},\r\n       count: { $sum: {$cond: { if: { $eq: [  \"$status\" , \"ABSENT\"] }, then: 1, else: 0 } }},\r\n       present: { $sum: {$cond: { if: { $eq: [  \"$status\" , \"PRESENT\"] }, then: 1, else: 0 } }}\r\n    }\r\n}");
		
		/*{
		    $project : {
		       name: "$_id.name",
		       area: "$_id.area",
		       group: "$_id.group",
		       lcode: "$_id.lcode",
		       dcode: "$_id.dcode",
		       absent: "$count"
		    }
		}*/
		DBObject project4 = (DBObject) JSON.parse("{\r\n    $project : {\r\n       id: \"$_id.id\",\r\n       name: \"$_id.name\",\r\n       area: \"$_id.area\",\r\n       group: \"$_id.group\",\r\n       lcode: \"$_id.lcode\",\r\n       dcode: \"$_id.dcode\",\r\n       lastDate: \"$lastDate\",\r\n       year: { $year: \"$lastDate\" },\r\n       month: { $month: \"$lastDate\" },\r\n       day: { $dayOfMonth: \"$lastDate\" },\r\n       absent: \"$count\",\r\n       present: \"$present\"\r\n    }\r\n}");
		
		DBObject group2 = (DBObject) JSON.parse("{\r\n    $group : {\r\n       _id : { \r\n   id: \"$id\",area: \"$"+ChartCategoryEnum.valueOf(type).getCode()+"\",\r\n          month: \"$month\" },\r\n        \r\n    }\r\n}");
		
		DBObject project5 = (DBObject) JSON.parse("{\r\n    $project : {\r\n       id: \"$_id.area\",\r\n       month: \"$_id.month\",\r\n       months: { $ifNull : [\"$links\" , [1,2,3,4,5,6,7,8,9,10,11,12]]} \r\n    }\r\n}");
		
		final DBObject unwind2 = new BasicDBObject("$unwind", "$months");
		
		DBObject group3 = (DBObject) JSON.parse("{\r\n    $group : {\r\n       _id : { \r\n           month: \"$months\" ,\r\n           id: \"$id\"\r\n          \r\n       },\r\n       count: {$sum: {$cond: { if: { $eq: [  \"$month\" , \"$months\"] }, then: 1, else: 0 } }}  \r\n    }\r\n}");
		
		DBObject project6 = (DBObject) JSON.parse("{\r\n    $project: {\r\n        month: \"$_id.month\",\r\n        area: \"$_id.id\",\r\n        count: \"$count\"\r\n    }\r\n}");
		
		DBObject sort = (DBObject) JSON.parse("{ $sort : { month : 1 } }");
		
		DBObject group4 = (DBObject) JSON.parse("{\r\n    $group : {\r\n       _id : { \r\n           id: \"$area\"\r\n          \r\n       },\r\n       data: {$push: \"$count\"}  \r\n    }\r\n}");
		
		DBObject project7 = (DBObject) JSON.parse("{\r\n    $project: {\r\n        id: \"$_id.id\",\r\n        data: \"$data\"\r\n    }\r\n}");
		
		
		final AggregationOutput aggregate = mongoTemplate.getCollection("memberModel").aggregate(unwind, project1, project2, group
				, project4, group2, project5,  unwind2, group3, project6, sort, group4, project7 );
		
		
		List<T> results = new ArrayList<T>();
		
		for(DBObject item : aggregate.results()){
			results.add(mongoTemplate.getConverter().read(clazz, item));
		}
		return results;
	}
}
