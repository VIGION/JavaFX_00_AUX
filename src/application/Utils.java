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
 *  - loginCSS: 			Login com CSS style sheet. Funcionamento igual ao login()
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
		
		Button btnNo = new Button("N�o");					// Cria bot�o para fechar janela
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
	 * 			Permite preencher o UserName e password
	 * 			N�o testa em BD. N�o tem parametros de entrada ou retorno
	 * 			Static para n�o criar objetos 
	 */
	
	public static void login() {

		Stage janela = new Stage();							// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// T�tulo da janela
			janela.setMinWidth(300);							// Largura m�nima da janela

			// RootLayout desta janela
			GridPane layoutLogin = new GridPane();
			layoutLogin.setPadding(new Insets(10, 10, 10, 10)); // espa�os para a janela
			layoutLogin.setVgap(8);								// espa�o das colunas (pixeis)
			layoutLogin.setHgap(10);							// espa�o das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutLogin.add(labelUsaerName, 0, 0);				// Adicionar � c�lula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutLogin.add(txtUserName, 1, 0);					// Adicionado � c+elula col 1, linha 0
			
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutLogin.add(labelPassword, 0, 1);				// Adicionar � c�lula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutLogin.add(txtPassword, 1, 1);					// Adicionado � c+elula coluna 1, linha 1

			
			// Bot�o Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Ac��o do bot�o:
				
				alertBox("SIMULA��O BD",						// Simula��o de acesso � BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do m�todo. T�m que ser static atributs
			layoutLogin.add(btnLogin,  1, 2);					// Bot�o Adicionado � coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutLogin, 300, 200);		// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena � janela
			janela.showAndWait();								// Executa e prende o controlo at� ser fechada
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	
	/*---------------------------------------------------------------------------------------------------
	 * LoginCSS - Aplica temas CSS para melhorar gr�ficos
	 * 			Janela com GridPane e CSS Theme: Exerc�cio. 19a
	 * 			Permite preencher o UserName e password, mas n�o testa em BD
	 * 			Nada devolve TRUE apenas. Static para n�o criar objetos
	 * 			 
	 */
	
	public static void loginCSS() {

		Stage janela = new Stage();								// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// T�tulo da janela
			janela.setMinWidth(300);							// Largura m�nima da janela

			// RootLayout desta janela
			GridPane layoutLogin = new GridPane();
			layoutLogin.setPadding(new Insets(10, 10, 10, 10)); // espa�os para a janela
			layoutLogin.setVgap(8);								// espa�o das colunas (pixeis)
			layoutLogin.setHgap(10);							// espa�o das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutLogin.add(labelUsaerName, 0, 0);				// Adicionar � c�lula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutLogin.add(txtUserName, 1, 0);					// Adicionado � c+elula col 1, linha 0
			
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutLogin.add(labelPassword, 0, 1);				// Adicionar � c�lula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutLogin.add(txtPassword, 1, 1);					// Adicionado � c+elula coluna 1, linha 1

			
			// Bot�o Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Ac��o do bot�o:
				
				alertBox("SIMULA��O BD",						// Simula��o de acesso � BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do m�todo. T�m que ser static atributs
			layoutLogin.add(btnLogin,  1, 2);					// Bot�o Adicionado � coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutLogin, 300, 200);		// Cria a Scene e associa o Layout 
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

	/*	VALIDAÇÃO DE DADOS - VERIFICAÇÃO DO CÓDIGO POSTAL
	 * 
	 * 		Passo1: Este m�todo recebe uma string vindo da textBox do c�digo postal
	 * 				inserido pelo utilizador.
	 * 		Passo2: Vai analisar se respeita a estrutura do c�digo postal.
	 * 		Passo3: Envia um boolean de valida��o, se estiver a true respeita, se 
	 * 				estiver a false n�o respeita.
	 */
	public static boolean isCPostal(String cPostal) {
		
		boolean validacao;	//Var valida��o
		
		int j = 0;	//Contador de �ndice
		int i = 0;	//Contador de n�mero de caracteres correctos		
		
		if(cPostal.length() == 8) {	//Se a string tiver 8 caracteres � v�lido
			for(i = 0; i < 8; i++) {	//Ciclo para verificar a cada volta o �ndice na posi��o indicada pelo contador
					if(i != 4) {		//Se for a posi��o dos digitos
						if(cPostal.charAt(i) == '0' ||
							cPostal.charAt(i) == '1' ||
							cPostal.charAt(i) == '2' ||
							cPostal.charAt(i) == '3' ||
							cPostal.charAt(i) == '4' ||
							cPostal.charAt(i) == '5' ||
							cPostal.charAt(i) == '6' ||
							cPostal.charAt(i) == '7' ||
							cPostal.charAt(i) == '8' ||
							cPostal.charAt(i) == '9') {								
							j++;		//Incrementa��o							
						}
					}
					else {				//Se for a posi��o do caracter '-'					
						if(cPostal.charAt(4) == '-' && i == 4) {
							j++;		//Incrementa��o
					}				
				}
			}
		}
		
		if(j == 8) {	//Se cada caracter respeita a estrutura do c�digo postal � aceite
			validacao = true;
			return validacao;			
		}
		else {			//Se n�o respeitar � inv�lido
			validacao = false;
			return validacao;			
		}
	}
	
	public static boolean isNFiscal(String nFiscal){
		
		boolean resultado;	//Var resultado
		
		int k = 0;	//Contador do indice
		int i = 0;	//Contador de numero de caracteres
		
		if(nFiscal.length() == 9) {	//Se a string tiver 9 caracteres 
			for(i = 0; i < 9; i++) {	//Ciclo para verificar a cada volta o indice na posi��o indicada pelo contador
				if(nFiscal.charAt(i) == '0' ||
					nFiscal.charAt(i) == '1' ||
					nFiscal.charAt(i) == '2' ||
					nFiscal.charAt(i) == '3' ||
					nFiscal.charAt(i) == '4' ||
					nFiscal.charAt(i) == '5' ||
					nFiscal.charAt(i) == '6' ||
					nFiscal.charAt(i) == '7' ||
					nFiscal.charAt(i) == '8' ||
					nFiscal.charAt(i) == '9') {								
					k++;		//Incrementacao							
				}
			}
		}
		
		if(k == 9) {	//Se cada caracter respeita a estrutura do numero fiscal return true
			resultado = true;
			return resultado;			
		}
		else {			//Se n�o return false
			resultado = false;
			return resultado;			
		}
	}
	
	public static boolean isVerificadorVaca(String campo, String equacao) 
	{
		//campo - o que foi introduzido naquele campo
		//equa��o - a restricao desejada para efetuar para cada letra
		
		boolean vaquidacao = true; // variavel que indica se o campo est� ok, 
								   //sen�o � tornada para false;
								   //por defeito est� a true ou seja, o campo est� bem
		
		String c; //variavel que � responsavel para guardar o leite que deve ser analisado
		
		byte i; //reponsavel para passar analisar os caracteres do campo, a cada itera��o
				//� retirado o leite para verificar se est� dentro do prazo
		
		campo = campo.trim();//remove lixos inuteis
		
		for(i = 0; i < campo.length(); i++)//ciclo que funciona enquanto o campo nao for
											//analisado cuidadosamente ate ao fim pelos
											//nossos pastores
				/*bloco de c�digo que faz mu*/
		{
			
			c = campo.substring(i,i+1); //parte retirada para ser analisada
									   //o campo � dividido em caracteres para ver se pertence
									   //a equa��o do pastor
			
			if (  !c.matches(equacao)) { //verifica se o leite est� mau
				
				//alertBox("Atencao",c + " � ilegal!"); 
				
				//se estiver mau...
				vaquidacao = false;//� marcado como mau.
								   //e proibida a saida para o mercado
			}

		}
		
		if(campo.isEmpty())//a vaca n�o aceita embalagens de leite vazias
		{
			return false;//nesse caso nega o leite
		}
		
		return vaquidacao;//Devolve a vaca
		
		//para mais informa��o sobre a equa��o
		//http://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html#sum
			
		/*Characters
		
		x The character x 
		\\ The backslash character 
		\0n The character with octal value 0n (0 <= n <= 7) 
		\0nn The character with octal value 0nn (0 <= n <= 7) 
		\0mnn The character with octal value 0mnn (0 <= m <= 3, 0 <= n <= 7) 
		\xhh The character with hexadecimal value 0xhh 
		\\uhhhh The character with hexadecimal value 0xhhhh 
		\x{h...h} The character with hexadecimal value 0xh...h (Character.MIN_CODE_POINT  <= 0xh...h <=  Character.MAX_CODE_POINT) 
		\t The tab character ('\u0009') 
		\n The newline (line feed) character ('\u000A') 
		\r The carriage-return character ('\u000D') 
		\f The form-feed character ('\u000C') 
		\a The alert (bell) character ('\u0007') 
		\e The escape character ('\u001B') 
		\cx The control character corresponding to x 
		
		Character classes
		
		[abc] a, b, or c (simple class) 
		[^abc] Any character except a, b, or c (negation) 
		[a-zA-Z] a through z or A through Z, inclusive (range) 
		[a-d[m-p]] a through d, or m through p: [a-dm-p] (union) 
		[a-z&&[def]] d, e, or f (intersection) 
		[a-z&&[^bc]] a through z, except for b and c: [ad-z] (subtraction) 
		[a-z&&[^m-p]] a through z, and not m through p: [a-lq-z](subtraction) 
		*/
		
	}
	
	/*
	 * Transformar letras iniciais em maiusculas
	 *  */

	public static String maiusculador(String campo)
	{
		//Neste c�digo os cavalos tomam conta do assunto
		String cavalo = campo.trim();//limpa a string
		byte i = 0;//variavel que identifica os espacos em branco
		cavalo = cavalo.substring(0,1).toUpperCase() + cavalo.substring(1);//troca a primeira letra por maiuscula
																		   //a primeira letra ser� sempre um caracter por causa do trim
		
		while(cavalo.matches(".*\\s[a-z]*.") && (i != -1))//procura enquanto houver um espaco em branco seguido de uma letra
														  //e se o contador de espacos nao execedeu a string
		{
			i = (byte) cavalo. indexOf(" ",i+1);		//identifica a posicao do espaco, inicia a procura na ultima posicao encontrada
			
			//substitui o caracter por uma maiuscula
			cavalo = cavalo.substring(0,i+1) + 
					 cavalo.substring(i+1,i+2).toUpperCase()+ 
					 cavalo.substring(i+2);				
			//alertBox("", "" + i + " " + cavalo);
		}
		
		return cavalo;//devolve as altera��es feitas pelo cavalo
	}
	
	//Metodo Elaborado por Bruno e Ventura
	public static boolean isVerificaEmail(String email)	//Metodo para proteger o campo EMAIL
	{
		int posicaoArroba=0;  //Variavel que indica a posi�ao do arroba
		int contaArroba = 0;  //Variavel para contar o numero de arrobas presentes na string EMAIL
		int numeroLetrasEmail = email.length();	//Inteiro que toma o valor do numero de caracteres da string EMAIL
		int verificaPonto=0;	//Variavel que indica a existencia do ponto
		int posicaoPonto=0;		//Variavel que indica a posicao do ponto
		
		//Ciclo para verificar quantos @ estao na string EMAIL
		for(int i = 0; i<numeroLetrasEmail; i++) //Ciclo for para verificar cada caracter da string EMAIL, em procura de mais do que um @
		{
			if(email.charAt(i) == '@')		//Verifica cada um dos caracteres, e caso seja um @:
			{
				posicaoArroba = i;				//Posi�ao atual do @, usado futuramente para saber se existem caracteres � frente do @
				contaArroba = contaArroba +1;	//Incrementa o contador de @
			}
			
			//Ciclo para verificar se existe ponto � frente do arroba
			for(int y=posicaoArroba; y<numeroLetrasEmail; y++)
			{
				if(email.charAt(y) == '.')		//Verifica cada um dos caracteres, e caso seja um @:
				{
					posicaoPonto = y;			//Indica a posicao do ponto
					verificaPonto = 1;			//Indica a existencia de um ponto
				}
			}
		}
		
		//Condi�oes finais de verifica�ao de email
		if(email.contains("@") == false)		//Se a string email nao tiver um @
		{
			Utils.alertBox("Erro", "Email nao contem @");	//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if( contaArroba != 1)	//Se o contador de arrobas for diferente de 1 (ou seja, contem mais do que um arroba)
		{
			Utils.alertBox("Erro", "Email contem mais do que 1 @");//Mostra mensagem de erro
			return false; 	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(numeroLetrasEmail < 3)
		{
			Utils.alertBox("Erro", "Email invalido");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(posicaoArroba + 1 == numeroLetrasEmail)		//Se nao houver informa�oes � frente do @, tera que ser preenchido
															//+1 porque o indice come�a no 0, para ser o 

											//valor real tem de se adicionar +1
		{
			Utils.alertBox("Erro", "Preencher � frente do @");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(verificaPonto == 0)
		{
			Utils.alertBox("Erro", "Necessita um ponto � frente do arroba");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(posicaoArroba == 0)
		{
			Utils.alertBox("Erro", "Necessita de informa�ao antes do arroba");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(posicaoArroba+1 == posicaoPonto)
		{
			Utils.alertBox("Erro", "Necessita de informa�ao antes do ponto");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else	//Caso o email esteja correctamente preenchido:
		{
			return true;	//Devolve true, ou seja, encontra-se tudo bem com o campo EMAIL 
		}
	}


}
