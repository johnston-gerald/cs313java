package familyHistory;

import databaseConfig.DatabaseConfig;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "Family", urlPatterns = {"/Family"})
public class Family extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<PersonModel> personList = new ArrayList<>();
        
        try {
            DatabaseConfig family_db = new DatabaseConfig("family_history");
            Connection conn = family_db.Config();
            
            // Execute SQL query
            String sql = "SELECT person_id, firstname, lastname, birthday FROM person "
                       + "ORDER BY lastname, firstname, birthday";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                PersonModel person = new PersonModel();
                
                person.setPerson_id(Integer.toString(rs.getInt("person_id")));
                person.setFirstname(rs.getString("firstname"));
                person.setLastname(rs.getString("lastname"));
                person.setBirthday(rs.getDate("birthday").toString());
                
                personList.add(person);
            }
            
            conn.close();   //close connection
        } catch (SQLException ex) {
            Logger.getLogger(Family.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("personList", personList);
        request.getRequestDispatcher("/assignments/peopleView.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
