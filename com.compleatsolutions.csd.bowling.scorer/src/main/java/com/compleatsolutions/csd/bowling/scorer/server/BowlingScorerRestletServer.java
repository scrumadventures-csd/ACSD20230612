/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2022  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerRestletServer.java                                             */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/
import org.restlet.Component;
import org.restlet.data.Protocol;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       BowlingScorerRestletServer                                                  */
/**
 * The stand-alone server for accepting and processing REST requests without needing an
 * instance of Jetty or Tomcat.
 */

/* Date     Author       Project    Tracking Change Description
 * -------- ------------ ---------- -------- ------------------
 * 20210417 ivanbiddles  CSD        n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorerRestletServer
{

    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** Prepares a Restlet <code>Component</code> instance for handling incoming HTTP
         * requests.
         */
        /*==============================================================================*/
   public  void  prepareForRequests (
                                    )
   {
                /*======================================================================*/
                /* Prepare and run the Restlet server                                   */
                /*======================================================================*/
      try {

                        /*==============================================================*/
                        /* Create a new Restlet component with an HTTP server connector */
                        /*==============================================================*/
         Component myComponent = new Component ();
         myComponent.getServers().add (Protocol.HTTP, Integer.valueOf (System.getenv ("PORT"))); // 8080

                        /*==============================================================*/
                        /* Attach the GameScore restlet                                 */
                        /*==============================================================*/
         BowlingScorerRestlet myBowlingScorerRestlet = new BowlingScorerRestlet ();

         (myComponent.getDefaultHost ()).attach ("/pinsetter", myBowlingScorerRestlet);

                        /*==============================================================*/
                        /* Start the Restlet component                                  */
                        /*                                                              */
                        /* Note: The HTTP server connector is also automatically        */
                        /*       started.                                               */
                        /*==============================================================*/
         myComponent.start ();
      }

                /*======================================================================*/
                /* Handle any exception encountered                                     */
                /*======================================================================*/
      catch (Throwable myThrowable) {
         myThrowable.printStackTrace ();
      }
   }

        /*==============================================================================*/
        /* OPERATION:   main                                                            */
        /**
         * The execution entry point for this server class.
         *
         * <p>
         * @param       aArgs
         *                 The array of command-line arguments.
         */
        /*==============================================================================*/
   public  static  final  void  main (
                                      String[]  aArgs
                                     )
   {
      System.out.println ("Starting BowlingScorerRestletServer ...");
      (new BowlingScorerRestletServer ()).prepareForRequests ();
   }


}  // EOF  BowlingScorerRestletServer.java
