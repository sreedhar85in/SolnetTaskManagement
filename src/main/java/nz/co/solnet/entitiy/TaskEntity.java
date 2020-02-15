package nz.co.solnet.entitiy;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

 
@Entity
public class TaskEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5256603351722268373L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	
	@Column(name = "title", nullable = false, columnDefinition = "varchar(256)")
	private String title;
	
	@Column(name = "description", columnDefinition = "varchar(1024)")
	private String description;
	
	
	private Date due_date;
	
	@Column(name = "status", columnDefinition = "varchar(10)")
	private String status;
	
	@NotNull
	private Date creation_date;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param long1 the id to set
	 */
	public void setId(Long long1) {
		this.id = long1;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the due_date
	 */
	public Date getDue_date() {
		return due_date;
	}

	/**
	 * @param due_date the due_date to set
	 */
	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the creation_date
	 */
	public Date getCreation_date() {
		return creation_date;
	}

	/**
	 * @param creation_date the creation_date to set
	 */
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TaskEntity [id=" + id + ", title=" + title + ", description=" + description + ", due_date=" + due_date
				+ ", status=" + status + ", creation_date=" + creation_date + "]";
	}
	
	


	

}