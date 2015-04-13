/**
 * TaggerServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package eu.clarin_pl.ws.wsg.tagger;

public class TaggerServiceLocator extends org.apache.axis.client.Service implements eu.clarin_pl.ws.wsg.tagger.TaggerService {

    public TaggerServiceLocator() {
    }


    public TaggerServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public TaggerServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for TaggerServicePort
    private java.lang.String TaggerServicePort_address = "http://ws.clarin-pl.eu/wsg/tagger/index.php";

    public java.lang.String getTaggerServicePortAddress() {
        return TaggerServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String TaggerServicePortWSDDServiceName = "TaggerServicePort";

    public java.lang.String getTaggerServicePortWSDDServiceName() {
        return TaggerServicePortWSDDServiceName;
    }

    public void setTaggerServicePortWSDDServiceName(java.lang.String name) {
        TaggerServicePortWSDDServiceName = name;
    }

    public eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType getTaggerServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(TaggerServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getTaggerServicePort(endpoint);
    }

    public eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType getTaggerServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            eu.clarin_pl.ws.wsg.tagger.TaggerServiceBindingStub _stub = new eu.clarin_pl.ws.wsg.tagger.TaggerServiceBindingStub(portAddress, this);
            _stub.setPortName(getTaggerServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setTaggerServicePortEndpointAddress(java.lang.String address) {
        TaggerServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                eu.clarin_pl.ws.wsg.tagger.TaggerServiceBindingStub _stub = new eu.clarin_pl.ws.wsg.tagger.TaggerServiceBindingStub(new java.net.URL(TaggerServicePort_address), this);
                _stub.setPortName(getTaggerServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("TaggerServicePort".equals(inputPortName)) {
            return getTaggerServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.clarin-pl.eu/wsg/tagger/", "TaggerService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.clarin-pl.eu/wsg/tagger/", "TaggerServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("TaggerServicePort".equals(portName)) {
            setTaggerServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
