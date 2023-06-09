/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreException.java                                                 */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/
//import java.sql.BatchUpdateException;
//import java.sql.SQLException;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreException                                                      */
/**
 * The standard exception type that is the basis for the exception handling strategy.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417  ivanbiddles CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@SuppressWarnings ({"serial","nls"})
public  class  BowlingScorerException  extends  RuntimeException
{
    /*==================================================================================*/
    /*=================================== Attributes ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   GameScoreException                                              */
        /**
         * The minimum constructor for this class.
         *
         * @param  aMessage    The exception text.
         */
        /*==============================================================================*/
   public  BowlingScorerException  (
                                String     aMessage
                               )
   {
      super (aMessage);
   }

        /*==============================================================================*/
        /* OPERATION:   GameScoreException                                              */
        /**
         * The maximum constructor for this class.
         *
         * @param  aMessage    The exception text.
         * @param  aThrowable  The root cause exception.
         */
        /*==============================================================================*/
   public  BowlingScorerException  (
                                String     aMessage,
                                Throwable  aThrowable
                               )
   {
      super (aMessage, aThrowable);
   }

        /*==============================================================================*/
        /* OPERATION:   handle                                                          */
        /**
         * Provides a standard handling for any exception by performing the following
         * steps:
         * <p>
         * <ol>
         *   <li>If no exception is passed, the supplied message is logged and a new
         *       <code>GameScoreException</code> is returned.
         *   </li>
         *   <li>If the exception passed is already a <code>GameScoreException</code>
         *       instance, it is only logged if a non-blank message is passed (without
         *       a stack trace), and simply returned as-is.
         *   </li>
         *   <li>If a different exception is passed, the supplied message is logged (with
         *       a stack trace of the original exception) and the exception is wrapped in
         *       a new instance of <code>GameScoreException</code> which is then returned.
         *   </li>
         * </ol>
         * <p>
         * If the caller does not want to relay the exception, it can make use of this
         * method but simply not throw the returned instance.
         * <p>
         * <b>Note: </b>
         * If a null or empty string is supplied as the message for a non-logged
         * exception, the message is replaced with the result of the
         * <code><i>Throwable</i>.toString ()</code> method.
         *
         * @param  aMessage              The exception text (and the content of the logged message).
         * @param  aThrowable            The root cause exception.
         * @return :GameScoreException - A new (or existing) instance of this class.
         */
        /*==============================================================================*/
   public  static  final  BowlingScorerException  handle (
                                                      String             aMessage,
                                                      Throwable          aThrowable //,
//                                                    Object             aCallingObject
                                                     )
   {
                /*======================================================================*/
                /* Process according to the passed Throwable type                       */
                /*======================================================================*/
      String myMessage = null;

                        /*==============================================================*/
                        /* No exception passed - create one and log the message         */
                        /*==============================================================*/
      if (aThrowable == null) {
//       myMessage = addContext (aMessage, aCallingObject);
         System.err.println ("[EXCEPTION] " + myMessage);
         return (new BowlingScorerException (myMessage));
      }

                        /*==============================================================*/
                        /* Already a logged exception - log any new message only        */
                        /*==============================================================*/
      if (aThrowable instanceof BowlingScorerException) {
         if ((aMessage != null) && (aMessage.length () > 0)) {
//          myMessage = addContext (aMessage, aCallingObject);
            System.err.println ("[EXCEPTION] " + myMessage);
//          aLogLevel.log (aLogger, myMessage);
         }
         return ((BowlingScorerException) aThrowable);
      }

                /*======================================================================*/
                /* Process according to the passed Throwable type                       */
                /*======================================================================*/
//      if ((aMessage != null) && (aMessage.length () > 0)) {
//         myMessage = addContext (aMessage, aCallingObject);
//      }
//      else {
//         myMessage = addContext (aThrowable.toString (), aCallingObject);
//      }

                        /*==============================================================*/
                        /* Some exception stack contains a BatchUpdateException which   */
                        /* just tell you to call getNextException to find out what the  */
                        /* problem is, annoying! So this solves this problem.           */
                        /*==============================================================*/
//      Throwable myExceptionCause = extractBatchUpdateExceptionCause (aThrowable);
//      if (myExceptionCause == null) {
//         myExceptionCause = aThrowable;
//      }

                        /*==============================================================*/
                        /* SQLException - log the existing exception (with SQL codes)   */
                        /*                and create a GameScoreException from it       */
                        /*==============================================================*/
//      if (myExceptionCause instanceof SQLException) {
//         SQLException mySQLException = (SQLException) myExceptionCause;
//         int          myErrorCode    = mySQLException.getErrorCode ();
//         if (myErrorCode == 1) {                                      // Unique Constraint
//            myMessage = "[KEY NOT UNIQUE] " + myMessage;
//         }
//         aLogger.error (myMessage, myExceptionCause);
////       aLogLevel.log (aLogger, myMessage, myExceptionCause, myErrorCode);
//
//         return (new GameScoreException (myMessage, myExceptionCause, myErrorCode));
//      }

                        /*==============================================================*/
                        /* Not a logged exception - log the existing exception and      */
                        /*                          create a GameScoreException from it */
                        /*==============================================================*/
//      else {

                                /*======================================================*/
                                /* CsiRunTimeException - preserve the error code        */
                                /*======================================================*/
//         if (myExceptionCause instanceof GameScoreException) {
//            int myErrorCode = ((CompleatRuntimeException) myExceptionCause).getErrorCode ();
//            aLogger.error (myMessage, aThrowable);
////          aLogLevel.log      (aLogger, myMessage, myExceptionCause, myErrorCode);
//            return (new GameScoreException (myMessage, myExceptionCause, myErrorCode));
//         }

                                /*======================================================*/
                                /* Other exception                                      */
                                /*======================================================*/
//         else {
            System.err.println ("[EXCEPTION] " + myMessage);
            aThrowable.printStackTrace (System.err);
            return (new BowlingScorerException (myMessage, aThrowable));
//         }
//      }
   }


}  // EOF  GameScoreException.java
