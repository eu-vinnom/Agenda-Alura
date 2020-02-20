package br.com.alura.agenda.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 2, exportSchema = false)
public abstract class AgendaBD extends RoomDatabase{

	public static AgendaBD getInstance(Context contexto){
		return Room.
			databaseBuilder(contexto, AgendaBD.class, "agenda.db").
			allowMainThreadQueries().
			addMigrations(new Migration(1,2){
				@Override
				public void migrate(@NonNull SupportSQLiteDatabase database){
					database.execSQL("ALTER TABLE `Aluno` ADD COLUMN `sobrenome` TEXT");
				}
			}).
			build();
	}

	public abstract AlunoDao getRoomAlunoDao();


}
