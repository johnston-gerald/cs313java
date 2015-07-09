package majors;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne; 

@Entity
public class Student implements Serializable { 

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int id;
       private String name;

       @ManyToOne
       private Major major; 

       public int getId() {
              return id;
       } 

       public void setId(int id) {
              this.id = id;
       }

       public String getName() {
              return name;
       } 

       public void setName(String name) {
              this.name = name;
       }

       public Major getMajor() {
              return major;
       }

       public void setMajor(Major major) {
              this.major = major;
       }
}