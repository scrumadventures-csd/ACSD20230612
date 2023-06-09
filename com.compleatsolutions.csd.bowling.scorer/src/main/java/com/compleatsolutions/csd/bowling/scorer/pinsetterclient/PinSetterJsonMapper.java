/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      PinSetterJsonMapper.java                                                */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.pinsetterclient;

import com.compleatsolutions.csd.bowling.scorer.pinsetterclient.PinSetterConfig;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       PinSetterJsonMapper                                                     */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  PinSetterJsonMapper
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   mapSequenceToJsonString                                         */
        /**
         *
         *
         * <p>
         * @param       aPinSetterConfig
         *                  The sequence to map.
         * <p>
         * @return      :String -
         *                  The JSON string equivalent.
         */
        /*==============================================================================*/
   public  static  String  mapConfigToJsonString (
                                                  PinSetterConfig  aPinSetterConfig
                                                 )
   {
      ObjectMapper myObjectMapper = new ObjectMapper ();
      String       myJsonString   = "";

      try {
         myJsonString = myObjectMapper.writeValueAsString (aPinSetterConfig);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myJsonString);
   }

        /*==============================================================================*/
        /* OPERATION:   mapJsonStringToConfig                                          */
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
   public  static  PinSetterConfig  mapJsonStringToConfig (
                                                           String  aJsonString
                                                          )
   {
      ObjectMapper    myObjectMapper    = new ObjectMapper ();
      PinSetterConfig myPinSetterConfig = null;

      try {
         myPinSetterConfig = myObjectMapper.readValue (aJsonString, PinSetterConfig.class);
      }

      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }

      return (myPinSetterConfig);
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


}  // EOF  PinSetterJsonMapper.java
