package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.AlunoDao;
import br.com.alura.agenda.room.TelefoneDao;

import static br.com.alura.agenda.model.TipoTelefone.CELULAR;
import static br.com.alura.agenda.model.TipoTelefone.FIXO;

public class SalvaAlunoAsyncTask extends AsyncTask<Void, Void, Void> {

	private final AlunoDao alunoDao;
	private final TelefoneDao telefoneDao;
	private final Aluno aluno;
	private final String numeroFixo;
	private final String numeroCelular;
	private final FinalizaAtividadeListener listener;

	public SalvaAlunoAsyncTask(AlunoDao alunoDao,
														 TelefoneDao telefoneDao,
														 Aluno aluno, String numeroFixo, String numeroCelular, FinalizaAtividadeListener listener){
		this.alunoDao = alunoDao;
		this.telefoneDao = telefoneDao;
		this.aluno = aluno;
		this.numeroFixo = numeroFixo;
		this.numeroCelular = numeroCelular;
		this.listener = listener;
	}


	@Override
	protected Void doInBackground(Void... voids){
		int alunoId = alunoDao.salva(aluno).intValue();
		telefoneDao.salva(new Telefone(numeroFixo, FIXO, alunoId), new Telefone(numeroCelular, CELULAR, alunoId));

		listener.finaliza();

		return null;
	}
}
