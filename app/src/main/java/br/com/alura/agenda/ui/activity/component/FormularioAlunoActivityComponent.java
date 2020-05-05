package br.com.alura.agenda.ui.activity.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.AgendaBD;
import br.com.alura.agenda.room.AlunoDao;
import br.com.alura.agenda.room.TelefoneDao;

import static br.com.alura.agenda.model.TipoTelefone.CELULAR;
import static br.com.alura.agenda.model.TipoTelefone.FIXO;
import static br.com.alura.agenda.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

public class FormularioAlunoActivityComponent {

	private static final String AVISO_CAMPO_OBRIGATORIO = "Campo nome é obrigatório!";
	private final AlunoDao alunoDao;
	private final TelefoneDao telefoneDao;
	private EditText campoNome;
	private EditText campoTelefoneFixo;
	private EditText campoEmail;
	private EditText campoTelefoneCelular;
	private Aluno aluno;
	private Activity activity;
	private Context context;
	private List<Telefone> telefones;
	private String numeroFixo;
	private String numeroCelular;

	public FormularioAlunoActivityComponent(Activity activity, Context context){
		this.activity = activity;
		this.context = context;
		this.alunoDao = AgendaBD.getInstance(context).getRoomAlunoDao();
		this.telefoneDao = AgendaBD.getInstance(context).getTelefoneDao();
	}

	public void finalizaFormulario(){
		defineAluno();
		if(aluno.naoEhNulo()){
			if(aluno.temIdValido()){
				atualizaAluno();
			} else{
				salvaAluno();
			}
			activity.finish();
		}
		Toast.makeText(context, AVISO_CAMPO_OBRIGATORIO, Toast.LENGTH_SHORT).show();
	}

	private void defineAluno(){

		String nome = campoNome.getText().toString();
		String email = campoEmail.getText().toString();

		aluno.setNome(nome);
		aluno.setEmail(email);
	}

	public void recupera(Intent dadosAluno){
		aluno = (Aluno) dadosAluno.getSerializableExtra(CHAVE_ALUNO);
		campoNome.setText(aluno.getNome());
		campoEmail.setText(aluno.getEmail());

		recuperaTelefones();
	}

	private void salvaAluno(){
		int alunoId = alunoDao.salva(aluno).intValue();
		defineTelefones();
		telefoneDao.salva(new Telefone(numeroFixo, FIXO, alunoId), new Telefone(numeroCelular, CELULAR, alunoId));

		Toast.makeText(context, "Aluno " + aluno.getNome() + " salvo!",
			Toast.LENGTH_SHORT).show();
	}

	private void atualizaAluno(){
		alunoDao.atualiza(aluno);
		defineTelefones();
		Telefone telefoneFixo = new Telefone(numeroFixo, FIXO, aluno.getId());
		Telefone telefoneCelular = new Telefone(numeroCelular, CELULAR, aluno.getId());

		for(Telefone telefone : telefones){
			if(telefone.getTipo() == FIXO){
				telefoneFixo.setId(telefone.getId());
			} else{
				telefoneCelular.setId(telefone.getId());
			}
		}

		telefoneDao.atualiza(telefoneFixo, telefoneCelular);
		Toast.makeText(context, "Aluno " + aluno.getNome() + " editado!",
			Toast.LENGTH_SHORT).show();
	}

	private void defineTelefones(){
		numeroFixo = campoTelefoneFixo.getText().toString();
		numeroCelular = campoTelefoneCelular.getText().toString();
	}

	private void recuperaTelefones(){
		telefones = telefoneDao.devolveTodosTelefones(aluno.getId());
		for(Telefone telefone : telefones){
			if(telefone.getTipo() == FIXO){
				campoTelefoneFixo.setText(telefone.getNumero());
			} else{
				campoTelefoneCelular.setText(telefone.getNumero());
			}
		}
	}

	public void setCampoNome(EditText campoNome){
		this.campoNome = campoNome;
	}

	public void setCampoTelefoneFixo(EditText campoTelefoneFixo){
		this.campoTelefoneFixo = campoTelefoneFixo;
	}

	public void setCampoTelefoneCelular(EditText campoTelefoneCelular){
		this.campoTelefoneCelular = campoTelefoneCelular;
	}

	public void setCampoEmail(EditText campoEmail){
		this.campoEmail = campoEmail;
	}

	public void setAluno(Aluno aluno){
		this.aluno = aluno;
	}
}
