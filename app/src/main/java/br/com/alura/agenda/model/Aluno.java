package br.com.alura.agenda.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Aluno implements Serializable{

	@PrimaryKey(autoGenerate = true)
	private int id = 0;
	private String nome;
	private String telefone;
	private String email;
	private Calendar dataInsercao;

	public Aluno(){
		this.dataInsercao = Calendar.getInstance();
	}

	public String getNome(){
		return nome;
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

	public Calendar getDataInsercao(){
		return dataInsercao;
	}

	public void setDataInsercao(Calendar dataInsercao){
		this.dataInsercao = dataInsercao;
	}

	public String getDataFormatada(){
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		if(dataInsercao != null)
			return formatador.format(dataInsercao.getTime());
		else return "";
	}
}
