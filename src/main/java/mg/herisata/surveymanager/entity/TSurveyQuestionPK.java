/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.herisata.surveymanager.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author herisata <hery@multimicro.fr>
 */
@Embeddable
public class TSurveyQuestionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_survey")
    private int idSurvey;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_question")
    private int idQuestion;

    public TSurveyQuestionPK() {
    }

    public TSurveyQuestionPK(int idSurvey, int idQuestion) {
        this.idSurvey = idSurvey;
        this.idQuestion = idQuestion;
    }

    public int getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(int idSurvey) {
        this.idSurvey = idSurvey;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSurvey;
        hash += (int) idQuestion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TSurveyQuestionPK)) {
            return false;
        }
        TSurveyQuestionPK other = (TSurveyQuestionPK) object;
        if (this.idSurvey != other.idSurvey) {
            return false;
        }
        if (this.idQuestion != other.idQuestion) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TSurveyQuestionPK[ idSurvey=" + idSurvey + ", idQuestion=" + idQuestion + " ]";
    }
    
}
