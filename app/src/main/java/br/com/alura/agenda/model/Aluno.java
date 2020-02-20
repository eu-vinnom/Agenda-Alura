package br.com.alura.agenda.model;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Aluno implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private int id = 0;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String email;

	public String getNome(){
		return nome;
	}

	public String getNomeCompleto(){
		return nome + " " + sobrenome;
	}

	public void setNome(String nome){
		this.nome = nome;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTelefone(){
		return telefone;
	}

	public void setTelefone(String telefone){
		this.telefone = telefone;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	@NonNull
	@Override
	public String toString(){
		return nome;
	}

	public boolean temIdValido(){
		return id > 0;
	}

	public boolean naoEhNulo(){
		if(getNome().isEmpty()){
			return false;
		} else return !getNome().trim().isEmpty();
	}

	public String getSobrenome(){
		return sobrenome;
	}

	public void setSobrenome(String sobrenome){
		this.sobrenome = sobrenome;
	}
}
