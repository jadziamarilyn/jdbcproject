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

public class UpdateContact extends HttpServlet {
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Reader reader = request.getReader();
        JSONParser parser = new JSONParser();
        String name;
        String age;
        String mobileno;
        String mailid;
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            String personid = (String) jsonObject.get("key");
            name = (String) jsonObject.get("name");
            age = (String) jsonObject.get("age");
            mobileno = (String) jsonObject.get("mobileno");
            mailid = (String) jsonObject.get("mailid");
            String url = "jdbc:mysql://localhost:3306/usercontact";
            String uname = "root";
            String password = "Jadseb@1329";
            if  (!name.equals("")){
                String query="UPDATE usercontact SET name=? WHERE personid=?";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, password);
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,name);
                st.setInt(2, Integer.parseInt(personid));
                st.executeUpdate();
                st.close();
                con.close();
            }
            if  (!age.equals("")){
                String query="UPDATE usercontact SET age=? WHERE personid=?";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, password);
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,age);
                st.setInt(2, Integer.parseInt(personid));
                st.executeUpdate();
                st.close();
                con.close();
            }
            if  (!mobileno.equals("")){
                String query="UPDATE usercontact SET mobileno=? WHERE personid=?";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, password);
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,mobileno);
                st.setInt(2, Integer.parseInt(personid));
                st.executeUpdate();
                st.close();
                con.close();
            }
            if  (!mailid.equals("")){
                String query="UPDATE usercontact SET mailid=? WHERE personid=?";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, uname, password);
                PreparedStatement st = con.prepareStatement(query);
                st.setString(1,mailid);
                st.setInt(2, Integer.parseInt(personid));
                st.executeUpdate();
                st.close();
                con.close();
            }
        }
        catch (ParseException |SQLException |ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
