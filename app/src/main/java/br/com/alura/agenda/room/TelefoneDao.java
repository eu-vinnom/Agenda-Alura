package br.com.alura.agenda.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.alura.agenda.model.Telefone;

import static androidx.room.OnConflictStrategy.*;

@Dao
public interface TelefoneDao {

	@Query("SELECT * FROM Telefone WHERE alunoId = :alunoId AND numero != '' LIMIT 1")
	Telefone devolvePrimeiroTelefone(int alunoId);

	@Insert
	void salva(Telefone... telefones);

	@Query("SELECT * FROM Telefone WHERE alunoId = :alunoId AND numero != ''")
	List<Telefone> devolveTodosTelefones(int alunoId);

	@Insert(onConflict = REPLACE)
	void atualiza(Telefone... telefones);
}
