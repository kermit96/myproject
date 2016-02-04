package MongoDB;

import com.mongodb.*;

public class InsertData {

	public InsertData()
	{
		
         MongoClient mongo = new MongoClient("localhost",27017);
         
         DB db = mongo.getDB("test");
	   
          DBCollection collection = db.getCollection("person");
          		
          BasicDBObject  doc = new BasicDBObject (); 
          
          doc.append("name","홍길동");
          collection.insert(doc);
          
          System.out.println("완성");
          
          mongo.close();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       new InsertData();
	}

}
