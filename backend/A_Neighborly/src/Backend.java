import java.util.Arrays;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;

public class Backend {
	public static void main(String[] args) {	
		insertData();
		int nid = 0;
		queryData(nid);
	}
		
	public static void insertData() {
		ServerAddress sa = new ServerAddress("ds147995.mlab.com:47995");
		MongoCredential cred = MongoCredential.createCredential("admin", "neighborly", "admin".toCharArray());
		MongoClient mongo = new MongoClient(sa, Arrays.asList(cred));
        System.out.println("Connect to database successfully");
       
        String str = new String ("{\"nid\" : 7,\"active_user\" : false}");
		DBObject dbObject = (DBObject) JSON.parse(str);
		DBCollection table  = mongo.getDB("neighborly").getCollection("users");
		table.insert(dbObject);
		System.out.println("Data inserted in " + table.getName());
	}
	
	public static void queryData(int nid) {
		ServerAddress sa = new ServerAddress("ds147995.mlab.com:47995");
		MongoCredential cred = MongoCredential.createCredential("admin", "neighborly", "admin".toCharArray());
		MongoClient mongo = new MongoClient(sa, Arrays.asList(cred));
        System.out.println("Connect to database successfully");
                
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("nid", nid);
      
        DBCursor cursor = mongo.getDB("neighborly").getCollection("users").find(whereQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
	}
	
	public static void queryAll(String availableCategory) {
		ServerAddress sa = new ServerAddress("ds147995.mlab.com:47995");
		MongoCredential cred = MongoCredential.createCredential("admin", "neighborly", "admin".toCharArray());
		MongoClient mongo = new MongoClient(sa, Arrays.asList(cred));
        System.out.println("Connect to database successfully");
                
        DBCursor cursor = mongo.getDB("neighborly").getCollection("users").find();
        while (cursor.hasNext()) {
        	if (cursor.next().containsField(availableCategory)) {
                System.out.println(cursor.getCursorId());
        	}
        }
	}
		
}
