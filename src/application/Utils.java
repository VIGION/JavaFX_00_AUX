package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/* PARA IMPORTAR ESTA CLASSE: 
 * 	1 - No projeto x, registar este projeto:
 *  	Rato dt� sobre o nome do projeto x - Properties
 *  	JavaBuild Path -> Projects - Add 
 *  	(Mostra os projetos do workSpace: escolher este) - OK
 *	2 - No project x, escrever: import application.Utils; 
 *		Os m�todos destas clases j� estar�o dispon�veis.
 *		Nota: se o projeto tiver um package diferente, 
 *		ter� que ser referenciado: 	import package.Class  
 * --------------------------------------------------------------------------------------------------------
 * 	Lista de utilit�rios:
 * 	- AlertBox:				Controi msgBox. Recebe 2 strings para t�tulo e msg.
 * 	- ConfirmationBox:		devolve umboolean com a resposta de 2 bot�es
 * 							para emitir uma mensagem passada pela janela principal e obter uma resposta boolean
 * 	- confirmationCloseBox: Identico ao anterior, mas encerra a app.
 * 	- login: 				Permite preencher o UserName e password, simula a verifica��o na BD e devolve true se ok
 *  - isNumeric: 			valida��o de dados num TextField - verifica se � num�rico.
 *  - isText: 				valida��o dos dados de uma string - verifica se contem texto a-z, A-Z
 *  - isMail				valida��o de dados num TextField - verifica se � email.
 *  
 *  Notas 	Todos os m�todos s�o static. Pertencem � classe, n�o s�o instanci�veis: Utils.m�todo().
 *  		Vars static tem sempre o mesmo valor em todos os objetos
 */


public class Utils {
	
	// Atributos da classe - vars globais, vistos por todos os m�todos: Static - pertence � classe
	static boolean resposta;								// recebe a resposta de um qq utilit�rio
	
	
	
	
	/*------------------------------------------------------------------------------------------------------
	 * 	alertBox()	Este m�todo cria uma janela modal, para emitir uma mensagem passada pela janela
	 * 				principal e devolve uma boolean como resposta, obtida por um de 2 bot�es: sim ou n�o.
	 * 				Recebe 2 parametros: t�tulo para a janela e texto para a mensagem.
	 * 				Devolve um boolean como resposta para a janela principal, atrav�s da a��o de um de
	 * 				2 bot�es: Sim ou N�o.
	 */
	
	public static void alertBox(String title, String msg){		

		Stage janela = new Stage();							// Cria uma blank window
		janela.initModality(Modality.APPLICATION_MODAL);	// Define a janela MODAL.
		//janela.initModality(Modality.WINDOW_MODAL);		// Define a janela N�o MODAL.
		janela.setTitle(title);								// Como t�tulo, recebe a string do parametro
		janela.setMinWidth(300);							// Largura da janela
		
		Label mensagem = new Label(msg);					// Cria a label para mostrar a mensagem
		Button btnClose = new Button("Fechar");				// Cria bot�o para fechar janela
		btnClose.setOnAction(e -> janela.close());			// A��o fecha esta janela
		
		VBox layout = new VBox(10);							// Layout vertical com 10px entre c�lulas
		layout.getChildren().addAll(mensagem, btnClose);	// Adiciona Label e Button ao Layout
		layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro
		
		Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
		janela.setScene(scene);								// Associa a Scena � janela
		janela.showAndWait();								// Executa e prende o controlo at� ser fechada
	}
	
	
	
	
	
	
	
	/*-----------------------------------------------------------------------------------------------------
	 * 	confirmationBox() - Este m�todo cria uma janela modal, no m�todo display, para emitir uma
	 * 						mensagem passada pela janela principal e devolve uma boolean como
	 * 						resposta, obtida por um de 2 bot�es: sim ou n�o.
	 * 						Recebe 2 parametros: t�tulo para a janela e texto para a mensagem.
	 * 						Devolve um boolean como resposta para a janela principal, atrav�s da
	 * 						a��o de um de 2 bot�es:	Sim ou N�o.
	 */
	public static boolean confirmationBox(String title, String msg){
		
		Stage janela = new Stage();							// Cria uma blank window
		janela.initModality(Modality.APPLICATION_MODAL);	// Define a janela MODAL.
		janela.setTitle(title);								// Como t�tulo, recebe a string do parametro
		janela.setMinWidth(300);							// Largura m�nims da janela
		
		Label mensagem = new Label(msg);					// Cria a label para mostrar a mensagem
		
		Button btnYes = new Button("Sim");					// Cria bot�o para fechar janela
		btnYes.setOnAction(e -> {							// A��o: resposta true, fecha janela
			resposta = true;								
			janela.close();
		});
		
		Button btnNo = new Button("N�o");				// Cria bot�o para fechar janela
		btnNo.setOnAction(e -> {							// A��o: resposta false, fecha janela
			resposta = false;								
			janela.close();
		});			
		
		VBox layout = new VBox(10);							// Layout vertical com 10px entre c�lulas
		layout.getChildren().addAll(mensagem,btnYes,btnNo);	// Adiciona Label e Bot�es ao Layout
		layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro
		
		Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
		janela.setScene(scene);								// Associa a Scena � janela
		janela.showAndWait();								// Executa e prende o controlo at� ser fechada
		
		return resposta;									// Ao fechar, devolve a resposta para a Main.
	}

	
	
	
	
	
	
	/*-----------------------------------------------------------------------------------------------------
	 * 	confirmationCloseBox()  Este m�todo cria uma janela modal, para obter confirma��o de encerramento
	 * 							Devolve true se clicar no bot�o Sim, false se bot�o N�o
	*/
	public static boolean confirmationCloseBox(){
				
		try {
			Stage janela = new Stage();							// Cria uma blank window
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("ENCERRAMENTO");					// T�tulo da janela
			janela.setMinWidth(300);							// Largura m�nima da janela
			
			Label mensagem = new Label("Pretende Sair");		// Cria a label para mostrar a mensagem
			
			Button btnYes = new Button("Sim");					// Cria bot�o para fechar janela
			btnYes.setOnAction(e -> {							// A��o: resposta true, fecha janela
				resposta = true;								
				janela.close();
			});
			
			Button btnNo = new Button("N�o");					// Cria bot�o para fechar janela
			btnNo.setOnAction(e -> {							// A��o: resposta false, fecha janela
				resposta = false;								
				janela.close();
			});			
					
			HBox layoutBtn = new HBox(20);						// Layout vertical com 10px entre c�lulas
			layoutBtn.getChildren().addAll(btnYes,btnNo);		// Adiciona Label e Bot�es ao Layout
			layoutBtn.setAlignment(Pos.CENTER);
			
			VBox layout = new VBox(10);							// Layout vertical com 10px entre c�lulas
			layout.getChildren().addAll(mensagem,layoutBtn);	// Adiciona Label e Bot�es ao Layout
			layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro

			
			Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena � janela
			janela.showAndWait();								// Executa e prende o controlo at� ser fechada
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;										// Ao fechar, devolve a resposta para a Main.
	}
	
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------
	 * Login - 	Janela com GridPane para exemplo de Layout.
	 * 			Permite preencher o UserName,
	 * 			N�o testa em BD.
	 * 			Devolve TRUE apenas
	 * 			Static para n�o criar objetos 
	 */
	
	public static void login() {

		Stage janela = new Stage();							// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// T�tulo da janela
			janela.setMinWidth(300);							// Largura m�nima da janela

			// RootLayout
			GridPane layoutRoot = new GridPane();
			layoutRoot.setPadding(new Insets(10, 10, 10, 10)); 	// espa�os para a janela
			layoutRoot.setVgap(8);								// espa�o das colunas (pixeis)
			layoutRoot.setHgap(10);								// espa�o das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutRoot.add(labelUsaerName, 0, 0);				// Adicionar � c�lula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutRoot.add(txtUserName, 1, 0);					// Adicionado � c+elula col 1, linha 0
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutRoot.add(labelPassword, 0, 1);				// Adicionar � c�lula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutRoot.add(txtPassword, 1, 1);					// Adicionado � c+elula coluna 1, linha 1

			// Bot�o Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Ac��o do bot�o:
				
				alertBox("SIMULA��O BD",						// Simula��o de acesso � BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do m�todo. T�m que ser static atributs
			layoutRoot.add(btnLogin,  1, 2);					// Bot�o Adicionado � coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutRoot, 300, 200);		// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena � janela
			janela.showAndWait();								// Executa e prende o controlo at� ser fechada
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


		
	
	/*-------------------------------------------------------------------------------------------------
	 * isNumeric - Valida dados num�ricos em TextFields
	 * 				Recebe o TextField e uma string com o seu nome
	 * 				Se n�o for n�mero, int, float ou double devolve true de inteiro,
	 * 				SEN�O: gera exce��o: NumberFormatException e devolve Falso.
	*/
	
	static public boolean isNumeric(TextField input, String textFieldName) {
		
		boolean result= false;
		try {
			Double.parseDouble(input.getText());
			result = true;
		} 
		catch (NumberFormatException e) {
			alertBox("ERRO", textFieldName+", O valor n�o � num�rico");
			result = false;
		}
		catch (Exception e) {		// Apanha qualquer outra exce��o
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	
	


	
	/*------------------------------------------------------------------------------------------------
	 * isText	 - Valida dados num�ricos em TextFields
	 * 				Recebe o TextField e uma string com o nome do campo
	 * 				Se n�o for n�mero, int, float ou double devolve true de inteiro,
	 * 				SEN�O: gera exce��o: NumberFormatException e devolve Falso.
	*/
	
	
	static public boolean isText(TextField input, String textFieldName) {
		
		boolean result = false;
		if(input.getText().matches("[a-z]")){
			result = true;
		}
		//if(input.getText().isEmpty()){
		//	result = true;
		//}
		return result;
	}	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------
	 * 
	 * 
	 * */




}
