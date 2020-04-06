package com.process.business.helper;

import com4j.*;

@IID("{23809702-318A-4F13-B6BD-1EAECD39D807}")
public interface _cDiag extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809344) //= 0x60030000. The runtime will prefer the VTID if present
  @VTID(7)
  java.lang.Object[] obtenerVersionSO();


  /**
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809345) //= 0x60030001. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.Object[] getWBInfo();


  /**
   * @param pFile Mandatory Holder<java.lang.String> parameter.
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809346) //= 0x60030002. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.Object[] getVersionFromFile(
    Holder<java.lang.String> pFile);


  /**
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809348) //= 0x60030004. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.Object[] getDsnDrv();


  /**
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809351) //= 0x60030007. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.Object[] getPackagesMTS();


  /**
   * @param nombre Mandatory java.lang.String parameter.
   * @param tipo Optional parameter. Default value is 0
   * @return  Returns a value of type java.lang.Object[]
   */

  @DISPID(1610809352) //= 0x60030008. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.Object[] componente(
    java.lang.String nombre,
    @Optional @DefaultValue("0") Holder<Short> tipo);


  /**
   * @return  Returns a value of type int
   */

  @DISPID(1610809355) //= 0x6003000b. The runtime will prefer the VTID if present
  @VTID(13)
  int checkCriptoApi();


  /**
   * @param nombre Mandatory java.lang.String parameter.
   * @param tipo Optional parameter. Default value is 0
   * @param error Optional parameter. Default value is ""
   * @return  Returns a value of type byte[]
   */

  @DISPID(1610809356) //= 0x6003000c. The runtime will prefer the VTID if present
  @VTID(14)
  byte[] getComponente(
    java.lang.String nombre,
    @Optional @DefaultValue("0") short tipo,
    @Optional Holder<java.lang.String> error);


  /**
   * @return  Returns a value of type boolean
   */

  @DISPID(1610809357) //= 0x6003000d. The runtime will prefer the VTID if present
  @VTID(15)
  boolean checkEncript();


  /**
   * @return  Returns a value of type boolean
   */

  @DISPID(1610809358) //= 0x6003000e. The runtime will prefer the VTID if present
  @VTID(16)
  boolean checkDesencript();


  /**
   * @param service Mandatory java.lang.String parameter.
   * @return  Returns a value of type boolean
   */

  @DISPID(1610809359) //= 0x6003000f. The runtime will prefer the VTID if present
  @VTID(17)
  boolean checkIIS(
    java.lang.String service);


  // Properties:
}
