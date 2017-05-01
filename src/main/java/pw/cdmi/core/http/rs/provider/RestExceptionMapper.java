package pw.cdmi.core.http.rs.provider;

import java.util.Locale;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.axis.InternalException;

import net.sf.json.JSONObject;
import pw.cdmi.core.http.Headers;
import pw.cdmi.core.http.exception.AWSException;
import pw.cdmi.core.http.rs.RequestContext;


/**
 * <p>Map {@link InternalException} to an HTTP Status 500 response.</p>
 */
@Provider
public class RestExceptionMapper implements ExceptionMapper<AWSException>
{
	
	@Override
    public Response toResponse(AWSException e) {
		ResponseBuilder rb = Response.status(200);
        rb.type("application/json;charset=UTF-8");
        rb.header(Headers.REQUEST_ID, RequestContext.getExtParameter(Headers.REQUEST_ID));
        rb.header(Headers.RESOURCE_PATH, RequestContext.getExtParameter(Headers.RESOURCE_PATH));
    	
        rb.header("Access-Control-Allow-Origin", "*");
    	rb.header("Access-Control-Allow-Headers", "Authorization");
    	rb.header("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, DELETE");
        rb.header("Content-Type", "text/html;charset=utf-8");
        
        JSONObject json = e.getJsonText();
        
        rb.entity(json);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        RequestContext.destroy();
        return r;
    }


}