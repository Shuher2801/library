package DaoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import it.by.library.dao.exception.DaoException;
import it.by.library.dao.impl.GenreDaoImpl;
import it.by.library.entity.Genres;

@ContextConfiguration("/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional()
public class GenreDaoImplTest {

	@Autowired
	private GenreDaoImpl genreDaoImpl;

	public GenreDaoImplTest() {
	}

	@Test
	public void getByIdTest() {
		Genres t = null;
		try {
			t = (Genres) genreDaoImpl.getById(5L);
			// assertNotNull(t);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		assertNotNull(t);
	}

	@Test
	public void addTest() {

		Genres t = new Genres("aaa");
		Long id = null;
		try {

			genreDaoImpl.add(t);

			id = t.getId();
			Genres genre = (Genres) genreDaoImpl.getById(id);

			assertNotNull(genre);
			assertEquals("aaa", genre.getGenre());

		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			genreDaoImpl.remove(id);
			assertNull(genreDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void removeByGenreTest() {
		Genres t = new Genres("aaa");
		Long id = null;
		try {

			genreDaoImpl.add(t);

			id = t.getId();
			Genres genre = (Genres) genreDaoImpl.getById(id);

			assertNotNull(genre);
			assertEquals("aaa", genre.getGenre());

		} catch (DaoException e) {
			e.printStackTrace();
		}

		try {
			genreDaoImpl.remove(t);
		} catch (DaoException e) {
			e.printStackTrace();
		}

		try {
			assertNull(genreDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllTest() {
		Long id = null;
		try {
			List<Genres> list = genreDaoImpl.getAll();
			int sizeList = list.size();

			Genres t = new Genres("aaa");
			genreDaoImpl.add(t);
			id = t.getId();

			List<Genres> list2 = genreDaoImpl.getAll();

			assertEquals(sizeList, list2.size() - 1);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			genreDaoImpl.remove(id);
			assertNull(genreDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void getByGenreTest() {
		Genres t = null;
		Long id = null;
		try {

			t = new Genres("aaa");
			genreDaoImpl.add(t);
			id = t.getId();
			Genres genre2 = genreDaoImpl.getByGenre(t.getGenre());
			assertNotNull(genre2);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		try {
			genreDaoImpl.remove(id);
			assertNull(genreDaoImpl.getById(id));
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}
}
