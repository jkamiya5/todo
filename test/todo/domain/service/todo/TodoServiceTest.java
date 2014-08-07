/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.domain.service.todo;

import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import todo.domain.model.Todo;

/**
 *
 * @author user
 */
public class TodoServiceTest {

    private EJBContainer container;
    private Context context;

    public TodoServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        context = container.getContext();
    }

    @After
    public void tearDown() {
        container.close();
    }

    /**
     * Test of findAll method, of class TodoService.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        TodoService instance = (TodoService) context.lookup("java:global/classes/TodoService");
        List<Todo> result = instance.findAll();
        System.out.println(result);
        assertNotNull(result);
    }
}