/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2015  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreServiceApplication.java                                       */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

import org.restlet.Application;
import org.restlet.Context;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreServiceApplication                                             */
/**
 * The <code>Restlet</code> application for the Bowling Scorer service.
 *
 */
/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
public  class  BowlingScorerServiceApplication  extends  Application
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /** The constructor for this class.
         *
         * @param    aParentContext    The parent context.
         */
        /*==============================================================================*/
   public BowlingScorerServiceApplication (
                                       Context  aParentContext
                                      )
   {
      super (aParentContext);
   }

        /*==============================================================================*/
        /** Creates a root Restlet that will receive all incoming calls.
         *
         * @see         org.restlet.Application#createInboundRoot()
         */
        /*==============================================================================*/
   @Override
   public  synchronized  Restlet  createInboundRoot (
                                                    )
   {
                /*======================================================================*/
                /* Create a router Restlet that routes each call to a new instance      */
                /*======================================================================*/
      Router myRouter = new Router (getContext ());

                /*======================================================================*/
                /* Define a single route                                                */
                /*======================================================================*/
      myRouter.attachDefault (BowlingScorerServiceResource.class);

      return (myRouter);
   }


}  // EOF  GameScoreServiceApplication.java
