package br.com.alura.agenda.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;
import br.com.alura.agenda.model.Aluno;

public class ListaAlunosActivity extends AppCompatActivity{

	public static final String TITULO_APPBAR = "Lista de Alunos";
	public static final int LISTA_SIMPLES = android.R.layout.simple_list_item_1;
	public static final String CHAVE_ALUNO = "aluno";
	private AlunoDao alunoDao = new AlunoDao();
	private Intent daListaProForm;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_alunos);
		setTitle(TITULO_APPBAR);
		alunoDao.salva(new Aluno("vi", "123456789", "vi@vi.com.br"));
		alunoDao.salva(new Aluno("ka", "123456789", "ka@ka.com.br"));

		defineFabNovoAluno();
	}

	@Override
	protected void onResume(){
		super.onResume();
		daListaProForm = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
		listaAlunos();
	}

	private void defineFabNovoAluno(){
		FloatingActionButton novoAluno = findViewById(R.id.lista_alunos_fab);
		abreFormularioNovoAluno(novoAluno);
	}

	private void abreFormularioNovoAluno(FloatingActionButton novoAluno){
		novoAluno.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				startActivity(daListaProForm);
			}
		});
	}

	private void listaAlunos(){
		ListView listView = findViewById(R.id.lista_alunos_listView);
		List<Aluno> alunos = alunoDao.listagem();
		listView.setAdapter(new ArrayAdapter<>(this, LISTA_SIMPLES, alunos));

		abreFormularioEditaAluno(listView);
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
}