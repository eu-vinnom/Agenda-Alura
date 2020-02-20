package br.com.alura.agenda.ui.activity.component;

import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AgendaBD;
import br.com.alura.agenda.room.AlunoDao;
import br.com.alura.agenda.ui.adapter.ListaAlunoAdapter;

public class ListaAlunosActivityComponent{


	private ListaAlunoAdapter listaAdapter;
	private AlunoDao alunoDao;
	private Context context;

	public ListaAlunosActivityComponent(Context context){
		this.context = context;
		this.alunoDao = AgendaBD.getInstance(context).getRoomAlunoDao();
		this.listaAdapter = new ListaAlunoAdapter(this.context);
	}

	public void defineMenuDeContextoRemover(MenuItem item){
		final AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

		int itemId = item.getItemId();

		if(itemId == R.id.activity_lista_alunos_menu_remover){
			geraDialogoRemocaoAluno(menuInfo);
		}
	}

	private void geraDialogoRemocaoAluno(final AdapterView.AdapterContextMenuInfo menuInfo){
		new AlertDialog.Builder(context)
			.setTitle("Removendo aluno")
			.setMessage("Gostaria de remover o aluno?")
			.setPositiveButton("SIM", (dialogInterface, i) -> {
				Aluno aluno = listaAdapter.getItem(menuInfo.position);
				remove(aluno);
			})
			.setNegativeButton("NÃO", null)
			.create()
			.show();
	}

	public void atualizaLista(){
		listaAdapter.atualizaAdapter();
	}

	public void defineListaAdapter(ListView listView){
		listView.setAdapter(listaAdapter);
	}

	private void remove(Aluno aluno){
		listaAdapter.remove(aluno);
		alunoDao.remove(aluno);
	}
}
