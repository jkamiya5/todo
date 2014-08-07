/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.domain.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "TODO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t"),
    @NamedQuery(name = "Todo.findByTodoId", query = "SELECT t FROM Todo t WHERE t.todoId = :todoId"),
    @NamedQuery(name = "Todo.findByFinished", query = "SELECT t FROM Todo t WHERE t.finished = :finished"),
    @NamedQuery(name = "Todo.findByCreatedAt", query = "SELECT t FROM Todo t WHERE t.createdAt = :createdAt"),
    @NamedQuery(name = "Todo.findByVersion", query = "SELECT t FROM Todo t WHERE t.version = :version")})
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TODO_ID")
    private Integer todoId;
    @Lob
    @Column(name = "TODO_TITLE")
    private String todoTitle;
    @Size(max = 5)
    @Column(name = "FINISHED")
    private boolean finished;
    @Column(name = "CREATED_AT")
    @Temporal(TemporalType.TIME)
    private Date createdAt;
    @Column(name = "VERSION")
    @Version
    private Integer version;

    public Todo() {
    }

    public Todo(Integer todoId, String todoTitle, boolean finished, Date createdAt) {
        this.todoId = todoId;
        this.todoTitle = todoTitle;
        this.finished = finished;
        this.createdAt = createdAt;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (todoId != null ? todoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.todoId == null && other.todoId != null) || (this.todoId != null && !this.todoId.equals(other.todoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "todo.domain.model.Todo[ todoId=" + todoId + " ]";
    }
}
