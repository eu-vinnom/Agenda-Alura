package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AlunoDao;

public class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Aluno>> {

	private final List<Aluno> alunos;
	private final AlunoDao alunoDao;

	public AtualizaListaAsyncTask(List<Aluno> alunos, AlunoDao alunoDao){
		this.alunos = alunos;
		this.alunoDao = alunoDao;
	}

	@Override
	protected List<Aluno> doInBackground(Void... voids){
		List<Aluno> todosAlunos = alunoDao.listagem();
		return todosAlunos;
	}

	@Override
	protected void onPostExecute(List<Aluno> todosAlunos){
		super.onPostExecute(alunos);
		alunos.clear();
		alunos.addAll(todosAlunos);
	}
}
