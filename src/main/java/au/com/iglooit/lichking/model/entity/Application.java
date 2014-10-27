package au.com.iglooit.lichking.model.entity;

import com.google.appengine.api.search.Document;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Application extends BaseEntity {
    private String applicationName;
    private String applicationURL;
    private String adminName;
    private String adminPassword;
    private String token;
    private String paypalEmail;
    private Integer maxUser = 100;
    private Long userCount = 0L;

    private ApplicationType applicationType;
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ApplicationOwnUser> applicationOwnUsers;

    public Application() {

    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationURL() {
        return applicationURL;
    }

    public void setApplicationURL(String applicationURL) {
        this.applicationURL = applicationURL;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

    public List<ApplicationOwnUser> getApplicationOwnUsers() {
        return applicationOwnUsers;
    }

    public void setApplicationOwnUsers(List<ApplicationOwnUser> applicationOwnUsers) {
        this.applicationOwnUsers = applicationOwnUsers;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public Integer getMaxUser() {
        return maxUser;
    }

    public void setMaxUser(Integer maxUser) {
        this.maxUser = maxUser;
    }

    public Long getUserCount() {
        return userCount;
    }

    public void setUserCount(Long userCount) {
        this.userCount = userCount;
    }

    @Override
    public Document toFullTextDocument() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Boolean allowableAddUser() {
        return userCount < maxUser;
    }
}
