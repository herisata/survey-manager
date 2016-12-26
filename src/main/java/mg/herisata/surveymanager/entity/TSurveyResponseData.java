/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Entity
@Table(name = "t_survey_response_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSurveyResponseData.findAll", query = "SELECT t FROM TSurveyResponseData t")
    , @NamedQuery(name = "TSurveyResponseData.findById", query = "SELECT t FROM TSurveyResponseData t WHERE t.id = :id")})
public class TSurveyResponseData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Size(max = 100)
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @JoinColumn(name = "choice", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TQuestionChoice choice;
    @JoinColumn(name = "survey_question", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TQuestion surveyQuestion;
    @JoinColumn(name = "survey_response", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TSurveyResponse surveyResponse;

    public TSurveyResponseData() {
    }

    public TSurveyResponseData(Integer id) {
        this.id = id;
    }

    public TSurveyResponseData(Integer id, Date createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    public TQuestionChoice getChoice() {
        return choice;
    }

    public void setChoice(TQuestionChoice choice) {
        this.choice = choice;
    }

    public TQuestion getSurveyQuestion() {
        return surveyQuestion;
    }

    public void setSurveyQuestion(TQuestion surveyQuestion) {
        this.surveyQuestion = surveyQuestion;
    }

    public TSurveyResponse getSurveyResponse() {
        return surveyResponse;
    }

    public void setSurveyResponse(TSurveyResponse surveyResponse) {
        this.surveyResponse = surveyResponse;
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
        if (!(object instanceof TSurveyResponseData)) {
            return false;
        }
        TSurveyResponseData other = (TSurveyResponseData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TSurveyResponseData[ id=" + id + " ]";
    }
    
}
