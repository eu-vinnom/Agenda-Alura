package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.TelefoneDao;

public class AtualizaTelefonesAsyncTask extends AsyncTask<Void, Void, Void> {

	private final TelefoneDao telefoneDao;
	private final Telefone telefoneFixo;
	private final Telefone telefoneCelular;
	private final DefineTelefonesListener listener;
	private final List<Telefone> telefones;
	private final FinalizaAtividadeListener finalizaAtividadeListener;

	public AtualizaTelefonesAsyncTask(TelefoneDao telefoneDao,
																		Telefone telefoneFixo, Telefone telefoneCelular,
																		List<Telefone> telefones, DefineTelefonesListener listener,
																		FinalizaAtividadeListener finalizaAtividadeListener){
		this.telefoneDao = telefoneDao;
		this.telefoneFixo = telefoneFixo;
		this.telefoneCelular = telefoneCelular;
		this.telefones = telefones;
		this.listener = listener;
		this.finalizaAtividadeListener = finalizaAtividadeListener;
	}

	@Override
	protected Void doInBackground(Void... voids){
		telefoneDao.atualiza(telefoneFixo, telefoneCelular);
		listener.defineNumerosTelefone(telefones);
		finalizaAtividadeListener.finaliza();
		return null;
	}


}
