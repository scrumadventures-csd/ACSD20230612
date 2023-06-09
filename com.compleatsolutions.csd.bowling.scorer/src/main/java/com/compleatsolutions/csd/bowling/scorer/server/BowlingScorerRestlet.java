/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerRestlet.java                                               */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

import com.compleatsolutions.csd.bowling.scorer.BowlingScorer;
import com.compleatsolutions.csd.bowling.scorer.GameScore;

import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

/*======================================================================================*/
/* CLASS:       BowlingScorerRestlet                                                    */
/**
 * The basic Restlet that exposes the three Bowling Scorer web services -- register, roll,
 * and check.
 */

/* Date     Author       Project    Tracking Change Description
 * -------- ------------ ---------- -------- ------------------
 * 20220201 ivanbiddles  CSD        n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorerRestlet  extends  Restlet
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** Handles the incoming Restlet request and provides a corresponding response.
         * <p>
         * <b>Note: </b>
         * The super class method must be called first to ensure that the Restlet is
         * initialized - by setting the current context and by attempting to start it,
         * unless it was already started.
         * <p>
         * If an exception is thrown during the start action, then the response status
         * is set to {@link Status#SERVER_ERROR_INTERNAL}.
         *
         * <p>
         * @see         org.restlet.Restlet#handle(Request,Response)
         * @param       aRequest       The Restlet data request.
         * @param       aResponse      The Restlet data response.
         */
        /*==============================================================================*/
   @Override
   public  void  handle (
                         Request   aRequest,
                         Response  aResponse
                        )
   {
                /*======================================================================*/
                /* Ensure that the restlet is initialized                               */
                /*======================================================================*/
      super.handle (aRequest, aResponse);

                /*======================================================================*/
                /* Handle the request by delegating it to the service wrapper class     */
                /*======================================================================*/
      try {
         Reference myResourceReference = aRequest.getResourceRef ();
//         String    myMessage           =   "Resource URI  : " + myResourceReference
//                                       + "\nRoot URI      : " + aRequest.getRootRef ()
//                                       + "\nRouted part   : " + myResourceReference.getBaseRef       ()
//                                       + "\nRemaining part: " + myResourceReference.getRemainingPart ();
         Form   myRawForm = myResourceReference.getQueryAsForm ();
         String myAction  = myRawForm.getFirstValue ("action");

         System.out.println ("Raw action: " + myAction);

                        /*==============================================================*/
                        /* Request provided as query parameter (action)                 */
                        /*==============================================================*/
                                /*======================================================*/
                                /* register - the configuration with the pinsetter      */
                                /*======================================================*/
         if ("register".equalsIgnoreCase (myAction)) {
            GameScore myGameScore = new GameScore (
                                                   Integer.parseInt (myRawForm.getFirstValue ("frames")),
                                                   Integer.parseInt (myRawForm.getFirstValue ("pins")),
                                                   Integer.parseInt (myRawForm.getFirstValue ("rolls")),
                                                   myRawForm.getFirstValue ("test")
                                                  );
            String myId = BowlingScorer.register  (myGameScore);
            aResponse.setEntity                   (myId, MediaType.TEXT_PLAIN);
         }

                                /*======================================================*/
                                /* roll     - return the pins from the next roll        */
                                /*======================================================*/
          else if ("roll".equalsIgnoreCase (myAction)) {
            String myId    = myRawForm.getFirstValue ("id");
            String myScore = BowlingScorer.roll      (myId);

            aResponse.setEntity      (myScore, MediaType.TEXT_PLAIN);
         }

                                /*======================================================*/
                                /* check    - return the game configuration             */
                                /*======================================================*/
          else if ("check".equalsIgnoreCase (myAction)) {
            String myId     = myRawForm.getFirstValue ("id");
            String myConfig = BowlingScorer.check     (myId);

            aResponse.setEntity (myConfig, MediaType.TEXT_PLAIN);
         }

                        /*==============================================================*/
                        /* Request provided as a JSON payload                           */
                        /*==============================================================*/
         else {
            Representation myRepresentation = aRequest.getEntity   ();
            Form           myForm           = new Form             (myRepresentation);
            String         myRequestString  = myForm.getFirstValue ("bowlingScorerRequestResponse");
            String         myResponseString = ((BowlingScorerServiceWrapper.instance ())
                                                                           .handle   (myRequestString));
            aResponse.setEntity (myResponseString, MediaType.TEXT_PLAIN);
         }
      }

      catch (Throwable myThrowable) {
         aResponse.setEntity (myThrowable.toString (), MediaType.TEXT_PLAIN);
      }

                /*======================================================================*/
                /* Ensure no Cross-Origin Resource Transfer (CORS) errors at the client */
                /*======================================================================*/
      aResponse.setAccessControlAllowOrigin ("*");
   }


}  // EOF  BowlingScorerRestlet.java
