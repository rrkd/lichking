package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.PaymentTransaction;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 1:45 PM
 */
public interface PaymentTransactionDAO extends IEntityService<PaymentTransaction> {
    void createPaymentTransaction(PaymentTransaction paymentTransaction);
}
