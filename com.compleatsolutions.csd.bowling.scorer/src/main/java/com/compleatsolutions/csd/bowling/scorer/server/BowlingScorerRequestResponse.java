/*======================================================================================*/
/*         |        Copyright (c) 1999 - 2021  Compleat Solutions Inc.        |         */
/*         |                Confidential and Proprietary                      |         */
/*         +==================================================================+         */
/*                                                                                      */
/* MODULE:      GameScoreRequestResponse.java                                           */
/*                                                                                      */
/*======================================================================================*/

/*======================================================================================*/
/*                                  Package Definition                                  */
/*======================================================================================*/
package com.compleatsolutions.csd.bowling.scorer.server;

/*======================================================================================*/
/*                                       Imports                                        */
/*======================================================================================*/
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*======================================================================================*/
/*                           Class Definition / Implementation                          */
/*======================================================================================*/
/*======================================================================================*/
/* CLASS:       GameScoreRequestResponse                                                */
/**
 * Represents a generic request or response object.
 * <p>
 * In addition to the payload object itself, the class also provides an action property
 * and a caller-specifiable reference string.
 */

/* Date     Author       Project   Tracking Change Description
 * -------- ------------ --------- -------- ------------------
 * 20210417 ivanbiddles  CSD       n/a      Initial Creation
 */
/*======================================================================================*/
@XmlRootElement
public  class  BowlingScorerRequestResponse  implements  Serializable,
                                                     Externalizable
{

   /*==================================================================================*/
    /*===================================            ===================================*/
    /*=================================== Attributes ===================================*/
    /*===================================            ===================================*/
    /*==================================================================================*/

    /*==================================================================================*/
    /* Protected Attributes                                                             */
    /*==================================================================================*/

    /*==================================================================================*/
    /* Private Attributes                                                               */
    /*==================================================================================*/
                /** The requested web service action                                    */
   private  String  action;
               /** Returns the payload (the object that represents the actual request).
                * @return  :String - The requested web service action                   */
   public   String  getAction ()                { return (action);   }
               /** Sets the caller-specified reference string.
                 * @param   aAction   The caller-specified reference string             */
   public   void    setAction (String  aAction) { action = aAction;  }

                /** The payload (the object that represents the actual request)         */
   private  Object            payload;
               /** Returns the payload (the object that represents the actual request).
                * @return  :Object - The payload                                        */
   @XmlElement (name="payload", required=true, namespace="")
   public   Object  getPayload ()                 {  return (payload);   }
               /** Sets the payload (the object that represents the actual request).
                 * @param   aPayload The payload object                                 */
   public   void    setPayload (Object  aPayload) {  payload = aPayload; }

                /** The caller-specified reference string.                              */
   private  String            reference;
               /** Returns the caller-specified reference string.
                * @return  :Object - The caller-specified reference string              */
   public   String  getReference ()                   { return (reference);      }
               /** Sets the caller-specified reference string.
                 * @param   aReference The caller-specified reference string            */
   public   void    setReference (String  aReference) { reference = aReference;  }

                /** The unique serialization ID for this class.                         */
   private  static  final  long  serialVersionUID = -3428860285056075594L;

    /*==================================================================================*/
    /*=================================== Operations ===================================*/
    /*==================================================================================*/
        /*==============================================================================*/
        /* OPERATION:   toString                                                        */
        /**
         * Returns a string representation of this instance.
         *
         * <p>
         * @return      :String -
         *                 The string representation of this instance.
         */
        /*==============================================================================*/
   @SuppressWarnings ("nls")
   public  String  toString (
                            )
   {
      StringBuilder myStringBuilder = new StringBuilder ();

      myStringBuilder.append ("\nAction   : ")
                     .append (getAction    ())
                     .append ("\nPayload  : ")
                     .append (getPayload   ())
                     .append ("\nReference: ")
                     .append (getReference ());

      return (myStringBuilder.toString ());
   }

        /*==============================================================================*/
        /* OPERATION:   readExternal                                                    */
        /**
         *
         *
         * @see         java.io.Externalizable#readExternal(java.io.ObjectInput)
         * @param       aObjectInput
         * @throws      IOException
         * @throws      ClassNotFoundException
         */
        /*==============================================================================*/
// @Override
   public  void  readExternal (
                               ObjectInput  aObjectInput
                              )
                        throws IOException,
                               ClassNotFoundException
   {
      setAction    ((String) aObjectInput.readObject ());
      setPayload   (         aObjectInput.readObject ());
      setReference ((String) aObjectInput.readObject ());
   }

        /*==============================================================================*/
        /* OPERATION:   writeExternal                                                   */
        /**
         *
         *
         * @see         java.io.Externalizable#writeExternal(java.io.ObjectOutput)
         * @param       aObjectOutput
         * @throws      IOException
         */
        /*==============================================================================*/
// @Override
   public  void  writeExternal (
                                ObjectOutput  aObjectOutput
                               )
                         throws IOException
   {
      aObjectOutput.writeObject (getAction    ());
      aObjectOutput.writeObject (getPayload   ());
      aObjectOutput.writeObject (getReference ());
   }


}  // EOF  GameScoreRequestResponse.java
