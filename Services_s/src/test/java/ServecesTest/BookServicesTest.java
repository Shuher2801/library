package ServecesTest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import it.by.library.dao.exception.DaoException;
import it.by.library.entity.Books;
import it.by.library.services.exception.ServiceException;
import it.by.library.services.impl.BookServices;

public class BookServicesTest {

/*	@Test
	public void findAll() {
		BookServices serv = BookServices.getInstance();
		List<Books> list = null;
		try {
			list = serv.findAll();
		} catch (ServiceException | DaoException e) {
			e.printStackTrace();
		}
		Long size = (long) list.size();
		assertNotNull(size);
	}

	@Test
	public void delete() {
		BookServices serv = BookServices.getInstance();
		Books bk = new Books();

		try {
			serv.create(bk);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		Long id = bk.getId();

		try {
			serv.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		try {
			assertNull(serv.findEntityById(id));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findEntityById() {
		BookServices serv = BookServices.getInstance();
		Books bk = new Books("2", "2", "2", "2", 2);

		try {
			serv.create(bk);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		Long id = bk.getId();
		Books book = null;
		try {
			book = serv.findEntityById(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		assertNotNull(book);
		assertEquals("2", book.getName_book());
		assertEquals("2", book.getAuthor());
		assertEquals("2", book.getPublication_date());
		assertEquals("2", book.getPublisher());
		//assertEquals(2, book.getCount());

		try {
			serv.delete(id);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
*/
}
