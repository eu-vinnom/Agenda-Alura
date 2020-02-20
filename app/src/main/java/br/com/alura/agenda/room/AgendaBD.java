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
import br.com.alura.agenda.room.conversor.DataConversor;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters(value = {DataConversor.class})
public abstract class AgendaBD extends RoomDatabase{

	public static AgendaBD getInstance(Context contexto){
		return Room.
			databaseBuilder(contexto, AgendaBD.class, "agenda.db").
			allowMainThreadQueries().
			addMigrations(new Migration(1, 2){
				@Override
				public void migrate(@NonNull SupportSQLiteDatabase database){
					database.execSQL("ALTER TABLE `Aluno` ADD COLUMN `sobrenome` TEXT");
				}
			}, new Migration(2, 3){
				@Override
				public void migrate(@NonNull SupportSQLiteDatabase database){
					database.execSQL(
						"CREATE TABLE IF NOT EXISTS `Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");
					database.execSQL("INSERT INTO `Aluno_novo` SELECT `id`, `nome`, `telefone`, `email`  FROM `Aluno`");
					database.execSQL("DROP TABLE IF EXISTS `Aluno`");
					database.execSQL("ALTER TABLE `Aluno_novo` RENAME TO `Aluno` ");
				}
			}, new Migration(3, 4){
				@Override
				public void migrate(@NonNull SupportSQLiteDatabase database){
					database.execSQL("ALTER TABLE `Aluno` ADD COLUMN `dataInsercao` INTEGER");
				}
			}).
			build();
	}

	public abstract AlunoDao getRoomAlunoDao();


}
