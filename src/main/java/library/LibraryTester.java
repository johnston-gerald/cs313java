package library;

import databaseConfig.JPAConfig;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LibraryTester", urlPatterns = {"/LibraryTester"})
public class LibraryTester extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        JPAConfig library_db = new JPAConfig("library");
        Map<String, String> properties = library_db.Config();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("libraryJPA", properties);
        EntityManager em = emf.createEntityManager();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //create new author
//            em.getTransaction().begin(); 
//            Author newAuthor = new Author();
//            newAuthor.setName("Orson Scott Card");
//            em.persist(newAuthor);
//            em.getTransaction().commit();

            //create new book
//            em.getTransaction().begin();
//            Book newBook = new Book();
//            newBook.setTitle("Ender's Game"); 
//            Author thirdAuthor = em.find(Author.class, 3);
//            newBook.setAuthor(thirdAuthor); 
//            em.persist(newBook);
//            em.getTransaction().commit();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Library Tester</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Library Tester</h1>");

            Query query = em.createQuery("SELECT a FROM Author a");
            List<Author> authors = query.getResultList(); 

            for (Author author : authors) {
                out.println("Author: " + author.getName() + "<br>");

                for (Book book : author.getBooks()) {
                    out.println("&nbsp;&nbsp;&nbsp;&nbsp;Book: " + book.getTitle() + "<br>");
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
        
    }
}
