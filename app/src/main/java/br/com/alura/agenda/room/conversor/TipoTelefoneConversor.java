package br.com.alura.agenda.room.conversor;

import androidx.room.TypeConverter;

import br.com.alura.agenda.model.TipoTelefone;

public class TipoTelefoneConversor {

	@TypeConverter
	public String paraString(TipoTelefone tipo){
		return tipo.name();
	}

	@TypeConverter
	public TipoTelefone paraTipoTelefone(String tipoEmTexto){
		if(tipoEmTexto != null){
			return TipoTelefone.valueOf(tipoEmTexto);
		}
		return TipoTelefone.FIXO;
	}
}
