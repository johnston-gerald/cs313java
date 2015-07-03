package familyHistory;

import databaseConfig.DatabaseConfig;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerrygj
 */
@WebServlet(name = "PersonDetails", urlPatterns = {"/PersonDetails"})
public class PersonDetails extends HttpServlet {
    
    private String firstname = "";
    private String lastname = "";
    private String birthday = "";
    private ArrayList<PersonModel> ancestorList = new ArrayList<>();
    private ArrayList<PersonModel> parentList = new ArrayList<>();
    private ArrayList<PersonModel> childrenList = new ArrayList<>();

    private void getParents (String person_id) {

        try {
            DatabaseConfig family_db = new DatabaseConfig("family_history");
            Connection conn = family_db.Config();
            
            // Execute SQL query
            String sql = "SELECT firstname, lastname, birthday FROM person "
                       + "WHERE person_id = (SELECT mother_person_id FROM person WHERE person_id = ?) "
                       + "OR person_id = (SELECT father_person_id FROM person WHERE person_id = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person_id);
            pstmt.setString(2, person_id);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                PersonModel parent = new PersonModel();

                parent.setFirstname(rs.getString("firstname"));
                parent.setLastname(rs.getString("lastname"));
                parent.setBirthday(rs.getDate("birthday").toString());

                parentList.add(parent);
            }
            
            conn.close();   //close connection
            
        } catch (SQLException ex) {
            Logger.getLogger(Family.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getAncestors (String person_id) {

        String[] parents = new String[2];
        
        try {
            DatabaseConfig family_db = new DatabaseConfig("family_history");
            Connection conn = family_db.Config();
            
            // Execute SQL query
            String sql = "SELECT person_id, firstname, lastname, birthday FROM person "
                       + "WHERE person_id = (SELECT mother_person_id FROM person WHERE person_id = ?) "
                       + "OR person_id = (SELECT father_person_id FROM person WHERE person_id = ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person_id);
            pstmt.setString(2, person_id);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            int i = 0;
            while(rs.next()){
                PersonModel parent = new PersonModel();

                parent.setFirstname(rs.getString("firstname"));
                parent.setLastname(rs.getString("lastname"));
                parent.setBirthday(rs.getDate("birthday").toString());

                ancestorList.add(parent);
                parents[i] = Integer.toString(rs.getInt("person_id"));
                i++;
            }
            
            conn.close();   //close connection
            
        } catch (SQLException ex) {
            Logger.getLogger(Family.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(parents[0] != null){
            getAncestors(parents[0]);
        }
        if(parents[1] != null){
            getAncestors(parents[1]);
        }
    }
    
    private void getChildren (String person_id) {

        try {
            DatabaseConfig family_db = new DatabaseConfig("family_history");
            Connection conn = family_db.Config();
            
            // Execute SQL query
            String sql = "SELECT firstname, lastname, birthday FROM person "
                       + "WHERE mother_person_id = ? OR father_person_id = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person_id);
            pstmt.setString(2, person_id);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                PersonModel child = new PersonModel();

                child.setFirstname(rs.getString("firstname"));
                child.setLastname(rs.getString("lastname"));
                child.setBirthday(rs.getDate("birthday").toString());

                childrenList.add(child);
            }
            
            conn.close();   //close connection
            
        } catch (SQLException ex) {
            Logger.getLogger(Family.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String person_id = request.getParameter("person_id");
        
        //find person
        
        try {
            DatabaseConfig family_db = new DatabaseConfig("family_history");
            Connection conn = family_db.Config();
            
            // Execute SQL query
            String sql = "SELECT firstname, lastname, birthday FROM person "
                    + "WHERE person_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, person_id);
            
            // Extract data from result set
            ResultSet rs = pstmt.executeQuery();
            
            // Extract data from result set
            while(rs.next()){
                firstname = rs.getString("firstname");
                lastname = rs.getString("lastname");
                birthday = rs.getDate("birthday").toString();
            }
            
            conn.close();   //close connection
        } catch (SQLException ex) {
            Logger.getLogger(Family.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getParents(person_id);      //find parents
        getAncestors(person_id);    //find ancestors
        getChildren(person_id);     //find children
        
        request.setAttribute("firstname", firstname);
        request.setAttribute("lastname", lastname);
        request.setAttribute("birthday", birthday);
        request.setAttribute("parentList", parentList);
        request.setAttribute("ancestorList", ancestorList);
        request.setAttribute("childrenList", childrenList);
        request.getRequestDispatcher("/assignments/personView.jsp").forward(request, response);
        
        //lists (otherwise, the results will be duplicated everytime we refresh the page)
        ancestorList.clear();
        parentList.clear();
        childrenList.clear();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}