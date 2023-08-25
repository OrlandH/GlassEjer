package com.ejer.glassejer;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    static final String DB_URL = "jdbc:mysql://localhost/POOPRUEBA2";
    static final String USER = "root";
    static final String PASS = "root_bas3";

    @Override
    public void init() {
        message = "Aquí se mostrarán los datos de la base:";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM est_prueba2")) {

            out.println("<table border='1'>");
            out.println("<tr><th>Código</th><th>Cédula</th><th>Nombre</th><th>Fecha de Nacimiento</th><th>Signo Zodiacal</th></tr>");

            while (rs.next()) {
                int codigo = rs.getInt("codigo_est");
                int cedula = rs.getInt("cedula");
                String nombre = rs.getString("nombre");
                String fechanac = rs.getString("fecha_nac");
                String signoz = rs.getString("signo");

                out.println("<tr><td>" + codigo + "</td><td>" + cedula + "</td><td>" + nombre + "</td><td>" + fechanac + "</td><td>" + signoz + "</td></tr>");
            }

            out.println("</table>");

        } catch (SQLException ex) {
            ex.printStackTrace();
            out.println("Error al acceder a la base de datos.");
        }

        out.println("</body></html>");
    }

    public void destroy() {
    }
}