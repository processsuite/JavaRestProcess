package com.process.business.plantilla;

import com.process.domain.generadordocument.Plantilla;

public interface GeneradorIreportManager {


	void setEngineId(Integer engineId);

	String ireportGenerator(String nombreForm, String wfa, Plantilla plantilla);

}
