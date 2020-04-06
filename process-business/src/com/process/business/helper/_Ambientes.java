package com.process.business.helper;

import com4j.Com4jObject;
import com4j.DISPID;
import com4j.DefaultMethod;
import com4j.Holder;
import com4j.IID;
import com4j.VTID;

@IID("{786DD2FB-E09A-4A17-B056-F898DDE153AB}")
public interface _Ambientes extends Com4jObject {
  // Methods:
  /**
   */

  @DISPID(1610809350) //= 0x60030006. The runtime will prefer the VTID if present
  @VTID(7)
  void noUsarHttp();


  /**
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809352) //= 0x60030008. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String httpserver();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param vntIndexKey Mandatory java.lang.Object parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  java.lang.String item(
    java.lang.Object vntIndexKey);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027077) //= 0x68030005. The runtime will prefer the VTID if present
  @VTID(10)
  int count();


  /**
   * <p>
   * Getter method for the COM property "FmtDecimales"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1745027076) //= 0x68030004. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String fmtDecimales();


  /**
   * <p>
   * Getter method for the COM property "FmtFecha"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1745027075) //= 0x68030003. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String fmtFecha();


  /**
   * <p>
   * Getter method for the COM property "FmtMiles"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1745027074) //= 0x68030002. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String fmtMiles();


  /**
   * @param vNewValue Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809355) //= 0x6003000b. The runtime will prefer the VTID if present
  @VTID(14)
  java.lang.String darFmtFecha(
    java.lang.String vNewValue);


  /**
   * @param sAplicacion Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(1610809356) //= 0x6003000c. The runtime will prefer the VTID if present
  @VTID(15)
  void init(
    Holder<java.lang.String> sAplicacion);


  /**
   * @param pAmb Mandatory java.lang.String parameter.
   * @param pVar Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809359) //= 0x6003000f. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String leerVarAmbienteEx(
    java.lang.String pAmb,
    java.lang.String pVar);


  /**
   * @param pAmb Mandatory java.lang.String parameter.
   * @param pVar Mandatory java.lang.String parameter.
   * @param psRetVal Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type short
   */

  @DISPID(1610809360) //= 0x60030010. The runtime will prefer the VTID if present
  @VTID(17)
  short leerVarAmbiente(
    java.lang.String pAmb,
    java.lang.String pVar,
    Holder<java.lang.String> psRetVal);


  /**
   * @param pVar Mandatory java.lang.String parameter.
   * @param psRetVal Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type short
   */

  @DISPID(1610809361) //= 0x60030011. The runtime will prefer the VTID if present
  @VTID(18)
  short leerVarUsuario(
    java.lang.String pVar,
    Holder<java.lang.String> psRetVal);


  /**
   * @param pVar Mandatory java.lang.String parameter.
   * @param pVal Mandatory java.lang.String parameter.
   * @return  Returns a value of type short
   */

  @DISPID(1610809362) //= 0x60030012. The runtime will prefer the VTID if present
  @VTID(19)
  short escribirVarUsuario(
    java.lang.String pVar,
    java.lang.String pVal);


  /**
   * <p>
   * Setter method for the COM property "Ambiente"
   * </p>
   * @param rhs Mandatory Holder<java.lang.String> parameter.
   */

  @DISPID(1745027073) //= 0x68030001. The runtime will prefer the VTID if present
  @VTID(20)
  void ambiente(
    Holder<java.lang.String> rhs);


  /**
   * <p>
   * Getter method for the COM property "Ambiente"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1745027073) //= 0x68030001. The runtime will prefer the VTID if present
  @VTID(21)
  java.lang.String ambiente();


  /**
   * @param pkey Mandatory java.lang.String parameter.
   * @param pVar Mandatory java.lang.String parameter.
   * @param pVal Mandatory java.lang.String parameter.
   * @return  Returns a value of type short
   */

  @DISPID(1610809363) //= 0x60030013. The runtime will prefer the VTID if present
  @VTID(22)
  short escribirVarAmbiente(
    java.lang.String pkey,
    java.lang.String pVar,
    java.lang.String pVal);


  /**
   * @param clave Mandatory Holder<ENUM_HKEY> parameter.
   * @param subclave Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.String[]
   */

  @DISPID(1610809364) //= 0x60030014. The runtime will prefer the VTID if present
  @VTID(23)
  java.lang.String[] enumerarRegistro(
    Holder<ENUM_HKEY> clave,
    java.lang.String subclave);


  /**
   * <p>
   * Getter method for the COM property "cHKEY_LOCAL_MACHINE"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027072) //= 0x68030000. The runtime will prefer the VTID if present
  @VTID(24)
  int cHKEY_LOCAL_MACHINE();


  /**
   * @param clave Mandatory ENUM_HKEY parameter.
   * @param pkey Mandatory java.lang.String parameter.
   * @return  Returns a value of type short
   */

  @DISPID(1610809365) //= 0x60030015. The runtime will prefer the VTID if present
  @VTID(25)
  short eliminarVarAmbiente(
    ENUM_HKEY clave,
    java.lang.String pkey);


  /**
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809366) //= 0x60030016. The runtime will prefer the VTID if present
  @VTID(26)
  java.lang.String namemachine();


  /**
   * @return  Returns a value of type java.lang.String[]
   */

  @DISPID(1610809367) //= 0x60030017. The runtime will prefer the VTID if present
  @VTID(27)
  java.lang.String[] dsnSystem();


  /**
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809369) //= 0x60030019. The runtime will prefer the VTID if present
  @VTID(28)
  java.lang.Object[] dsnSystemEx();


  /**
   * @param clave Mandatory Holder<Integer> parameter.
   * @param subclave Mandatory java.lang.String parameter.
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809370) //= 0x6003001a. The runtime will prefer the VTID if present
  @VTID(29)
  java.lang.Object[] enumerarRegistroEx(
    Holder<Integer> clave,
    java.lang.String subclave);


  /**
   * @param seccion Mandatory ENUM_HKEY parameter.
   * @param camino Mandatory java.lang.String parameter.
   * @param variable Mandatory java.lang.String parameter.
   * @param valor Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type int
   */

  @DISPID(1610809376) //= 0x60030020. The runtime will prefer the VTID if present
  @VTID(30)
  int regEscribir(
    ENUM_HKEY seccion,
    java.lang.String camino,
    java.lang.String variable,
    Holder<java.lang.String> valor);


  // Properties:
}
