/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      FrameScore.java                                                         */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

/*======================================================================================*/
/* CLASS:       FrameScore                                                              */
/**
 * The computed score and rolls for one frame.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220128 vanyab9      ACSD      n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ( { "javadoc", "nls" } )
public  class  LastFrameScore  extends  FrameScore
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
                /*======================================================================*/
                /** Whether or not this is he final frame of the game                   */
                /*======================================================================*/
   public   boolean  isFinalFrame  ()                { return (true); }

                /*======================================================================*/
                /** The computed bonus pins for this frame only                         */
                /*======================================================================*/
   private  String     bonusPins = " ";
   public   String  getBonusPins  ()                   { return (bonusPins); }
   public   void    setBonusPins  (String  aBonusPins) { bonusPins = aBonusPins; }

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** The mandatory constructor.
         *
         * <p>@param    aMaxRolls   The maximum rolls per frame.
         */
        /*==============================================================================*/
   public  LastFrameScore (
                           int  aMaxRolls
                          )
   {
     super (aMaxRolls);
   }

        /*==============================================================================*/
        /** The mandatory constructor.
         *
         * <p>@param    aFrameString   A string representing the frame details.
         */
        /*==============================================================================*/
   public  LastFrameScore (
                           String  aFrameString
                          )
   {
      super (aFrameString);
   }

        /*==============================================================================*/
        /** Converts an array of frame scores to a string representation.
         *
         * <p>@param    aFrameScores   The list of frame scores.
         * <p>@return   :String        The string representation.
         */
        /*==============================================================================*/
   public  String  toString (
                            )
   {
      StringBuilder myStringBuilder = new StringBuilder ();
      boolean       myIsComplete    = isClosedFrame     ();
      int           myGameTotal     = getGameTotal      ();
      String        myRollValues    = getRollValues     ();
                    myRollValues    = myRollValues.trim () + getBonusPins ();

      myStringBuilder.append (myRollValues.substring (0, (rollsPerFrame + 1)));
      myStringBuilder.append (TOTAL_DIVIDER);
      myStringBuilder.append (((myGameTotal >= 0) && myIsComplete) ? myGameTotal : " ");

      return (myStringBuilder.toString ());
   }


}  // EOF  FrameScore.java
