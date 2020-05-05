package br.com.alura.agenda.room;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.agenda.model.TipoTelefone;

import static br.com.alura.agenda.model.TipoTelefone.FIXO;

public class AgendaBDMigrations {

	private static final Migration MIGRACAO_V1_V2 = new Migration(1, 2) {
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL("ALTER TABLE `Aluno` ADD COLUMN `sobrenome` TEXT");
		}
	};
	private static final Migration MIGRACAO_V2_V3 = new Migration(2, 3) {
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
				"`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `telefone` TEXT, `email` TEXT)");
			database.execSQL("INSERT INTO `Aluno_novo` SELECT `id`, `nome`, `telefone`, `email`  FROM `Aluno`");
			database.execSQL("DROP TABLE IF EXISTS `Aluno`");
			database.execSQL("ALTER TABLE `Aluno_novo` RENAME TO `Aluno`");
		}
	};
	private static final Migration MIGRACAO_V3_V4 = new Migration(3, 4) {
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL("ALTER TABLE `Aluno` ADD COLUMN `dataInsercao` INTEGER");
		}
	};
	private static final Migration MIGRACAO_V4_V5 = new Migration(4, 5) {
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
				"`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
				"`nome` TEXT, `telefoneFixo` TEXT, `telefoneCelular` TEXT, `email` TEXT, `dataInsercao` INTEGER)");
			database.execSQL("INSERT INTO `Aluno_novo` (id, nome, telefoneFixo, email, dataInsercao) " +
				"SELECT `id`, `nome`, `telefone`, `email`, `dataInsercao`  FROM `Aluno`");
			database.execSQL("DROP TABLE IF EXISTS `Aluno`");
			database.execSQL("ALTER TABLE `Aluno_novo` RENAME TO `Aluno`");
		}
	};
	private static final Migration MIGRACAO_V5_V6 = new Migration(5, 6) {
		@Override
		public void migrate(@NonNull SupportSQLiteDatabase database){
			database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo` (" +
				"`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT, `email` TEXT, `dataInsercao` INTEGER)");
			database.execSQL("INSERT INTO `Aluno_novo` (id, nome, email, dataInsercao) " +
				"SELECT `id`, `nome`, `email`, `dataInsercao`  FROM `Aluno`");
			database.execSQL("CREATE TABLE IF NOT EXISTS `Telefone` (" +
				"`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `numero` TEXT, `tipo` TEXT, `alunoId` INTEGER NOT NULL)");
			database.execSQL("INSERT INTO `Telefone` (numero, alunoId) " +
				"SELECT `telefoneFixo`, `id` FROM `Aluno`");
			database.execSQL("UPDATE `Telefone` SET tipo = ?", new TipoTelefone[]{FIXO});
			database.execSQL("DROP TABLE IF EXISTS `Aluno`");
			database.execSQL("ALTER TABLE `Aluno_novo` RENAME TO `Aluno`");
		}
	};
	private Migration[] migrations;

	public AgendaBDMigrations(){
		this.migrations = new Migration[]{MIGRACAO_V1_V2, MIGRACAO_V2_V3, MIGRACAO_V3_V4, MIGRACAO_V4_V5, MIGRACAO_V5_V6};
	}

	public Migration[] getMigrations(){
		return migrations;
	}
}
