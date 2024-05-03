import model.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "/Login", urlPatterns = "/Login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");


    }

    public boolean checkUser(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "1111");
            try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE name = ? AND password = ?");) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet res = stmt.executeQuery();
                return res.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setPassword(pass);

        boolean user = checkUser(name, pass);
        if(user){
            response.sendRedirect("welcome.jsp");
        }else{
            response.sendRedirect("index.jsp");
        }
    }
}
