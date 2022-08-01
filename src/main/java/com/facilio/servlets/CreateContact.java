package com.facilio.servlets;
import javax.servlet.http.HttpServlet;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class CreateContact extends HttpServlet {
    public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Reader reader = request.getReader();
        JSONParser parser = new JSONParser();
        String name;
        String age;
        String mobileno;
        String mailid;
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            name = (String) jsonObject.get("name");
            age = (String) jsonObject.get("age");
            mobileno = (String) jsonObject.get("mobileno");
            mailid = (String) jsonObject.get("mailid");
            String url = "jdbc:mysql://localhost:3306/usercontact";
            String uname = "root";
            String password = "Jadseb@1329";
            String query = "INSERT INTO usercontact(name,age,mobileno,mailid) VALUES(?,?,?,?)";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, uname, password);
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, name);
            st.setString(2, age);
            st.setString(3, mobileno);
            st.setString(4, mailid);
            st.executeUpdate();
            st.close();
            con.close();
        } catch (ParseException | SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JsonObject message = new JsonObject();
        message.addProperty("Status", "Created Successfully");
        out.println(message);
    }

}
