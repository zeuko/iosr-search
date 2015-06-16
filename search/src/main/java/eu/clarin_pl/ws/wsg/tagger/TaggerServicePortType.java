/**
 * TaggerServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package eu.clarin_pl.ws.wsg.tagger;

public interface TaggerServicePortType extends java.rmi.Remote {

    /**
     * Send request to server
     */
    public eu.clarin_pl.ws.wsg.tagger.SendRequestResponseType sendRequest(eu.clarin_pl.ws.wsg.tagger.SendRequestRequestType parameters) throws java.rmi.RemoteException;

    /**
     * Ask server for status of request with specific token
     */
    public eu.clarin_pl.ws.wsg.tagger.CheckStatusResponseType checkStatus(eu.clarin_pl.ws.wsg.tagger.CheckStatusRequestType parameters) throws java.rmi.RemoteException;

    /**
     * Get result from server
     */
    public eu.clarin_pl.ws.wsg.tagger.GetResultResponseType getResult(eu.clarin_pl.ws.wsg.tagger.GetResultRequestType parameters) throws java.rmi.RemoteException;

    /**
     * Get result from server in JSON
     */
    public eu.clarin_pl.ws.wsg.tagger.GetResultJSONResponseType getResultJSON(eu.clarin_pl.ws.wsg.tagger.GetResultJSONRequestType parameters) throws java.rmi.RemoteException;
}
