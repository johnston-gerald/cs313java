package majors;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Major implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(mappedBy = "major")
    private List<Student> students;

    private String name;
        
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
    
    public List<Student> getStudents() {
       return students;
    }

    public void setStudents(List<Student> students) {
           this.students = students;
    }
}