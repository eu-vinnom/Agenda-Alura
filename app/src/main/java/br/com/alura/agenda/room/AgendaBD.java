package br.com.alura.agenda.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.model.Telefone;
import br.com.alura.agenda.room.conversor.DataConversor;
import br.com.alura.agenda.room.conversor.TipoTelefoneConversor;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters(value = {DataConversor.class, TipoTelefoneConversor.class})
public abstract class AgendaBD extends RoomDatabase{

	public static final String NOME_AGENDA_BD = "agenda.db";
	private static final AgendaBDMigrations migrations = new AgendaBDMigrations();

	public abstract AlunoDao getRoomAlunoDao();
	public abstract TelefoneDao getTelefoneDao();

	public static AgendaBD getInstance(Context contexto){
		return Room.
			databaseBuilder(contexto, AgendaBD.class, NOME_AGENDA_BD).
			addMigrations(migrations.getMigrations()).
			build();
	}
}
