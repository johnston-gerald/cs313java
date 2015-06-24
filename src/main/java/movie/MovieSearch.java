package movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author gerrygj
 */
@WebServlet(name = "MovieSearch", urlPatterns = {"/MovieSearch"})
public class MovieSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        
//        System.out.println(json.toString());
//        System.out.println(json.get("Title"));
        
//        request.setAttribute("title", json.get("Title"));
        request.getRequestDispatcher("/assignments/movieSearch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        
        if(!"".equals(title)){
            
            URL url = new URL("http://www.omdbapi.com/?s=" + URLEncoder.encode(title, "UTF-8"));

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(url, Map.class);

            List list = (List)map.get("Search");

            ArrayList<MovieModel> movieList = new ArrayList<>();
            for (Object item : list) {
                Map<String, Object> innerMap = (Map<String, Object>)item;

                MovieModel movie = new MovieModel();
                for (String key : innerMap.keySet()) {

                    switch (key) {
                        case "Title":
                            movie.setTitle(key + ": " + innerMap.get(key));
                            break;
                        case "Year": 
                            movie.setYear(key + ": " + innerMap.get(key));
                            break;
                        case "imdbID":
                            movie.setImdbID(key + ": " + innerMap.get(key));
                            break;
                        case "Type":        
                            movie.setType(key + ": " + innerMap.get(key));
                            break;
                    }
                }
                movieList.add(movie);
            }

            request.setAttribute("movieList", movieList);
        }

        request.getRequestDispatcher("/assignments/movieSearch.jsp").forward(request, response);
    }
}
