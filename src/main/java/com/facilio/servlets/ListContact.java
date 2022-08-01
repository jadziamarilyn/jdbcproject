package com.facilio.servlets;
import com.google.gson.Gson;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ListContact extends HttpServlet {
    private Gson gson;
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        String url = "jdbc:mysql://localhost:3306/usercontact";
        String uname = "root";
        String password = "Jadseb@1329";
        String query="SELECT * FROM usercontact";
        try {
            gson = new Gson();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, password);
            PreparedStatement st = con.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Integer personid = rs.getInt(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String mobileno = rs.getString(4);
                String mailid = rs.getString(5);
                Contact c = new Contact(personid,name,age,mobileno,mailid);
                String detailsjson = this.gson.toJson(c);
                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                out.println(detailsjson);
            }
            st.close();
            con.close();
        } catch (ClassNotFoundException | SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
