package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDao{

	private final static List<Aluno> alunos = new ArrayList<>();

	public List<Aluno> listagem(){
		return new ArrayList<>(alunos);
	}

	public void salva(Aluno aluno){
		alunos.add(aluno);
	}
}
