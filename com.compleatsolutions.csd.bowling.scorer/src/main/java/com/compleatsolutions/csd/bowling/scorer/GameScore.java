/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScore.java                                                          */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import java.util.ArrayList;
import java.util.List;

/*======================================================================================*/
/** The configuration and computed score and rolls for a single game.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220129 vanyab9      ACSD      n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ( { "javadoc", "nls" } )
public  class  GameScore
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
                /*======================================================================*/
                /** Unique ID of this game (shared by engine, pin setter and client)    */
                /*======================================================================*/
   private  String     uniqueId = "" + System.currentTimeMillis (); // Default for testing purposes
   public   String  getUniqueId  ()                  { return (uniqueId);    }
   public   void    setUniqueId  (String  aUniqueId) { uniqueId = aUniqueId; }

                /*======================================================================*/
                /** The configuration for this game                                     */
                /*======================================================================*/
                        /*==============================================================*/
                        /** The number of frames in this game                           */
                        /*==============================================================*/
   private  int       frameCount = -1;
   public   int    getFrameCount  ()                 { return (frameCount);      }
   public   void   setFrameCount  (int  aFrameCount) { frameCount = aFrameCount; }

                        /*==============================================================*/
                        /** The number of pins in each frame                            */
                        /*==============================================================*/
   private  int       pinCount   = -1;
   public   int    getPinCount    ()                 { return (pinCount);        }
   public   void   setPinCount    (int  aPinCount)   { pinCount = aPinCount;     }

                        /*==============================================================*/
                        /** The number of rolls for each frame                          */
                        /*==============================================================*/
   private  int       rollsPerFrame  = -1;
   public   int    getRollsPerFrame   ()                     { return (rollsPerFrame);         }
   public   void   setRollsPerFrame   (int  aRollsPerFrame)  { rollsPerFrame = aRollsPerFrame; }

                        /*==============================================================*/
                        /** The optional test data to feed to the PinSetter             */
                        /*==============================================================*/
   private  String     testData  = "";
   public   String  getTestData    ()                   { return (testData);       }
   public   void    setTestData    (String  aTestData)  { testData = aTestData;    }

                /*======================================================================*/
                /** The current sequence of pins knocked down in thios game             */
                /*======================================================================*/
   private  String     pinSequence = "";
   public   String  getPinSequence  ()
   {
      return ((pinSequence.endsWith (".")) ? pinSequence.substring (0, (pinSequence.length () - 1)) : pinSequence);
   }
   public   void    setPinSequence  (String  aPinSequence) { pinSequence  = aPinSequence; }
   public   void    addNextRoll     (String  aNextPin)     {
                                                             pinSequence += aNextPin;
                                                             computeScore ();
                                                           }

                /*======================================================================*/
                /** The list of individual frame scores for this game                   */
                /*======================================================================*/
   private  List<FrameScore>  frameScores = new ArrayList<> ();
   public   void       addFrameScore   (FrameScore  aFrameScore) { frameScores.add (aFrameScore);     }
   public  FrameScore  getFrameScore   (int  aIndex)             { return (frameScores.get (aIndex)); }
   public  int         getNFrameScores ()                        { return (frameScores.size ());      }

                /*======================================================================*/
                /** The computed total pins for this game                               */
                /*======================================================================*/
   private  int       gameTotal = -1;
   public   int    getGameTotal  ()                { return (gameTotal);     }
   public   void   setGameTotal  (int  aGameTotal) { gameTotal = aGameTotal; }

                /*======================================================================*/
                /** Divider definitions                                                 */
                /*======================================================================*/
   public  static  final  String  FRAME_DIVIDER  = "&";

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** The default constructor which defaults to the standard game.
         */
        /*==============================================================================*/
   public  GameScore (
                     )
   {
      this (10, 2, 2, "");
   }

        /*==============================================================================*/
        /** The initializing constructor.
         *
         * <p>
         * @param    aFrameCount     The number of frames in this game.
         * @param    aPinCount       The number of pins per frame.
         * @param    aRollsPerFrame  The maximum rolls per frame.
         * @param    aTestData       An optional sequence of pins knocked down (for testing
         *                           purposes).
         */
        /*==============================================================================*/
   public  GameScore (
                      int     aFrameCount,
                      int     aPinCount,
                      int     aRollsPerFrame,
                      String  aTestData
                     )
   {
      setFrameCount    (aFrameCount);
      setPinCount      (aPinCount);
      setRollsPerFrame (aRollsPerFrame);
      setTestData      (aTestData);

      resetFrameScores ();
   }

        /*==============================================================================*/
        /** Distributes the sequence of pins to the various frames.
         */
        /*==============================================================================*/
   private  void  distributePinsToFrames (
                                         )
   {
       int        myFrameIndex     = 0;
       FrameScore myFrameScore     = null;
       int        myRollIndex      = 0;
       String     myPinSequence    = getPinSequence ();
       boolean    myIncrementFrame = false;
       int        myFrameCount     = getFrameCount ();
       int        myTotalRolls     = myPinSequence.length ();

       for (int i = 0; i < myTotalRolls; i++) {
          char myChar  = myPinSequence.charAt (i);
          myFrameScore = getFrameScore        (myFrameIndex);
          myFrameScore.setRollValue           (myRollIndex++, myChar);

          if ((myChar >= '0') && (myChar <= '9')) {
             int myNumber = myChar - '0';
             myFrameScore.setFrameTotal (myFrameScore.getFrameTotal () + myNumber);
          }
          else if (myChar == '-') {
//
          }

          String myBonusString = "";
          if (myChar == '/') {
             myFrameScore.setRollsToComplete (1);
             myFrameScore.setFrameTotal      (getPinCount ());
             myFrameScore.setClosedFrame     (i < (myTotalRolls - 1));
             if (myFrameScore.isClosedFrame  ()) {
                myBonusString = myPinSequence.substring (i + 1);
                myFrameScore.setBonusTotal   (computeBonus (myBonusString, 1));
                if (myFrameIndex == (myFrameCount - 1)) {
                   String myRollValues = myFrameScore.getRollValues ()
                                       + myBonusString.substring (0, 1);
                   myFrameScore.setRollValuesDirect (myRollValues);
                }
             }
             myIncrementFrame = true;
          }
          else if (myChar == 'X') {
             myFrameScore.setRollsToComplete (2);
             myFrameScore.setFrameTotal      (getPinCount ());
             myFrameScore.setClosedFrame     (i < (myTotalRolls - 2));
             if (myFrameScore.isClosedFrame  ()) {
                myBonusString = myPinSequence.substring (i + 1);
                myFrameScore.setBonusTotal   (computeBonus (myBonusString, 2));
                if (myFrameIndex == (myFrameCount - 1)) {
                   String myRollValues = myFrameScore.getRollValues ()
                                       + myBonusString.substring (0, 2);
                   myFrameScore.setRollValuesDirect (myRollValues);
                }
             }
             myIncrementFrame = true;
          }
          else if (myRollIndex >= getRollsPerFrame ()) {
             myFrameScore.setRollsToComplete (0);
             myFrameScore.setClosedFrame     (true);
             myIncrementFrame = true;
          }
          else {
             myFrameScore.setRollsToComplete (getRollsPerFrame () - myRollIndex);
          }

          if (myIncrementFrame) {
             if (myFrameIndex >= (myFrameCount - 1)) {
//                ((LastFrameScore) myFrameScore).setBonusPins (myBonusString);
                break;
             }
             myFrameIndex++;
             myRollIndex      = 0;
             myIncrementFrame = false;
          }
       }
   }

        /*==============================================================================*/
        /** Resets the frame scores before computing the results.
         */
        /*==============================================================================*/
   private  int  computeBonus (
                               String  aBonusString,
                               int     aNBonusRolls
                              )
   {
      int myBonusScore = 0;

      if (aNBonusRolls == 1) {
         char myBonusChar = aBonusString.charAt (0);

         if (myBonusChar == 'X') {
            myBonusScore = 10;
         }
         else  if ((myBonusChar >= '0') && (myBonusChar <= '9')) {
            myBonusScore = myBonusChar - '0';
         }
      }

      else  if (aNBonusRolls == 2) {
         char myBonusChar1 = aBonusString.charAt (0);
         char myBonusChar2 = aBonusString.charAt (1);
         if (myBonusChar2 == '/') {
            myBonusScore = getPinCount ();
         }
         else {
            if (myBonusChar1 == 'X') {
               myBonusScore = 10;
            }
            else  if ((myBonusChar1 >= '0') && (myBonusChar1 <= '9')) {
               myBonusScore += myBonusChar1 - '0';
            }

            if (myBonusChar2 == 'X') {
               myBonusScore += 10;
            }
            else  if ((myBonusChar2 >= '0') && (myBonusChar2<= '9')) {
               myBonusScore += myBonusChar2 - '0';
            }
         }
      }

      return (myBonusScore);
   }

        /*==============================================================================*/
        /** Resets the frame scores before computing the results.
         */
        /*==============================================================================*/
    public  void  resetFrameScores (
                                   )
   {
       frameScores.clear ();
       int myRollsPerFrame = getRollsPerFrame ();

       for (int i = 0; i < getFrameCount () - 1; i++) {
          addFrameScore (new FrameScore (myRollsPerFrame));
       }
       addFrameScore (new LastFrameScore (myRollsPerFrame));
   }

        /*==============================================================================*/
        /** Computes the score of the current state of the game.
         */
        /*==============================================================================*/
    public  void  computeScore (
                               )
   {
       System.err.println ("computeScore not fully implemented");
       resetFrameScores       ();
       distributePinsToFrames ();

       int        myGameTotal   = 0;
       FrameScore myFrameScore  = null;

       for (int i = 0; i < getFrameCount (); i++) {
          myFrameScore = getFrameScore  (i);

          if (myFrameScore.isClosedFrame ()) {
             myGameTotal += myFrameScore.getFrameTotal () + myFrameScore.getBonusTotal ();
             myFrameScore.setGameTotal (myGameTotal);
          }
          else {
             myGameTotal = -1;
          }
       }
   }

        /*==============================================================================*/
        /** Converts an array of frame scores to a string representation.
         *
         * <p>@return   :String        The string representation.
         */
        /*==============================================================================*/
   public  String  toString (
                            )
   {
      StringBuilder myStringBuilder = new StringBuilder ();
      FrameScore    myFrameScore    = null;

      for (int i = 0; i < getNFrameScores (); i++) {
         myFrameScore = getFrameScore (i);

         if (i > 0) {
            myStringBuilder.append (FRAME_DIVIDER);
         }
         myStringBuilder.append (myFrameScore.toString ());
      }

      return (myStringBuilder.toString ());
   }


}  // EOF  GameScore.java
