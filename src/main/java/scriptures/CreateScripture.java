package scriptures;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import scriptures.model.FileScriptureHandler;
import scriptures.model.Scripture;

/**
* Servlet implementation class CreateScripture
*/
@WebServlet("/CreateScripture")
public class CreateScripture extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
    * @see HttpServlet#HttpServlet()  
    */
    public CreateScripture() {
        super();
        // TODO Auto-generated constructor stub
    }

        /**
        * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
        */
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String book = request.getParameter("txtBook");
            int chapter = Integer.parseInt(request.getParameter("txtChapter"));
            int verse = Integer.parseInt(request.getParameter("txtVerse"));

            Scripture newScripture = new Scripture(book, chapter, verse);

            FileScriptureHandler handler = new FileScriptureHandler("list.txt");
            handler.addScripture(newScripture);

            response.sendRedirect("ShowList");
        }
}