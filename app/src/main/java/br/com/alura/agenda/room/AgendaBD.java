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

	public static final String NOME_AGENDA_BD = "agenda.db";
	private static final String ADICIONA_COLUNA_SOBRENOME = "ALTER TABLE `Aluno` ADD COLUMN `sobrenome` TEXT";
	private static final String CRIA_NOVA_TABELA = "CREATE TABLE IF NOT EXISTS " +
		"`Aluno_novo` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)";
	private static final String COPIA_DA_ANTIGA_PARA_A_NOVA = "INSERT INTO `Aluno_novo` SELECT " +
		"`id`, `nome`, `telefone`, `email`  FROM `Aluno`";
	private static final String REMOVE_TABELA_ANTIGA = "DROP TABLE IF EXISTS `Aluno`";
	private static final String RENOMEIA_NOVA_TABELA = "ALTER TABLE `Aluno_novo` RENAME TO `Aluno`";
	public static final String INSERE_COLUNA_DATA = "ALTER TABLE `Aluno` ADD COLUMN `dataInsercao` INTEGER";

	private static final Migration MIGRACAO_V1_V2 = new Migration(1, 2){
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL(ADICIONA_COLUNA_SOBRENOME);
		}
	};
	private static final Migration MIGRACAO_V2_V3 = new Migration(2, 3){
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL(CRIA_NOVA_TABELA);
			database.execSQL(COPIA_DA_ANTIGA_PARA_A_NOVA);
			database.execSQL(REMOVE_TABELA_ANTIGA);
			database.execSQL(RENOMEIA_NOVA_TABELA);
		}
	};
	private static final Migration MIGRACAO_V3_V4 = new Migration(3, 4){
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL(INSERE_COLUNA_DATA);
		}
	};

	public static AgendaBD getInstance(Context contexto){
		return Room.
			databaseBuilder(contexto, AgendaBD.class, NOME_AGENDA_BD).
			allowMainThreadQueries().
			addMigrations(MIGRACAO_V1_V2, MIGRACAO_V2_V3, MIGRACAO_V3_V4).
			build();
	}

	public abstract AlunoDao getRoomAlunoDao();

}
