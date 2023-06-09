/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      PinSetterConfig.java                                                    */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.pinsetterclient;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       PinSetterConfig                                                         */
/**
 * Bowling pin setter custom game configuration parameters.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 vanyab9      CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  PinSetterConfig
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
                /*======================================================================*/
                /** The number of frames in the custom game                             */
   private  int     frameCount;
                /** Returns the number of frames in the custom game.
                 *  @return :int  -       the number of frames in the custom game.      */
   public   int     getFrameCount ()                      { return (frameCount); }
                /** Sets the number of frames in the custom game.
                 *  @param  aFrameCount   the number of frames in the custom game.      */
   public   void    setFrameCount (int aFrameCount)       { frameCount = aFrameCount; }

                /*======================================================================*/
                /** The number of pins per frames in the custom game                    */
   private  int     pinCount;
                /** Returns the pins per frames in the custom game.
                 *  @return :int  -       the number of pins per frames.                */
   public   int     getPinCount ()                        { return (pinCount); }
                /** Sets the number of pins per frames in the custom game.
                 *  @param  aPinCount     the number of pins per frames.                */
   public   void    setPinCount (int aPinCount)           { pinCount = aPinCount; }

                /*======================================================================*/
                /** The number of balls rolled in each frames in the custom game        */
   private  int     rollsPerFrame;
                /** Returns the number of balls rolled in each frames in the custom game.
                 *  @return :int  -         The number of rols per frame.               */
   public   int     getRollsPerFrame ()                   { return (rollsPerFrame); }
                /** Sets the number of balls rolled in each frames in the custom game.
                 *  @param  aRollsPerFrame  The number of rols per frame.      .        */
   public   void    setRollsPerFrame (int aRollsPerFrame) { rollsPerFrame = aRollsPerFrame; }

                /*======================================================================*/
                /** The optional test string of scores                                  */
   private  String  scoreString   = "";
                /** Returns the optional test string of scores.
                 *  @return :String  -      The optional test string of scores.         */
   public   String  getScoreString ()                     { return (scoreString); }
                /** Sets the optional test string of scores.
                  *  @param  aRScoreString   The optional test string of scores.        */
   public   void    setScoreString (String aRScoreString) { scoreString = aRScoreString; }

                /*======================================================================*/
                /** The specific pin setter identifier                                  */
   private  String  id            = "";
                /** Returns the specific pin setter identifier.
                 *  @return :String  -      The specific pin setter identifier.         */
   public   String  getId ()                     { return (id); }
                /** Sets the ospecific pin setter identifier.
                  *  @param  aId            The specific pin setter identifier.         */
   public   void    setId (String aId)           { id = aId;    }

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   PinSetterConfig                                                 */
        /**
         * The default constructor.
         */
        /*==============================================================================*/
   public  PinSetterConfig ()
   {

   }

        /*==============================================================================*/
        /* OPERATION:   PinSetterConfig                                                 */
        /**
         * The initializing constructor.
         *
         *  @param  aFrames         The number of frames in the custom game.
         *  @param  aPins           The number of pins per frames.
         *  @param  aRolls          The number of rolls per frame.      .
         *  @param  aScoreString    The optional test string of scores.
         */
        /*==============================================================================*/
   public  PinSetterConfig (
                            String  aFrames,
                            String  aPins,
                            String  aRolls,
                            String  aScoreString
                           )
   {
      setFrameCount    (formValidInt (aFrames, 1, 99));
      setPinCount      (formValidInt (aPins,   1, 10));
      setRollsPerFrame (formValidInt (aRolls,  1,  5));
      setScoreString   (aScoreString);
   }

        /*==============================================================================*/
        /* OPERATION:   formValidInt                                                    */
        /**
         * Converts the given string into an integer value that is restricted to the
         * given min/max range..
         *
         *  @param  aStringValue   The string value to be converted and validated..
         *  @param  aMinValue      The minimum valid value.
         *  @param  aMaxValue      The maximum valid value.      .
         *  @return :int         - The validate integer values.
         */
        /*==============================================================================*/
   private  int  formValidInt (
                              String  aStringValue,
                              int     aMinValue,
                              int     aMaxValue
                             )
   {
      int myIntValue = 0;

      try {
         myIntValue = Integer.parseInt (aStringValue);
      }
      catch (NumberFormatException myNumberFormatException) {
         myIntValue = 0;
      }

      if (myIntValue < aMinValue) {
          myIntValue = aMinValue;
      }
      else if (myIntValue > aMaxValue) {
               myIntValue = aMaxValue;
      }

      return (myIntValue);
   }


}  // EOF  PinSetterConfig.java
