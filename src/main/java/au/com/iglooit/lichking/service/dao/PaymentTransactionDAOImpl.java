package au.com.iglooit.lichking.service.dao;

import au.com.iglooit.lichking.model.entity.PaymentTransaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 25/10/2014
 * Time: 1:46 PM
 */
@Repository
@Transactional
public class PaymentTransactionDAOImpl extends BaseRepository<PaymentTransaction> implements PaymentTransactionDAO {
    public PaymentTransactionDAOImpl() {
        super(PaymentTransaction.class);
    }

    @Override
    public void createPaymentTransaction(PaymentTransaction paymentTransaction) {
        add(paymentTransaction);
    }
}
