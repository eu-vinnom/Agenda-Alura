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

public class ListaAlunosActivityComponent {


	private static final String DIALOGO_REMOCAO = "Removendo aluno";
	private static final String CONFIRMA_REMOCAO = "Gostaria de remover o aluno?";
	private static final String SIM = "SIM";
	private static final String NAO = "NÃO";
	private ListaAlunoAdapter listaAdapter;
	private Context context;

	public ListaAlunosActivityComponent(Context context){
		this.context = context;
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
			.setTitle(DIALOGO_REMOCAO)
			.setMessage(CONFIRMA_REMOCAO)
			.setPositiveButton(SIM, (dialogInterface, i) -> {
				Aluno aluno = listaAdapter.getItem(menuInfo.position);
				remove(aluno);
			})
			.setNegativeButton(NAO, null)
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
		listaAdapter.notifyDataSetChanged();
	}
}
