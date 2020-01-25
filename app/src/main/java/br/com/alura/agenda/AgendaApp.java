package br.com.alura.agenda;

import android.app.Application;

import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

@SuppressWarnings("WeakerAccess")
public class AgendaApp extends Application{

	@Override
	public void onCreate(){
		super.onCreate();
		criaAlunosParaTeste();
	}

	private void criaAlunosParaTeste(){
		AlunoDao alunoDao = new AlunoDao();
		alunoDao.salva(new Aluno("vi", "123456789", "vi@vi.com.br"));
		alunoDao.salva(new Aluno("ka", "123456789", "ka@ka.com.br"));
	}
}
