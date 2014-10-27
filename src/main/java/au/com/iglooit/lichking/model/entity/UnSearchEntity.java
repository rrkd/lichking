package au.com.iglooit.lichking.model.entity;

import com.google.appengine.api.search.Document;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

/**
 * Created with IntelliJ IDEA.
 * User: nicholas.zhu
 * Date: 24/10/2014
 * Time: 2:18 PM
 */
@Entity
@MappedSuperclass
public class UnSearchEntity extends BaseEntity {
    @Override
    public Document toFullTextDocument() {
        throw new UnsupportedClassVersionError("This class does not support full test search");
    }
}
