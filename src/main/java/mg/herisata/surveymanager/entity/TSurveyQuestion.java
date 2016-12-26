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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Entity
@Table(name = "t_survey_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TSurveyQuestion.findAll", query = "SELECT t FROM TSurveyQuestion t")
    , @NamedQuery(name = "TSurveyQuestion.findByIdSurvey", query = "SELECT t FROM TSurveyQuestion t WHERE t.tSurveyQuestionPK.idSurvey = :idSurvey")
    , @NamedQuery(name = "TSurveyQuestion.findByIdQuestion", query = "SELECT t FROM TSurveyQuestion t WHERE t.tSurveyQuestionPK.idQuestion = :idQuestion")
    , @NamedQuery(name = "TSurveyQuestion.findByEnabled", query = "SELECT t FROM TSurveyQuestion t WHERE t.enabled = :enabled")})
public class TSurveyQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TSurveyQuestionPK tSurveyQuestionPK;
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
    @JoinColumn(name = "id_question", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TQuestion tQuestion;
    @JoinColumn(name = "id_survey", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TSurvey tSurvey;

    public TSurveyQuestion() {
    }

    public TSurveyQuestion(TSurveyQuestionPK tSurveyQuestionPK) {
        this.tSurveyQuestionPK = tSurveyQuestionPK;
    }

    public TSurveyQuestion(TSurveyQuestionPK tSurveyQuestionPK, short enabled, Date createdAt) {
        this.tSurveyQuestionPK = tSurveyQuestionPK;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

    public TSurveyQuestion(int idSurvey, int idQuestion) {
        this.tSurveyQuestionPK = new TSurveyQuestionPK(idSurvey, idQuestion);
    }

    public TSurveyQuestionPK getTSurveyQuestionPK() {
        return tSurveyQuestionPK;
    }

    public void setTSurveyQuestionPK(TSurveyQuestionPK tSurveyQuestionPK) {
        this.tSurveyQuestionPK = tSurveyQuestionPK;
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

    public TQuestion getTQuestion() {
        return tQuestion;
    }

    public void setTQuestion(TQuestion tQuestion) {
        this.tQuestion = tQuestion;
    }

    public TSurvey getTSurvey() {
        return tSurvey;
    }

    public void setTSurvey(TSurvey tSurvey) {
        this.tSurvey = tSurvey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tSurveyQuestionPK != null ? tSurveyQuestionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSurveyQuestion)) {
            return false;
        }
        TSurveyQuestion other = (TSurveyQuestion) object;
        if ((this.tSurveyQuestionPK == null && other.tSurveyQuestionPK != null) || (this.tSurveyQuestionPK != null && !this.tSurveyQuestionPK.equals(other.tSurveyQuestionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TSurveyQuestion[ tSurveyQuestionPK=" + tSurveyQuestionPK + " ]";
    }
    
}
