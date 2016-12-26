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
import javax.persistence.Lob;
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
@Table(name = "t_survey")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSurvey.findAll", query = "SELECT t FROM TSurvey t")
    , @NamedQuery(name = "TSurvey.findById", query = "SELECT t FROM TSurvey t WHERE t.id = :id")
    , @NamedQuery(name = "TSurvey.findByLabel", query = "SELECT t FROM TSurvey t WHERE t.label = :label")
    , @NamedQuery(name = "TSurvey.findByDate", query = "SELECT t FROM TSurvey t WHERE t.date = :date")})
public class TSurvey implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String label;
    @Size(max = 500)
    @Column(name = "short_desc")
    private String shortDesc;
    @Lob
    @Size(max = 65535)
    @Column(name = "long_desc")
    private String longDesc;
    @Basic(optional = false)
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tSurvey", fetch = FetchType.LAZY)
    private Set<TSurveyQuestion> tSurveyQuestionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "survey", fetch = FetchType.LAZY)
    private Set<TSurveyResponse> tSurveyResponseSet;
    @JoinColumn(name = "creator", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TUser creator;

    public TSurvey() {
    }

    public TSurvey(Integer id) {
        this.id = id;
    }

    public TSurvey(Integer id, String label, Date date, Date createdAt) {
        this.id = id;
        this.label = label;
        this.date = date;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @XmlTransient
    public Set<TSurveyQuestion> getTSurveyQuestionSet() {
        return tSurveyQuestionSet;
    }

    public void setTSurveyQuestionSet(Set<TSurveyQuestion> tSurveyQuestionSet) {
        this.tSurveyQuestionSet = tSurveyQuestionSet;
    }

    @XmlTransient
    public Set<TSurveyResponse> getTSurveyResponseSet() {
        return tSurveyResponseSet;
    }

    public void setTSurveyResponseSet(Set<TSurveyResponse> tSurveyResponseSet) {
        this.tSurveyResponseSet = tSurveyResponseSet;
    }

    public TUser getCreator() {
        return creator;
    }

    public void setCreator(TUser creator) {
        this.creator = creator;
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
        if (!(object instanceof TSurvey)) {
            return false;
        }
        TSurvey other = (TSurvey) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TSurvey[ id=" + id + " ]";
    }
    
}
