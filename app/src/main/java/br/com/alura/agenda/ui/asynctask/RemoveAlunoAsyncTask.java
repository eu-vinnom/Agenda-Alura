package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AlunoDao;

public class RemoveAlunoAsyncTask extends AsyncTask<Void, Void, Void> {

	private final Aluno aluno;
	private final List<Aluno> alunos;
	private final AlunoDao alunoDao;

	public RemoveAlunoAsyncTask(Aluno aluno, List<Aluno> alunos, AlunoDao alunoDao){
		this.aluno = aluno;
		this.alunos = alunos;
		this.alunoDao = alunoDao;
	}

	@Override
	protected Void doInBackground(Void... voids){
		alunos.remove(aluno);
		alunoDao.remove(aluno);
		return null;
	}
}
