/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      BowlingScorerWebClient.java                                             */
/*                                                                                      */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.webclient;

import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

/*======================================================================================*/
/* CLASS:       BowlingScorerWebClient                                                  */
/**
 * Web client for the Bowling Scorer web services for testing purposes.
 *
 * Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20220203 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ("nls")
public  class  BowlingScorerWebClient
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   invokeRegisterJson                                              */
        /**
         * Invokes the Bowling Scorer <i>register</i> web service.
         *
         * @param  aRequestUrl     The root of the host URL.
         * @param  aFrameCount     The number of frames in this custom game.
         * @param  aPinCount       The number of pins set up in each framein this custom
         *                         game.
         * @param  aRollsPerFrame  The number of rolls per frames in this custom game.
         * @param  aPinString      [Optional] A string of numbers and marks (X, -, /) to be
         *                         returned one a time. [For testing purposes]
         * @return :String -       The object representing the response content.
         */
        /*==============================================================================*/
//   protected  String  invokeRegisterJson (
//                                          String  aRequestUrl,
//                                          int     aFrameCount,
//                                          int     aPinCount,
//                                          int     aRollsPerFrame,
//                                          String  aPinString
//                                         )
//   {
//                /*======================================================================*/
//                /* Invoke the web service using the Fluent HTTP client facade framework */
//                /*======================================================================*/
//      try {
//         String myFullRequestUrl = String.format ("%sregister/", aRequestUrl);
//         String myAction         = "/pinsetter/register";
//         String myJsonPayload    = String.format (
//                                                  "{\"action\":\"%s\",\"frameCount\":%d,\"pinCount\":%d,\"rollsPerFrame\":%d,\"scoreString\":\"%s\"}",
//                                                  myAction, aFrameCount, aPinCount, aRollsPerFrame,
//                                                  ((aPinString != null) ? aPinString : "")
//                                                 );
////       String myReference      = "270570";
//         System.out.println ("[HTTP:REQUEST ] " + myFullRequestUrl + "\t" + myJsonPayload);
//
//
//                        /*==============================================================*/
//                        /* Marshall the request instance to a JSON string               */
//                        /*==============================================================*/
//         List<NameValuePair> myParameters    = Form.form  ()
////                                                 .add   ("username",    "vip")
////                                                 .add   ("password",    "secret")
////                                                 .add   ("frames",      String.valueOf (aFrameCount))
////                                                 .add   ("pins",        String.valueOf (aPinCount))
////                                                 .add   ("rolls",       String.valueOf (aRollsPerFrame))
////                                                 .add   ("scoreString", aPinString)
//                                                   .add   ("action",      myAction)
//                                                   .add   ("bowlingScorerRequestResponse",     myJsonPayload)
//                                                   .build ();
//
//                        /*==============================================================*/
//                        /* Execute the HTTP request                                     */
//                        /*==============================================================*/
//         Request  myRequest        = Request.Post              (myFullRequestUrl)
//                                            .useExpectContinue ()
//                                            .version           (HttpVersion.HTTP_1_1)
//                                            .bodyForm          (myParameters);
//         Response myResponse       = myRequest.execute         ();
//         String   myResponseString = myResponse.returnContent  ()
//                                               .asString       ();
//
////                        /*==============================================================*/
////                        /* Unmarshall the response from the body JSON string            */
////                        /*==============================================================*/
////         CompleatRequestResponse myCompleatResponse =
////                                  (CompleatRequestResponse)
////                                  CompleatJsonMapper.mapJsonStringToObject (
////                                                                            myResponseString,
////                                                                            CompleatRequestResponse.class
////                                                                           );
//         return (myResponseString);
//      }
//
//                /*======================================================================*/
//                /* Rethrow any exception as a run-time exception                        */
//                /*======================================================================*/
//      catch (Throwable myThrowable) {
//         if (myThrowable instanceof RuntimeException) {
//            throw (RuntimeException) myThrowable;
//         }
//         throw new RuntimeException (null, myThrowable);
//      }
//   }
//
//        /*==============================================================================*/
//        /* OPERATION:   invokeRollJson                                                  */
//        /**
//         * Invokes the Bowling Scorer <i>nextScore</i> web service.
//         *
//         * @param  aRequestUrl  The root of the host URL.
//         * @param  aId          The identifier of te registered pin setter instance.
//         * @return :String -    The object representing the response content.
//         */
//        /*==============================================================================*/
//   protected  String  invokeRollJson (
//                                      String  aRequestUrl,
//                                      String  aId
//                                     )
//   {
//                /*======================================================================*/
//                /* Invoke the web service using the Fluent HTTP client facade framework */
//                /*======================================================================*/
//      try {
//         String myFullRequestUrl = String.format ("%sroll/", aRequestUrl);
//
//         String myAction         = "/pinsetter/roll";
//         String myJsonPayload    = String.format ("{\\\"action\\\":\\\"%s\\\",\"id\":%\"%s\"}", myAction, aId);
//         String myReference      = "270571";
//
//         BowlingScorerRequestResponse myBowlingScorerRequest = new BowlingScorerRequestResponse ();
//
//         myBowlingScorerRequest.setAction    (myAction);
//         myBowlingScorerRequest.setPayload   (myJsonPayload);
//         myBowlingScorerRequest.setReference (myReference);
//
//         String myRequestString = BowlingScorerJsonMapper.mapObjectToJsonString (myBowlingScorerRequest);
//         System.out.println ("[JSON:REQUEST ] " + myFullRequestUrl + "\t" + myRequestString);
//
//                        /*==============================================================*/
//                        /* Marshall the request instance to a JSON string               */
//                        /*==============================================================*/
//         List<NameValuePair> myParameters    = Form.form  ()
////                                                 .add   ("username",   "vip")
////                                                 .add   ("password",   "secret")
//                                                   .add   ("bowlingScorerRequestResponse",  myRequestString)
//                                                   .build ();
//
//                        /*==============================================================*/
//                        /* Execute the HTTP request                                     */
//                        /*==============================================================*/
//         Request  myRequest        = Request.Post              (aRequestUrl)
//                                            .useExpectContinue ()
//                                            .version           (HttpVersion.HTTP_1_1)
//                                            .bodyForm          (myParameters);
//         Response myResponse       = myRequest.execute         ();
//         String   myResponseString = myResponse.returnContent  ()
//                                               .asString       ();
////
////                        /*==============================================================*/
////                        /* Unmarshall the response from the body JSON string            */
////                        /*==============================================================*/
////         CompleatRequestResponse myCompleatResponse =
////                                  (CompleatRequestResponse)
////                                  CompleatJsonMapper.mapJsonStringToObject (
////                                                                            myResponseString,
////                                                                            CompleatRequestResponse.class
////                                                                           );
//
//         return (myResponseString);
//      }
//
//                /*======================================================================*/
//                /* Rethrow any exception as a run-time exception                        */
//                /*======================================================================*/
//      catch (Throwable myThrowable) {
//         if (myThrowable instanceof RuntimeException) {
//            throw (RuntimeException) myThrowable;
//         }
//         throw new RuntimeException (null, myThrowable);
//      }
//   }

        /*==============================================================================*/
        /** Invokes the Bowling Scorer <i>register</i> web service.
         *
         *  @param  aRequestUrl     The root of the host URL.
         *  @param  aFrameCount     The number of frames in this custom game.
         *  @param  aPinCount       The number of pins set up in each framein this custom
         *                          game.
         *  @param  aRollsPerFrame  The number of rolls per frames in this custom game.
         *  @param  aPinString      [Optional] A string of numbers and marks (X, -, /) to be
         *                          returned one a time. [For testing purposes]
         *  @return :String -       The object representing the response content.
         */
        /*==============================================================================*/
   protected  String  invokeRegisterHttp (
                                          String  aRequestUrl,
                                          int     aFrameCount,
                                          int     aPinCount,
                                          int     aRollsPerFrame,
                                          String  aPinString
                                         )
   {
                /*======================================================================*/
                /* Invoke the web service using the Fluent HTTP client facade framework */
                /*======================================================================*/
      try {
         String myFullRequestUrl = String.format (
                                                  "%s?action=register&frames=%d&pins=%d&rolls=%d&test=%s",
                                                  aRequestUrl,
                                                  aFrameCount,
                                                  aPinCount,
                                                  aRollsPerFrame,
                                                  aPinString
                                                 );
         System.out.println ("[HTTP:REQUEST ] " + myFullRequestUrl);

                        /*==============================================================*/
                        /* Execute the HTTP request                                     */
                        /*==============================================================*/
         Request  myRequest        = Request.Get               (myFullRequestUrl)
                                            .useExpectContinue ()
                                            .version           (HttpVersion.HTTP_1_1);
         Response myResponse       = myRequest.execute         ();
         String   myResponseString = myResponse.returnContent  ()
                                               .asString       ();

         return (myResponseString);
      }

                /*======================================================================*/
                /* Rethrow any exception as a run-time exception                        */
                /*======================================================================*/
      catch (Throwable myThrowable) {
         if (myThrowable instanceof RuntimeException) {
            throw (RuntimeException) myThrowable;
         }
         throw new RuntimeException (null, myThrowable);
      }
   }

        /*==============================================================================*/
        /** Invokes the Bowling Scorer <i>check</i> web service.
         *
         *  @param  aRequestUrl  The root of the host URL.
         *  @param  aId          The identifier of te registered pin setter instance.
         *  @return :String -    The object representing the response content.
         */
        /*==============================================================================*/
   protected  String  invokeCheckHttp (
                                       String  aRequestUrl,
                                       String  aId
                                      )
   {
                /*======================================================================*/
                /* Invoke the web service using the Fluent HTTP client facade framework */
                /*======================================================================*/
      try {
         String myFullRequestUrl = String.format ("%s?action=check&id=%s", aRequestUrl, aId);
         System.out.println ("[HTTP:REQUEST ] " + myFullRequestUrl);

                        /*==============================================================*/
                        /* Execute the HTTP request                                     */
                        /*==============================================================*/
         Request  myRequest        = Request.Get               (myFullRequestUrl)
                                            .useExpectContinue ()
                                            .version           (HttpVersion.HTTP_1_1);
         Response myResponse       = myRequest.execute         ();
         String   myResponseString = myResponse.returnContent  ()
                                               .asString       ();

         return (myResponseString);
      }

                /*======================================================================*/
                /* Rethrow any exception as a run-time exception                        */
                /*======================================================================*/
      catch (Throwable myThrowable) {
         if (myThrowable instanceof RuntimeException) {
            throw (RuntimeException) myThrowable;
         }
         throw new RuntimeException (null, myThrowable);
      }
   }

        /*==============================================================================*/
        /* OPERATION:   invokeRollHttp                                                  */
        /**
         * Invokes the Bowling Scorer <i>roll</i> web service.
         *
         * @param  aRequestUrl  The root of the host URL.
         * @param  aId          The identifier of te registered pin setter instance.
         * @return :String -    The object representing the response content.
         */
        /*==============================================================================*/
   protected  String  invokeRollHttp (
                                      String  aRequestUrl,
                                      String  aId
                                     )
   {
                /*======================================================================*/
                /* Invoke the web service using the Fluent HTTP client facade framework */
                /*======================================================================*/
      try {
         String myFullRequestUrl = String.format ("%s?action=roll&id=%s", aRequestUrl, aId);
         System.out.println ("[HTTP:REQUEST ] " + myFullRequestUrl);

                        /*==============================================================*/
                        /* Execute the HTTP request                                     */
                        /*==============================================================*/
         Request  myRequest        = Request.Get               (myFullRequestUrl)
                                            .useExpectContinue ()
                                            .version           (HttpVersion.HTTP_1_1);
         Response myResponse       = myRequest.execute         ();
         String   myResponseString = myResponse.returnContent  ()
                                               .asString       ();

         return (myResponseString);
      }

                /*======================================================================*/
                /* Rethrow any exception as a run-time exception                        */
                /*======================================================================*/
      catch (Throwable myThrowable) {
         if (myThrowable instanceof RuntimeException) {
            throw (RuntimeException) myThrowable;
         }
         throw new RuntimeException (null, myThrowable);
      }
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
      String myInvocation   = (aArgs.length > 0) ? aArgs [0] : "H";  // H or J or HJ
      String myRequestUrl   = (aArgs.length > 1) ? aArgs [1] : "http://127.0.0.1:8080/bowlingscorer/";
//    String myRequestUrl   = (aArgs.length > 1) ? aArgs [1] : "http://pinsetter.herokuapp.com/bowlingscorer/";
      String myFramesString = (aArgs.length > 2) ? aArgs [2] : "10";
      String myPinsString   = (aArgs.length > 3) ? aArgs [3] : "10";
      String myRollsString  = (aArgs.length > 4) ? aArgs [4] : "2";
//    String myScoreString  = (aArgs.length > 5) ? aArgs [5] : "1-2-3-4-5-6-7-8-9/X12";
      String myScoreString  = (aArgs.length > 5) ? aArgs [5] : "";

      BowlingScorerWebClient myBowlingScorerWebClient = new BowlingScorerWebClient ();
      String                 myResponseString         = "";

                 /*=====================================================================*/
                 /* Run the tests as an HTTP web service                                */
                 /*=====================================================================*/
      if (myInvocation.contains ("H")) {
         myResponseString = myBowlingScorerWebClient.invokeRegisterHttp  (
                                                                      myRequestUrl,
                                                                      Integer.parseInt (myFramesString),
                                                                      Integer.parseInt (myPinsString),
                                                                      Integer.parseInt (myRollsString),
                                                                      myScoreString
                                                                    );
         System.out.println ("[HTTP:REGISTER] " + myResponseString);
         String myId = myResponseString;

         for (int i = 1; ; i++) {
            myResponseString = myBowlingScorerWebClient.invokeRollHttp (myRequestUrl, myId);

            System.out.format ("[HTTP:ROLL %3d] \"%s\"\n", i, myResponseString);

            if ((myResponseString == null) || (myResponseString.length () == 0)) {
               break;
            }
            else if ("null".equalsIgnoreCase (myResponseString)) {
               break;
            }
            else if (".".equals (myResponseString)) {
               break;
            }
         }

         myResponseString = myBowlingScorerWebClient.invokeCheckHttp (myRequestUrl, myId);
         System.out.println ("[HTTP:CHECK   ] " + myResponseString);
      }

//                 /*=====================================================================*/
//                 /* 2. Run the tests as a JSON web service                              */
//                 /*=====================================================================*/
//      if (myInvocation.contains ("J")) {
//         myResponseString = myBowlingScorerWebClient.invokeRegisterJson (
//                                                                      myRequestUrl,
//                                                                      Integer.parseInt (myFramesString),
//                                                                      Integer.parseInt (myPinsString),
//                                                                      Integer.parseInt (myRollsString),
//                                                                      myScoreString
//                                                                    );
//         System.out.println ();
//         System.out.println ("[JSON:REGISTER] " + myResponseString);
//         String myId = myResponseString;
//
//         for (int i = 1; ; i++) {
//            myResponseString = myBowlingScorerWebClient.invokeRollJson (myRequestUrl, myId);
//
//            System.out.format ("[JSON:ROLL %3d]  \"%s\"\n", i, myResponseString);
//
//            if ((myResponseString == null) || (myResponseString.length () == 0)) {
//               break;
//            }
//            else if ("null".equalsIgnoreCase (myResponseString)) {
//               break;
//            }
//            else if (".".equals (myResponseString)) {
//               break;
//            }
//         }
//      }
   }


}  // EOF  BowlingScorerWebClient.java
