/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.domain.service.todo;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import todo.domain.common.exception.BusinessException;
import todo.domain.common.exception.ResourceNotFoundException;
import todo.domain.model.Todo;

/**
 *
 * @author user
 */
@Stateless
public class TodoService {

    private static final long MAX_UNFINISHED_COUNT = 5;
    @PersistenceContext
    protected EntityManager entityManager;

    public List<Todo> findAll() {
        TypedQuery<Todo> q = entityManager.createNamedQuery("Todo.findAll", Todo.class);
        return q.getResultList();
    }

    public Todo findOne(Integer todoId) {
        //(1)
        Todo todo = entityManager.find(Todo.class, todoId);
        if (todo == null) {
            //(2)
            throw new ResourceNotFoundException("aaaaaaaaaaaa" + todoId);
        }
        return todo;
    }

    public Todo create(Todo todo) {
        //(3)
        TypedQuery<Long> q = entityManager.createQuery("SELECT COUNT(x) FROM Todo x WHERE x.finished = :finished", Long.class).setParameter("finished", false);
        long unfinishedCount = q.getSingleResult();
        if (unfinishedCount > MAX_UNFINISHED_COUNT) {
            //(4)
            throw new BusinessException("bbbbbbbbbbbbbb" + unfinishedCount);
        }
        todo.setFinished(false);
        todo.setCreatedAt(new Date());
        //(5)
        entityManager.persist(todo);
        return todo;
    }

    public Todo finish(Integer todoId) {
        Todo todo = findOne(todoId);
        if (todo.isFinished()) {
            throw new BusinessException("cccccccccccccc" + todoId);
        }
        todo.setFinished(true);
        //(6)
        entityManager.merge(todo);
        return todo;
    }

    public void delete(Integer todoId) {
        Todo todo = findOne(todoId);
        //(7)
        entityManager.remove(todo);
    }
}
