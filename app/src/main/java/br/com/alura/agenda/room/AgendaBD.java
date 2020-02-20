package br.com.alura.agenda.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 1, exportSchema = false)
public abstract class AgendaBD extends RoomDatabase{

	public static AgendaBD getInstance(Context contexto){
		return Room.databaseBuilder(contexto, AgendaBD.class, "agenda.db").allowMainThreadQueries().build();
	}

	public abstract AlunoDao getRoomAlunoDao();


}
