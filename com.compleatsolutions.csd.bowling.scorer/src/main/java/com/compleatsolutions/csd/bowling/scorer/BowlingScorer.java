/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorer.java                                                      */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import com.compleatsolutions.csd.bowling.scorer.pinsetterclient.PinSetterWebClient;

/*======================================================================================*/
/* CLASS:       BowlingScorer                                                           */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220204 vanyab9      CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorer
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
        /** The base URL for the PinSetter web services                                 */
   public  static  final String PINSETTER_BASEURL = "http://pinsetter.herokuapp.com/pinsetter/";

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/
//        /*==============================================================================*/
//        /* OPERATION:   stringToPins                                                    */
//        /**
//         *
//         *
//         * <p>
//         * @param       aPinString
//         *                 The string of numbers and marks (X, -, /) that makes up a
//         *                 complete game of bowling.
//         * <p>
//         * @return      :int[] -
//         *                 An array containing the number of pins for each roll.
//         */
//        /*==============================================================================*/
//   public  int[]  stringToPins (
//                                String  aPinString
//                               )
//   {
//      int   myRollCount = aPinString.length ();
//      int[] myPinCounts = new int [myRollCount];
//
//      for (int i = 0; i < myRollCount; i++) {
//         char myRoll     = aPinString.charAt (i);
//         int  myPinCount = 0;
//
//         switch (myRoll) {
//            case 'X':
//               myPinCount = 10;
//               break;
//            case '-':
//               myPinCount =  0;
//               break;
//            case '/':
//               myPinCount = 10 - myPinCounts [i - 1];
//               break;
//            default:
//               if ((myRoll >= '1') && (myRoll <= '9')) {
//                  myPinCount = Character.getNumericValue (myRoll);
//               }
//               else {
//                  throw new RuntimeException ("Invalid roll character: '" + myRoll + "'");
//               }
//               break;
//         }
//
//         myPinCounts [i] = myPinCount;
//      }
//
//      return (myPinCounts);
//   }
//
//        /*==============================================================================*/
//        /* OPERATION:   pinsToFrameScores                                               */
//        /**
//         *
//         *
//         * <p>
//         * @param       aPinCounts   An array containing the number of pins for each roll.
//         * @return      :int[]     - An array containing the cumulative scores per frame.
//         */
//        /*==============================================================================*/
//   public  int[]  pinsToFrameTotals (
//                                     int[]  aPinCounts
//                                    )
//   {
//      boolean myIsFrameComplete = false;
//      boolean myIsNewFrame      = true;
//      int     myRollIndex       =  0;
//      int     myFrameIndex      =  0;
//      int     myFrameCount      = 10;
//      int     myTotalScore      =  0;
//      int[]   myFrameScores     = new int [myFrameCount];
//
//      do {
//         int myPins = aPinCounts [myRollIndex];
//
//         System.out.printf ("Roll: %2d; frame: %2d; pins: %2d\n", myRollIndex, myFrameIndex, myPins);
//
//                /*======================================================================*/
//                /* First ball of a frame                                                */
//                /*======================================================================*/
//         if (myIsNewFrame) {
//
//                        /*==============================================================*/
//                        /* Strike - add the next 2 rolls                                */
//                        /*==============================================================*/
//            if (myPins == 10) {
//               myTotalScore      += myPins + aPinCounts [myRollIndex + 1] + aPinCounts [myRollIndex + 2];
//               myIsFrameComplete  = true;
//            }
//
//                        /*==============================================================*/
//                        /* Pins left                                                    */
//                        /*==============================================================*/
//            else {
//               myTotalScore     += myPins;
//               myIsFrameComplete = false;
//            }
//         }
//
//                /*======================================================================*/
//                /* Second ball of a frame                                               */
//                /*======================================================================*/
//         else {
//
//                        /*==============================================================*/
//                        /* Spare - add the next roll                                    */
//                        /*==============================================================*/
//            if (myPins == (10 - aPinCounts [myRollIndex - 1])) {
//               myTotalScore      += myPins + aPinCounts [myRollIndex + 1];
//               myIsFrameComplete  = true;
//            }
//            else {
//               myTotalScore     += myPins;
//               myIsFrameComplete = true;
//            }
//         }
//
//                /*======================================================================*/
//                /* Frame complete - store the cumulative frame score                    */
//                /*======================================================================*/
//         if (myIsFrameComplete) {
//            myFrameScores [myFrameIndex++] = myTotalScore;
//            myIsNewFrame                   = true;
//         }
//         else {
//            myIsNewFrame                   = false;
//         }
//
//         myRollIndex++;
////    } while (( ! myIsGameComplete) && (myRollIndex < 10));
//      } while (myFrameIndex < myFrameCount);
//
//      return (myFrameScores);
//   }
//
//        /*==============================================================================*/
//        /**
//         *
//         *
//         * <p>
//         * @param       aGameScore          The current game.
//         * @param       aPinSequenceString   A sequence of pins knocked down per roll.
//         * @return      :GameScore - An array containing the cumulative scores per frame.
//         */
//        /*==============================================================================*/
//   public  GameScore  pinsToGameScore (
//                                       GameScore   aGameScore,
//                                       String      aPinSequenceString
//                                      )
//   {
//      GameScore myGameScore   = new GameScore     ();
//      int[]     myPinCounts   = stringToPins      (aPinSequenceString);
//      int[]     myFrameTotals = pinsToFrameTotals (myPinCounts);
//
//      System.err.println ("pinsToGameScore not fully implemented");
//      return (myGameScore);
//   }

        /*==============================================================================*/
        /** Registers the game with the pin setter.
         *
         * <p>
         * @param   aGameScore   The new game.
         * @return  :String    - The unique registration ID.
         */
        /*==============================================================================*/
   public  static  final  String  register (
                                            GameScore  aGameScore
                                           )
   {
      String myId = (new PinSetterWebClient ()).invokeRegisterHttp (
                                                                    PINSETTER_BASEURL,
                                                                    aGameScore.getFrameCount    (),
                                                                    aGameScore.getPinCount      (),
                                                                    aGameScore.getRollsPerFrame (),
                                                                    aGameScore.getTestData      ()
                                                                  );
      aGameScore.setUniqueId (myId);
      GameScoreCache.addNew   (aGameScore);
      return                  (myId);
   }

        /*==============================================================================*/
        /** Gets the result of the next roll from the pin setter and computes the score.
         *
         * <p>
         * @param   aId       The unique registration ID for this game.
         * @return  :String - The currernt score.
         */
        /*==============================================================================*/
   public  static  final  String  roll (
                                        String  aId
                                       )
   {
      GameScore myGameScore = GameScoreCache.get      (aId);
      String    myNextRoll  = (new PinSetterWebClient ()).invokeRollHttp (PINSETTER_BASEURL, aId);

      myGameScore.addNextRoll  (myNextRoll);
      myGameScore.computeScore ();

      return (myGameScore.toString ());
   }

        /*==============================================================================*/
        /** Returns this game's configuration.
         *
         * <p>
         * @param   aId       The unique registration ID for this game.
         * @return  :String - The game configuration.
         */
        /*==============================================================================*/
   public  static  final  String  check (
                                         String  aId
                                        )
   {
      String     myConfig     = "id not found";
      GameScore  myGameScore  = GameScoreCache.get (aId);
      if (myGameScore != null) {
         myConfig = String.format (
                                   "\"config\" = { \"frames\" = %d, \"pins\" = %d, \"rolls\" = %d }",
                                   myGameScore.getFrameCount    (),
                                   myGameScore.getPinCount      (),
                                   myGameScore.getRollsPerFrame ()
                                 );
      }

      return (myConfig);
   }


}  // EOF  BowlingScorer.java
