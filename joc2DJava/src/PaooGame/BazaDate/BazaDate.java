package PaooGame.BazaDate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BazaDate {
    private Connection connection = null;
    public static BazaDate BazaGame = null;
    private Statement statement = null;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime now = LocalDateTime.now();
    private BazaDate(){
        // Establish the connection to the database
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:PAOO.db");
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            this.CreateTables();
        }
        catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public static BazaDate getInstance(){
        if(BazaGame == null) {
            BazaGame =new BazaDate();
        }
        return BazaGame;
    }
    private void CreateTables(){
        if(connection != null){
            try{
                String playerTableSql = "CREATE TABLE SCORURI " +
                        "(SCORE     INT     NOT NULL, " +
                        "TIMPUL_JOCULUI  TEXT PRIMARY KEY   NOT NULL);";

                statement.executeUpdate(playerTableSql);
                connection.commit();
            }catch (Exception e){
                System.out.println( e.getMessage() );
            }
        }
    }
    public void insertNewScore(int score){
        try {
            String sql = "INSERT INTO SCORURI (SCORE ,TIMPUL_JOCULUI) " +
                    "VALUES ("+score+", '"+dtf.format(now).toString()+" ' );";
            statement.executeUpdate(sql);
            connection.commit();
        }
       catch(Exception e){
            System.out.println( e.getMessage() );
        }
    }
    public void Close(){
        if(connection != null){
            try {
                statement.close();
                connection.close();
                System.out.println("The connection has successfully been closed!");
            }
            catch(Exception e){
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
        }
    }

}
