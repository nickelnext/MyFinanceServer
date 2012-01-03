package mainpackage;
/*
 * Classe dedicata alla gestione del Database.
 * Gestisce l'apertura e la chiusura della connessione col Database
 * Fornisce i metodi per l'esecuzione delle query sul Database
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class MyDatabase {
   private String dbName;       // Nome del Database a cui connettersi
   private String userName;   // Nome utente utilizzato per la connessione al Database
   private String userPwd;    // Password usata per la connessione al Database
   private String error;       // Raccoglie informazioni riguardo l'ultima eccezione sollevata
   private Connection db;       // La connessione col Database
   private boolean connected;    // Flag che indica se la connessione è attiva o meno

   static final String DRIVER = "com.mysql.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3309/";
   
   public MyDatabase(String dbName) { this(dbName, "", ""); }


   public MyDatabase(String dbName, String userName, String userPwd) {
	this.dbName = dbName;
	this.userName = userName;
	this.userPwd = userPwd;
	this.connected = false;
	this.error = "";
}

// Apre la connessione con il Database
   public boolean connect() {
      connected = false;
      try {

         // Carico il driver JDBC per la connessione con il database MySQL
         Class.forName(DRIVER);

         // Controllo che il nome del Database non sia nulla
         if (!dbName.equals("")) {

            // Controllo se il nome utente va usato o meno per la connessione
            if (userName.equals("")) {

               // La connessione non richiede nome utente e password
               db = DriverManager.getConnection(DB_URL + dbName);
            } else {

               // La connessione richiede nome utente, controllo se necessita anche della password
               if (userPwd.equals("")) {

                  // La connessione non necessita di password
                  db = DriverManager.getConnection(DB_URL + dbName + "?user=" + userName);
               } else {

                  // La connessione necessita della password
                  db = DriverManager.getConnection(DB_URL + dbName + "?user=" + userName + "&password=" + userPwd);
               }
            }

            // La connessione è avvenuta con successo
            connected = true;
         } else {
            System.out.println("Manca il nome del database!!");
            System.out.println("Scrivere il nome del database da utilizzare all'interno del file \"config.xml\"");
            System.exit(0);
         }
      } catch (Exception e) { error = e.getMessage(); }
      return connected;
   }

   // Esegue una query di selezione dati sul Database
   // query: una stringa che rappresenta un'istruzione SQL di tipo SELECT da eseguire
   // columns: il numero di columns di cui sarà composta la tupla del result
   // ritorna un Vector contenente tutte le tuple del result
   @SuppressWarnings({ "rawtypes", "unchecked" })
public Vector execQuery(String query) {
      Vector v = null;
      String [] record;
      int columns = 0;
      try {
         Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
         ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
         v = new Vector();
         ResultSetMetaData rsmd = rs.getMetaData();
         columns = rsmd.getColumnCount();

         while(rs.next()) {   // Creo il vettore result scorrendo tutto il ResultSet
            record = new String[columns];
            for (int i=0; i<columns; i++) record[i] = rs.getString(i+1);
            v.add( (String[]) record.clone() );
         }
         rs.close();     // Chiudo il ResultSet
         stmt.close();   // Chiudo lo Statement
      } catch (Exception e) { e.printStackTrace(); error = e.getMessage(); }

      return v;
   }

   public ArrayList<String[]> execQuery2(String query) {
	      ArrayList<String[]> v = null;
	      String [] record;
	      int columns = 0;
	      try {
	         Statement stmt = db.createStatement();     // Creo lo Statement per l'esecuzione della query
	         ResultSet rs = stmt.executeQuery(query);   // Ottengo il ResultSet dell'esecuzione della query
	         v = new ArrayList<>();
	         ResultSetMetaData rsmd = rs.getMetaData();
	         columns = rsmd.getColumnCount();

	         while(rs.next()) {   // Creo il vettore result scorrendo tutto il ResultSet
	            record = new String[columns];
	            for (int i=0; i<columns; i++) record[i] = rs.getString(i+1);
	            v.add( (String[]) record.clone() );
	         }
	         rs.close();     // Chiudo il ResultSet
	         stmt.close();   // Chiudo lo Statement
	      } catch (Exception e) { e.printStackTrace(); error = e.getMessage(); }

	      return v;
	   }

   
   // Esegue una query di aggiornamento sul Database
   // query: una stringa che rappresenta un'istuzione SQL di tipo UPDATE da eseguire
   // ritorna TRUE se l'esecuzione è adata a buon fine, FALSE se c'è stata un'eccezione
   public boolean updateQuery(String query) {
	   @SuppressWarnings("unused")
	int number = 0;
	   boolean result = false;
	   try {
		   Statement stmt = db.createStatement();
		   number = stmt.executeUpdate(query);
		   result = true;
		   stmt.close();
	   } catch (Exception e) {
		   e.printStackTrace();
		   error = e.getMessage();
		   result = false;
	   }
	   return result;
   }

   public boolean insertQuery(String query) {
	   @SuppressWarnings("unused")
	int number = 0;
	   boolean result = false;
	   try {
		   Statement stmt = db.createStatement();
		   number = stmt.executeUpdate(query);
		   result = true;
		   stmt.close();
	   } catch (Exception e) {
		   e.printStackTrace();
		   error = e.getMessage();
		   result = false;
	   }
	   return result;
   }

   // Chiude la connessione con il Database
   public void disconnect() {
      try {
         db.close();
         connected = false;
      } catch (Exception e) { e.printStackTrace(); }
   }

   public boolean isConnected() { return connected; }   // Ritorna TRUE se la connessione con il Database è attiva
   public String getError() { return error; }       // Ritorna il messaggio d'errore dell'ultima eccezione sollevata
}