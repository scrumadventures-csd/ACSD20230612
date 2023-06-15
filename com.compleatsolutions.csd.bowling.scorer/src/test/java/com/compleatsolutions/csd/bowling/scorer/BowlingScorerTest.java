/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerTest.java                                                  */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import com.compleatsolutions.csd.bowling.scorer.BowlingScorer;
import com.compleatsolutions.csd.bowling.scorer.GameScore;

import org.junit.Assert;
import org.junit.Test;

/*======================================================================================*/
/* CLASS:       BowlingScorerTest                                                       */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220203 vanyab9      CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorerTest
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Operations                                                               */
    /*==================================================================================*/
//        /*==============================================================================*/
//        /** Asserts that the given score string is correctly converted to pin counts per
//         *  ball.
//         *
//         * @param   aScoreString    The string representing pins and marks.
//         * @param   aExpectedPins   The expected array of pin counts per ball.
//         */
//        /*==============================================================================*/
   private  void  assertStringToPins (
                                      String  aScoreString,
                                      int[]   aExpectedPins
                                     )
   {
      BowlingScorer myBowlingScorer = new BowlingScorer ();
      int[]         myPinCounts     = myBowlingScorer.stringToPins (aScoreString);

      Assert.assertArrayEquals (myPinCounts, aExpectedPins);
   }
//
//        /*==============================================================================*/
//        /** Asserts that the given pin counts per ball are correctly converted to the
//         *  expected cumulative frame totals.
//         *
//         * @param   aActualPins        The given array of pin counts per ball.
//         * @param   aExpectedFrames    The expected array of cumulative frame scores.
//         */
//        /*==============================================================================*/
//   private  void  assertPinsToFrameTotals (
//                                           int[]  aActualPins,
//                                           int[]  aExpectedFrames
//                                          )
//   {
//      BowlingScorer myBowlingScorer = new BowlingScorer                 ();
//      int[]         myFrameTotals   = myBowlingScorer.pinsToFrameTotals (aActualPins);
//
//      Assert.assertArrayEquals (aExpectedFrames, myFrameTotals);
//   }
//
//        /*==============================================================================*/
//        /** Asserts that the given score string is correctly converted to pin counts per
//         *  ball.
//         *
//         * @param   aScoreString     The string representing pins and marks.
//         * @param   aExpectedFrames  The expected array of cumulative frame scores.
//         */
//        /*==============================================================================*/
//   private  void  assertStringToFrameTotals (
//                                             String  aScoreString,
//                                             int[]   aExpectedFrames
//                                            )
//   {
//      BowlingScorer myBowlingScorer = new BowlingScorer                 ();
//      int[]         myPinCounts     = myBowlingScorer.stringToPins      (aScoreString);
//      int[]         myFrameTotals   = myBowlingScorer.pinsToFrameTotals (myPinCounts);
//
//      Assert.assertArrayEquals (aExpectedFrames, myFrameTotals);
//   }
//
//        /*==============================================================================*/
//        /** Asserts that the given score string is correctly converted to frame scores.
//         *
//         * @param   aScoreString               A string representing pins and marks.
//         * @param   aExpectedFrameScoreString  The expected cumulative frame scores.
//         */
//        /*==============================================================================*/
//   private  void  assertStringToFrameScores (
//                                             String  aScoreString,
//                                             String  aExpectedFrameScoreString
//                                            )
//   {
//      BowlingScorer myBowlingScorer     = new BowlingScorer                    ();
//      FrameScore[]  myFrameScores       = myBowlingScorer.stringToFrameScores  (aScoreString);
//      String        myFrameScoreString  = FrameScore.fromFrameScorestoString   (myFrameScores);
//
//      Assert.assertEquals (aExpectedFrameScoreString, myFrameScoreString);
//   }
//
//        /*==============================================================================*/
//        /** Asserts that the given score string is correctly converted to the game total.
//         *
//         * @param   aScoreString    The string representing pins and marks.
//         * @param   aExpectedTotal  The expected total score.
//         */
//        /*==============================================================================*/
//   private  void  assertStringToTotal (
//                                       String  aScoreString,
//                                       int     aExpectedTotal
//                                      )
//   {
//      BowlingScorer myBowlingScorer = new BowlingScorer                 ();
//      int[]        myPinCounts      = myBowlingScorer.stringToPins      (aScoreString);
//      int[]        myFrameScores    = myBowlingScorer.pinsToFrameTotals (myPinCounts);
//
//      Assert.assertEquals (aExpectedTotal, myFrameScores [9]);
//   }

        /*==============================================================================*/
        /** Asserts that the given score string is correctly converted to the game total.
         *
         * @param   aScoreString     The string representing pins and marks.
         * @param   aExpectedString  The expected total score.
         */
        /*==============================================================================*/
//   private  void  assertStringToGameScore (
//                                           String  aScoreString,
//                                           String  aExpectedString
//                                          )
//   {
//      BowlingScorer   myBowlingScorer   = new BowlingScorer               ();
////    int[]           myPinCounts       = myBowlingScorer.stringToPins    (aScoreString);
//      GameScore       myGameScore       = myBowlingScorer.pinsToGameScore ("10", "10", "2", aScoreString,
//                                                                           aScoreString);
//
//      Assert.assertEquals (aExpectedString, myGameScore.toString ());
//   }

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/
//        /*==============================================================================*/
//        /** Tests that various score strings are correctly converted to pin counts per ball.
//         */
//        /*==============================================================================*/
//   @Test
   public  void  testStringToPins_Perfect (
                                         )
  {
     assertStringToPins (
                         "XXXXXXXXXXXX",
                         new int[] { 10,   10,   10,   10,   10,
                                     10,   10,   10,   10,   10,10,10 }
                        );
  }

    //    /*==============================================================================*/
    //    /** Tests that various score strings are correctly converted to pin counts per ball.
    //     */
    //    /*==============================================================================*/
//   @Test
//   public  void  testStringToPins_Open (
//                                       )
//   {
//      assertStringToPins (
//                          "9/7-",
//                          new int[] { 9,1,  7,0  }
//                         );
//   }
//
//        /*==============================================================================*/
//        /** Tests that the given pin counts per ball are correctly converted to the
//         *  expected cumulative frame scores.
//         */
//        /*==============================================================================*/
//   @Test
//   public  void  testPinsToFrameTotals (
//                                       )
//   {
//      assertPinsToFrameTotals (
//                               new int[] { 1,1,  1,1,  1,1,  1,1,  1,1,
//                                           1,1,  1,1,  1,1,  1,1,  1,1 },
//                               new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 }
//                              );
//   }
//
//        /*==============================================================================*/
//        /** Tests that the various score strings are correctly converted to the
//         *  expected cumuolative frame scores.
//         */
//        /*==============================================================================*/
//   @Test
//   public  void  testStringToFrameTotals (
//                                         )
//   {
//      assertStringToFrameTotals (
//                                 "11111111111111111111",
//                                 new int[] { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 }
//                                );
//      assertStringToFrameTotals (
//                                 "XXXXXXXXXXXX",
//                                 new int[] { 30, 60, 90, 120, 150, 180, 210, 240, 270, 300 }
//                                );
//   }
//
//        /*==============================================================================*/
//        /** Tests that the various score strings are correctly converted to the
//         *  expected cumuolative frame scores.
//         */
//        /*==============================================================================*/
//   @Test
//   public  void  testStringToFrameScores (
//                                         )
//   {
//      assertStringToFrameScores (
//                                 "11111111111111111111",
//                                 "[11=2][11=4][11=6][11=8][11=10][11=12][11=14][11=16][11=18][11=20]"
//                                );
//   }
//
//        /*==============================================================================*/
//        /** Tests that the various score strings are correctly converted to the
//         *  expected cumulative frame scores.
//         */
//        /*==============================================================================*/
//   @Test
//   public  void  testStringToTotal (
//                                   )
//   {
//      assertStringToTotal ("11111111111111111111",   20);
//      assertStringToTotal ("XXXXXXXXXXXX",          300);
//      assertStringToTotal ("9/9/9/9/9/9/9/9/9/9/9", 190);
//      assertStringToTotal ("--------------------",    0);
//   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
//   @Test
//   public  void  testStringToGameScore (
//                                       )
//   {
//      assertStringToGameScore (
//                               "11111111111111111111",
//                               "11=2&11=4&11=6&11=8&11=10&11=12&11=14&11=16&11=18&11=20"
//                              );
//   }


}  // EOF  BowlingScorerTest.java
