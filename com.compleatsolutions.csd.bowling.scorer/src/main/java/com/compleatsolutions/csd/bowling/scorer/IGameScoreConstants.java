/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      IGameScoreConstants.java                                                 */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

/*======================================================================================*/
/* CLASS:       IGameScoreConstants                                                      */
/**
 * Web client for the game scorer web serviceds for testing purposes.
 *
 * Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210424 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  IGameScoreConstants
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
                /** The URL to Pin Setter   */
   public  static  final  String  PINSETTER_URL      = "http://pinsetter.herokuapp.com/pinsetter/";

                /** The notification used to make a scorer <i>register</i> request.  */
   public  static  final  String  REQUEST_REGISTER   = "scorer/register";

                /** The notification used to make a scorer <i>roll</i> request.      */
   public  static  final  String  REQUEST_ROLL       = "scorer/roll";


}  // EOF  IGameScoreConstants.java
