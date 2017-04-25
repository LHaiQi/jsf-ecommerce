package br.com.fiap.ecommerce.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static Connection connection = null;
	private static String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private static String user = "rm73612";
	private static String pass = "040397";
	
	public static Connection getConnection(){
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url, user, pass);
			} 
			catch (SQLException e) {
				System.out.println("Erro Conexão com Banco de Dados - Detalhes: " + e);
			}
			catch (ClassNotFoundException e) {
				System.out.println("Erro Carregamento Driver Conexão - Detalhes: " + e);
			}
		}
		
		return connection;		
	}
}
