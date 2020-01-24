package br.com.alura.agenda.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDao{

	private static List<Aluno> alunos = new ArrayList<>();
	private static int id = 1;

	public List<Aluno> listagem(){
		return new ArrayList<>(alunos);
	}

	public void salva(Aluno aluno){
		aluno.setId(id);
		alunos.add(aluno);
		atualizaId();
	}

	private int atualizaId(){
		return id++;
	}

	public void edita(Aluno aluno){
		Aluno alunoEditavel = procuraAlunoPeloId(aluno);
		if(alunoEditavel != null){
			int posicaoAluno = alunos.indexOf(alunoEditavel);
			alunos.set(posicaoAluno, aluno);
		}
	}

	private Aluno procuraAlunoPeloId(Aluno aluno){
		for (Aluno a : alunos ) {
		    if(a.getId() == aluno.getId()){
		    	return a;
				}
		}
		return null;
	}


}
