/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "t_user_role")
@XmlRootElement
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@NamedQueries({
    @NamedQuery(name = "TUserRole.findAll", query = "SELECT t FROM TUserRole t")
    , @NamedQuery(name = "TUserRole.findById", query = "SELECT t FROM TUserRole t WHERE t.id = :id")
    , @NamedQuery(name = "TUserRole.findByLabel", query = "SELECT t FROM TUserRole t WHERE t.label = :label")
    , @NamedQuery(name = "TUserRole.findByAllowSurveyCrud", query = "SELECT t FROM TUserRole t WHERE t.allowSurveyCrud = :allowSurveyCrud")
    , @NamedQuery(name = "TUserRole.findByAllowQuestionCrud", query = "SELECT t FROM TUserRole t WHERE t.allowQuestionCrud = :allowQuestionCrud")
    , @NamedQuery(name = "TUserRole.findByAllowUserCrud", query = "SELECT t FROM TUserRole t WHERE t.allowUserCrud = :allowUserCrud")})
public class TUserRole implements Serializable {

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
    @Column(name = "allow_survey_crud")
    private boolean allowSurveyCrud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_question_crud")
    private boolean allowQuestionCrud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_user_crud")
    private boolean allowUserCrud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
    private Set<TUser> tUserSet;

    public TUserRole() {
    }

    public TUserRole(Integer id) {
        this.id = id;
    }

    public TUserRole(Integer id, String label, boolean allowSurveyCrud, boolean allowQuestionCrud, boolean allowUserCrud, Date createdAt) {
        this.id = id;
        this.label = label;
        this.allowSurveyCrud = allowSurveyCrud;
        this.allowQuestionCrud = allowQuestionCrud;
        this.allowUserCrud = allowUserCrud;
        this.createdAt = createdAt;
    }

    @JsonIgnore
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

    public boolean getAllowSurveyCrud() {
        return allowSurveyCrud;
    }

    public void setAllowSurveyCrud(boolean allowSurveyCrud) {
        this.allowSurveyCrud = allowSurveyCrud;
    }

    public boolean getAllowQuestionCrud() {
        return allowQuestionCrud;
    }

    public void setAllowQuestionCrud(boolean allowQuestionCrud) {
        this.allowQuestionCrud = allowQuestionCrud;
    }

    public boolean getAllowUserCrud() {
        return allowUserCrud;
    }

    public void setAllowUserCrud(boolean allowUserCrud) {
        this.allowUserCrud = allowUserCrud;
    }

    @JsonIgnore
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @XmlTransient
    @JsonIgnore
    public Set<TUser> getTUserSet() {
        return tUserSet;
    }

    public void setTUserSet(Set<TUser> tUserSet) {
        this.tUserSet = tUserSet;
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
        if (!(object instanceof TUserRole)) {
            return false;
        }
        TUserRole other = (TUserRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TUserRole[ id=" + id + " ]";
    }
}
