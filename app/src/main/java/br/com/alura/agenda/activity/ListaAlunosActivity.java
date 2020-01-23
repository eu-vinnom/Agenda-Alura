package br.com.alura.agenda.activity;

import androidx.appcompat.app.AppCompatActivity;
import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaAlunosActivity extends AppCompatActivity{

	public static final String TITULO_LISTA_ALUNOS = "Lista de Alunos";
	private AlunoDao alunoDao = new AlunoDao();

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_alunos);
		setTitle(TITULO_LISTA_ALUNOS);

		formularioNovoAluno();
	}

	@Override
	protected void onResume(){
		super.onResume();

		listaAlunos();

	}

	private void formularioNovoAluno(){
		FloatingActionButton novoAluno = findViewById(R.id.lista_alunos_fab);
		novoAluno.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view){
				startActivity(new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class));
			}
		});
	}

	private void listaAlunos(){
		ListView listView = findViewById(R.id.lista_alunos_listView);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunoDao.listagem()));
	}
}
