package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AlunoDao;

public class AtualizaListaAsyncTask extends AsyncTask<Void, Void, List<Aluno>> {

	private final List<Aluno> alunos;
	private final AlunoDao alunoDao;
	private final DepoisDaExecucao depoisDaExecucao;

	public AtualizaListaAsyncTask(List<Aluno> alunos, AlunoDao alunoDao, DepoisDaExecucao depoisDaExecucao){
		this.alunos = alunos;
		this.alunoDao = alunoDao;
		this.depoisDaExecucao = depoisDaExecucao;
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
		depoisDaExecucao.executa();
	}

	public interface DepoisDaExecucao{
		void executa();
	}
}
