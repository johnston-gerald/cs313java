package library;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 

@Entity
public class Book implements Serializable { 

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int id;
       private String title;

       @ManyToOne
       private Author author; 

       public int getId() {
              return id;
       } 

       public void setId(int id) {
              this.id = id;
       }

       public String getTitle() {
              return title;
       } 

       public void setTitle(String title) {
              this.title = title;
       }

       public Author getAuthor() {
              return author;
       }

       public void setAuthor(Author author) {
              this.author = author;
       }
}