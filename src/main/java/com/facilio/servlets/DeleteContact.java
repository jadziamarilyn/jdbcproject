package com.facilio.servlets;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteContact extends HttpServlet {
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Reader reader = request.getReader();
        JSONParser parser = new JSONParser();
        try {
            JSONObject  jsonObject = (JSONObject) parser.parse(reader);
            String personid = (String) jsonObject.get("key");
            String url = "jdbc:mysql://localhost:3306/usercontact";
            String uname = "root";
            String password = "Jadseb@1329";
            String query="DELETE FROM usercontact WHERE personid=?";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, password);
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, Integer.parseInt(personid));
            st.executeUpdate();
            st.close();
            con.close();
        } catch (ClassNotFoundException |ParseException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
