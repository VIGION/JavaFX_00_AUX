package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application.Parameters;

import javax.sql.rowset.JdbcRowSet;
import java.sql.ResultSetMetaData;


public class AlunoDataAcesser {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";			// Connector para o SGBD
	static final String DB_URL = "jdbc:mysql://localhost:8080/escola";	// url e nome da bd
	static final String DB_USER = "root";								// BD user name 
	static final String DB_PASS = "123";								// BD password
	
	private Connection connection ;					// Para Ligação completa à BD
	//private JdbcRowSet rowSet = null;				// Para acesso aos registos das tabelas
	
	
	
	// Construtor da classe ligação - monta a ligação completa à BD, a cada objeto criado
	public AlunoDataAcesser() {
		try {
			Class.forName(JDBC_DRIVER);											// ClassNotFoundException
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);	// SQLException
	   	}
	   	catch(SQLException e){							// Apanha Erro da connection ou DML
			Utils.alertBox("AlunoDataAcessor", "Construtor - Erro na ligação");
		}
		catch(ClassNotFoundException e){				// Apanha Erro da Class.forName()
			Utils.alertBox("AlunoDataAcessor", "Construtor - Erro no Driver");
		}
		catch(Exception e){								// Apanha todas as restantes Exceções
			Utils.alertBox("AlunoDataAcessor", "Construtor - Erro genérico na ligação");
			//e.printStackTrace();
		}
	}
	
	// Termina a ligação
	public void shutdown(){
		try{		
	        if (connection != null) {connection.close(); }
		}catch(SQLException e){							// Apanha Erro da connection ou DML
			Utils.alertBox("AlunoDataAcessor", "shutDown - Erro na ligação");
		}
		catch(Exception e){								// Apanha todas as restantes Exceções
			Utils.alertBox("AlunoDataAcessor", "shutDown - Erro genérico na ligação");
			//e.printStackTrace();
		}
    }


	// Extrai todos os alunos da tabela Alunos
	public List<Aluno> sqlGetAllRecords(){
        try (
            Statement stmnt = connection.createStatement();				// Prepara uma execução SQL à BD
            ResultSet rs = stmnt.executeQuery("select * from Aluno");	// Puxa toda a tabela para o rs
        ){
        	/* Depois de puxada toda a tabela para o objeto rs (ResultSet),
        	 * vamos extrair todos os registos, mas apenas as colunas que 
        	 * interessam para este teste: nº processo, nº aluno e nome.
        	 * Para isso vamos usar um ciclo onde, a cada iteração extrai
        	 * para um objeto aluno, estes atributos, adicionando-o a uma
        	 * lista de alunos (collection), já nossa conhecida. 
        	 */
            List<Aluno> listaAlunos = new ArrayList<>();	// Lista de Alunos
            while (rs.next()) {								// Ciclo: extração dos atributos
                int nProc 	= rs.getInt("nProc");			// nº processo
                int nAluno 	= rs.getInt("NAluno");			// nº aluno
                String nome = rs.getString("Nome");			// nome
                
                Aluno a = new Aluno(nProc, nAluno, nome);// cria um novo aluno com estes dados
                listaAlunos.add(a);
            }
            return listaAlunos ;		// a Lista é devolvida a quem a chamar
		}
        catch(SQLException e){							// Apanha Erro da connection ou DML
			Utils.alertBox("AlunoDataAcessor", "shutDown - Erro na ligação");
			return null;
		}
		catch(Exception e){								// Apanha todas as restantes Exceções
			Utils.alertBox("AlunoDataAcessor", "shutDown - Erro genérico na ligação");
			return null;
			//e.printStackTrace();
		}
    }
	   
	public void sqlInsert(int nProc, int nAluno, String nome){
        try{
            Statement stmnt = connection.createStatement();					// Prepara uma execução SQL à BD
            /*ResultSet rs = stmnt.executeQuery("INSERT INTO ALUNOS"
        				+" (nProc, NAluno, Nome)"
        				+" Values(1,1,'Teste')");*/
        	String sql 	= "INSERT INTO ALUNOS"
        				+" (nProc, NAluno, Nome)"
        				+" Values(1,1,'Teste')";
        		
            stmnt.executeQuery(sql); 
        }
        catch(SQLException e){							// Apanha Erro da connection ou DML
			Utils.alertBox("AlunoDataAcessor", "sqlInsert - Erro na ligação");
		}
		catch(Exception e){								// Apanha todas as restantes Exceções
			Utils.alertBox("AlunoDataAcessor", "sqlInsert - Erro genérico na ligação");
			//e.printStackTrace();
		}
    }	   
	
	
	/*
	// Insre um aluno na tabela
	public void create(Aluno a) {
		
		try {
			rowSet = new JdbcRowSetImpl(rs);
			rowSet.moveToInsertRow();
		    rowSet.updateInt("nProc", a.getnProc());
		    rowSet.updateInt("NAluno", a.getNTurma());
		    rowSet.updateString("Nome", a.getNome());
		   
		    rowSet.insertRow();
		    rowSet.moveToCurrentRow();
		} 
		catch (SQLException e) {
			try {
				rowSet.rollback();
		        a = null;
		        Utils.alertBox("AlunoDataAcessor", "create - Erro ao Criar Aluno na BD");
		     } 
		     catch (SQLException ex) {
		    	 Utils.alertBox("AlunoDataAcessor", "create - ERRO Rollback");
		     }
		}
	}
	
	   public void update(Aluno a) {
	      try {
	         rowSet.updateString("firstName", a.getFirstName());
	         rowSet.updateString("middleName", a.getMiddleName());
	         rowSet.updateString("lastName", a.getLastName());
	         rowSet.updateString("email", a.getEmail());
	         rowSet.updateString("phone", a.getPhone());
	         rowSet.updateRow();
	         rowSet.moveToCurrentRow();
	      } catch (SQLException ex) {
	         try {
	            rowSet.rollback();
	         } catch (SQLException e) {
	
	         }
	         ex.printStackTrace();
	      }
	   }
	
	   public void delete() {
	      try {
	         rowSet.moveToCurrentRow();
	         rowSet.deleteRow();
	      } catch (SQLException ex) {
	         try {
	            rowSet.rollback();
	         } catch (SQLException e) { }
	         ex.printStackTrace();
	      }
	
	   }
	
	   public Aluno moveFirst() {
	      Aluno a = new Aluno();
	      try {
	         rowSet.first();
	         a.setPersonId(rowSet.getInt("personId"));
	         a.setFirstName(rowSet.getString("firstName"));
	         a.setMiddleName(rowSet.getString("middleName"));
	         a.setLastName(rowSet.getString("lastName"));
	         a.setEmail(rowSet.getString("email"));
	         a.setPhone(rowSet.getString("phone"));
	
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return a;
	   }
	
	   public Aluno moveLast() {
	      Aluno p = new Aluno();
	      try {
	         rowSet.last();
	         a.setPersonId(rowSet.getInt("personId"));
	         a.setFirstName(rowSet.getString("firstName"));
	         a.setMiddleName(rowSet.getString("middleName"));
	         a.setLastName(rowSet.getString("lastName"));
	         a.setEmail(rowSet.getString("email"));
	         a.setPhone(rowSet.getString("phone"));
	
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return a;
	   }
	
	   public Aluno moveNext() {
	      Aluno p = new Aluno();
	      try {
	         if (rowSet.next() == false)
	            rowSet.previous();
	         a.setPersonId(rowSet.getInt("personId"));
	         a.setFirstName(rowSet.getString("firstName"));
	         a.setMiddleName(rowSet.getString("middleName"));
	         a.setLastName(rowSet.getString("lastName"));
	         a.setEmail(rowSet.getString("email"));
	         a.setPhone(rowSet.getString("phone"));
	
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return a;
	   }
	
	   public Aluno movePrevious() {
	      Aluno p = new Aluno();
	      try {
	         if (rowSet.previous() == false)
	            rowSet.next();
	         a.setPersonId(rowSet.getInt("personId"));
	         a.setFirstName(rowSet.getString("firstName"));
	         a.setMiddleName(rowSet.getString("middleName"));
	         a.setLastName(rowSet.getString("lastName"));
	         a.setEmail(rowSet.getString("email"));
	         a.setPhone(rowSet.getString("phone"));
	
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return a;
	   }
	
	   public Aluno getCurrent() {
	      Aluno p = new Aluno();
	      try {
	         rowSet.moveToCurrentRow();
	         a.setPersonId(rowSet.getInt("personId"));
	         a.setFirstName(rowSet.getString("firstName"));
	         a.setMiddleName(rowSet.getString("middleName"));
	         a.setLastName(rowSet.getString("lastName"));
	         a.setEmail(rowSet.getString("email"));
	         a.setPhone(rowSet.getString("phone"));
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }
	      return a;
	   }
	}	
	
	*/
}
