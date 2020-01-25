package br.com.alura.agenda.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.activity.component.ListaAlunosActivityComponent;
import br.com.alura.agenda.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosActivity extends AppCompatActivity{

	private static final String TITULO_APPBAR = "Lista de Alunos";
	public static final String CHAVE_ALUNO = "aluno";
	private Intent daListaProForm;
	private ListaAlunosActivityComponent component;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_alunos);
		setTitle(TITULO_APPBAR);

		component = new ListaAlunosActivityComponent(this);
		defineFabNovoAluno();
		listaAlunos();
	}

	@Override
	protected void onResume(){
		super.onResume();
		daListaProForm = geraIntentProForm();
		component.atualizaLista();
	}

	private void defineFabNovoAluno(){
		FloatingActionButton novoAluno = findViewById(R.id.lista_alunos_fab);
		abreFormularioNovoAluno(novoAluno);
	}

	private void listaAlunos(){
		ListView listView = findViewById(R.id.lista_alunos_listView);
		component.defineListaAdapter(listView);
		registerForContextMenu(listView);

		abreFormularioEditaAluno(listView);
	}

	private Intent geraIntentProForm(){
		return new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
	}

	private void abreFormularioNovoAluno(FloatingActionButton novoAluno){
		novoAluno.setOnClickListener(view -> startActivity(daListaProForm));
	}

	private void abreFormularioEditaAluno(ListView listView){
		listView.setOnItemClickListener((adapterView, view, posicao, id) -> {
			Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
			daListaProForm.putExtra(CHAVE_ALUNO, aluno);
			startActivity(daListaProForm);
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item){
		component.defineMenuDeContextoRemover(item);
		return super.onContextItemSelected(item);
	}

}