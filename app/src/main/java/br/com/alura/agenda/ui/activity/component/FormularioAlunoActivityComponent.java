package br.com.alura.agenda.ui.activity.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AgendaBD;
import br.com.alura.agenda.room.AlunoDao;

import static br.com.alura.agenda.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

public class FormularioAlunoActivityComponent{

	private EditText campoNome;
	private EditText campoTelefone;
	private EditText campoEmail;
	private Aluno aluno;
	private Activity activity;
	private AlunoDao alunoDao;
	private Context context;

	public FormularioAlunoActivityComponent(Activity activity, Context context){
		this.activity = activity;
		this.context = context;
		this.alunoDao = AgendaBD.getInstance(context).getRoomAlunoDao();
	}

	public void finalizaFormulario(){
		defineAluno();
		if(aluno.naoEhNulo()){
			if(aluno.temIdValido()){
				editaAluno();
			} else{
				salvaAluno();
			}
			activity.finish();
		}
		Toast.makeText(context, "Campo nome é obrigatório!", Toast.LENGTH_SHORT).show();
	}

	private void defineAluno(){

		String nome = campoNome.getText().toString();
		String telefone = campoTelefone.getText().toString();
		String email = campoEmail.getText().toString();

		aluno.setNome(nome);
		aluno.setTelefone(telefone);
		aluno.setEmail(email);
	}

	public void recupera(Intent dadosAluno){
		aluno = (Aluno) dadosAluno.getSerializableExtra(CHAVE_ALUNO);
		campoNome.setText(aluno.getNome());
		campoTelefone.setText(aluno.getTelefone());
		campoEmail.setText(aluno.getEmail());
	}

	private void salvaAluno(){
		alunoDao.salva(aluno);
		Toast.makeText(context, "Aluno " + aluno.getNome() + " salvo!",
			Toast.LENGTH_SHORT).show();
	}

	private void editaAluno(){
		alunoDao.edita(aluno);
		Toast.makeText(context, "Aluno " + aluno.getNome() + " editado!",
			Toast.LENGTH_SHORT).show();
	}

	public void setCampoNome(EditText campoNome){
		this.campoNome = campoNome;
	}

	public void setCampoTelefone(EditText campoTelefone){
		this.campoTelefone = campoTelefone;
	}

	public void setCampoEmail(EditText campoEmail){
		this.campoEmail = campoEmail;
	}

	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
}
