package br.com.alura.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormularioAlunoActivity extends AppCompatActivity{

	public static final String TITULO_NOVO_ALUNO = "Novo Aluno";
	private AlunoDao alunoDao = new AlunoDao();
	private EditText campoNome;
	private EditText campoTelefone;
	private EditText campoEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_aluno);
		setTitle(TITULO_NOVO_ALUNO);

		defineCampos();
		salvaAluno();
	}

	private void defineCampos(){
		campoNome = findViewById(R.id.activity_formulario_aluno_nome);
		campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
		campoEmail = findViewById(R.id.activity_formulario_aluno_email);
	}

	private Aluno defineAluno(){
		String nome = campoNome.getText().toString();
		String telefone = campoTelefone.getText().toString();
		String email = campoEmail.getText().toString();

		return new Aluno(nome, telefone, email);
	}

	private void salvaAluno(){
		Button botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
		botaoSalvar.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				Aluno aluno = defineAluno();
				alunoDao.salva(aluno);

				Toast.makeText(FormularioAlunoActivity.this, "Aluno " + aluno.getNome() + " salvo!",
					Toast.LENGTH_SHORT).show();
				finish();
			}
		});
	}
}
