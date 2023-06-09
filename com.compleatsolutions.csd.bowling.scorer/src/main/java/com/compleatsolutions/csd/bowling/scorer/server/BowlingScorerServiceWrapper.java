/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreServiceWrapper.java                                            */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

import com.compleatsolutions.csd.bowling.scorer.IGameScoreConstants;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreServiceWrapper                                                 */
/**
 * A Restlet wrapper (or adapter) around the standard transport-independent web service
 * methods.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings("nls")
public  class  BowlingScorerServiceWrapper
{

    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/
                /** The singleton instance of this class.                               */
   private  static  final  BowlingScorerServiceWrapper  singletonInstance = new BowlingScorerServiceWrapper ();
                /** The transport-independent web service object.                       */
   private  static  BowlingScorerService  gameScoreService;

    /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Operations ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

        /*==============================================================================*/
        /* OPERATION:   GameScoreServiceWrapper                                         */
        /**
         * The constructor for this class, declared with <code>private</code> scope in
         * accordance with the <code>Singleton</code> design pattern.
         */
        /*==============================================================================*/
   private  BowlingScorerServiceWrapper (
                                    )
   {
   }

        /*==============================================================================*/
        /* OPERATION:   getGameScoreService                                             */
        /**
         * Retrieves (via lazy instantiation) the service instance.
         *
         * <p>
         * @return      :GameScoreService -
         */
        /*==============================================================================*/
   private  BowlingScorerService  getGameScoreService (
                                                  )
   {
      if (gameScoreService == null) {
         gameScoreService = new BowlingScorerService ();
      }
      return (gameScoreService);
   }

        /*==============================================================================*/
        /* OPERATION:   handle                                                          */
        /**
         * Handles a service request by unmarshalling the supplied XML string, calling the
         * appropriate service, and marshalling the response to XML for return to the
         * caller.
         *
         * <p>
         * @param       aGameScoreRequestString
         *                 The generic request/response object as an XML string.
         * <p>
         * @return      :String -
         *                 The response marshalled as an XML string.
         */
        /*==============================================================================*/
   public  String  handle (
                           String  aGameScoreRequestString
                          )
   {
      String                    myAction                   = null;
      String                    myGameScoreResponseString  = null;
      BowlingScorerRequestResponse  myGameScoreResponse        = null;
      BowlingScorerRequestResponse  myGameScoreRequest         = null;
      BowlingScorerService          myGameScoreService         = getGameScoreService ();

                /*======================================================================*/
                /* Unmarshall the XML string into its POJO equivalent                   */
                /*======================================================================*/
      try {
         myGameScoreRequest = unmarshall (aGameScoreRequestString);
      }
      catch (Throwable myThrowable) {
         BowlingScorerException.handle        (null, myThrowable);
         myGameScoreResponse             = new BowlingScorerRequestResponse ();
         myGameScoreResponse.setAction    ("fault:unmarshall");
         myGameScoreResponse.setPayload   (aGameScoreRequestString + "\n" + myThrowable.toString ());
         myGameScoreResponse.setReference ("UNKNOWN");
      }

                /*======================================================================*/
                /* Call the appropriate service based upon the specific action          */
                /*======================================================================*/
      if (myGameScoreRequest != null) {
         try {
            myAction = myGameScoreRequest.getAction ();

                        /*==============================================================*/
                        /* Sequence-related actions                                     */
                        /*==============================================================*/
                                /*======================================================*/
                                /* Retrieve (list of) sequences request                 */
                                /*======================================================*/
            if (IGameScoreConstants.REQUEST_REGISTER.equals (myAction)) {
               myGameScoreResponse = myGameScoreService.register (myGameScoreRequest);
            }

                                /*======================================================*/
                                /* Subscribe for sequences request                      */
                                /*======================================================*/
            else if (IGameScoreConstants.REQUEST_ROLL.equals (myAction)) {
               myGameScoreResponse = myGameScoreService.roll  (myGameScoreRequest);
            }

                        /*==============================================================*/
                        /* Unsupported action                                           */
                        /*==============================================================*/
            else {
               String myErrorText = "Unsupported action, \"" + myAction + "\", requested";

               System.err.println (myErrorText);

               myGameScoreResponse = new BowlingScorerRequestResponse ();
               myGameScoreResponse.setAction    (myAction);
               myGameScoreResponse.setPayload   (myErrorText);
               myGameScoreResponse.setReference (myGameScoreRequest.getReference ());
            }
         }

         catch (Throwable myThrowable) {
            BowlingScorerException.handle        (null, myThrowable);
            myGameScoreResponse             = new BowlingScorerRequestResponse ();
            myGameScoreResponse.setAction    ("fault:" + myAction);
            myGameScoreResponse.setPayload   (myThrowable.toString ());
            myGameScoreResponse.setReference (myGameScoreRequest.getReference ());
         }
      }

                /*======================================================================*/
                /* Marshall the response back to a JSON string                          */
                /*======================================================================*/
      try {
         myGameScoreResponseString = marshall (myGameScoreResponse);
      }

      catch (Throwable myThrowable) {
         BowlingScorerException.handle (null, myThrowable);
         myGameScoreResponseString = "<gameScoreRequestResponse><action>fault:"
                             + myAction
                             + "</action><payload>"
                             + myThrowable.toString ()
                             + "</payload><reference>"
                             + ((myGameScoreRequest != null) ? myGameScoreRequest.getReference () : "UNKNOWN")
                             + "</reference></gameScoreRequestResponse>";
      }

      return (myGameScoreResponseString);
   }

        /*==============================================================================*/
        /* OPERATION:   closeConsumerThread                                             */
        /**
         * Causes the consumer thread to exit gracefully.
         */
        /*==============================================================================*/
   public  void  closeConsumerThread (
                                     )
   {
      (getGameScoreService ()).closeConsumerThread ();
   }

    /*==================================================================================*/
    /* Abstract Operations (definitions)                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (implementations)                                            */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Class (static) Operations                                                        */
    /*==================================================================================*/
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
      BowlingScorerRequestResponse myGameScoreRequest = new BowlingScorerRequestResponse ();

      myGameScoreRequest.setAction    ("/pinsetter/register");
      myGameScoreRequest.setPayload   ("{\"frameCount\":10,\"pinCount\":10,\"rollsPerFrame\":2,\"scoreString\":\"-1-2-3-4-5-6-7-8-9-/X12\"}");
      myGameScoreRequest.setReference ("270570");

      String   myJsonString = marshall (myGameScoreRequest);

      System.out.println ();
      System.out.println (myJsonString);

      BowlingScorerRequestResponse myGameScoreResponse = unmarshall (myJsonString);

      System.out.println ();
      System.out.println (myGameScoreResponse.toString ());
   }

         /*=============================================================================*/
         /* OPERATION:   instance                                                       */
         /**
          * Returns the singleton instance of this class.
          *
          * <p>
          * @return      :ScnTestingPersistor -
          *                  The singleton instance of this class.
          */
         /*=============================================================================*/
   public  static  BowlingScorerServiceWrapper  instance (
                                                     )
   {
      return (singletonInstance);
   }

        /*==============================================================================*/
        /* OPERATION:   marshall                                                        */
        /**
         * Marshalls the supplied request/response object into a JSON string using the
         * Jackson framework.
         *
         * @param  aGameScoreRequestResponse   The response object to be marshalled.
         * @return :String -                   The response object marshalled as a JSON string.
         */
        /*==============================================================================*/
   protected  static  final  String  marshall (
                                               BowlingScorerRequestResponse  aGameScoreRequestResponse
                                              )
   {
      return (BowlingScorerJsonMapper.mapObjectToJsonString (aGameScoreRequestResponse));
   }

        /*==============================================================================*/
        /* OPERATION:   unmarshall                                                      */
        /**
         * Unmarshalls the supplied JSON string into a request/response object using the
         * Jackson framework.
         *
         * <p>
         * @param       aJsonString
         *                 The request object as a JSON string.
         * <p>
         * @return      :GameScoreRequestResponse -
         *                 The marshalled request object.
         */
        /*==============================================================================*/
   protected  static  final  BowlingScorerRequestResponse  unmarshall (
                                                                  String  aJsonString
                                                                 )
   {
      return (
              (BowlingScorerRequestResponse)
              BowlingScorerJsonMapper.mapJsonStringToObject (
                                                        aJsonString,
                                                        BowlingScorerRequestResponse.class
                                                       )
             );
   }


}  // EOF  GameScoreServiceWrapper.java
