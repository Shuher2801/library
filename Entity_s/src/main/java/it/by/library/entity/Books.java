package it.by.library.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="books")
public class Books extends Model {
	private static final long serialVersionUID = -247300301612287790L;
	
	@Pattern(regexp = "[a-z]{1,50}", message = "not correctly entered data")
	@Column(name="name_book",nullable = false)
	private String name_book;
	
	
	@Pattern(regexp = "[a-z]+$", message = "not correctly entered data")
	@Column(name="author",nullable = false)
	private String author;
	
	@Column(name="publication_date")
	private String publication_date;
	
	@Column(name="publisher")
	private String publisher;
	
	@DecimalMax(value = "99", message = "Digit must be a less than 99")
    @DecimalMin(value = "0", message = "Digit must be a greater than 0")
	@NotNull(message = "The above field must not be blank.")
	@Column(name="count", nullable = false)
	private Integer count;
	
	@ManyToOne
	@JoinColumn(name = "id_genre", referencedColumnName="id")
	private Genres genres;

	public Books() {
		super();
	}

	public Books( String name_book, String author, String publication_date, String publisher, int count) {
		
		this.name_book = name_book;
		this.author = author;
		this.publication_date = publication_date;
		this.publisher = publisher;
		this.count = count;

	}

	public String getName_book() {
		return name_book;
	}

	public void setName_book(String name_book) {
		this.name_book = name_book;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublication_date() {
		return publication_date;
	}

	public void setPublication_date(String publication_date) {
		this.publication_date = publication_date;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Genres getGenres() {
		return genres;
	}

	public void setGenres(Genres genres) {
		this.genres = genres;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((genres == null) ? 0 : genres.hashCode());
		result = prime * result + ((name_book == null) ? 0 : name_book.hashCode());
		result = prime * result + ((publication_date == null) ? 0 : publication_date.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
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
		Books other = (Books) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (genres == null) {
			if (other.genres != null)
				return false;
		} else if (!genres.equals(other.genres))
			return false;
		if (name_book == null) {
			if (other.name_book != null)
				return false;
		} else if (!name_book.equals(other.name_book))
			return false;
		if (publication_date == null) {
			if (other.publication_date != null)
				return false;
		} else if (!publication_date.equals(other.publication_date))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Books [name_book=" + name_book + ", author=" + author + ", publication_date=" + publication_date
				+ ", publisher=" + publisher + ", count=" + count + ", genres=" + genres + "]";
	}
	 
}
