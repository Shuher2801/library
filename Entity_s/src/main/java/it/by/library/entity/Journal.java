package it.by.library.entity;

import java.sql.Date;
import java.util.GregorianCalendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="journal")
@Proxy(lazy=false)
public class Journal extends Model {

	

	private static final long serialVersionUID = -4643690669074808483L;
	@ManyToOne
	@JoinColumn(name="book_id", referencedColumnName="id")
	private Books books;
	
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id")
	private Users users;
	
	@Column(name="date_time_take")
	private Date date_time_take;
	
	
	@Column(name="date_time_return")
	private Date date_time_return;
	
	
	public  Journal(){
		super();
	}
	public Journal(Books books, Users users, Date date_time_take, Date date_time_return) {
		super();
		this.books = books;
		this.users = users;
		this.date_time_take = date_time_take;
		this.date_time_return = date_time_return;
	}
	public  Journal(long id){
		super(id);
	}
	


	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	

	public Date getDate_time_take() {
		return date_time_take;
	}
	public void setDate_time_take(Date date_time_take) {
		this.date_time_take = date_time_take;
	}
	public Date getDate_time_return() {
		return date_time_return;
	}
	public void setDate_time_return(Date date_time_return) {
		this.date_time_return = date_time_return;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((books == null) ? 0 : books.hashCode());
		result = prime * result + ((date_time_return == null) ? 0 : date_time_return.hashCode());
		result = prime * result + ((date_time_take == null) ? 0 : date_time_take.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Journal other = (Journal) obj;
		if (books == null) {
			if (other.books != null)
				return false;
		} else if (!books.equals(other.books))
			return false;
		if (date_time_return == null) {
			if (other.date_time_return != null)
				return false;
		} else if (!date_time_return.equals(other.date_time_return))
			return false;
		if (date_time_take == null) {
			if (other.date_time_take != null)
				return false;
		} else if (!date_time_take.equals(other.date_time_take))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Journal [books=" + books + ", users=" + users + ", date_time_take=" + date_time_take
				+ ", date_time_return=" + date_time_return + "]";
	}
	
}
