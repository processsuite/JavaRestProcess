package com.process.business.helper;

import com4j.*;

/**
 */
public enum ENUM_HKEY implements ComEnum {
  /**
   * <p>
   * HKEY_CLASSES_ROOT
   * </p>
   * <p>
   * The value of this constant is -2147483648
   * </p>
   */
  HKEY_CLASSES_ROOT(-2147483648),
  /**
   * <p>
   * HKEY_CURRENT_USER
   * </p>
   * <p>
   * The value of this constant is -2147483647
   * </p>
   */
  HKEY_CURRENT_USER(-2147483647),
  /**
   * <p>
   * HKEY_LOCAL_MACHINE
   * </p>
   * <p>
   * The value of this constant is -2147483646
   * </p>
   */
  HKEY_LOCAL_MACHINE(-2147483646),
  /**
   * <p>
   * HKEY_USERS
   * </p>
   * <p>
   * The value of this constant is -2147483645
   * </p>
   */
  HKEY_USERS(-2147483645),
  /**
   * <p>
   * HKEY_CURRENT_CONFIG
   * </p>
   * <p>
   * The value of this constant is -2147483643
   * </p>
   */
  HKEY_CURRENT_CONFIG(-2147483643),
  /**
   * <p>
   * HKEY_DYN_DATA
   * </p>
   * <p>
   * The value of this constant is -2147483642
   * </p>
   */
  HKEY_DYN_DATA(-2147483642),
  /**
   * <p>
   * HKEY_PERFORMANCE_DATA
   * </p>
   * <p>
   * The value of this constant is -2147483644
   * </p>
   */
  HKEY_PERFORMANCE_DATA(-2147483644),
  ;

  private final int value;
  ENUM_HKEY(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
