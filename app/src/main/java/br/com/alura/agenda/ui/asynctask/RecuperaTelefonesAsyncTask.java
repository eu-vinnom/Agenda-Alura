package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.TelefoneDao;

public class RecuperaTelefonesAsyncTask extends AsyncTask<Void, Void, List<Telefone>> {

	private final TelefoneDao telefoneDao;
	private final Aluno aluno;
	private final DefineTelefonesListener listener;

	public RecuperaTelefonesAsyncTask(List<Telefone> telefones, TelefoneDao telefoneDao, Aluno aluno, DefineTelefonesListener listener){
		this.telefoneDao = telefoneDao;
		this.aluno = aluno;
		this.listener = listener;
	}

	@Override
	protected List<Telefone> doInBackground(Void... voids){
		List<Telefone> telefones = telefoneDao.devolveTodosTelefones(aluno.getId());
		return telefones;
	}

	@Override
	protected void onPostExecute(List<Telefone> telefones){
		super.onPostExecute(telefones);
		listener.defineNumerosTelefone(telefones);
	}
}
