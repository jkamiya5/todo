/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.domain.service.todo;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import todo.domain.model.Todo;

/**
 *
 * @author user
 */
@Stateless
public class TodoService {

    @PersistenceContext
    protected EntityManager entityManager;

    public List<Todo> findAll() {
        TypedQuery<Todo> q = entityManager.createNamedQuery("Todo.findAll", Todo.class);
        return q.getResultList();
    }
}
