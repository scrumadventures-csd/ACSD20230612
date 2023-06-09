/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerService.java                                               */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

import java.io.Serializable;

import com.compleatsolutions.csd.bowling.scorer.GameScore;
import com.compleatsolutions.csd.bowling.scorer.GameScoreCache;
import com.compleatsolutions.csd.bowling.scorer.IGameScoreConstants;
import com.compleatsolutions.csd.bowling.scorer.pinsetterclient.PinSetterWebClient;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       BowlingScorerService                                                    */
/**
 * Provides the functions that give access to the internal methods of the testing system.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings("nls")
public  class  BowlingScorerService  implements  Serializable
{
    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/
                /** The unique serialization ID for this class.                         */
   private  static  final  long  serialVersionUID = -8429594239647345323L;

    /*===================================            ===================================*/
    /*=================================== Operations ===================================*/
    /*===================================            ===================================*/
        /*==============================================================================*/
        /* OPERATION:   closeConsumerThread                                             */
        /**
         * Causes the consumer thread to exit gracefully.
         */
        /*==============================================================================*/
   public  void  closeConsumerThread (
                                     )
   {
      System.err.println ("[service] Consumer thread closed");
   }

        /*==============================================================================*/
        /* OPERATION:   register                                                        */
        /**
         * Registers the client with a specific pin setter and sets the required game
         * configuration parametere.
         *
         * <p>
         * @param  aGameScoreRequest
         *                 The generic request/response object that must hold the pin
         *                 setter configuration parametere.
         * @return  :GameScoreRequestResponse -
         *                 The response that contains to unique pin setter ID.
          */
        /*==============================================================================*/
   public  BowlingScorerRequestResponse  register (
                                               BowlingScorerRequestResponse  aGameScoreRequest
                                              )
   {
      BowlingScorerRequestResponse myGameScoreResponse  = new BowlingScorerRequestResponse ();
      System.out.println ("[SERVICE ] register");

                /*======================================================================*/
                /* Marshall the input from the front-end (the scorer client)            */
                /*======================================================================*/
      String myFramesString = "10"; // TODO get from request
      String myPinsString   =  "2"; // TODO get from request
      String myRollsString  =  "2"; // TODO get from request
      String myScoreString  =   ""; // TODO get from request
      GameScore myGameScore = new GameScore (
                                             Integer.parseInt (myFramesString),
                                             Integer.parseInt (myPinsString),
                                             Integer.parseInt (myRollsString),
                                             myScoreString
                                            );

                /*======================================================================*/
                /* Register with the Pin Setter                                         */
                /*======================================================================*/
      PinSetterWebClient myPinSetterWebClient = new PinSetterWebClient ();
      String             myUniqueId           = myPinSetterWebClient
                                                   .invokeRegisterHttp (
                                                                        IGameScoreConstants.PINSETTER_URL,
                                                                        myGameScore.getFrameCount    (),
                                                                        myGameScore.getPinCount      (),
                                                                        myGameScore.getRollsPerFrame (),
                                                                        myScoreString
                                                                       );
      myGameScore.setUniqueId (myUniqueId);

                /*======================================================================*/
                /* Save the registration ID and game score instance to the score cache  */
                /*======================================================================*/
      GameScoreCache.addNew   (myGameScore);

                /*======================================================================*/
                /* Return the response to the scorer client                             */
                /*======================================================================*/
      myGameScoreResponse.setAction    (aGameScoreRequest.getAction ());
      myGameScoreResponse.setPayload   ("OK");
      myGameScoreResponse.setReference (aGameScoreRequest.getReference ());

      return (myGameScoreResponse);
   }

        /*==============================================================================*/
        /* OPERATION:   roll                                                            */
        /**
         * Retrieves the list of sequences.
         *
         * <p>
         * @param       aBowlingScorerRequest
         *                 The generic request/response object that must hold a test
         *                 match criteria instance.
         * <p>
         * @return      :BowlingScorerRequestResponse -
         *                 The response including the retrieved set of sequences.
         */
        /*==============================================================================*/
   public  BowlingScorerRequestResponse  roll (
                                               BowlingScorerRequestResponse  aBowlingScorerRequest
                                              )
   {
      BowlingScorerRequestResponse myBowlingScorerResponse = new BowlingScorerRequestResponse ();
      System.out.println ("[SERVICE ] roll");

//    GameScoreConfig myGameScoreConfig = BowlingScorerJsonMapper.mapJsonStringToConfig ((String) aGameScoreRequest.getPayload ());
      String          myId              = "0001-"; // myGameScore.getUniqueId      ();
      GameScore       myGameScore       = GameScoreCache.get           (myId);
      myGameScore.computeScore (); // TODO session-based

      myBowlingScorerResponse.setAction    (aBowlingScorerRequest.getAction ());
      myBowlingScorerResponse.setPayload   ("" + myGameScore.toString ());
      myBowlingScorerResponse.setReference (aBowlingScorerRequest.getReference ());

      return (myBowlingScorerResponse);
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
      BowlingScorerService         myBowlingScorerService  = new BowlingScorerService ();
      GameScore                    myGameScore             = new GameScore            ();
      BowlingScorerRequestResponse myBowlingScorerResponse = null;
      BowlingScorerRequestResponse myBowlingScorerRequest  = new BowlingScorerRequestResponse ();
      int                          myBaseReference         = 270570;
      char                         myChar                  = ' ';

                /*======================================================================*/
                /* Test the register (bowlingscorer/register) RESTful web service       */
                /*======================================================================*/
      myGameScore.setFrameCount    (10);
      myGameScore.setPinCount      (10);
      myGameScore.setRollsPerFrame (2);
      myGameScore.setTestData      ("-1-2-3-4-5-6-7-8-9-/X");

      myBowlingScorerRequest.setAction    ("/bowlingscorer/register");
      myBowlingScorerRequest.setPayload   (BowlingScorerJsonMapper.mapGameToJsonString (myGameScore));
      myBowlingScorerRequest.setReference (String.valueOf (myBaseReference++));

      System.out.println ();
      System.out.println ("[REQUEST ] " + BowlingScorerJsonMapper.mapObjectToJsonString (myBowlingScorerRequest));

      myBowlingScorerResponse = myBowlingScorerService.register (myBowlingScorerRequest);

      System.out.println ("[RESPONSE] " + BowlingScorerJsonMapper.mapObjectToJsonString (myBowlingScorerResponse));

                /*======================================================================*/
                /* Test the roll (pinsetter/roll) RESTful web service                   */
                /*======================================================================*/
      myBowlingScorerRequest.setAction    ("/pinsetter/roll");
      myBowlingScorerRequest.setPayload   (" ");

      while (myChar != '.') {
         myBowlingScorerRequest.setReference (String.valueOf (myBaseReference++));

         System.out.println ();
         System.out.println ("[REQUEST ] " + BowlingScorerJsonMapper.mapObjectToJsonString (myBowlingScorerRequest));

         myBowlingScorerResponse = myBowlingScorerService.roll  (myBowlingScorerRequest);
         String myPayload        = (String) myBowlingScorerResponse.getPayload ();

         if (".".equals (myPayload)) {
            myChar = '.';
         }
         System.out.println ("[RESPONSE] " + BowlingScorerJsonMapper.mapObjectToJsonString (myBowlingScorerResponse));

//                /*======================================================================*/
//                /* Test unmarshalling the JSON string back to a sequence object         */
//                /*======================================================================*/
//         System.out.println ();
//         System.out.println ("[SEQUENCE]");
//         System.out.println (((GameScoreSequence) myGameScoreResponse.getPayload ()).toRawString ());
      }
   }


}  // EOF  BowlingScorerService.java
