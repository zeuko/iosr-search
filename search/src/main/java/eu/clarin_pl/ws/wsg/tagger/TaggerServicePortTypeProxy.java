package eu.clarin_pl.ws.wsg.tagger;

public class TaggerServicePortTypeProxy implements eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType {
  private String _endpoint = null;
  private eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType taggerServicePortType = null;
  
  public TaggerServicePortTypeProxy() {
    _initTaggerServicePortTypeProxy();
  }
  
  public TaggerServicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initTaggerServicePortTypeProxy();
  }
  
  private void _initTaggerServicePortTypeProxy() {
    try {
      taggerServicePortType = (new eu.clarin_pl.ws.wsg.tagger.TaggerServiceLocator()).getTaggerServicePort();
      if (taggerServicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)taggerServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)taggerServicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (taggerServicePortType != null)
      ((javax.xml.rpc.Stub)taggerServicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public eu.clarin_pl.ws.wsg.tagger.TaggerServicePortType getTaggerServicePortType() {
    if (taggerServicePortType == null)
      _initTaggerServicePortTypeProxy();
    return taggerServicePortType;
  }
  
  public eu.clarin_pl.ws.wsg.tagger.SendRequestResponseType sendRequest(eu.clarin_pl.ws.wsg.tagger.SendRequestRequestType parameters) throws java.rmi.RemoteException{
    if (taggerServicePortType == null)
      _initTaggerServicePortTypeProxy();
    return taggerServicePortType.sendRequest(parameters);
  }
  
  public eu.clarin_pl.ws.wsg.tagger.CheckStatusResponseType checkStatus(eu.clarin_pl.ws.wsg.tagger.CheckStatusRequestType parameters) throws java.rmi.RemoteException{
    if (taggerServicePortType == null)
      _initTaggerServicePortTypeProxy();
    return taggerServicePortType.checkStatus(parameters);
  }
  
  public eu.clarin_pl.ws.wsg.tagger.GetResultResponseType getResult(eu.clarin_pl.ws.wsg.tagger.GetResultRequestType parameters) throws java.rmi.RemoteException{
    if (taggerServicePortType == null)
      _initTaggerServicePortTypeProxy();
    return taggerServicePortType.getResult(parameters);
  }
  
  public eu.clarin_pl.ws.wsg.tagger.GetResultJSONResponseType getResultJSON(eu.clarin_pl.ws.wsg.tagger.GetResultJSONRequestType parameters) throws java.rmi.RemoteException{
    if (taggerServicePortType == null)
      _initTaggerServicePortTypeProxy();
    return taggerServicePortType.getResultJSON(parameters);
  }
  
  
}