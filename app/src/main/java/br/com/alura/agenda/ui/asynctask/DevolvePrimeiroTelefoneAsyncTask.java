package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.TelefoneDao;

public class DevolvePrimeiroTelefoneAsyncTask extends AsyncTask<Void, Void, Telefone> {

	private final TelefoneDao telefoneDao;
	private final int alunoId;
	private final DevolvePrimeiroTelefone telefoneListener;

	public DevolvePrimeiroTelefoneAsyncTask(TelefoneDao telefoneDao, int alunoId, DevolvePrimeiroTelefone telefoneListener){
		this.telefoneDao = telefoneDao;
		this.telefoneListener = telefoneListener;
		this.alunoId = alunoId;
	}

	@Override
	protected Telefone doInBackground(Void... voids){
		Telefone primeiroTelefone = telefoneDao.devolvePrimeiroTelefone(alunoId);
		if(primeiroTelefone != null){
			telefoneListener.devolveQuandoEncontrado(primeiroTelefone);
		}

		return primeiroTelefone;
	}

	@Override
	protected void onPostExecute(Telefone telefone){
		super.onPostExecute(telefone);

	}

	public interface DevolvePrimeiroTelefone {
		void devolveQuandoEncontrado(Telefone telefone);
	}

}