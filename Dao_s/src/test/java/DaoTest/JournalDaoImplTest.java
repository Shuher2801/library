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


}
