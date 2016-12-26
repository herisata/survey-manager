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
@Table(name = "t_question_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TQuestionType.findAll", query = "SELECT t FROM TQuestionType t")
    , @NamedQuery(name = "TQuestionType.findById", query = "SELECT t FROM TQuestionType t WHERE t.id = :id")
    , @NamedQuery(name = "TQuestionType.findByLabel", query = "SELECT t FROM TQuestionType t WHERE t.label = :label")
    , @NamedQuery(name = "TQuestionType.findByHaveChoices", query = "SELECT t FROM TQuestionType t WHERE t.haveChoices = :haveChoices")
    , @NamedQuery(name = "TQuestionType.findByAllowMultiple", query = "SELECT t FROM TQuestionType t WHERE t.allowMultiple = :allowMultiple")})
public class TQuestionType implements Serializable {

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
    @Column(name = "have_choices")
    private boolean haveChoices;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allow_multiple")
    private boolean allowMultiple;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "type", fetch = FetchType.LAZY)
    private Set<TQuestion> tQuestionSet;

    public TQuestionType() {
    }

    public TQuestionType(Integer id) {
        this.id = id;
    }

    public TQuestionType(Integer id, String label, boolean haveChoices, boolean allowMultiple, Date createdAt) {
        this.id = id;
        this.label = label;
        this.haveChoices = haveChoices;
        this.allowMultiple = allowMultiple;
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

    public boolean getHaveChoices() {
        return haveChoices;
    }

    public void setHaveChoices(boolean haveChoices) {
        this.haveChoices = haveChoices;
    }

    public boolean getAllowMultiple() {
        return allowMultiple;
    }

    public void setAllowMultiple(boolean allowMultiple) {
        this.allowMultiple = allowMultiple;
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
    public Set<TQuestion> getTQuestionSet() {
        return tQuestionSet;
    }

    public void setTQuestionSet(Set<TQuestion> tQuestionSet) {
        this.tQuestionSet = tQuestionSet;
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
        if (!(object instanceof TQuestionType)) {
            return false;
        }
        TQuestionType other = (TQuestionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mg.herisata.surveymanager.entity.TQuestionType[ id=" + id + " ]";
    }
    
}
