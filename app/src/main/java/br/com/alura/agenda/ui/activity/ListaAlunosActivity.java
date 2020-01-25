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
import br.com.alura.agenda.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosActivity extends AppCompatActivity{

	public static final String TITULO_APPBAR = "Lista de Alunos";
	public static final int LISTA_SIMPLES = android.R.layout.simple_list_item_1;
	public static final String CHAVE_ALUNO = "aluno";
	private AlunoDao alunoDao = new AlunoDao();
	private ListaAlunoAdapter listaAdapter;
	private Intent daListaProForm;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_alunos);
		setTitle(TITULO_APPBAR);

		defineFabNovoAluno();
		listaAlunos();
	}

	@Override
	protected void onResume(){
		super.onResume();
		daListaProForm = geraIntentProForm();
		atualizaLista();
	}

	private void defineFabNovoAluno(){
		FloatingActionButton novoAluno = findViewById(R.id.lista_alunos_fab);
		abreFormularioNovoAluno(novoAluno);
	}

	private void listaAlunos(){
		ListView listView = findViewById(R.id.lista_alunos_listView);
		defineListaAdapter(listView);
		registerForContextMenu(listView);

		abreFormularioEditaAluno(listView);
	}

	private Intent geraIntentProForm(){
		return new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
	}

	private void atualizaLista(){
		listaAdapter.atualizaAdapter();
	}

	private void abreFormularioNovoAluno(FloatingActionButton novoAluno){
		novoAluno.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				startActivity(daListaProForm);
			}
		});
	}

	private void abreFormularioEditaAluno(ListView listView){
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id){
				Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
				daListaProForm.putExtra(CHAVE_ALUNO, aluno);
				startActivity(daListaProForm);
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item){
		defineMenuDeContextoRemover(item);
		return super.onContextItemSelected(item);
	}

	private void defineMenuDeContextoRemover(MenuItem item){
		final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		int itemId = item.getItemId();

		if(itemId == R.id.activity_lista_alunos_menu_remover){
			geraDialogoRemocaoAluno(menuInfo);
		}
	}

	private void geraDialogoRemocaoAluno(final AdapterView.AdapterContextMenuInfo menuInfo){
		new AlertDialog.Builder(this)
			.setTitle("Removendo aluno")
			.setMessage("Gostaria de remover o aluno?")
			.setPositiveButton("SIM", new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialogInterface, int i){
					Aluno aluno = listaAdapter.getItem(menuInfo.position);
					remove(aluno);
				}
			})
			.setNegativeButton("NÃO", null)
			.create()
			.show();
	}

	private void defineListaAdapter(ListView listView){
		listaAdapter = new ListaAlunoAdapter(this);
		listView.setAdapter(listaAdapter);
	}

	private void remove(Aluno aluno){
		listaAdapter.remove(aluno);
		alunoDao.remove(aluno);
	}
}
