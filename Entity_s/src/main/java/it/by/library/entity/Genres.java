package it.by.library.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "genres")
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Genres extends Model {
	private static final long serialVersionUID = -2175999949976243074L;

	@Column(name = "genre", unique = true, nullable = false)
	@Pattern(regexp = "[a-z]+$", message = "not correctly entered data")
	private String genre;
	/*
	 * @OneToMany(mappedBy="genres", fetch=FetchType.LAZY) private List<Books>
	 * books = new ArrayList<>();
	 */

	public Genres() {
		super();
	}

	public Genres(String genre) {
		super();
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();

		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genres other = (Genres) obj;

		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Genres [genre=" + genre + ", books=" + "]";
	}

}
