package com.process.business.component;

import java.util.List;

import com.process.domain.component.Puesto;

public interface AdmuserExt {

	List<Puesto> obtenerUsuarios(String usuario, String ambiente);

	List<Puesto> agregarUsuario(List<Puesto> lp, String ambiente);

	Boolean eliminarUsuairo(String usuario, String ambiente);

	Boolean registrarPerfil(Puesto p, String ambiente, String puestoIns);

	Boolean eliminarPerfil(String usuario, String ambiente, String puestoIns);

	
	
}
