package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AlunoDao;

public class AtualizaAlunosAsyncTask extends AsyncTask<Void, Void, Void> {

	private final AlunoDao alunoDao;
	private final Aluno aluno;

	public AtualizaAlunosAsyncTask(AlunoDao alunoDao, Aluno aluno){
		this.alunoDao = alunoDao;
		this.aluno = aluno;
	}

	@Override
	protected Void doInBackground(Void... voids){
		alunoDao.atualiza(aluno);

		return null;
	}

}
