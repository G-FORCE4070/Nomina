package co.edu.unbosque.model.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	private static final String URL = "jdbc:mysql://192.168.0.25:3306/proyectoBases";
	private static final String USUARIO = "proyectoBases";
	private static final String CONTRASENA = "bases2025";

	public static Connection conectar() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
	}

	public static void main(String[] args) {
		System.out.println("Intentando conectar a la base de datos...");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
			if (conexion != null) {
				System.out.println("✅ Conexión exitosa a la base de datos.");
				conexion.close();
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("❌ Error de conexión: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
