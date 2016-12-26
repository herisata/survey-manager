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
@Table(name = "t_question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TQuestion.findAll", query = "SELECT t FROM TQuestion t")
    , @NamedQuery(name = "TQuestion.findById", query = "SELECT t FROM TQuestion t WHERE t.id = :id")
    , @NamedQuery(name = "TQuestion.findByText", query = "SELECT t FROM TQuestion t WHERE t.text LIKE :text")})
public class TQuestion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 160)
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyQuestion", fetch = FetchType.LAZY)
    private Set<TSurveyResponseData> tSurveyResponseDataSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idQuestion", fetch = FetchType.LAZY)
    private Set<TQuestionChoice> tQuestionChoiceSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tQuestion", fetch = FetchType.LAZY)
    private Set<TSurveyQuestion> tSurveyQuestionSet;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TQuestionType type;

    public TQuestion() {
    }

    public TQuestion(Integer id) {
        this.id = id;
    }

    public TQuestion(Integer id, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @XmlTransient
    public Set<TQuestionChoice> getTQuestionChoiceSet() {
        return tQuestionChoiceSet;
    }

    public void setTQuestionChoiceSet(Set<TQuestionChoice> tQuestionChoiceSet) {
        this.tQuestionChoiceSet = tQuestionChoiceSet;
    }

    @XmlTransient
    public Set<TSurveyQuestion> getTSurveyQuestionSet() {
        return tSurveyQuestionSet;
    }

    public void setTSurveyQuestionSet(Set<TSurveyQuestion> tSurveyQuestionSet) {
        this.tSurveyQuestionSet = tSurveyQuestionSet;
    }

    public TQuestionType getType() {
        return type;
    }

    public void setType(TQuestionType type) {
        this.type = type;
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
        if (!(object instanceof TQuestion)) {
            return false;
        }
        TQuestion other = (TQuestion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TQuestion[ id=" + id + " ]";
    }
    
}
