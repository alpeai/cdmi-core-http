package pw.cdmi.core.http.rs;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import pw.cdmi.core.http.Headers;


public class ResponseInterceptor extends AbstractPhaseInterceptor<Message>{

    public ResponseInterceptor(String phase) {
        super(phase);
    }

    public ResponseInterceptor(){
        this(Phase.PRE_STREAM);
    }
    
    @Override
    public void handleMessage(Message message) throws Fault {
        Map<String, List<String>> headers = org.apache.cxf.transport.http.Headers.getSetProtocolHeaders(message);
        
//        List<String> content_type = headers.get("Content-Type");
//        if(content_type != null && !content_type.contains("charset=utf-8")){
//            content_type.add("charset=utf-8");
//        }
        headers.put("Access-Control-Allow-Origin", Arrays.asList("*"));
        headers.put("Access-Control-Allow-Headers", Arrays.asList("Authorization"));
        headers.put("Access-Control-Allow-Methods", Arrays.asList("POST", "GET", "PUT", "PATCH", "DELETE"));
        /*headers.put("Content-Type", Arrays.asList("text/html;charset=utf-8"));*/
        headers.put(Headers.REQUEST_ID, Arrays.asList(RequestContext.getExtParameter(Headers.REQUEST_ID)));
        headers.put(Headers.RESOURCE_PATH, Arrays.asList(RequestContext.getExtParameter(Headers.RESOURCE_PATH)));
        RequestContext.destroy();
    }

}
