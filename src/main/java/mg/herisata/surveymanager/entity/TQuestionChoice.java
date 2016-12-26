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
@Table(name = "t_question_choice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TQuestionChoice.findAll", query = "SELECT t FROM TQuestionChoice t")
    , @NamedQuery(name = "TQuestionChoice.findById", query = "SELECT t FROM TQuestionChoice t WHERE t.id = :id")
    , @NamedQuery(name = "TQuestionChoice.findByValue", query = "SELECT t FROM TQuestionChoice t WHERE t.value = :value")})
public class TQuestionChoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    private String value;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(mappedBy = "choice", fetch = FetchType.LAZY)
    private Set<TSurveyResponseData> tSurveyResponseDataSet;
    @JoinColumn(name = "id_question", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TQuestion idQuestion;

    public TQuestionChoice() {
    }

    public TQuestionChoice(Integer id) {
        this.id = id;
    }

    public TQuestionChoice(Integer id, String value, Date createdAt) {
        this.id = id;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public TQuestion getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(TQuestion idQuestion) {
        this.idQuestion = idQuestion;
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
        if (!(object instanceof TQuestionChoice)) {
            return false;
        }
        TQuestionChoice other = (TQuestionChoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TQuestionChoice[ id=" + id + " ]";
    }
    
}
