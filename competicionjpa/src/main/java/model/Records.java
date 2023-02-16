package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="records")
public class Records {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String genre;
	@Column
	private String category;
	@Column
	private int benchpress;
	@Column
	private int deadlift;
	@Column
	private int squat;
	@Column
	private int total;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getBenchpress() {
		return benchpress;
	}
	public void setBenchpress(int benchpress) {
		this.benchpress = benchpress;
	}
	public int getDeadlift() {
		return deadlift;
	}
	public void setDeadlift(int deadlift) {
		this.deadlift = deadlift;
	}
	public int getSquat() {
		return squat;
	}
	public void setSquat(int squat) {
		this.squat = squat;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "Records [id=" + id + ", genre=" + genre + ", category=" + category + ", benchpress=" + benchpress
				+ ", deadlift=" + deadlift + ", squat=" + squat + ", total=" + total + "]";
	}
	
	

}
