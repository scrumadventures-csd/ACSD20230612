/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreJsonMapper.java                                                */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

import com.compleatsolutions.csd.bowling.scorer.GameScore;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreJsonMapper                                                     */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220203 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorerJsonMapper
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /**
         *
         * <p>
         * @param   aGameScore   The GameScore object to map.
         * @return  :String    - The JSON string equivalent.
         */
        /*==============================================================================*/
   public  static  String  mapGameToJsonString (
                                                GameScore  aGameScore
                                               )
   {
      ObjectMapper myObjectMapper = new ObjectMapper ();
      String       myJsonString   = "";

      try {
         myJsonString = myObjectMapper.writeValueAsString (aGameScore);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myJsonString);
   }

        /*==============================================================================*/
        /* OPERATION:   mapJsonStringToConfig                                           */
        /**
         *
         *
          * <p>
          * @param       aJsonString
          *                  The JSON string to map.
          * <p>
          * @return      :String -
          *                  The reconstructed sequence instance.
         */
        /*==============================================================================*/
   public  static  GameScore  mapJsonStringToGame (
                                                   String  aJsonString
                                                  )
   {
      ObjectMapper myObjectMapper = new ObjectMapper ();
      GameScore    myGameScore    = null;

      try {
         myGameScore = myObjectMapper.readValue (aJsonString, GameScore.class);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myGameScore);
   }

        /*==============================================================================*/
        /* OPERATION:   mapObjectToJsonString                                           */
        /**
         *
         *
         * <p>
         * @param       aObject
         *                  The sequence to map.
         * <p>
         * @return      :String -
         *                  The JSON string equivalent.
         */
        /*==============================================================================*/
   public  static  String  mapObjectToJsonString (
                                                  Object  aObject
                                                 )
   {
      ObjectMapper myObjectMapper = new ObjectMapper ();
      String       myJsonString   = "";

      try {
         myJsonString = myObjectMapper.writeValueAsString (aObject);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myJsonString);
   }

        /*==============================================================================*/
        /* OPERATION:   mapJsonStringToObject                                           */
        /**
         *
         *
          * <p>
          * @param       aJsonString
          *                  The JSON string to map.
          * <p>
          * @param       aClass
          *                  The class of the object to be created.
          * <p>
          * @return      :String -
          *                  The reconstructed sequence instance.
         */
        /*==============================================================================*/
   public  static  Object  mapJsonStringToObject (
                                                  String    aJsonString,
                                                  Class<?>  aClass
                                                 )
   {
      ObjectMapper myObjectMapper = new ObjectMapper();
      Object       myObject       = null;

      try {
         myObject = myObjectMapper.readValue (aJsonString, aClass);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myObject);
   }


}  // EOF  GameScoreJsonMapper.java
