package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import modelo.Recetas;

public class RecetasDAO implements DAO<Recetas>{

		private static final String SQL_INSERT = "INSERT INTO recetas (titulo,harina,cantidadHarina,liquidos,cantidadLevadura,cantidadAzucar,cantidadSal,preparacion)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		private static final String SQL_DELETE = "DELETE FROM recetas WHERE titulo= ?";
		private static final String SQL_UPDATE = "UPDATE recetas SET titulo = ?, harina = ?,"
				+ "cantidadHarina = ?, liquidos = ?, cantidadLevadura= ? ,cantidadAzucar= ?, cantidadSal= ?, preparacion= ? WHERE titulo = ?";
		private static final String SQL_READALL = "SELECT * FROM recetas";

		private DataSource origenDatos;

		public RecetasDAO(DataSource origenDatos) {
			
			this.origenDatos = origenDatos;
		}

	
	@Override
	public boolean create(Recetas c) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_INSERT);
			
			ps.setString(1, c.getTitulo());
			ps.setString(2, c.getHarina());
			ps.setDouble(3, c.getCantidadHarina());
			ps.setString(4, c.getLiquidos());
			ps.setDouble(5, c.getCantidadLevadura());
			ps.setDouble(6, c.getCantidadAzucar());
			ps.setDouble(7, c.getCantidadSal());
			ps.setString(8, c.getPreparacion());
			
			if (ps.executeUpdate() > 0){
				return true;
			}
				
		}  finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		return false;
	}

	@Override
	public boolean delete(Object key) {
		
		PreparedStatement ps=null;
		Connection con=null;
		
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_DELETE); 
															
			ps.setString(1, key.toString());
			if (ps.executeUpdate() > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	
	}

	@Override
	public boolean update(Recetas c) {
		
		PreparedStatement ps=null;
		Connection con=null;// con es mi conexion

		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_UPDATE);
		
			ps.setString(1, c.getTitulo());
			ps.setString(2, c.getHarina());
			ps.setDouble(3, c.getCantidadHarina());
			ps.setString(4, c.getLiquidos());
			ps.setDouble(5, c.getCantidadLevadura());
			ps.setDouble(6, c.getCantidadAzucar());
			ps.setDouble(7, c.getCantidadSal());
			ps.setString(8, c.getPreparacion());
			
			if (ps.executeUpdate() > 0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
		
	}

	@Override
	public List<Recetas> readAll() {
		
		Connection con=null;
		PreparedStatement ps;
		ResultSet rs;
		List<Recetas> recetas=new ArrayList<Recetas>();
		try {
			con=origenDatos.getConnection();
			ps = con.prepareStatement(SQL_READALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				recetas.add( new Recetas(rs.getString(1),rs.getString(2),rs.getDouble(3)
						,rs.getString(4),rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8)));
			}
			return recetas;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recetas;
		
	}

}
