package au.com.iglooit.lichking.model.entity;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 1:41 PM
 */
@Entity
public class PaymentTransaction extends UnSearchEntity {
    private String payKey;
    private String buyerEmail;
    private String sellerEmail;
    private String ack;
    private String build;
    private String timestamp;
    private String correlationId;
    private String paymentDetailsJSON;
    private List<String> errorList = new ArrayList<>();
    private PaymentTransactionType paymentTransactionType;

    public PaymentTransaction() {

    }

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getSellerEmail() {
        return sellerEmail;
    }

    public void setSellerEmail(String sellerEmail) {
        this.sellerEmail = sellerEmail;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getPaymentDetailsJSON() {
        return paymentDetailsJSON;
    }

    public void setPaymentDetailsJSON(String paymentDetailsJSON) {
        this.paymentDetailsJSON = paymentDetailsJSON;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public PaymentTransactionType getPaymentTransactionType() {
        return paymentTransactionType;
    }

    public void setPaymentTransactionType(PaymentTransactionType paymentTransactionType) {
        this.paymentTransactionType = paymentTransactionType;
    }
}
