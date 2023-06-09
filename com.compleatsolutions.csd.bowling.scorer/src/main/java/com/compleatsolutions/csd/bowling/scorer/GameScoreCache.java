/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreCache.java                                                     */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer;

import java.util.Map;
import java.util.TreeMap;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreCache                                                          */
/**
 * A Restlet wrapper (or adapter) around the standard transport-independent web service
 * methods.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
//@SuppressWarnings("nls")
public  class  GameScoreCache
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/
                /** The cache of GameScore instances.                                   */
   private  static  Map<String,GameScore>  gameScoreMap = new TreeMap<> ();
                /** The unique serialization ID for this class.                         */
//   private  static  int                    index        = 1;

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** The constructor for this class, declared with <code>private</code> scope in
         *  accordance with the <code>Singleton</code> design pattern.
         */
        /*==============================================================================*/
   private  GameScoreCache () { }

        /*==============================================================================*/
        /** Adds a new game score to the service instance.
         *
         * <p>
         * @param  aGameScore   The game score instance to add.
         */
        /*==============================================================================*/
   public  static  final  void  addNew (
                                        GameScore  aGameScore
                                       )
   {
      gameScoreMap.put (aGameScore.getUniqueId (), aGameScore);
   }

        /*==============================================================================*/
        /** Retrieves (via lazy instantiation) the service instance.
         *
         * @param   aId          The ID of the required game score.
         * @return  :GameScore -
         */
        /*==============================================================================*/
   public  static  final  GameScore  get (
                                          String  aId
                                         )
   {
      return (gameScoreMap.get (aId));
   }

         /*=============================================================================*/
         /* OPERATION:   main                                                           */
         /**
          * The executable entry point of this class for testing purposes.
          *
          * <p>
          * @param       aArgs
          *                  The array of command line arguments.
          */
         /*=============================================================================*/
   public  static  final  void  main (
                                      String[]  aArgs
                                     )
   {
//    TODO
   }


}  // EOF  GameScoreCache.java
