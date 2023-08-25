package com.ejer.glassejer;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.sql.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    public String nombre, fechanac, signoz;
    private int codigo, cedula;
    static final String DB_URL = "jdbc:mysql://localhost/POOPRUEBA2";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    @Override
    public void init() {
        message = "Aqui se mostrara los datos de la base:";
        nombre="heyer tinoco";
        try(Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);){
            String SQL_Query_select = "SELECT * FROM est_prueba2 WHERE nombre like ?";
            try(PreparedStatement pstm = conn.prepareStatement(SQL_Query_select)){
                pstm.setString(1,nombre);
                try (ResultSet rs = pstm.executeQuery()){
                    if (rs.next()){
                        codigo= rs.getInt("codigo_est");
                        cedula = rs.getInt("cedula");
                        fechanac= rs.getString("fecha_nac");
                        signoz=rs.getString("signo");

                    } else {

                    }
                }
            }
        } catch (SQLException ex){
            ex.printStackTrace();
            nombre="Holi";
            fechanac="No se esta conectandoaaooo";
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>" + "Código de Estudiante:" + "</h1>");
        out.println("<p>"+codigo+"</p>");
        out.println("<h1>" + "Cedula estudiante:" + "</h1>");
        out.println("<p>"+cedula+"</p>");
        out.println("<h1>" + "Nombre:" + "</h1>");
        out.println("<p>"+nombre+"</p>");
        out.println("<h1>" + "Fecha de Nacimiento:" + "</h1>");
        out.println("<p>"+fechanac+"</p>");
        out.println("<h1>" + "Signo Zodiacal:" + "</h1>");
        out.println("<p>"+signoz+"</p>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}