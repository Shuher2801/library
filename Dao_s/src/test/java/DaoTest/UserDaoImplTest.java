package DaoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.exception.DaoException;
import it.by.library.dao.impl.UserDaoImpl;
import it.by.library.entity.Users;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class UserDaoImplTest {

/*	@Test
	public void getByIdTest() {
		Users t = null;
		try {
			t = (Users) dao.getById(1L);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		session.close();
		assertNotNull(t);
	}

	@Test
	public void addTest() {
		
		Users t = new Users("1", "1", true);
		try {
			transaction = session.beginTransaction();
			dao.add(t);
			Long id = t.getId();

			Users us2 = (Users) dao.getById(id);

			assertNotNull(us2);
			assertEquals("1", us2.getName());
			assertEquals("1", us2.getPassword());

		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}

	}

	@Test
	public void removeTest() {
		
		Users t = new Users("1", "1", true);
		try {
			transaction = session.beginTransaction();
			dao.add(t);
			Long id = t.getId();

			dao.remove(id);

			assertNull(dao.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}

	}*/
}
