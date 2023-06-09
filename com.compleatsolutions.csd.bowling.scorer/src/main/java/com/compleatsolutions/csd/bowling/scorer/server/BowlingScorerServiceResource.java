/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2015  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreServiceResource.java                                           */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/
import java.util.List;

import org.restlet.Response;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.representation.Variant;
import org.restlet.resource.ServerResource;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreServiceResource                                                */
/**
 * The single resource for the Pin Setter system.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings("nls")
public  class  BowlingScorerServiceResource  extends  ServerResource
{

    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/

    /*===================================            ===================================*/
    /*=================================== Operations ===================================*/
    /*===================================            ===================================*/

        /*==============================================================================*/
        /* OPERATION:   GameScoreServiceResource                                       */
        /**
         * The default constructor for this class.
         */
        /*==============================================================================*/
   public  BowlingScorerServiceResource (
                                    )
   {
      super         ();
      setMediaTypes ();
   }

        /*==============================================================================*/
        /* OPERATION:   GameScoreServiceResource                                        */
        /**
         * The initializing constructor for this class.
         *
         * @param       aContext
         * @param       aRequest
         * @param       aResponse
         */
        /*==============================================================================*/
//   public  GameScoreServiceResource (
//                                      Context   aContext,
//                                      Request   aRequest,
//                                      Response  aResponse
//                                     )
//   {
//      super         (aContext, aRequest, aResponse);
//      setMediaTypes ();
//   }

        /*==============================================================================*/
        /* OPERATION:   setMediaTypes                                                   */
        /**
         * Registers the media types supported by this resource.
         */
        /*==============================================================================*/
   private  void  setMediaTypes (
                                )
   {
      List<Variant> myVariantList = getVariants ();

      myVariantList.add (new Variant (MediaType.ALL));
//    myVariantList.add (new Variant (MediaType.TEXT_PLAIN));
//    myVariantList.add (new Variant (MediaType.TEXT_HTML));
//    myVariantList.add (new Variant (MediaType.TEXT_XML));
   }

    /*==================================================================================*/
    /* Protected Operations                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Package Operations                                                               */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Public Operations                                                                */
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   getRepresentation                                               */
        /**
         * Returns a full representation for a given variant.
         *
         * <p>
         * @see         Resource getRepresentation(Variant)
         * <p>
         * @param       aVariant
         *                 The representation of the request object.
         */
        /*==============================================================================*/
//   @Override
//   public  Representation  getRepresentation (
//                                              Variant  aVariant
//                                             )
//   {
//      Representation myRepresentation = new StringRepresentation (
//                                                                  "Scientology Testing Restlet",
//                                                                  MediaType.TEXT_PLAIN
//                                                                 );
//      return (myRepresentation);
//   }

        /*==============================================================================*/
        /* OPERATION:   getRepresentation                                               */
        /**
         * Services a POST request.
         *
         * <p>
         * @param       aRepresentation
         *                 The representation of the POST request object.
         */
        /*==============================================================================*/
   @Override
   public  Representation  post (
                                 Representation  aRepresentation
                                )
   {
                /*======================================================================*/
                /* Retrieve the POST parameters                                         */
                /*======================================================================*/
      Representation myRepresentation    = null;
      String         myXmlResponseString = null;
      Form           myForm              = new Form (aRepresentation);
      String         myXmlRequestString  = myForm.getFirstValue ("csiRequestResponse");

                /*======================================================================*/
                /* Invoke the specific underlying Scientology Testing service           */
                /*======================================================================*/
      myXmlResponseString = (BowlingScorerServiceWrapper.instance ()).handle (myXmlRequestString);
      myRepresentation    = new StringRepresentation (myXmlResponseString, MediaType.TEXT_PLAIN);

                /*======================================================================*/
                /* Formulate the response                                               */
                /*======================================================================*/
      Response myResponse = getResponse ();
      myResponse.setStatus (Status.SUCCESS_OK);
      myResponse.setEntity (myRepresentation);

      return (myRepresentation);
   }

        /*==============================================================================*/
        /* OPERATION:   allowPost                                                       */
        /**
         * Denotes that POST requests are supported.
         *
         * <p>
         * @see         org.restlet.resource.Resource#allowPost()
         * <p>
         * @return      :boolean -
         *                  Always returns <code>true</code>.
         */
        /*==============================================================================*/
//   @Override
//   public  boolean  allowPost (
//                              )
//   {
//      return (true);
//   }

    /*==================================================================================*/
    /* Abstract Operations (definitions)                                                */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Abstract Operations (implementations)                                            */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Class (static) Operations                                                        */
    /*==================================================================================*/


}  // EOF  GameScoreServiceResource.java
