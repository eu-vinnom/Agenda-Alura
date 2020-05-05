package br.com.alura.agenda.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.*;

@Entity
public class Telefone {

	@PrimaryKey(autoGenerate = true)
	private int id = 0;
	private String numero;
	private TipoTelefone tipo;
	@ForeignKey(entity = Aluno.class, parentColumns = "id", childColumns = "alunoId", onDelete = CASCADE, onUpdate = CASCADE)
	private int alunoId = 0;

	public Telefone(String numero, TipoTelefone tipo, int alunoId){
		this.numero = numero;
		this.tipo = tipo;
		this.alunoId = alunoId;
	}

	public int getAlunoId(){
		return alunoId;
	}

	public void setAlunoId(int alunoId){
		this.alunoId = alunoId;
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getNumero(){
		return numero;
	}

	public void setNumero(String numero){
		this.numero = numero;
	}

	public TipoTelefone getTipo(){
		return tipo;
	}

	public void setTipo(TipoTelefone tipo){
		this.tipo = tipo;
	}

}
