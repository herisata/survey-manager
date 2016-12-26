/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Entity
@Table(name = "t_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TUser.findAll", query = "SELECT t FROM TUser t")
    , @NamedQuery(name = "TUser.findById", query = "SELECT t FROM TUser t WHERE t.id = :id")
    , @NamedQuery(name = "TUser.findByLabel", query = "SELECT t FROM TUser t WHERE t.label = :label")
    , @NamedQuery(name = "TUser.findByLogin", query = "SELECT t FROM TUser t WHERE t.login = :login")
    , @NamedQuery(name = "TUser.findByEnabled", query = "SELECT t FROM TUser t WHERE t.enabled = :enabled")})
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String label;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 64)
    private String password;
    @Basic(optional = false)
    @NotNull
    private short enabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @JoinColumn(name = "role", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUserRole role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAnswering", fetch = FetchType.LAZY)
    private Set<TSurveyResponse> tSurveyResponseSet;
    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY)
    private Set<TSurvey> tSurveySet;

    public TUser() {
    }

    public TUser(Integer id) {
        this.id = id;
    }

    public TUser(Integer id, String label, String login, String password, short enabled, Date createdAt) {
        this.id = id;
        this.label = label;
        this.login = login;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public TUserRole getRole() {
        return role;
    }

    public void setRole(TUserRole role) {
        this.role = role;
    }

    @XmlTransient
    public Set<TSurveyResponse> getTSurveyResponseSet() {
        return tSurveyResponseSet;
    }

    public void setTSurveyResponseSet(Set<TSurveyResponse> tSurveyResponseSet) {
        this.tSurveyResponseSet = tSurveyResponseSet;
    }

    @XmlTransient
    public Set<TSurvey> getTSurveySet() {
        return tSurveySet;
    }

    public void setTSurveySet(Set<TSurvey> tSurveySet) {
        this.tSurveySet = tSurveySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TUser)) {
            return false;
        }
        TUser other = (TUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TUser[ id=" + id + " ]";
    }
    
}
