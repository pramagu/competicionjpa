package model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name="atletas")
public class Atletas {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String fullName;
	@Column
	private int age;
	@Column
	private String genre;
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
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
		return "Atletas [id=" + id + ", fullName=" + fullName + ", age=" + age + ", genre=" + genre + ", benchpress="
				+ benchpress + ", deadlift=" + deadlift + ", squat=" + squat + ", total=" + total + "]";
	}
	
	public String toCompetiString() {
		
		return fullName + " --> Total= " + total;
		
	}
	
	
	
	public Atletas() {
		super();
	}
	
	public Atletas(int benchpress, int deadlift, int squat, int total) {
		super();
		this.benchpress = benchpress;
		this.deadlift = deadlift;
		this.squat = squat;
		this.total = total;
	}
	
	
	
	
	
	

}
