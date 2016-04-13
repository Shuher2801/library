package DaoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.dao.exception.DaoException;
import it.by.library.dao.impl.BookDaoImpl;
import it.by.library.dao.impl.GenreDaoImpl;
import it.by.library.entity.Books;
import it.by.library.entity.Genres;


@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class BookDaoImplTest {

	@Autowired
	private BookDaoImpl bookDaoImpl;

	@Autowired
	private GenreDaoImpl genreDaoImpl;

	@Test
	public void getByIdTest() {
		Books t = null;

		try {
			t = (Books) bookDaoImpl.getById(1L);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		assertNotNull(t);
	}

	@Test
	public void addTest() {
		long id = 0;
		Books t = new Books("eee", "eee", "12", "eee", 1);
		try {

			bookDaoImpl.add(t);
			id = t.getId();

			Books book2 = (Books) bookDaoImpl.getById(id);

			assertNotNull(book2);
			assertEquals("eee", book2.getName_book());
			assertEquals("eee", book2.getAuthor());
			assertEquals("12", book2.getPublication_date());
			assertEquals("eee", book2.getPublisher());
			assertEquals(1, (long) book2.getCount());

		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			bookDaoImpl.remove(id);
			assertNull(bookDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void removeByBookTest() {

		long id = 0;
		Books t = new Books("eee", "eee", "12", "eee", 1);
		try {

			bookDaoImpl.add(t);
			id = t.getId();

			Books book2 = (Books) bookDaoImpl.getById(id);

		} catch (DaoException e) {
			e.printStackTrace();
		}

		try {
			bookDaoImpl.remove(t);
			assertNull(bookDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void updateTest() {
		Long idBook = null;
		Books t = null;
		t = new Books("eee", "eee", "12", "eee", 1);
		try {
			bookDaoImpl.add(t);
		} catch (DaoException e1) {
			e1.printStackTrace();
		}
		idBook = t.getId();
		Books book2 = null;
		try {
			book2 = (Books) bookDaoImpl.getById(idBook);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		assertNotNull(book2);
		assertEquals("eee", book2.getName_book());
		assertEquals("eee", book2.getAuthor());
		assertEquals("12", book2.getPublication_date());
		assertEquals("eee", book2.getPublisher());
	    assertEquals(1,(long) book2.getCount());
		try {
			bookDaoImpl.remove(idBook);
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getByGenreTest() {
		Long id = null;
		Books t = null;
		Long idBook = null;
		Genres genre = new Genres("aaa");
		try {

			genreDaoImpl.add(genre);
			id = genre.getId();

			t = new Books("eee", "eee", "12", "eee", 1);
			t.setGenres(genre);
			bookDaoImpl.add(t);
			idBook = t.getId();

			List<Books> list = null;
			list = bookDaoImpl.getByGenre(id);

			Books book2 = list.get(0);
			assertEquals(1, list.size());
			assertEquals("eee", book2.getName_book());
			assertEquals("eee", book2.getAuthor());
			assertEquals("12", book2.getPublication_date());
			assertEquals("eee", book2.getPublisher());
			assertEquals(1, (long) book2.getCount());

		} catch (DaoException e) {
			e.printStackTrace();
		}

		try {
			genreDaoImpl.remove(id);
			assertNull(genreDaoImpl.getById(id));
			bookDaoImpl.remove(idBook);
			assertNull(bookDaoImpl.getById(idBook));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllTest() {

		Books t = null;
		Long idBook = null;
		try {

			List<Books> list = bookDaoImpl.getAll();
			int sizeList = list.size();

			t = new Books("eee", "eee", "12", "eee", 1);
			bookDaoImpl.add(t);
			idBook = t.getId();

			List<Books> list2 = null;
			list2 = bookDaoImpl.getAll();

			assertEquals(sizeList, list2.size() - 1);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			bookDaoImpl.remove(idBook);
			assertNull(bookDaoImpl.getById(idBook));
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

}