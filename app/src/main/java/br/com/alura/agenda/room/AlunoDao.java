package br.com.alura.agenda.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import br.com.alura.agenda.model.Aluno;

@Dao
public interface AlunoDao{

	String LISTA_TODOS = "SELECT * FROM Aluno";

	@Query(LISTA_TODOS)
	List<Aluno> listagem();

	@Insert
	void salva(Aluno aluno);

	@Update
	void edita(Aluno aluno);

	@Delete
	void remove(Aluno aluno);

}
