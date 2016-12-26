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
@Table(name = "t_survey_response")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSurveyResponse.findAll", query = "SELECT t FROM TSurveyResponse t")
    , @NamedQuery(name = "TSurveyResponse.findById", query = "SELECT t FROM TSurveyResponse t WHERE t.id = :id")})
public class TSurveyResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Lob
    @Size(max = 65535)
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyResponse", fetch = FetchType.LAZY)
    private Set<TSurveyResponseData> tSurveyResponseDataSet;
    @JoinColumn(name = "survey", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TSurvey survey;
    @JoinColumn(name = "user_answering", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TUser userAnswering;

    public TSurveyResponse() {
    }

    public TSurveyResponse(Integer id) {
        this.id = id;
    }

    public TSurveyResponse(Integer id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
    public Set<TSurveyResponseData> getTSurveyResponseDataSet() {
        return tSurveyResponseDataSet;
    }

    public void setTSurveyResponseDataSet(Set<TSurveyResponseData> tSurveyResponseDataSet) {
        this.tSurveyResponseDataSet = tSurveyResponseDataSet;
    }

    public TSurvey getSurvey() {
        return survey;
    }

    public void setSurvey(TSurvey survey) {
        this.survey = survey;
    }

    public TUser getUserAnswering() {
        return userAnswering;
    }

    public void setUserAnswering(TUser userAnswering) {
        this.userAnswering = userAnswering;
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
        if (!(object instanceof TSurveyResponse)) {
            return false;
        }
        TSurveyResponse other = (TSurveyResponse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TSurveyResponse[ id=" + id + " ]";
    }
    
}
