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
 *  	Rato dtº sobre o nome do projeto x - Properties
 *  	JavaBuild Path -> Projects - Add 
 *  	(Mostra os projetos do workSpace: escolher este) - OK
 *	2 - No project x, escrever: import application.Utils; 
 *		Os métodos destas clases já estarão disponíveis.
 *		Nota: se o projeto tiver um package diferente, 
 *		terá que ser referenciado: 	import package.Class  
 * --------------------------------------------------------------------------------------------------------
 * 	Lista de utilitários:
 * 	- AlertBox:				Controi msgBox. Recebe 2 strings para título e msg.
 * 	- ConfirmationBox:		devolve umboolean com a resposta de 2 botões
 * 							para emitir uma mensagem passada pela janela principal e obter uma resposta boolean
 * 	- confirmationCloseBox: Identico ao anterior, mas encerra a app.
 * 	- login: 				Permite preencher o UserName e password, simula a verificação na BD e devolve true se ok
 *  - isNumeric: 			validação de dados num TextField - verifica se é numérico.
 *  - isText: 				validação dos dados de uma string - verifica se contem texto a-z, A-Z
 *  - isMail				validação de dados num TextField - verifica se é email.
 *  
 *  Notas 	Todos os métodos são static. Pertencem à classe, não são instanciáveis: Utils.método().
 *  		Vars static tem sempre o mesmo valor em todos os objetos
 */


public class Utils {
	
	// Atributos da classe - vars globais, vistos por todos os métodos: Static - pertence à classe
	static boolean resposta;								// recebe a resposta de um qq utilitário
	
	
	
	
	/*------------------------------------------------------------------------------------------------------
	 * 	alertBox()	Este método cria uma janela modal, para emitir uma mensagem passada pela janela
	 * 				principal e devolve uma boolean como resposta, obtida por um de 2 botões: sim ou não.
	 * 				Recebe 2 parametros: título para a janela e texto para a mensagem.
	 * 				Devolve um boolean como resposta para a janela principal, através da ação de um de
	 * 				2 botões: Sim ou Não.
	 */
	
	public static void alertBox(String title, String msg){		

		Stage janela = new Stage();							// Cria uma blank window
		janela.initModality(Modality.APPLICATION_MODAL);	// Define a janela MODAL.
		//janela.initModality(Modality.WINDOW_MODAL);		// Define a janela Não MODAL.
		janela.setTitle(title);								// Como título, recebe a string do parametro
		janela.setMinWidth(300);							// Largura da janela
		
		Label mensagem = new Label(msg);					// Cria a label para mostrar a mensagem
		Button btnClose = new Button("Fechar");				// Cria botão para fechar janela
		btnClose.setOnAction(e -> janela.close());			// Ação fecha esta janela
		
		VBox layout = new VBox(10);							// Layout vertical com 10px entre células
		layout.getChildren().addAll(mensagem, btnClose);	// Adiciona Label e Button ao Layout
		layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro
		
		Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
		janela.setScene(scene);								// Associa a Scena à janela
		janela.showAndWait();								// Executa e prende o controlo até ser fechada
	}
	
	
	
	
	
	
	
	/*-----------------------------------------------------------------------------------------------------
	 * 	confirmationBox() - Este método cria uma janela modal, no método display, para emitir uma
	 * 						mensagem passada pela janela principal e devolve uma boolean como
	 * 						resposta, obtida por um de 2 botões: sim ou não.
	 * 						Recebe 2 parametros: título para a janela e texto para a mensagem.
	 * 						Devolve um boolean como resposta para a janela principal, através da
	 * 						ação de um de 2 botões:	Sim ou Não.
	 */
	public static boolean confirmationBox(String title, String msg){
		
		Stage janela = new Stage();							// Cria uma blank window
		janela.initModality(Modality.APPLICATION_MODAL);	// Define a janela MODAL.
		janela.setTitle(title);								// Como título, recebe a string do parametro
		janela.setMinWidth(300);							// Largura mínims da janela
		
		Label mensagem = new Label(msg);					// Cria a label para mostrar a mensagem
		
		Button btnYes = new Button("Sim");					// Cria botão para fechar janela
		btnYes.setOnAction(e -> {							// Ação: resposta true, fecha janela
			resposta = true;								
			janela.close();
		});
		
		Button btnNo = new Button("Não");				// Cria botão para fechar janela
		btnNo.setOnAction(e -> {							// Ação: resposta false, fecha janela
			resposta = false;								
			janela.close();
		});			
		
		VBox layout = new VBox(10);							// Layout vertical com 10px entre células
		layout.getChildren().addAll(mensagem,btnYes,btnNo);	// Adiciona Label e Botões ao Layout
		layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro
		
		Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
		janela.setScene(scene);								// Associa a Scena à janela
		janela.showAndWait();								// Executa e prende o controlo até ser fechada
		
		return resposta;									// Ao fechar, devolve a resposta para a Main.
	}

	
	
	
	
	
	
	/*-----------------------------------------------------------------------------------------------------
	 * 	confirmationCloseBox()  Este método cria uma janela modal, para obter confirmação de encerramento
	 * 							Devolve true se clicar no botão Sim, false se botão Não
	*/
	public static boolean confirmationCloseBox(){
				
		try {
			Stage janela = new Stage();							// Cria uma blank window
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("ENCERRAMENTO");					// Título da janela
			janela.setMinWidth(300);							// Largura mínima da janela
			
			Label mensagem = new Label("Pretende Sair");		// Cria a label para mostrar a mensagem
			
			Button btnYes = new Button("Sim");					// Cria botão para fechar janela
			btnYes.setOnAction(e -> {							// Ação: resposta true, fecha janela
				resposta = true;								
				janela.close();
			});
			
			Button btnNo = new Button("Não");					// Cria botão para fechar janela
			btnNo.setOnAction(e -> {							// Ação: resposta false, fecha janela
				resposta = false;								
				janela.close();
			});			
					
			HBox layoutBtn = new HBox(20);						// Layout vertical com 10px entre células
			layoutBtn.getChildren().addAll(btnYes,btnNo);		// Adiciona Label e Botões ao Layout
			layoutBtn.setAlignment(Pos.CENTER);
			
			VBox layout = new VBox(10);							// Layout vertical com 10px entre células
			layout.getChildren().addAll(mensagem,layoutBtn);	// Adiciona Label e Botões ao Layout
			layout.setAlignment(Pos.CENTER);					// Alinhar os conteudos ao Centro

			
			Scene scene = new Scene(layout);					// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena à janela
			janela.showAndWait();								// Executa e prende o controlo até ser fechada
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resposta;										// Ao fechar, devolve a resposta para a Main.
	}
	
	
	
	
	
	
	
	/*---------------------------------------------------------------------------------------------------
	 * Login - 	Janela com GridPane para exemplo de Layout.
	 * 			Permite preencher o UserName,
	 * 			Não testa em BD.
	 * 			Devolve TRUE apenas
	 * 			Static para não criar objetos 
	 */
	
	public static void login() {

		Stage janela = new Stage();							// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// Título da janela
			janela.setMinWidth(300);							// Largura mínima da janela

			// RootLayout
			GridPane layoutRoot = new GridPane();
			layoutRoot.setPadding(new Insets(10, 10, 10, 10)); 	// espaços para a janela
			layoutRoot.setVgap(8);								// espaço das colunas (pixeis)
			layoutRoot.setHgap(10);								// espaço das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutRoot.add(labelUsaerName, 0, 0);				// Adicionar à célula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutRoot.add(txtUserName, 1, 0);					// Adicionado à c+elula col 1, linha 0
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutRoot.add(labelPassword, 0, 1);				// Adicionar à célula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutRoot.add(txtPassword, 1, 1);					// Adicionado à c+elula coluna 1, linha 1

			// Botão Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Acção do botão:
				
				alertBox("SIMULAÇÃO BD",						// Simulação de acesso à BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do método. Têm que ser static atributs
			layoutRoot.add(btnLogin,  1, 2);					// Botão Adicionado à coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutRoot, 300, 200);		// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena à janela
			janela.showAndWait();								// Executa e prende o controlo até ser fechada
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


		
	
	/*-------------------------------------------------------------------------------------------------
	 * isNumeric - Valida dados numéricos em TextFields
	 * 				Recebe o TextField e uma string com o seu nome
	 * 				Se não for número, int, float ou double devolve true de inteiro,
	 * 				SENÃO: gera exceção: NumberFormatException e devolve Falso.
	*/
	
	static public boolean isNumeric(TextField input, String textFieldName) {
		
		boolean result= false;
		try {
			Double.parseDouble(input.getText());
			result = true;
		} 
		catch (NumberFormatException e) {
			alertBox("ERRO", textFieldName+", O valor não é numérico");
			result = false;
		}
		catch (Exception e) {		// Apanha qualquer outra exceção
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	
	


	
	/*------------------------------------------------------------------------------------------------
	 * isText	 - Valida dados numéricos em TextFields
	 * 				Recebe o TextField e uma string com o nome do campo
	 * 				Se não for número, int, float ou double devolve true de inteiro,
	 * 				SENÃO: gera exceção: NumberFormatException e devolve Falso.
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
