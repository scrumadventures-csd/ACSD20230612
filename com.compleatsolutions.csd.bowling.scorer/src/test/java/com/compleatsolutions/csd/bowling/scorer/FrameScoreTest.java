/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022 Compleat Solutions Inc.         |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerTest.java                                                  */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import com.compleatsolutions.csd.bowling.scorer.FrameScore;

import org.junit.Assert;
import org.junit.Test;

/*======================================================================================*/
/* CLASS:       FrameScoreTest                                                          */
/**
 *
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220128 vanyab9      ACSD      n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  FrameScoreTest
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
         * @param   aFrameScoreString  An array of frame scores.
         */
        /*==============================================================================*/
   private  void  assertFrameScoreToString (
                                            String  aFrameScoreString
                                           )
   {
      FrameScore  myFrameScore       = new FrameScore        (aFrameScoreString);
      String      myFrameScoreString = myFrameScore.toString ();

      Assert.assertEquals (aFrameScoreString, myFrameScoreString);
   }

        /*==============================================================================*/
        /** Tests that the various score strings are correctly converted to the
         *  expected cumuolative frame scores.
         */
        /*==============================================================================*/
   @Test
   public  void  testStringToFrameScoresNoMisses (
                                                 )
   {
      assertFrameScoreToString ("4,5=9");
   }


}  // EOF  BowlingScorerTest.java
