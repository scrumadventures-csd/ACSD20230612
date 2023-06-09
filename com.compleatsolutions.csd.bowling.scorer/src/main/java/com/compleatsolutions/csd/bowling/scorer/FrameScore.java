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
public  class  FrameScore
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
   protected  int     rollsPerFrame;

                /*======================================================================*/
                /** The values of the rolls for this frame                              */
                /*======================================================================*/
   private  String  rollValues = "";
   public   String  getRollValues         ()    { return (rollValues); }
   public   void    setRollValue  (int    aIndex,
                                   char   aRollValue)
   {
      String myRollValues  = rollValues.substring (0, aIndex) + aRollValue;
      if (aIndex < (rollsPerFrame - 1)) {
         myRollValues += rollValues.substring (aIndex);
    }
      setRollValues (myRollValues);
   }
   public   void    setRollValues (String aRollValues)
   {
      rollValues = (aRollValues + EMPTYROLLSTRING).substring (0, rollsPerFrame);
   }
   public   void    setRollValuesDirect (String aRollValues)
   {
      rollValues = aRollValues;
   }

                /*======================================================================*/
                /** The additional rolls needed to complete the scoring of this frame   */
                /*======================================================================*/
   private  int       rollsToComplete = 0;
   public   int    getRollsToComplete  ()                      { return (rollsToComplete); }
   public   void   setRollsToComplete  (int  aRollsToComplete) { rollsToComplete = aRollsToComplete; }

                /*======================================================================*/
                /** Whether or not this frame is closed (i.e can be scored)             */
                /*======================================================================*/
   private  boolean    closedFrame = false;
   public   boolean  isClosedFrame  ()                      { return (closedFrame); }
   public   void    setClosedFrame  (boolean  aClosedFrame) { closedFrame = aClosedFrame; }

                /*======================================================================*/
                /** Whether or not this is he final frame of the game                   */
                /*======================================================================*/
   public   boolean  isFinalFrame  ()                { return (false); }

                /*======================================================================*/
                /** The computed total pins for this frame only                         */
                /*======================================================================*/
   private  int       frameTotal = 0;
   public   int    getFrameTotal  ()                   { return (frameTotal); }
   public   void   setFrameTotal  (int    aFrameTotal) { frameTotal = aFrameTotal; }
   public   void   setFrameTotal  (String aFrameTotal)
   {
      try   { frameTotal = Integer.parseInt (aFrameTotal); }
      catch (Throwable myThrowable) { }
   }

                /*======================================================================*/
                /** The computed bonus pins for this frame only                         */
                /*======================================================================*/
   private  int       bonusTotal = 0;
   public   int    getBonusTotal  ()                   { return (bonusTotal); }
   public   void   setBonusTotal  (int    aBonusTotal) { bonusTotal = aBonusTotal; }

                /*======================================================================*/
                /** The cumulative total pins up to this frame                          */
                /*======================================================================*/
   private  int       gameTotal = -1;
   public   int    getGameTotal  ()                  { return (gameTotal); }
   public   void   setGameTotal  (int    aGameTotal) { gameTotal = aGameTotal; }
   public   void   setGameTotal  (String aGameTotal)
   {
      try   { gameTotal = Integer.parseInt (aGameTotal); }
      catch (Throwable myThrowable) { }
   }

                /*======================================================================*/
                /** Divider definitions                                                 */
                /*======================================================================*/
   public  static  final  String  TOTAL_DIVIDER   = "=";
   public  static  final  String  EMPTYROLLSTRING = "                                   ";

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** The mandatory constructor.
         *
         * <p>@param    aMaxRolls   The maximum rolls per frame.
         */
        /*==============================================================================*/
   public  FrameScore (
                       int  aMaxRolls
                      )
   {
      rollsPerFrame   = aMaxRolls;
      rollsToComplete = aMaxRolls;
      setRollValues    ("");
   }

        /*==============================================================================*/
        /** The mandatory constructor.
         *
         * <p>@param    aFrameString   A string representing the frame details.
         */
        /*==============================================================================*/
   public  FrameScore (
                       String  aFrameString
                      )
   {
      String[] myFrameParts = aFrameString.split (TOTAL_DIVIDER);

      setRollValues (myFrameParts [0]);
      setGameTotal  (myFrameParts [1]);
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

      if (myRollValues.startsWith ("X ")) {
         myStringBuilder.append ("X");
      }
      else {
         myStringBuilder.append (myRollValues);
      }
      myStringBuilder.append (TOTAL_DIVIDER);
      myStringBuilder.append (((myGameTotal >= 0) && myIsComplete) ? myGameTotal : " ");

      return (myStringBuilder.toString ());
   }


}  // EOF  FrameScore.java
