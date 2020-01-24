package br.com.alura.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static br.com.alura.agenda.activity.ListaAlunosActivity.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity{

	public static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
	private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
	private AlunoDao alunoDao = new AlunoDao();
	private EditText campoNome;
	private EditText campoTelefone;
	private EditText campoEmail;
	private Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_aluno);

		defineCampos();
		acaoDoBotaoSalvar();
	}

	private void defineCampos(){
		campoNome = findViewById(R.id.activity_formulario_aluno_nome);
		campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
		campoEmail = findViewById(R.id.activity_formulario_aluno_email);

		Intent dadosAluno = getIntent();
		defineFormularioEditaOuSalva(dadosAluno);

	}

	private void defineFormularioEditaOuSalva(Intent dadosAluno){
		if(dadosAluno.hasExtra(CHAVE_ALUNO)){
			setTitle(TITULO_APPBAR_EDITA_ALUNO);
			aluno = (Aluno) dadosAluno.getSerializableExtra(CHAVE_ALUNO);
			campoNome.setText(aluno.getNome());
			campoTelefone.setText(aluno.getTelefone());
			campoEmail.setText(aluno.getEmail());
		} else{
			setTitle(TITULO_APPBAR_NOVO_ALUNO);
			aluno = new Aluno();
		}
	}

	private void defineAluno(){

		String nome = campoNome.getText().toString();
		String telefone = campoTelefone.getText().toString();
		String email = campoEmail.getText().toString();

		aluno.setNome(nome);
		aluno.setTelefone(telefone);
		aluno.setEmail(email);
	}

	private void acaoDoBotaoSalvar(){
		Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
		botaoSalvar.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				finalizaFormulario();
			}
		});
	}

	private void finalizaFormulario(){
		defineAluno();
		if(aluno.temIdValido()){
			editaAluno();
		}else{
			salvaAluno();
		}
		finish();
	}

	private void salvaAluno(){
		alunoDao.salva(aluno);
		Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo!",
			Toast.LENGTH_SHORT).show();
	}

	private void editaAluno(){
		alunoDao.edita(aluno);
		Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " editado!",
			Toast.LENGTH_SHORT).show();
	}
}
