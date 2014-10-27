package au.com.iglooit.lichking.exception;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 26/10/2014
 * Time: 8:41 PM
 */
public class PaymentException extends Exception {
    private PaymentError error;
    private String errorMessage;

    public PaymentException(PaymentError error) {
        this.error = error;
    }

    public PaymentException(PaymentError error, Throwable e) {
        super(e);
        this.error = error;
    }

    public PaymentException(PaymentError error, String errorMessage, Throwable e) {
        super(e);
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public PaymentError getError() {
        return error;
    }

    public void setError(PaymentError error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public enum PaymentError {
        IO_EX(1000, "Payment Failed w/ IOException"),
        NO_APICREDENTIAL_EX(1001, "Payment Failed w/ IOException"),
        INVALID_API_CREDENTIALS_EX(1002, "Invalid API Credentials"),
        MISSING_PARAMETER(1003, "Missing Parameter"),
        REQUEST_HTTP_ERROR(1004, "Request HTTP Error"),
        INVALID_RESPONSE(1005, "Invalid Response"),
        NO_PAY_RESPONSE(1006, "No Pay Response"),
        PAYMENT_FLOW_ERROR(1007, "Payment flow error");
        private String errorMessage;
        private Integer errorCode;

        private PaymentError(Integer errorCode, String errorMessage) {
            this.errorMessage = errorMessage;
            this.errorCode = errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public Integer getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(Integer errorCode) {
            this.errorCode = errorCode;
        }
    }
}
