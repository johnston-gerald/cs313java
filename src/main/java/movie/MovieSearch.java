package movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "MovieSearch", urlPatterns = {"/MovieSearch"})
public class MovieSearch extends HttpServlet {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
          is.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
     
        
//        System.out.println(json.toString());
//        System.out.println(json.get("Title"));
        
//        request.setAttribute("title", json.get("Title"));
        request.getRequestDispatcher("/assignments/movieSearch.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        
//        JSONObject json = readJsonFromUrl("http://www.omdbapi.com/?s=" + URLEncoder.encode(title, "UTF-8"));
        
        if(!"".equals(title)){
            
            URL url = new URL("http://www.omdbapi.com/?s=" + URLEncoder.encode(title, "UTF-8"));

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = mapper.readValue(url, Map.class);

            List list = (List)map.get("Search");

    //        request.setAttribute("title", list.get(0));

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
//        request.setAttribute("title", json.get("Search"));
//        request.setAttribute("year", "Year: " + json.get("Year"));
        request.getRequestDispatcher("/assignments/movieSearch.jsp").forward(request, response);
    }
}
