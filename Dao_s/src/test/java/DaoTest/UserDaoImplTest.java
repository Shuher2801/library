package DaoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.exception.DaoException;
import it.by.library.dao.impl.BookDaoImpl;
import it.by.library.dao.impl.GenreDaoImpl;
import it.by.library.dao.impl.UserDaoImpl;
import it.by.library.entity.Users;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class UserDaoImplTest {

	
	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private GenreDaoImpl genreDaoImpl;
	
	@Test
	public void getByIdTest() {
		Users t = null;
		try {
			t = (Users) userDaoImpl.getById(1L);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		assertNotNull(t);
	}

	@Test
	public void addTest() {
		Long id=null;
		//Users t = new Users("154", "154", true);
		Users t = new Users();
		t.setName("154");
		t.setPassword("154");
		t.setValid(true);
		try {
			userDaoImpl.add(t);
			id = t.getId();

			Users us2 = (Users) userDaoImpl.getById(id);

			assertNotNull(us2);
			assertEquals("154", us2.getName());
			assertEquals("154", us2.getPassword());

		} catch (DaoException e) {
			e.printStackTrace();
		} 
		try {
			userDaoImpl.remove(id);
			assertNull(userDaoImpl.getById(id));

		} catch (DaoException e) {
			e.printStackTrace();
		}
		
	}


}
