package majors;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MajorTester", urlPatterns = {"/MajorTester"})
public class MajorTester extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("majorJpa");
        EntityManager em = emf.createEntityManager();

//        Major major = em.find(Major.class, 1);
        
        //create new major
//        em.getTransaction().begin(); 
//        Major newMajor = new Major();
//        newMajor.setName("Computer Engineering");
//        em.persist(newMajor);
//        em.getTransaction().commit();

        //create new student
//        em.getTransaction().begin();
//        Student newStudent = new Student();
//        newStudent.setName("Lance"); 
//        Major firstMajor = em.find(Major.class, 1);
//        newStudent.setMajor(firstMajor); 
//        em.persist(newStudent);
//        em.getTransaction().commit();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MajorTester</title>");            
            out.println("</head>");
            out.println("<body>");
//                out.println("Major: " + major.getName());
            
                //show majors
//                Query query = em.createQuery("SELECT m FROM Major m");
//                List<Major> majors = query.getResultList();
//                for (Major major : majors) {
//                    out.println("Major: " + major.getName() + "<br>");
//                }
            
//            Query studentQuery = em.createQuery("SELECT s FROM Student s");
//            List<Student> students = studentQuery.getResultList(); 
//
//            for (Student student : students) {
//                out.println("Student: " + student.getName() + " has major: " + student.getMajor().getName() + "<br>");
//            }
            
            Query query = em.createQuery("SELECT m FROM Major m");
            List<Major> majors = query.getResultList(); 

            for (Major major : majors) {
                out.println("Major: " + major.getName() + "<br>");

                for (Student student : major.getStudents()) {
                       out.println("&nbsp;&nbsp;&nbsp;&nbsp;Student: " + student.getName() + "<br>");
                }
                
                out.println("<br>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
