package com.process.business.helper;

import com4j.*;

@IID("{A5C5D880-54D3-417F-9158-688F3BCE51B7}")
public interface C_Bin extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "cCBIN_HEX_TO_CHAR"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027089) //= 0x68030011. The runtime will prefer the VTID if present
  @VTID(7)
  int cCBIN_HEX_TO_CHAR();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_CHAR_TO_HEX"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027088) //= 0x68030010. The runtime will prefer the VTID if present
  @VTID(8)
  int cCBIN_CHAR_TO_HEX();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_64_TO_CHAR"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027087) //= 0x6803000f. The runtime will prefer the VTID if present
  @VTID(9)
  int cCBIN_64_TO_CHAR();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_CHAR_TO_64"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027086) //= 0x6803000e. The runtime will prefer the VTID if present
  @VTID(10)
  int cCBIN_CHAR_TO_64();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_NO_TRANSF"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027085) //= 0x6803000d. The runtime will prefer the VTID if present
  @VTID(11)
  int cCBIN_NO_TRANSF();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_ERROR"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027084) //= 0x6803000c. The runtime will prefer the VTID if present
  @VTID(12)
  int cCBIN_ERROR();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_EXITO"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027083) //= 0x6803000b. The runtime will prefer the VTID if present
  @VTID(13)
  int cCBIN_EXITO();


  /**
   * <p>
   * Getter method for the COM property "cCBIN_NO_DISPONIBLE"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1745027082) //= 0x6803000a. The runtime will prefer the VTID if present
  @VTID(14)
  int cCBIN_NO_DISPONIBLE();


  /**
   * @param pStream Mandatory java.lang.String parameter.
   * @param pHexAscii Optional parameter. Default value is 4
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809364) //= 0x60030014. The runtime will prefer the VTID if present
  @VTID(15)
  java.lang.String transforma2(
    java.lang.String pStream,
    @Optional @DefaultValue("4") com.process.business.helper.ENUM_HEX_ASCII pHexAscii);


  /**
   * @param pStream Mandatory Holder<java.lang.String> parameter.
   * @param pHexAscii Optional parameter. Default value is 4
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610809365) //= 0x60030015. The runtime will prefer the VTID if present
  @VTID(16)
  java.lang.String transforma(
    String pStream,
    @Optional @DefaultValue("4") int pHexAscii);


  /**
   * <p>
   * Setter method for the COM property "Clave"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @DISPID(1745027081) //= 0x68030009. The runtime will prefer the VTID if present
  @VTID(17)
  void clave(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "Status"
   * </p>
   * @return  Returns a value of type test.wsh2.CBIN_CODERR
   */

  @DISPID(1745027080) //= 0x68030008. The runtime will prefer the VTID if present
  @VTID(18)
  com.process.business.helper.CBIN_CODERR status();


  // Properties:
}
