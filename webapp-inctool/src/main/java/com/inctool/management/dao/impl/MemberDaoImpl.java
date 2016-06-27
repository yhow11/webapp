package com.inctool.management.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.inctool.common.model.ActionModel;
import com.inctool.management.dao.MemberDao;
import com.inctool.management.model.MemberModel;
import com.inctool.management.util.ScheduleUtil;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

@Repository
public class MemberDaoImpl implements MemberDao {

	
    private MongoTemplate mongoTemplate;

    public MemberDaoImpl() {
		// TODO Auto-generated constructor stub
	}
    public MemberDaoImpl(MongoTemplate mongoTemplate) {
		// TODO Auto-generated constructor stub
    	this.mongoTemplate = mongoTemplate;
	}
	@Override
	public MemberModel save(MemberModel memberModel) {
		// TODO Auto-generated method stub
		mongoTemplate.save(memberModel);
		return memberModel;
	}
	@Override
	public List<MemberModel> findAll() throws ParseException {
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
		DBObject project4 = (DBObject) JSON.parse("{\r\n    $project : {\r\n       id: \"$_id.id\",\r\n       name: \"$_id.name\",\r\n       area: \"$_id.area\",\r\n       group: \"$_id.group\",\r\n       lcode: \"$_id.lcode\",\r\n       dcode: \"$_id.dcode\",\r\n       lastDate: \"$lastDate\",\r\n       absent: \"$count\",\r\n       present: \"$present\"\r\n    }\r\n}");
		
		final AggregationOutput aggregate = mongoTemplate.getCollection("memberModel").aggregate(unwind, project1, project2, group, project4);
		
		List<MemberModel> memberModels = new ArrayList<>();
		Iterable<DBObject> list= aggregate.results();
		for(DBObject item : list){
			ObjectId id = (ObjectId) item.get("id");
			MemberModel memberModel =  new MemberModel();
			memberModel.setId(id.toString());;
			memberModel.setDcode((String) item.get("dcode"));
			memberModel.setLcode((String) item.get("lcode"));
			memberModel.setArea((String) item.get("area"));
			memberModel.setGroup((String) item.get("group"));
			memberModel.setName((String) item.get("name"));
			memberModel.setAbsent((Integer) item.get("absent"));
			memberModel.setPresent((Integer) item.get("present"));
			memberModel.setCompletionDate((Date) item.get("lastDate"));
			if(memberModel.getCompletionDate() != null) {
				memberModel.setCompletionDate(ScheduleUtil.getCompletionDate(memberModel.getCompletionDate(), memberModel.getAbsent()));
			}
			ActionModel actionModel = new ActionModel();
			actionModel.setName("Edit");
			memberModel.getActions().add(actionModel);
			actionModel = new ActionModel();
			actionModel.setName("Delete");
			memberModel.getActions().add(actionModel);
			memberModels.add(memberModel);
		}
		return memberModels;
	}
	@Override
	public MemberModel findOne(String id) {
		// TODO Auto-generated method stub
		return mongoTemplate.findById(id, MemberModel.class);
	}
	@Override
	public MemberModel findDetails(String id) {
		// TODO Auto-generated method stub

		/*{ $unwind : "$r309" }*/
		final DBObject unwind = new BasicDBObject("$unwind", "$r309");
		
		DBObject matchFields = new BasicDBObject("_id", new ObjectId(id));
		final DBObject match = new BasicDBObject("$match", matchFields);
		
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
		DBObject project4 = (DBObject) JSON.parse("{\r\n    $project : {\r\n       id: \"$_id.id\",\r\n       name: \"$_id.name\",\r\n       area: \"$_id.area\",\r\n       group: \"$_id.group\",\r\n       lcode: \"$_id.lcode\",\r\n       dcode: \"$_id.dcode\",\r\n       lastDate: \"$lastDate\",\r\n       absent: \"$count\",\r\n       present: \"$present\"\r\n    }\r\n}");
		
		final AggregationOutput aggregate = mongoTemplate.getCollection("memberModel").aggregate(unwind, match, project1, project2, group, project4);
		
		List<MemberModel> memberModels = new ArrayList<>();
		Iterable<DBObject> list= aggregate.results();
		for(DBObject item : list){
			ObjectId _id = (ObjectId) item.get("id");
			MemberModel memberModel =  new MemberModel();
			memberModel.setId(_id.toString());;
			memberModel.setDcode((String) item.get("dcode"));
			memberModel.setLcode((String) item.get("lcode"));
			memberModel.setArea((String) item.get("area"));
			memberModel.setGroup((String) item.get("group"));
			memberModel.setName((String) item.get("name"));
			memberModel.setAbsent((Integer) item.get("absent"));
			memberModel.setPresent((Integer) item.get("present"));
			memberModel.setCompletionDate((Date) item.get("lastDate"));
			if(memberModel.getCompletionDate() != null) {
				memberModel.setCompletionDate(ScheduleUtil.getCompletionDate(memberModel.getCompletionDate(), memberModel.getAbsent()));
			}
			ActionModel actionModel = new ActionModel();
			actionModel.setName("Edit");
			memberModel.getActions().add(actionModel);
			actionModel = new ActionModel();
			actionModel.setName("Delete");
			memberModel.getActions().add(actionModel);
			memberModels.add(memberModel);
		}
		return memberModels.get(0);
	}
	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
		mongoTemplate.remove(mongoTemplate.findById(id, MemberModel.class));
	}
	
	
}
