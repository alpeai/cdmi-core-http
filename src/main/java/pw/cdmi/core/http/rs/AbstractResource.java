package pw.cdmi.core.http.rs;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import pw.cdmi.auth.Principal;
import pw.cdmi.core.http.Headers;


public class AbstractResource {

    @Context
    protected HttpHeaders headers;
    @Context
    protected UriInfo ui;
    
    private Principal principal;
    
    public AbstractResource(){
    }
    
    protected Principal getPrincipal(){
        if(principal == null){
            String authorization = headers.getRequestHeader(Headers.AUTHORIZATION)
                    .get(0);
            System.out.println(authorization);
            String accessKey = null;
            if (authorization != null) {
                int i = authorization.indexOf(" ");
                int j = authorization.indexOf(":");
                accessKey = authorization.substring(i + 1, j);
            } else {
//                throw new AWSClientException(ClientError.NoPermissions);
            }
            principal = new Principal(accessKey);
        }
        return principal;
    }
}
