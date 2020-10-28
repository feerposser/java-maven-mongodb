package br.com.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.com.db.DB;
import br.com.model.Personagem;

public class PersonagemDAO implements InterfaceDAO<Personagem> {

	private static MongoDatabase database = null;
	private static MongoCollection<Document> collection = null;

	public PersonagemDAO() {
		database = DB.getDatabase();
		collection = database.getCollection("personagem");
	}

	private Document createDocument(Personagem p, boolean id) {
		Document document = new Document();

		document.append("nome", p.getNome()).append("clan", p.getClan()).append("ano", p.getAno());

		return document;
	}
	
	private BasicDBObject getBasicDBObject(String id) {
		BasicDBObject obj = new BasicDBObject();
		obj.append("_id", new ObjectId(id));
		return obj;
	}

	@Override
	public boolean insert(Personagem t) {
		System.out.println("Insert");

		try {
			this.collection.insertOne(createDocument(t, false));
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Personagem findById(String id) {
		System.out.println("findById");

		Document result = collection.find(getBasicDBObject(id)).first();
		Personagem p = new Personagem();

		if (!result.isEmpty()) {
			System.out.println(result.toJson());
			p.setId(result.getObjectId("_id").toString());
			p.setNome(result.getString("nome"));
			p.setClan(result.getString("clan"));
			p.setAno(result.getInteger("ano"));

			return p;
		}

		return null;
	}

	@Override
	public List<Personagem> findAll() {
		System.out.println("findAll");

		List<Personagem> personagens = new ArrayList<Personagem>();

		MongoCursor<Document> documents = this.collection.find().iterator();

		while (documents.hasNext()) {
			Personagem p = new Personagem();
			Document document = documents.next();

			p.setNome(document.getString("nome"));
			p.setAno(document.getInteger("ano"));
			p.setClan(document.getString("clan"));
			p.setId(document.getObjectId("_id").toString());

			personagens.add(p);
		}

		return personagens;
	}

	@SuppressWarnings("finally")
	@Override
	public boolean update(Personagem t) {
		System.out.println("update");

		try {
			this.collection.replaceOne(getBasicDBObject(t.getId()), createDocument(t, false));
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Personagem t) {
		System.out.println("delete");

		try {
			this.collection.deleteOne(getBasicDBObject(t.getId()));
		} catch (Exception e) {
			System.out.println("erro: " + e.getMessage());
			return false;
		}

		return true;
	}

}
