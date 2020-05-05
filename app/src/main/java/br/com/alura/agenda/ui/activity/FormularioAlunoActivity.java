package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.activity.component.FormularioAlunoActivityComponent;

import static br.com.alura.agenda.ui.activity.ListaAlunosActivity.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity{

	private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo Aluno";
	private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita Aluno";
	private FormularioAlunoActivityComponent component;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario_aluno);

		component = new FormularioAlunoActivityComponent(this, this);

		defineCampos();
	}

	private void defineCampos(){
		component.setCampoNome(findViewById(R.id.activity_formulario_aluno_nome));
		component.setCampoTelefoneFixo(findViewById(R.id.activity_formulario_aluno_telefone_fixo));
		component.setCampoTelefoneCelular(findViewById(R.id.activity_formulario_aluno_telefone_celular));
		component.setCampoEmail(findViewById(R.id.activity_formulario_aluno_email));

		Intent dadosAluno = getIntent();
		defineFormularioEditaOuSalva(dadosAluno);
	}

	private void defineFormularioEditaOuSalva(Intent dadosAluno){
		if(dadosAluno.hasExtra(CHAVE_ALUNO)){
			setTitle(TITULO_APPBAR_EDITA_ALUNO);
			component.recupera(dadosAluno);
		} else{
			setTitle(TITULO_APPBAR_NOVO_ALUNO);
			component.setAluno(new Aluno());
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.activity_formulario_aluno_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId() == R.id.activity_formulario_aluno_menu_salvar){
			component.finalizaFormulario();
		}
		return super.onOptionsItemSelected(item);
	}


}