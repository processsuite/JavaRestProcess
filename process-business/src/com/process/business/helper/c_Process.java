package com.process.business.helper;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultValue;
import com4j.Holder;
import com4j.IID;
import com4j.NativeType;
import com4j.Optional;
import com4j.ReturnValue;
import com4j.VTID;

@IID("{CBCDCE27-BB2A-4D59-9BC5-94FD87082314}")
public interface c_Process extends Com4jObject {
	  // Methods:
	  /**
	   * <p>
	   * Getter method for the COM property "NumDocumento"
	   * </p>
	   * @return  Returns a value of type int
	   */

	  @DISPID(1073938683) //= 0x400300fb. The runtime will prefer the VTID if present
	  @VTID(87)
	  int numDocumento();


	  /**
	   * <p>
	   * Setter method for the COM property "NumDocumento"
	   * </p>
	   * @param numDocumento Mandatory int parameter.
	   */

	  @DISPID(1073938683) //= 0x400300fb. The runtime will prefer the VTID if present
	  @VTID(88)
	  void numDocumento(
	    int numDocumento);


	  /**
	   * <p>
	   * Getter method for the COM property "SesionCaida"
	   * </p>
	   * @return  Returns a value of type int
	   */

	  @DISPID(1073938573) //= 0x4003008d. The runtime will prefer the VTID if present
	  @VTID(7)
	  int sesionCaida();


	  /**
	   * <p>
	   * Setter method for the COM property "SesionCaida"
	   * </p>
	   * @param sesionCaida Mandatory int parameter.
	   */

	  @DISPID(1073938573) //= 0x4003008d. The runtime will prefer the VTID if present
	  @VTID(8)
	  void sesionCaida(
	    int sesionCaida);


	  /**
	   * <p>
	   * Getter method for the COM property "ResultadoXML"
	   * </p>
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1073938574) //= 0x4003008e. The runtime will prefer the VTID if present
	  @VTID(9)
	  java.lang.String resultadoXML();


	  /**
	   * <p>
	   * Setter method for the COM property "ResultadoXML"
	   * </p>
	   * @param resultadoXML Mandatory java.lang.String parameter.
	   */

	  @DISPID(1073938574) //= 0x4003008e. The runtime will prefer the VTID if present
	  @VTID(10)
	  void resultadoXML(
	    java.lang.String resultadoXML);


	  /**
	   * @param pData1 Mandatory java.lang.String parameter.
	   * @param pData2 Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809478) //= 0x60030086. The runtime will prefer the VTID if present
	  @VTID(11)
	  java.lang.String p4bParamData(
	    java.lang.String pData1,
	    java.lang.String pData2);


	  /**
	   * @param pCadena Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809480) //= 0x60030088. The runtime will prefer the VTID if present
	  @VTID(12)
	  java.lang.String p4bGuardarDatosContexto(
	    java.lang.String pCadena);


	  /**
	   * @param pTicketDatoContexto Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809481) //= 0x60030089. The runtime will prefer the VTID if present
	  @VTID(13)
	  java.lang.String p4bObtenerDatosContexto(
	    java.lang.String pTicketDatoContexto);


	  /**
	   * @param pCodigo Mandatory java.lang.String parameter.
	   * @param pClave Mandatory java.lang.String parameter.
	   * @param pAmbiente Mandatory java.lang.String parameter.
	   * @param pSesionID Mandatory int parameter.
	   * @param pIp Mandatory java.lang.String parameter.
	   * @param pValidaExt Mandatory int parameter.
	   * @param pSesionCaida Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809482) //= 0x6003008a. The runtime will prefer the VTID if present
	  @VTID(14)
	  int p4bEstablecerSesion(
	    java.lang.String pCodigo,
	    java.lang.String pClave,
	    java.lang.String pAmbiente,
	    int pSesionID,
	    java.lang.String pIp,
	    int pValidaExt,
	    int pSesionCaida);


	  /**
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809483) //= 0x6003008b. The runtime will prefer the VTID if present
	  @VTID(15)
	  int p4bCerrarSesion();


	  /**
	   * @param pTicket Mandatory java.lang.String parameter.
	   * @param omitirCargaListas Optional parameter. Default value is 0
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809484) //= 0x6003008c. The runtime will prefer the VTID if present
	  @VTID(16)
	  int p4bObtenerSesion(
	    java.lang.String pTicket,
	    @Optional @DefaultValue("0") int omitirCargaListas);


	  /**
	   * @param pXml Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809485) //= 0x6003008d. The runtime will prefer the VTID if present
	  @VTID(17)
	  java.lang.String p4bGuardarSesion(
	    int pXml);


	  /**
	   * @param pTicket Mandatory java.lang.String parameter.
	   * @param omitirCargaListas Optional parameter. Default value is 0
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809486) //= 0x6003008e. The runtime will prefer the VTID if present
	  @VTID(18)
	  int p4bObtenerSesionUnica(
	    java.lang.String pTicket,
	    @Optional @DefaultValue("0") int omitirCargaListas);


	  /**
	   * @param pXml Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809487) //= 0x6003008f. The runtime will prefer the VTID if present
	  @VTID(19)
	  java.lang.String p4bGuardarSesionUnica(
	    int pXml);


	  /**
	   */

	  @DISPID(1610809488) //= 0x60030090. The runtime will prefer the VTID if present
	  @VTID(20)
	  void p4bCerrarDocumento();


	  /**
	   */

	  @DISPID(1610809580) //= 0x600300ec. The runtime will prefer the VTID if present
	  @VTID(85)
	  void p4bCerrarDocumentoLectura();


	  /**
	   * @param pFrmn Mandatory int parameter.
	   * @param pTipoXml Mandatory int parameter.
	   * @param pApartirDe Optional parameter. Default value is -1
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809489) //= 0x60030091. The runtime will prefer the VTID if present
	  @VTID(21)
	  java.lang.String p4bObtenerDocumento(
	    int pFrmn,
	    int pTipoXml,
	    @Optional @DefaultValue("-1") int pApartirDe);


	  /**
	   * @param pFrmn Mandatory int parameter.
	   * @param pTipoXml Mandatory int parameter.
	   * @param pApartirDe Optional parameter. Default value is -1
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809574) //= 0x600300e6. The runtime will prefer the VTID if present
	  @VTID(83)
	  java.lang.String p4bObtenerCamposDoc(
	    int pFrmn,
	    int pTipoXml,
	    @Optional @DefaultValue("-1") int pApartirDe);


	  /**
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809490) //= 0x60030092. The runtime will prefer the VTID if present
	  @VTID(22)
	  int p4bStatus();


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809491) //= 0x60030093. The runtime will prefer the VTID if present
	  @VTID(23)
	  java.lang.String p4bStatusAll();


	  /**
	   * @param pCodigo Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809492) //= 0x60030094. The runtime will prefer the VTID if present
	  @VTID(24)
	  int p4bImpersonalizar(
	    java.lang.String pCodigo);


	  /**
	   * @param pTipo Mandatory int parameter.
	   * @param pApartirDe Mandatory int parameter.
	   * @param pNuDoc Mandatory int parameter.
	   * @param pWfp Mandatory int parameter.
	   * @param pWfa Mandatory int parameter.
	   * @param pFe_Ini Mandatory java.lang.String parameter.
	   * @param pFe_Fin Mandatory java.lang.String parameter.
	   * @param pDetalle Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809493) //= 0x60030095. The runtime will prefer the VTID if present
	  @VTID(25)
	  java.lang.String p4bObtenerCesta(
	    int pTipo,
	    int pApartirDe,
	    int pNuDoc,
	    int pWfp,
	    int pWfa,
	    java.lang.String pFe_Ini,
	    java.lang.String pFe_Fin,
	    java.lang.String pDetalle);


	  /**
	   * @param pTipo Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809495) //= 0x60030097. The runtime will prefer the VTID if present
	  @VTID(26)
	  java.lang.String p4bObtenerClasificacion(
	    int pTipo);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809496) //= 0x60030098. The runtime will prefer the VTID if present
	  @VTID(27)
	  java.lang.String p4bObtenerAnexos(
	    int pNuDoc);


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809497) //= 0x60030099. The runtime will prefer the VTID if present
	  @VTID(28)
	  java.lang.String p4bObtenerServicios();


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809498) //= 0x6003009a. The runtime will prefer the VTID if present
	  @VTID(29)
	  java.lang.String p4bObtenerConsultas();


	  /**
	   * @param pWfp Mandatory int parameter.
	   * @param pWfa Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809499) //= 0x6003009b. The runtime will prefer the VTID if present
	  @VTID(30)
	  java.lang.String p4bObtenerParametrosConsulta(
	    int pWfp,
	    int pWfa);


	  /**
	   * @param pWfp Mandatory int parameter.
	   * @param pWfa Mandatory int parameter.
	   * @param pOpciones Mandatory int parameter.
	   * @param pApartirDe Mandatory int parameter.
	   * @param pParam Mandatory java.lang.String parameter.
	   * @param pOrden Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809500) //= 0x6003009c. The runtime will prefer the VTID if present
	  @VTID(31)
	  java.lang.String p4bEjecutarConsulta(
	    int pWfp,
	    int pWfa,
	    int pOpciones,
	    int pApartirDe,
	    java.lang.String pParam,
	    java.lang.String pOrden);


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809501) //= 0x6003009d. The runtime will prefer the VTID if present
	  @VTID(32)
	  java.lang.String p4bObtenerDatosUsuario();


	  /**
	   * @param pWfa Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809502) //= 0x6003009e. The runtime will prefer the VTID if present
	  @VTID(33)
	  int p4bCrearDocumento(
	    int pWfa);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @param pNuInst Mandatory int parameter.
	   * @param pWfa Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809503) //= 0x6003009f. The runtime will prefer the VTID if present
	  @VTID(34)
	  int p4bAbrirDocumento(
	    int pNuDoc,
	    int pNuInst,
	    int pWfa);


	  /**
	   * @param pTipoSource Mandatory int parameter.
	   * @param pSource Mandatory java.lang.String parameter.
	   * @param pExt Mandatory java.lang.String parameter.
	   * @param pDescripcion Mandatory java.lang.String parameter.
	   * @param pAsunto Mandatory java.lang.String parameter.
	   * @param pAutor Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809504) //= 0x600300a0. The runtime will prefer the VTID if present
	  @VTID(35)
	  int p4bAnexarDocumento(
	    int pTipoSource,
	    java.lang.String pSource,
	    java.lang.String pExt,
	    java.lang.String pDescripcion,
	    java.lang.String pAsunto,
	    java.lang.String pAutor);


	  /**
	   * @param pTipoSource Mandatory int parameter.
	   * @param pSource Mandatory java.lang.String parameter.
	   * @param pExt Mandatory java.lang.String parameter.
	   * @param pDescripcion Mandatory java.lang.String parameter.
	   * @param pAsunto Mandatory java.lang.String parameter.
	   * @param pAutor Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809505) //= 0x600300a1. The runtime will prefer the VTID if present
	  @VTID(36)
	  int p4bAnexarDocumentoDefinitivo(
	    int pTipoSource,
	    java.lang.String pSource,
	    java.lang.String pExt,
	    java.lang.String pDescripcion,
	    java.lang.String pAsunto,
	    java.lang.String pAutor);


	  /**
	   * @param pTipoCriterio Mandatory int parameter.
	   * @param pCriterio Mandatory java.lang.String parameter.
	   * @param pBorrado Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809506) //= 0x600300a2. The runtime will prefer the VTID if present
	  @VTID(37)
	  int p4bMarcarAnexoDocumento(
	    int pTipoCriterio,
	    java.lang.String pCriterio,
	    int pBorrado);


	  /**
	   * @param pTipoCriterio Mandatory int parameter.
	   * @param pCriterio Mandatory java.lang.String parameter.
	   * @param pAtributo Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809507) //= 0x600300a3. The runtime will prefer the VTID if present
	  @VTID(38)
	  java.lang.String p4bObtenerAtributoAnexoDocumento(
	    int pTipoCriterio,
	    java.lang.String pCriterio,
	    java.lang.String pAtributo);


	  /**
	   * @param pObservacion Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809508) //= 0x600300a4. The runtime will prefer the VTID if present
	  @VTID(39)
	  int p4bGuardarDocumento(
	    java.lang.String pObservacion);


	  /**
	   * @param pCampo Mandatory java.lang.String parameter.
	   * @param pNumFilas Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809509) //= 0x600300a5. The runtime will prefer the VTID if present
	  @VTID(40)
	  int p4bTruncarValorMatrizDocumento(
	    java.lang.String pCampo,
	    int pNumFilas);


	  /**
	   * @param pFecha1 Mandatory Holder<java.lang.String> parameter.
	   * @param pFecha2 Mandatory Holder<java.lang.String> parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809510) //= 0x600300a6. The runtime will prefer the VTID if present
	  @VTID(41)
	  int p4bObtenerDiasHabilEntreFechas(
	    Holder<java.lang.String> pFecha1,
	    Holder<java.lang.String> pFecha2);


	  /**
	   * @param pFecha Mandatory java.lang.String parameter.
	   * @param pDias Mandatory Holder<Integer> parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809511) //= 0x600300a7. The runtime will prefer the VTID if present
	  @VTID(42)
	  java.lang.String p4bObtenerProxDiaHabil(
	    java.lang.String pFecha,
	    Holder<Integer> pDias);


	  /**
	   * @param pFecha Mandatory Holder<java.lang.String> parameter.
	   * @return  Returns a value of type boolean
	   */

	  @DISPID(1610809512) //= 0x600300a8. The runtime will prefer the VTID if present
	  @VTID(43)
	  boolean p4bVerificarDiaHabil(
	    Holder<java.lang.String> pFecha);


	  /**
	   * @param pWfp Mandatory int parameter.
	   * @param pNuDoc Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809513) //= 0x600300a9. The runtime will prefer the VTID if present
	  @VTID(44)
	  java.lang.String p4bObtenerActividadesPorEjecutar(
	    int pWfp,
	    int pNuDoc);


	  /**
	   * @param pCampo Mandatory java.lang.String parameter.
	   * @param pValor Mandatory java.lang.String parameter.
	   * @param pFila Mandatory int parameter.
	   * @param pColumna Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809514) //= 0x600300aa. The runtime will prefer the VTID if present
	  @VTID(45)
	  int p4bAsignarValorCampoDocumento(
	    java.lang.String pCampo,
	    java.lang.String pValor,
	    int pFila,
	    int pColumna);


	  /**
	   * @param pCampo Mandatory java.lang.String parameter.
	   * @param pFila Mandatory int parameter.
	   * @param pColumna Mandatory int parameter.
	   * @param pConFormato Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809515) //= 0x600300ab. The runtime will prefer the VTID if present
	  @VTID(46)
	  java.lang.String p4bObtenerValorCampoDocumento(
	    java.lang.String pCampo,
	    int pFila,
	    int pColumna,
	    int pConFormato);


	  /**
	   * @param pCadena Mandatory java.lang.String parameter.
	   * @param pApostrofe Mandatory int parameter.
	   * @param pCharMacro Mandatory java.lang.String parameter.
	   * @param pFilaContexto Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809516) //= 0x600300ac. The runtime will prefer the VTID if present
	  @VTID(47)
	  java.lang.String p4bExpandirVariables(
	    java.lang.String pCadena,
	    int pApostrofe,
	    java.lang.String pCharMacro,
	    int pFilaContexto);


	  /**
	   * @param pCampo Mandatory java.lang.String parameter.
	   * @param pFila Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809517) //= 0x600300ad. The runtime will prefer the VTID if present
	  @VTID(48)
	  int p4bEjecutarEventoCampo(
	    java.lang.String pCampo,
	    int pFila);


	  /**
	   * @param pCampo Mandatory java.lang.String parameter.
	   * @param pFila Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809518) //= 0x600300ae. The runtime will prefer the VTID if present
	  @VTID(49)
	  int p4bEjecutarEventoCampoAjax(
	    java.lang.String pCampo,
	    int pFila);


	  /**
	   * @param pCdAg Mandatory java.lang.String parameter.
	   * @param pContexto Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809519) //= 0x600300af. The runtime will prefer the VTID if present
	  @VTID(50)
	  int p4bEjecutarAgente(
	    java.lang.String pCdAg,
	    java.lang.String pContexto);


	  /**
	   * @param pCampoCdAg Mandatory java.lang.String parameter.
	   * @param pFila Mandatory int parameter.
	   * @param pSeleccionResultadoXml Mandatory java.lang.String parameter.
	   * @param pSeleccion Mandatory int parameter.
	   * @param pContexto Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809520) //= 0x600300b0. The runtime will prefer the VTID if present
	  @VTID(51)
	  int p4bResolverResultadoListaSQL(
	    java.lang.String pCampoCdAg,
	    int pFila,
	    java.lang.String pSeleccionResultadoXml,
	    int pSeleccion,
	    java.lang.String pContexto);


	  /**
	   * @param pValor Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809521) //= 0x600300b1. The runtime will prefer the VTID if present
	  @VTID(52)
	  int p4bAgregarResultadoColumnaListaSQL(
	    java.lang.String pValor);


	  /**
	   * @param pRutina Mandatory java.lang.String parameter.
	   * @param pMensaje Mandatory java.lang.String parameter.
	   */

	  @DISPID(1610809522) //= 0x600300b2. The runtime will prefer the VTID if present
	  @VTID(53)
	  void p4bTrazaInt(
	    java.lang.String pRutina,
	    java.lang.String pMensaje);


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809523) //= 0x600300b3. The runtime will prefer the VTID if present
	  @VTID(54)
	  java.lang.String p4bCalcularProximosDestinos();


	  /**
	   * @param pWfa Mandatory int parameter.
	   * @param pE Mandatory java.lang.String parameter.
	   * @param pPuesto Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809524) //= 0x600300b4. The runtime will prefer the VTID if present
	  @VTID(55)
	  int p4bAgregarProximoDestino(
	    int pWfa,
	    java.lang.String pE,
	    java.lang.String pPuesto);


	  /**
	   * @param pFirma Mandatory java.lang.String parameter.
	   * @param pPregunta Mandatory java.lang.String parameter.
	   * @param pRespuesta Mandatory java.lang.String parameter.
	   * @param pObservacion Mandatory java.lang.String parameter.
	   * @param pUrgente Mandatory int parameter.
	   * @param pEmail Mandatory int parameter.
	   * @param gradoSatisfaccion Mandatory int parameter.
	   * @param pSeleccionResultadoXml Mandatory java.lang.String parameter.
	   * @param pConCopiaEmailA Mandatory java.lang.String parameter.
	   * @param pFrmnCopia Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809525) //= 0x600300b5. The runtime will prefer the VTID if present
	  @VTID(56)
	  int p4bAvanzar(
	    java.lang.String pFirma,
	    java.lang.String pPregunta,
	    java.lang.String pRespuesta,
	    java.lang.String pObservacion,
	    int pUrgente,
	    int pEmail,
	    int gradoSatisfaccion,
	    java.lang.String pSeleccionResultadoXml,
	    java.lang.String pConCopiaEmailA,
	    int pFrmnCopia);


	  /**
	   * @param pObservacion Mandatory java.lang.String parameter.
	   * @param pUrgente Mandatory int parameter.
	   * @param pEmail Mandatory int parameter.
	   * @param pConCopiaEmailA Mandatory java.lang.String parameter.
	   * @param pFrmnCopia Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809526) //= 0x600300b6. The runtime will prefer the VTID if present
	  @VTID(57)
	  int p4bRechazar(
	    java.lang.String pObservacion,
	    int pUrgente,
	    int pEmail,
	    java.lang.String pConCopiaEmailA,
	    int pFrmnCopia);


	  /**
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809527) //= 0x600300b7. The runtime will prefer the VTID if present
	  @VTID(58)
	  int p4bDeshacerEnvio();


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @param pNuInst Mandatory int parameter.
	   * @return  Returns a value of type boolean
	   */

	  @DISPID(1610809528) //= 0x600300b8. The runtime will prefer the VTID if present
	  @VTID(59)
	  boolean p4bAbrirDocumentoAdHoc(
	    int pNuDoc,
	    int pNuInst);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @param pNuInst Mandatory int parameter.
	   * @return  Returns a value of type boolean
	   */

	  @DISPID(1610809576) //= 0x600300e8. The runtime will prefer the VTID if present
	  @VTID(84)
	  boolean p4bAbrirDocumentoLectura(
	    int pNuDoc,
	    int pNuInst);


	  /**
	   * @param pObservacion Mandatory java.lang.String parameter.
	   * @param pEmail Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809529) //= 0x600300b9. The runtime will prefer the VTID if present
	  @VTID(60)
	  int p4bCancelar(
	    java.lang.String pObservacion,
	    int pEmail);


	  /**
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809530) //= 0x600300ba. The runtime will prefer the VTID if present
	  @VTID(61)
	  java.lang.String p4bObtenerAgentesGenerales();


	  /**
	   * @param pNombreUsuario Mandatory java.lang.String parameter.
	   * @param pEmail Mandatory java.lang.String parameter.
	   * @param pApellido Optional parameter. Default value is ""
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809531) //= 0x600300bb. The runtime will prefer the VTID if present
	  @VTID(62)
	  int p4bActualizarDatosUsuario(
	    java.lang.String pNombreUsuario,
	    java.lang.String pEmail,
	    @Optional java.lang.String pApellido);


	  /**
	   * @param pContexto Mandatory int parameter.
	   * @param pClave1 Mandatory java.lang.String parameter.
	   * @param pClave2 Mandatory java.lang.String parameter.
	   * @param pClave3 Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809532) //= 0x600300bc. The runtime will prefer the VTID if present
	  @VTID(63)
	  int p4bActualizarSeguridadUsuario(
	    int pContexto,
	    java.lang.String pClave1,
	    java.lang.String pClave2,
	    java.lang.String pClave3);


	  /**
	   * @param pEmail Mandatory java.lang.String parameter.
	   * @param pPregunta Mandatory java.lang.String parameter.
	   * @param pRespuesta Mandatory java.lang.String parameter.
	   * @param pSoloValida Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809533) //= 0x600300bd. The runtime will prefer the VTID if present
	  @VTID(64)
	  int p4bObtenerClave(
	    java.lang.String pEmail,
	    java.lang.String pPregunta,
	    java.lang.String pRespuesta,
	    int pSoloValida);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @param pNuInst Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809534) //= 0x600300be. The runtime will prefer the VTID if present
	  @VTID(65)
	  int p4bEliminarNotificacion(
	    int pNuDoc,
	    int pNuInst);


	  /**
	   * @param pTipo Mandatory int parameter.
	   * @param pNuDoc Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809535) //= 0x600300bf. The runtime will prefer the VTID if present
	  @VTID(66)
	  java.lang.String p4bObtenerSeguimiento(
	    int pTipo,
	    int pNuDoc);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809536) //= 0x600300c0. The runtime will prefer the VTID if present
	  @VTID(67)
	  java.lang.String p4bObtenerDocumentosRelacionados(
	    int pNuDoc);


	  /**
	   * @param pObservacion Mandatory java.lang.String parameter.
	   * @param pEmail Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809537) //= 0x600300c1. The runtime will prefer the VTID if present
	  @VTID(68)
	  int p4bAdquirir(
	    java.lang.String pObservacion,
	    int pEmail);


	  /**
	   * @param pPathArch Mandatory java.lang.String parameter.
	   * @param pNombreArch Mandatory java.lang.String parameter.
	   * @param pExtArch Mandatory java.lang.String parameter.
	   * @param pDescripcion Mandatory java.lang.String parameter.
	   * @param pAnexar Mandatory int parameter.
	   * @param pDelimitador Mandatory java.lang.String parameter.
	   * @param pConsulta Mandatory java.lang.String parameter.
	   * @param pCampoInd Mandatory java.lang.String parameter.
	   * @param pXmlData Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809586) //= 0x600300f2. The runtime will prefer the VTID if present
	  @VTID(86)
	  java.lang.String p4bGenerarReporte(
	    java.lang.String pPathArch,
	    java.lang.String pNombreArch,
	    java.lang.String pExtArch,
	    java.lang.String pDescripcion,
	    int pAnexar,
	    java.lang.String pDelimitador,
	    java.lang.String pConsulta,
	    java.lang.String pCampoInd,
	    java.lang.String pXmlData);


	  /**
	   * @param pXmlCall Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809539) //= 0x600300c3. The runtime will prefer the VTID if present
	  @VTID(69)
	  java.lang.String p4bInterpretarLlamadaMultiple(
	    java.lang.String pXmlCall);


	  /**
	   * @param pFuente Mandatory java.lang.String parameter.
	   * @param pMomento Mandatory java.lang.String parameter.
	   * @param pT1 Mandatory java.lang.String parameter.
	   * @param pT2 Mandatory java.lang.String parameter.
	   */

	  @DISPID(1610809556) //= 0x600300d4. The runtime will prefer the VTID if present
	  @VTID(70)
	  void p4bRegistrarTiempoExterno(
	    java.lang.String pFuente,
	    java.lang.String pMomento,
	    java.lang.String pT1,
	    java.lang.String pT2);


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @param pApartirDe Mandatory int parameter.
	   * @param pSoloPropios Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809558) //= 0x600300d6. The runtime will prefer the VTID if present
	  @VTID(71)
	  java.lang.String p4bObtenerCorreosDocumento(
	    int pNuDoc,
	    int pApartirDe,
	    int pSoloPropios);


	  /**
	   * @param pNuMes Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809559) //= 0x600300d7. The runtime will prefer the VTID if present
	  @VTID(72)
	  java.lang.String p4bObtenerContenidoCorreo(
	    int pNuMes);


	  /**
	   * @param pVariable Mandatory java.lang.String parameter.
	   * @param pData Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809560) //= 0x600300d8. The runtime will prefer the VTID if present
	  @VTID(73)
	  int p4bGuardarProfile(
	    java.lang.String pVariable,
	    java.lang.String pData);


	  /**
	   * @param pVariable Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809561) //= 0x600300d9. The runtime will prefer the VTID if present
	  @VTID(74)
	  java.lang.String p4bObtenerProfile(
	    java.lang.String pVariable);


	  /**
	   * @param pIndicador Mandatory java.lang.String parameter.
	   * @param pParams Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809562) //= 0x600300da. The runtime will prefer the VTID if present
	  @VTID(75)
	  java.lang.String p4bObtenerIndicador(
	    java.lang.String pIndicador,
	    java.lang.String pParams);


	  /**
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809563) //= 0x600300db. The runtime will prefer the VTID if present
	  @VTID(76)
	  int p4bDeshacerDocumentoTerminado();


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809564) //= 0x600300dc. The runtime will prefer the VTID if present
	  @VTID(77)
	  int p4bObtenerStatusDocumento(
	    int pNuDoc);


	  /**
	   * @param pFrmn Mandatory int parameter.
	   * @param pMatriz Mandatory java.lang.String parameter.
	   * @param pApartirDe Mandatory int parameter.
	   * @param pAgrega Mandatory int parameter.
	   * @return  Returns a value of type java.lang.String
	   */

	  @DISPID(1610809565) //= 0x600300dd. The runtime will prefer the VTID if present
	  @VTID(78)
	  java.lang.String p4bObtenerPaginaMatriz(
	    int pFrmn,
	    java.lang.String pMatriz,
	    int pApartirDe,
	    int pAgrega);


	  /**
	   * @param pFrmn Mandatory int parameter.
	   * @param pMatriz Mandatory java.lang.String parameter.
	   * @param pApartirDe Mandatory int parameter.
	   * @param pFilasDelete Mandatory java.lang.String parameter.
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809566) //= 0x600300de. The runtime will prefer the VTID if present
	  @VTID(79)
	  int p4bBorrarFilasPaginaMatriz(
	    int pFrmn,
	    java.lang.String pMatriz,
	    int pApartirDe,
	    java.lang.String pFilasDelete);


	  /**
	   * @return  Returns a value of type int
	   */

	  @DISPID(1610809568) //= 0x600300e0. The runtime will prefer the VTID if present
	  @VTID(80)
	  int p4bNuevoCompromiso();


	  /**
	   * @param pNuDoc Mandatory int parameter.
	   */

	  @DISPID(1610809569) //= 0x600300e1. The runtime will prefer the VTID if present
	  @VTID(81)
	  void p4bDocumentoContexto(
	    int pNuDoc);


	  /**
	   * @param pPara Mandatory java.lang.String parameter.
	   * @param pNbu Mandatory java.lang.String parameter.
	   * @param pAsunto Mandatory java.lang.String parameter.
	   * @param pTexto Mandatory java.lang.String parameter.
	   * @param pListaDesAnexos Mandatory java.lang.String parameter.
	   * @return  Returns a value of type java.lang.Object
	   */

	  @DISPID(1610809570) //= 0x600300e2. The runtime will prefer the VTID if present
	  @VTID(82)
	  @ReturnValue(type=NativeType.VARIANT)
	  java.lang.Object p4bReEnviarCorreo(
	    java.lang.String pPara,
	    java.lang.String pNbu,
	    java.lang.String pAsunto,
	    java.lang.String pTexto,
	    java.lang.String pListaDesAnexos);


	  // Properties:
	}
