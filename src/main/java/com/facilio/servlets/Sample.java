package com.facilio.servlets;
import java.sql.*;
public class Sample {
    public static void main(String args[]) throws Exception {
        String url="jdbc:mysql://localhost:3306/usercontact";
        String uname="root";
        String password="Jadseb@1329";
        int personid=2;
        String name ="Sebastin";
        int age1 = 17;
        int mobileno= 987654329;
        String mailid="jjadzi@gmail.com";
        String query = "INSERT INTO usercontact VALUES(?,?,?,?,?)";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url,uname,password);
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,personid);
        st.setString(2,name);
        st.setInt(3,age1);
        st.setInt(4,mobileno);
        st.setString(5,mailid);
        int rs = st.executeUpdate();
        System.out.println(rs);
        st.close();
        con.close();
    }
}
