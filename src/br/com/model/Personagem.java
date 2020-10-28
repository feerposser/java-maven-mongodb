package br.com.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.bson.types.ObjectId;

@XmlRootElement
public class Personagem {
	
	private String id;
	private String nome;
	private int ano;
	private String clan;
	
	//getters, setters e construtores
	
	public Personagem(String id, String nome,  int anoNascimento, String clan) {
		this.ano = anoNascimento;
		this.nome = nome;
		this.clan = clan;
		this.id = id;
	}
	
	public Personagem() {}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}

}
