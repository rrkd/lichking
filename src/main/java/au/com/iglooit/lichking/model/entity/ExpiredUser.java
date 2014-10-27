package au.com.iglooit.lichking.model.entity;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Basic;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 3:31 PM
 */
@Entity
public class ExpiredUser extends UnSearchEntity {
    private Date expiredDate;
    private UserState userState;
    @Basic
    private Key userKey;

    public ExpiredUser() {

    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Key getUserKey() {
        return userKey;
    }

    public void setUserKey(Key userKey) {
        this.userKey = userKey;
    }

    public UserState getUserState() {
        return userState;
    }

    public void setUserState(UserState userState) {
        this.userState = userState;
    }
}
