package pw.cdmi.core.http.exception;

import pw.cdmi.exception.ErrorMessage;
import pw.cdmi.exception.ErrorReason;

public class AWSClientException extends AWSException {
	private static final long serialVersionUID = 6413756326321098091L;
    
    public AWSClientException(ErrorMessage message, ErrorReason reason) {
        super(message, reason);
    }
    
    public AWSClientException(ErrorMessage message, ErrorReason reason, String... Parameters) {
        super(message, reason);
    }
    
}
