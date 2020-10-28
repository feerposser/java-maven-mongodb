package br.com.db;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public final class DB {

	private static String url = 
			"mongodb+srv://admin:admin@poo2-narutoapi.6c4zg.mongodb.net/"
			+ "naruto?w=majority&"
			+ "connectedTimeoutMS=30000&"
			+ "socketTimeoutMS=30000";
	
	private static String url2 = "mongodb://localhost:27017";
	
	private static MongoDatabase database = null;
	
	public static MongoDatabase getDatabase() {
		
		String databaseName = "naruto";

		if (database == null) {
			try {
				MongoClientURI uri = new MongoClientURI(url2);

				MongoClient mongoClient = new MongoClient(uri);
				
				database = mongoClient.getDatabase(databaseName);
								
//				MongoCollection<Document> collection = database.getCollection("personagens");
//				
//				for (String name : database.listCollectionNames())
//					System.out.println(name);
//				
//				System.out.println(collection.countDocuments());
				
			} catch (Exception e) {
				System.out.println("===================");
				e.printStackTrace();
				throw new DbException(e.getMessage());
			}
		}
		return database;
	}
	
}
