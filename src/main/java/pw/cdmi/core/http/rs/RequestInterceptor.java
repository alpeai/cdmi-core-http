package pw.cdmi.core.http.rs;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;

import pw.cdmi.core.http.Headers;
import pw.cdmi.utils.AlphaMath;

public class RequestInterceptor extends AbstractPhaseInterceptor<Message> {

	public RequestInterceptor(String phase) {
		super(phase);
	}

	public RequestInterceptor() {
		this(Phase.RECEIVE);
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		String requestid = AlphaMath.getRandomString(28);
		RequestContext.addExtParameter(Headers.REQUEST_ID, requestid);
		RequestContext.addExtParameter(Headers.RESOURCE_PATH, (String) message.get(Message.REQUEST_URI));
	}

}
