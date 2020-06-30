package br.com.alura.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.room.AgendaBD;
import br.com.alura.agenda.room.AlunoDao;
import br.com.alura.agenda.room.TelefoneDao;
import br.com.alura.agenda.ui.asynctask.AtualizaListaAsyncTask;
import br.com.alura.agenda.ui.asynctask.DevolvePrimeiroTelefoneAsyncTask;
import br.com.alura.agenda.ui.asynctask.RemoveAlunoAsyncTask;

public class ListaAlunoAdapter extends BaseAdapter {

	private final Context context;
	private final TelefoneDao telefoneDao;
	private final AlunoDao alunoDao;
	private List<Aluno> alunos;

	public ListaAlunoAdapter(Context context) {
		this.context = context;
		alunoDao = AgendaBD.getInstance(context).getRoomAlunoDao();
		telefoneDao = AgendaBD.getInstance(context).getTelefoneDao();
		alunos = new ArrayList<>();
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Aluno getItem(int posicao) {
		return alunos.get(posicao);
	}

	@Override
	public long getItemId(int posicao) {
		return alunos.get(posicao).getId();
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewGroup) {

		return defineAlunoItemView(posicao, viewGroup);
	}

	private View defineAlunoItemView(int posicao, ViewGroup viewGroup) {
		Aluno aluno = alunos.get(posicao);
		View alunoItemView = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);

		TextView campoNome = alunoItemView.findViewById(R.id.item_aluno_nome);
		campoNome.setText(aluno.getNome());

		TextView campoTelefone = alunoItemView.findViewById(R.id.item_aluno_telefone);

		new DevolvePrimeiroTelefoneAsyncTask(telefoneDao, aluno.getId(), telefone -> {
			campoTelefone.setText(telefone.getNumero());
		}).execute();

		return alunoItemView;
	}

	public void atualizaAdapter() {
		new AtualizaListaAsyncTask(alunos, alunoDao, this::notifyDataSetChanged).execute();
	}

	public void remove(Aluno aluno) {
		new RemoveAlunoAsyncTask(aluno, alunos, alunoDao).execute();
	}
}
