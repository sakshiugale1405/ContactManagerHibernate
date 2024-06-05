package com.jspiders.contactmanagerhibernet.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="Contact_Manager_hibernate")
public class ContactManager {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @Column(nullable = false,unique = false)
	    private String firstName;
	    @Column(nullable = false,unique = false)
	    private String lastName;
	    @Column(nullable = false,unique = true)
	    private String email;
	    @Column(nullable = false,unique = true)
	    private long phoneNumber;

}
