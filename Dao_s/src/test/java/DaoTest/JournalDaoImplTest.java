package DaoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.exception.DaoException;
import it.by.library.dao.impl.BookDaoImpl;
import it.by.library.dao.impl.GenreDaoImpl;
import it.by.library.dao.impl.JournalDaoImpl;
import it.by.library.dao.impl.UserDaoImpl;
import it.by.library.entity.Books;
import it.by.library.entity.Journal;
import it.by.library.entity.Users;
@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class JournalDaoImplTest {
	
	@Autowired
	private BookDaoImpl bookDaoImpl;

	@Autowired
	private GenreDaoImpl genreDaoImpl;
	
	@Autowired
	private UserDaoImpl userDaoImpl;
	
	@Autowired
	private JournalDaoImpl journalDaoImpl;
	
	@Test
	public void getByIdTest() {
		Journal t = null;
		
		try {
			
			Journal journal = new Journal();
			journal.setDate_time_take(null);
			journalDaoImpl.add(journal);
			Long id = journal.getId();

			t = (Journal) journalDaoImpl.getById(id);
			assertNotNull(t);
		} catch (DaoException e) {
			e.printStackTrace();
		} 
	}

	@Test
	public void addTest() {

		Long id=null;
		Long idUser= null;
		Long idJournal= null;
		try {
			
			Books t = new Books("eee", "eee", "eee", "eee", 1);
			 bookDaoImpl.add(t);
			id = t.getId();

			Users user = new Users();
			user.setName("rr");
			user.setPassword("rr");
			userDaoImpl.add(user);
			idUser = user.getId();

			long curTime = System.currentTimeMillis();
			Date curDate = new Date(curTime);

			Journal journal = new Journal(t, user, curDate, curDate);
			journalDaoImpl.add(journal);
			idJournal = journal.getId();

			assertNotNull(journal);
			assertEquals("eee", journal.getBooks().getName_book());
			assertEquals("eee", journal.getBooks().getAuthor());
			assertEquals("eee", journal.getBooks().getPublication_date());
			assertEquals("eee", journal.getBooks().getPublisher());
			assertEquals(1, (long)journal.getBooks().getCount());
			assertEquals("rr", journal.getUsers().getName());
			assertEquals("rr", journal.getUsers().getPassword());

		} catch (DaoException e) {
			e.printStackTrace();
		} 
		try {
		userDaoImpl.remove(idUser);
		bookDaoImpl.remove(id);
			journalDaoImpl.remove(idJournal);
			assertNull(userDaoImpl.getById(idUser));
			assertNull(bookDaoImpl.getById(id));
			assertNull(journalDaoImpl.getById(idJournal));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}


	@Test
	public void removeByJournalTest() {
		Long id=null;
		Long idUser= null;
		Long idJournal= null;
		Journal journal = null;
		try {
			
			Books t = new Books("eee", "eee", "eee", "eee", 1);
			 bookDaoImpl.add(t);
			id = t.getId();

			Users user = new Users();
			user.setName("rr");
			user.setPassword("rr");
			userDaoImpl.add(user);
			idUser = user.getId();

			long curTime = System.currentTimeMillis();
			Date curDate = new Date(curTime);

		 journal = new Journal(t, user, curDate, curDate);
			journalDaoImpl.add(journal);
			idJournal = journal.getId();

			assertNotNull(journal);
			idJournal = journal.getId();
			journalDaoImpl.remove(journal);
		} catch (DaoException e) {
			e.printStackTrace();
		} 
		try {
			userDaoImpl.remove(idUser);
			bookDaoImpl.remove(id);	
			journalDaoImpl.remove(journal);
				assertNull(userDaoImpl.getById(idUser));
				assertNull(bookDaoImpl.getById(id));
				assertNull(journalDaoImpl.getById(idJournal));
			} catch (DaoException e) {
				e.printStackTrace();
			}
	}

/*	@Test
	public void updateTest() {
		HibernateUtil util = HibernateUtil.getInstance();
		Session session = util.getSession();
		Transaction transaction = null;
		BookDaoImpl dao = new BookDaoImpl(session);

		Books t = new Books("1", "1", "1", "1", 1);
		try {
			dao.add(t);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		Long id = t.getId();
		Books book2 = null;
		try {
			book2 = (Books) dao.getById(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		assertNotNull(book2);
		assertEquals("1", book2.getName_book());
		assertEquals("1", book2.getAuthor());
		assertEquals("1", book2.getPublication_date());
		assertEquals("1", book2.getPublisher());
		//assertEquals(1, book2.getCount());
		try {
			dao.remove(id);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void getByUserTest() {
		HibernateUtil util = HibernateUtil.getInstance();
		Session session = util.getSession();
		Transaction transaction = null;
		BookDaoImpl dao = new BookDaoImpl(session);
		UserDaoImpl daoUser = new UserDaoImpl(session);
		JournalDaoImpl daoJ = new JournalDaoImpl(session);

		try {
			transaction = session.beginTransaction();
			Users user = new Users();
			user.setName("2");
			user.setPassword("2");
			daoUser.add(user);

			long curTime = System.currentTimeMillis();
			Date curDate = new Date(curTime);

			Journal journal = new Journal(new Books(), user, curDate, null);
			daoJ.add(journal);
			assertNotNull(journal);
			List<Journal> list2 = daoJ.getByUser(user);
			Journal journal2 = list2.get(0);

			assertNotNull(journal2);
			assertEquals(null, journal2.getBooks().getName_book());
			assertEquals(null, journal2.getBooks().getAuthor());
			assertEquals(null, journal2.getBooks().getPublication_date());
			assertEquals(null, journal2.getBooks().getPublisher());
			//assertEquals(0, journal2.getBooks().getCount());
			assertEquals("2", journal2.getUsers().getName());
			assertEquals("2", journal2.getUsers().getPassword());
			assertEquals(null, journal2.getDate_time_return());

		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}
	}

	@Ignore
	@Test
	public void getByTimeTest() {
		HibernateUtil util = HibernateUtil.getInstance();
		Session session = util.getSession();
		Transaction transaction = null;
		BookDaoImpl dao = new BookDaoImpl(session);
		UserDaoImpl daoUser = new UserDaoImpl(session);
		JournalDaoImpl daoJ = new JournalDaoImpl(session);

		try {
			transaction = session.beginTransaction();

			long curTime = System.currentTimeMillis();
			Date curDate = new Date(curTime);

			Journal journal = new Journal(new Books(), new Users(), curDate, null);
			Journal journal2 = new Journal(new Books(), new Users("2", "3", true), curDate, curDate);
			daoJ.add(journal);
			daoJ.add(journal2);

			// List<Journal> JT2 = daoJ.getByTime();

			// assertEquals(JT.size(), JT2.size()+1);
			List<Journal> JT = daoJ.getByTime();
			assertNotNull(JT);

		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}
	}

	@Ignore
	@Test
	public void getAllTest() {
		HibernateUtil util = HibernateUtil.getInstance();
		Session session = util.getSession();
		Transaction transaction = null;
		BookDaoImpl dao = new BookDaoImpl(session);

		try {
			transaction = session.beginTransaction();
			List<Books> list = dao.getAll();
			int sizeList = list.size();

			Books t = new Books("1", "1", "1", "1", 1);
			dao.add(t);
			long id = t.getId();

			List<Books> list2 = null;
			list2 = dao.getAll();

			assertEquals(sizeList, list2.size() - 1);
		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}
	}

	@Ignore
	@Test
	public void updatrByIdTest() {
		try {
			Books t = new Books("1", "1", "1", "1", 1);
			dao.add(t);
			Long id = t.getId();

			Users user = new Users();
			user.setName("2");
			user.setPassword("2");
			daoUser.add(user);

			long curTime = System.currentTimeMillis();
			Date curDate = new Date(curTime);

			Journal journal = new Journal(t, user, curDate, null);
			daoJ.add(journal);
			assertNotNull(journal);
			Long idj = journal.getId();
			daoJ.updateById(idj);

			// Journal journal2 = (Journal) daoJ.getById(idj);
			List<Journal> list2 = daoJ.getByUser(user);
			Journal journal2 = list2.get(0);
			// Long id2 = journal2.getId();

			assertNotNull(journal2);
			assertEquals("1", journal2.getBooks().getName_book());
			assertEquals("1", journal2.getBooks().getAuthor());
			assertEquals("1", journal2.getBooks().getPublication_date());
			assertEquals("1", journal2.getBooks().getPublisher());
			//assertEquals(1, journal2.getBooks().getCount());
			assertEquals("1", journal2.getUsers().getName());
			assertEquals("1", journal2.getUsers().getPassword());
			assertEquals(curDate, journal2.getDate_time_return());

		} catch (DaoException e) {
			e.printStackTrace();
		} finally {
			transaction.rollback();
			session.close();
		}

	}
*/
}
