import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class Driver 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		try 
		{
			//connection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","Colloreto_database","123");
		
		    //create statement
			Statement myStmt= myConn.createStatement();
		
			// execute sql query
		    ResultSet myRs = myStmt.executeQuery("select * from player");
		    // query 2
		    String sql = "insert into player"+"playerNumber,username,highscore"+"values ('1','Gerrard','50')";
		    myStmt.executeUpdate(sql);
		    System.out.println("insert complete.");
		}
		    
		    catch (Exception exc) {
		    	exc.printStackTrace();
		    }
		
		    //proces the result set
		   

			/*while(myRs.next()) 
		    {
			System.out.println(myRs.getString("playerNumber") + ", "myRs.getString("username") + ", " + myRs.getString("highscore"));
		    
		    }*/
		
		}
 
	}
		


