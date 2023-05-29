package coisas_e_coisas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Request {	
	private int id;
	public int customer_id;
	public String contract_date;
	
	public ArrayList<Service> services = new ArrayList<Service>();
	
	private static String table_name = "request";
	
	public Request(int customer_id, String contract_date) {
		this.customer_id = customer_id;
		this.contract_date = contract_date;
	}
	
	public int getId() {
		return this.id;
	}
	
	public static Request findOne(int id) {
	    Connection conn = Database.getConnection();
	    String sql = "SELECT * FROM " + Request.table_name + " WHERE id = ?";
	    
	    try {
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();
	        if (!rs.next()) {return null;}

	        Request req = new Request(rs.getInt("customer_id"), rs.getString("contract_date"));   
	        req.id = rs.getInt("id");
	        
	        sql = "SELECT * FROM Request_Service WHERE request = ?";
	        ps = conn.prepareStatement(sql);
	        ps.setInt(1, req.id);
	        rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	Service new_service = Service.findOne(rs.getInt(id));
	        	req.services.add(new_service);
	        }
	        
	        return req;
		
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return null;

	}
	
	public boolean save() {
	    Connection conn = Database.getConnection();

	    try {
	        PreparedStatement ps = null;

	        if (Request.findOne(this.id) != null) {
	        	String query = "UPDATE " + Request.table_name + " SET customer_id = ?, contract_date = ? WHERE id = ?;";
	            ps = conn.prepareStatement(query);

	            ps.setInt(1, this.customer_id);
	            ps.setString(2, this.contract_date);
	            ps.setInt(3, this.id);
	            ps.executeUpdate();
	            
	            // TODO: Verificar mudanças no services (ArrayList) e alterar se necessário (Service e Request_Service) //
	            

	        } else {
	            String query = "INSERT INTO " + Request.table_name + " (customer_id, contract_date) VALUES (?, ?);";
	            ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

	            ps.setInt(1, this.customer_id);
	            ps.setString(2, this.contract_date);
	            ps.executeUpdate();

	            ResultSet generatedKeys = ps.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                this.id = generatedKeys.getInt(1);
	            }
	        }

	        return true;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	
	public boolean delete() {
		Connection conn = Database.getConnection();
		String sql = "DELETE FROM " + Request.table_name + " WHERE id = ?;";
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, this.id);
			ps.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}