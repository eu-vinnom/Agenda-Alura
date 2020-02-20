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

public class ListaAlunoAdapter extends BaseAdapter{

	private final Context context;
	private AlunoDao alunoDao;
	private List<Aluno> alunos;


	public ListaAlunoAdapter(Context context){
		this.context = context;
		alunoDao = AgendaBD.getInstance(context).getRoomAlunoDao();
		alunos = new ArrayList<>(alunoDao.listagem());
	}

	@Override
	public int getCount(){
		return alunos.size();
	}

	@Override
	public Aluno getItem(int posicao){
		return alunos.get(posicao);
	}

	@Override
	public long getItemId(int posicao){
		return alunos.get(posicao).getId();
	}

	@Override
	public View getView(int posicao, View view, ViewGroup viewGroup){

		return defineAlunoItemView(posicao, viewGroup);
	}

	private View defineAlunoItemView(int posicao, ViewGroup viewGroup){
		Aluno aluno = alunos.get(posicao);
		View alunoItemView = LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);

		TextView nome = alunoItemView.findViewById(R.id.item_aluno_nome);
		nome.setText(aluno.getNome());

		TextView telefone = alunoItemView.findViewById(R.id.item_aluno_telefone);
		telefone.setText(aluno.getTelefone());

		return alunoItemView;
	}

	public void atualizaAdapter(){
		alunos.clear();
		alunos.addAll(alunoDao.listagem());
		notifyDataSetChanged();
	}

	public void remove(Aluno aluno){
		alunos.remove(aluno);
		notifyDataSetChanged();
	}
}
