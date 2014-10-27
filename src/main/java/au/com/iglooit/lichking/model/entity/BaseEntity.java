package au.com.iglooit.lichking.model.entity;

import au.com.iglooit.lichking.utils.DateUtils;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * IGUser: nicholas.zhu
 * Date: 7/04/2014
 * Time: 10:03 PM
 */
@Entity
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Key key;

    private Date createdOn = DateUtils.getNow();

    private Boolean valid = Boolean.TRUE;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getKeyString() {
        return KeyFactory.keyToString(key);
    }

    public Long getKeyId() {
        return key.getId();
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "key: " + key + "createdOn: " + createdOn;
    }

    public abstract Document toFullTextDocument();
}
