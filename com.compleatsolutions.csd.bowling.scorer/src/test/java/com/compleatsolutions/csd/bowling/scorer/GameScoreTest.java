/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreTest.java                                                      */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import com.compleatsolutions.csd.bowling.scorer.GameScore;

import org.junit.Assert;
import org.junit.Test;

/*======================================================================================*/
/* CLASS:       GameScoreTest                                                           */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220128 vanyab9      ACSD      n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  GameScoreTest
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** Asserts that the given score string is correctly converted to frame scores
         *  and back again.
         *
         * <p>
         * @param  aGameScore        The state and configuration of the current game.
         * @param  aExpectedString   The expected result.
         */
        /*==============================================================================*/
   private  void  assertGameScoreToString (
                                           GameScore  aGameScore,
                                           String     aExpectedString
                                          )
   {
      aGameScore.setPinSequence (aGameScore.getTestData ());
      aGameScore.computeScore   ();
      String myGameScoreString = aGameScore.toString ();

      Assert.assertEquals (aExpectedString, myGameScoreString);
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
//   @Test
//   public  void  testPartialGameNoMarks (
//                                        )
//   {
//      assertGameScoreToString (
//                               new GameScore (10, 10, 2, "1-1112"),
//                               "1-=1&11=3&12=6&  = &  = &  = &  = &  = &  = &  = "
//                              );
//   }
//
//        /*==============================================================================*/
//        /** Tests that the various score strings are correctly converted to the
//         *  expected cumulative frame scores.
//         */
//        /*==============================================================================*/
//   @Test
//   public  void  testFullGameNoMarks (
//                                     )
//   {
//      assertGameScoreToString (
//                               new GameScore (10, 10, 2, "1-11121314151617181-"),
//                               "1-=1&11=3&12=6&13=10&14=15&15=21&16=28&17=36&18=45&1-=46"
//                              );
//   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testPartialGameWithClosedMarks (
                                                )
   {
      assertGameScoreToString (
                               new GameScore (10, 10, 2, "9/61"),
                               "9/=16&61=23&  = &  = &  = &  = &  = &  = &  = &  = "
                              );
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testPartialGameWithOpenMarks (
                                                )
   {
      assertGameScoreToString (
                               new GameScore (10, 10, 2, "9/6/"),
                               "9/=16&6/= &  = &  = &  = &  = &  = &  = &  = &  = "
                              );
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testFullGameWithClosedMarks (
                                             )
   {
      assertGameScoreToString (
                               new GameScore (10, 10, 2, "9/9/9/9/9/9/9/9/9/9-"),
                               "9/=19&9/=38&9/=57&9/=76&9/=95&9/=114&9/=133&9/=152&9/=171&9- =180"
                              );
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testFullGameWithExtraRolls (
                                            )
   {
      assertGameScoreToString (
                               new GameScore (10, 10, 2, "9/9/9/9/9/9/9/9/9/9/9"),
                               "9/=19&9/=38&9/=57&9/=76&9/=95&9/=114&9/=133&9/=152&9/=171&9/9=190"
                              );
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumulative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testPerfectGame (
                                 )
   {
      assertGameScoreToString (
                               new GameScore (10, 10, 2, "XXXXXXXXXXXX"),
                               "X=30&X=60&X=90&X=120&X=150&X=180&X=210&X=240&X=270&XXX=300"
                              );
   }


}  // EOF  GameScoreTest.java
