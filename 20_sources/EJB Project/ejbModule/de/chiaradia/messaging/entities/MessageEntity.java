package de.chiaradia.messaging.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: MessageEntity
 *
 */
@Entity

public class MessageEntity implements Serializable {

	   
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String message;
	private static final long serialVersionUID = 1L;

	public MessageEntity() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
