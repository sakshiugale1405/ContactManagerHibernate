package com.jspiders.contactmanagerhibernet.dao;

import java.util.Scanner;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.jspiders.contactmanagerhibernet.dto.ContactManager;

public class ContactMain {

	
			  
			     private static EntityManagerFactory entityManagerFactory;
			     private static EntityManager entityManager;
			     private static EntityTransaction entityTransaction;
			     
			     
			     
			   private static void ContactInsert() {
			     Scanner scanner = new Scanner(System.in);
			       openConnection();
			       boolean addMore = true;
			       while (addMore) {
			         System.out.print("Enter the firstName:");
			         String firstName = scanner.nextLine();
			         System.out.print("Enter the lastName:");
			         String lastName = scanner.nextLine();
			         System.out.print("Enter the emailId:"); 
			         String emailId = scanner.nextLine();
			         System.out.print("Enter the mobileNumber:");
			         long mobileNumber = scanner.nextLong();
			          scanner.nextLine();
			         
			          ContactManager contactManager = new ContactManager();
			          contactManager.setFirstName(firstName);
			          contactManager.setLastName(lastName);
			          contactManager.setEmail(emailId);
			          contactManager.setPhoneNumber(mobileNumber);
			           entityTransaction.begin();
			         entityManager.persist(contactManager);
			         entityTransaction.commit();
			         System.out.println("The given contact is saved successfully!!");    
			         System.out.println("Do you want to add one more contact..?");
			         String choice = scanner.nextLine();
			         addMore = choice.equalsIgnoreCase("Yes");
			         
			       }    
			       scanner.close();
			       closeConnection();
			       
			     }
			     
			  private static void ContactUpdate() {
			       Scanner scanner = new Scanner(System.in);
			      openConnection();
			       System.out.print("Enter the Id of the contact to update:");
			       int id = scanner.nextInt();
			       scanner.nextLine();
			       System.out.print("Enter new first Name:");
			       String newFirstName = scanner.nextLine();
			       System.out.print("Enter new Last Name:");
			       String newLastName = scanner.nextLine();
			       System.out.print("Enter ne Email:");
			       String newEmail = scanner.nextLine();
			       System.out.println("Enter new Phone Number:");
			       long newPhonenumber = scanner.nextLong();
			       scanner.close();
			      
			         ContactManager contactManager = entityManager.find(ContactManager.class, id);
			         if (contactManager != null) {
			        contactManager.setFirstName(newFirstName);
			        contactManager.setLastName(newLastName);
			        contactManager.setEmail(newEmail);
			        contactManager.setPhoneNumber(newPhonenumber);
			        entityTransaction.begin();
			        entityManager.persist(contactManager);
			        entityTransaction.commit();
			        System.out.println("Contact updated successfully!");
			      }else {
			        System.out.println("Contact does not found!!!");
			      }
			       closeConnection();
			     }
			   private static void ContactView() {
			       Scanner scanner = new Scanner(System.in);
			       openConnection();
			       System.out.print("Enter the Id of the contact to view:");
			       int id = scanner.nextInt();
			       scanner.nextLine();
			       scanner.close();
			      ContactManager contactManager = entityManager.find(ContactManager.class, id);
			       if (contactManager != null) {
			      System.out.println(contactManager);
			    }else {
			      System.out.println("Contact does not exist");
			    }
			    closeConnection();
			    
			     }
			  private static void ContactDelete() {
			    Scanner scanner = new Scanner(System.in);
			    openConnection();
			    System.out.print("Enter the Id of the contact to delete:");
			    int id = scanner.nextInt();
			    scanner.close();
			    ContactManager contactManager = entityManager.find(ContactManager.class, id);
			    if (contactManager != null) {
			      entityTransaction.begin();
			      entityManager.remove(contactManager);

			entityTransaction.commit();
			      System.out.println("Contact deleted successfully");
			    }else {
			      System.out.println("Contact does not exist");
			    }
			    closeConnection();
			     }
			    
			     
			     
			  public static void main(String[] args) {
			      Scanner scanner = new Scanner(System.in);
			      System.out.println("WELCOME TO CONTACT MANAGER....!!!!");
			      System.out.println("1.Add Contacts\n"+"2.Update Contact\n"+"3.View Contact\n"+"4.Delete Contact\n"+"5.Exit");
			      System.out.print("Enter the choice:");
			      int choice = scanner.nextInt();
			      switch (choice) {
			    case 1:
			       ContactInsert();
			      break;
			        case 2:
			      ContactUpdate();
			      break;
			        case 3:
			          ContactView();
			        break;
			        case 4:
			          ContactDelete();
			      break;
			        case 5:System.out.println("Exit from the Contact_Manager Application");
			              System.exit(0);
			      break;
			    default:
			      System.out.println("Invalid choice");
			    }
			      scanner.close();
			      
			  }

			     
			  private static void openConnection() {
			    entityManagerFactory = Persistence.createEntityManagerFactory("contact_manager");
			    entityManager = entityManagerFactory.createEntityManager();
			    entityTransaction = entityManager.getTransaction();
			  }

			  private static void closeConnection() {
			    if (entityManagerFactory != null) {
			      entityManagerFactory.close();
			    }
			    if (entityManager != null) {
			      entityManager.close();
			    }
			    if ( entityTransaction != null) {
			     if (entityTransaction.isActive()) {
			      entityTransaction.rollback();
			    }
			    }
			  }
	 }
