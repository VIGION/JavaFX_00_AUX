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
 *  - loginCSS: 			Login com CSS style sheet. Funcionamento igual ao login()
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
		
		Button btnNo = new Button("Não");					// Cria botão para fechar janela
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
	 * 			Permite preencher o UserName e password
	 * 			Não testa em BD. Não tem parametros de entrada ou retorno
	 * 			Static para não criar objetos 
	 */
	
	public static void login() {

		Stage janela = new Stage();							// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// Título da janela
			janela.setMinWidth(300);							// Largura mínima da janela

			// RootLayout desta janela
			GridPane layoutLogin = new GridPane();
			layoutLogin.setPadding(new Insets(10, 10, 10, 10)); // espaços para a janela
			layoutLogin.setVgap(8);								// espaço das colunas (pixeis)
			layoutLogin.setHgap(10);							// espaço das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutLogin.add(labelUsaerName, 0, 0);				// Adicionar à célula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutLogin.add(txtUserName, 1, 0);					// Adicionado à c+elula col 1, linha 0
			
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutLogin.add(labelPassword, 0, 1);				// Adicionar à célula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutLogin.add(txtPassword, 1, 1);					// Adicionado à c+elula coluna 1, linha 1

			
			// Botão Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Acção do botão:
				
				alertBox("SIMULAÇÃO BD",						// Simulação de acesso à BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do método. Têm que ser static atributs
			layoutLogin.add(btnLogin,  1, 2);					// Botão Adicionado à coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutLogin, 300, 200);		// Cria a Scene e associa o Layout 
			janela.setScene(scene);								// Associa a Scena à janela
			janela.showAndWait();								// Executa e prende o controlo até ser fechada
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	
	
	/*---------------------------------------------------------------------------------------------------
	 * LoginCSS - Aplica temas CSS para melhorar gráficos
	 * 			Janela com GridPane e CSS Theme: Exercício. 19a
	 * 			Permite preencher o UserName e password, mas não testa em BD
	 * 			Nada devolve TRUE apenas. Static para não criar objetos
	 * 			 
	 */
	
	public static void loginCSS() {

		Stage janela = new Stage();								// Cria uma blank window
		
		try {
			
			janela.initModality(Modality.APPLICATION_MODAL);	// Define-a MODAL.
			janela.setTitle("LOGIN");							// Título da janela
			janela.setMinWidth(300);							// Largura mínima da janela

			// RootLayout desta janela
			GridPane layoutLogin = new GridPane();
			layoutLogin.setPadding(new Insets(10, 10, 10, 10)); // espaços para a janela
			layoutLogin.setVgap(8);								// espaço das colunas (pixeis)
			layoutLogin.setHgap(10);							// espaço das linhas	
			
			// UserName
			Label labelUsaerName = new Label("UserName:");
			layoutLogin.add(labelUsaerName, 0, 0);				// Adicionar à célula col 0,linha 0
			
			TextField txtUserName = new TextField();			// Campo de texto vazio
			layoutLogin.add(txtUserName, 1, 0);					// Adicionado à c+elula col 1, linha 0
			
			
			// Password
			Label labelPassword = new Label("Password:");
			layoutLogin.add(labelPassword, 0, 1);				// Adicionar à célula col 0, linha 1
			
			PasswordField txtPassword = new PasswordField();	// Campo de texto vazio
			txtPassword.setPromptText("password");				// Sugere texto. Desaparece ao toque	
			layoutLogin.add(txtPassword, 1, 1);					// Adicionado à c+elula coluna 1, linha 1

			
			// Botão Login
			Button btnLogin = new Button("Log Ins");
			btnLogin.setOnAction(e -> {							// Acção do botão:
				
				alertBox("SIMULAÇÃO BD",						// Simulação de acesso à BD. Devolve TRUE  
						"Username: "+txtUserName.getText()+", "
						+ "Password: "+txtPassword.getText());
				//todo: testar um qq match
			});													// vars locais do método. Têm que ser static atributs
			layoutLogin.add(btnLogin,  1, 2);					// Botão Adicionado à coluna 1, linha 2	 
			
			Scene scene = new Scene(layoutLogin, 300, 200);		// Cria a Scene e associa o Layout 
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

	/*	VALIDAÃ‡ÃƒO DE DADOS - VERIFICAÃ‡ÃƒO DO CÃ“DIGO POSTAL
	 * 
	 * 		Passo1: Este mï¿½todo recebe uma string vindo da textBox do cï¿½digo postal
	 * 				inserido pelo utilizador.
	 * 		Passo2: Vai analisar se respeita a estrutura do cï¿½digo postal.
	 * 		Passo3: Envia um boolean de validaï¿½ï¿½o, se estiver a true respeita, se 
	 * 				estiver a false nï¿½o respeita.
	 */
	public static boolean isCPostal(String cPostal) {
		
		boolean validacao;	//Var validaï¿½ï¿½o
		
		int j = 0;	//Contador de ï¿½ndice
		int i = 0;	//Contador de nï¿½mero de caracteres correctos		
		
		if(cPostal.length() == 8) {	//Se a string tiver 8 caracteres ï¿½ vï¿½lido
			for(i = 0; i < 8; i++) {	//Ciclo para verificar a cada volta o ï¿½ndice na posiï¿½ï¿½o indicada pelo contador
					if(i != 4) {		//Se for a posiï¿½ï¿½o dos digitos
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
							j++;		//Incrementaï¿½ï¿½o							
						}
					}
					else {				//Se for a posiï¿½ï¿½o do caracter '-'					
						if(cPostal.charAt(4) == '-' && i == 4) {
							j++;		//Incrementaï¿½ï¿½o
					}				
				}
			}
		}
		
		if(j == 8) {	//Se cada caracter respeita a estrutura do cï¿½digo postal ï¿½ aceite
			validacao = true;
			return validacao;			
		}
		else {			//Se nï¿½o respeitar ï¿½ invï¿½lido
			validacao = false;
			return validacao;			
		}
	}
	
	public static boolean isNFiscal(String nFiscal){
		
		boolean resultado;	//Var resultado
		
		int k = 0;	//Contador do indice
		int i = 0;	//Contador de numero de caracteres
		
		if(nFiscal.length() == 9) {	//Se a string tiver 9 caracteres 
			for(i = 0; i < 9; i++) {	//Ciclo para verificar a cada volta o indice na posição indicada pelo contador
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
		else {			//Se não return false
			resultado = false;
			return resultado;			
		}
	}
	
	public static boolean isVerificadorVaca(String campo, String equacao) 
	{
		//campo - o que foi introduzido naquele campo
		//equaï¿½ï¿½o - a restricao desejada para efetuar para cada letra
		
		boolean vaquidacao = true; // variavel que indica se o campo estï¿½ ok, 
								   //senï¿½o ï¿½ tornada para false;
								   //por defeito estï¿½ a true ou seja, o campo estï¿½ bem
		
		String c; //variavel que ï¿½ responsavel para guardar o leite que deve ser analisado
		
		byte i; //reponsavel para passar analisar os caracteres do campo, a cada iteraï¿½ï¿½o
				//ï¿½ retirado o leite para verificar se estï¿½ dentro do prazo
		
		campo = campo.trim();//remove lixos inuteis
		
		for(i = 0; i < campo.length(); i++)//ciclo que funciona enquanto o campo nao for
											//analisado cuidadosamente ate ao fim pelos
											//nossos pastores
				/*bloco de cï¿½digo que faz mu*/
		{
			
			c = campo.substring(i,i+1); //parte retirada para ser analisada
									   //o campo ï¿½ dividido em caracteres para ver se pertence
									   //a equaï¿½ï¿½o do pastor
			
			if (  !c.matches(equacao)) { //verifica se o leite estï¿½ mau
				
				//alertBox("Atencao",c + " ï¿½ ilegal!"); 
				
				//se estiver mau...
				vaquidacao = false;//ï¿½ marcado como mau.
								   //e proibida a saida para o mercado
			}

		}
		
		if(campo.isEmpty())//a vaca nï¿½o aceita embalagens de leite vazias
		{
			return false;//nesse caso nega o leite
		}
		
		return vaquidacao;//Devolve a vaca
		
		//para mais informaï¿½ï¿½o sobre a equaï¿½ï¿½o
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
		//Neste código os cavalos tomam conta do assunto
		String cavalo = campo.trim();//limpa a string
		byte i = 0;//variavel que identifica os espacos em branco
		cavalo = cavalo.substring(0,1).toUpperCase() + cavalo.substring(1);//troca a primeira letra por maiuscula
																		   //a primeira letra serï¿½ sempre um caracter por causa do trim
		
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
		
		return cavalo;//devolve as alteraï¿½ï¿½es feitas pelo cavalo
	}
	
	//Metodo Elaborado por Bruno e Ventura
	public static boolean isVerificaEmail(String email)	//Metodo para proteger o campo EMAIL
	{
		int posicaoArroba=0;  //Variavel que indica a posiçao do arroba
		int contaArroba = 0;  //Variavel para contar o numero de arrobas presentes na string EMAIL
		int numeroLetrasEmail = email.length();	//Inteiro que toma o valor do numero de caracteres da string EMAIL
		int verificaPonto=0;	//Variavel que indica a existencia do ponto
		int posicaoPonto=0;		//Variavel que indica a posicao do ponto
		
		//Ciclo para verificar quantos @ estao na string EMAIL
		for(int i = 0; i<numeroLetrasEmail; i++) //Ciclo for para verificar cada caracter da string EMAIL, em procura de mais do que um @
		{
			if(email.charAt(i) == '@')		//Verifica cada um dos caracteres, e caso seja um @:
			{
				posicaoArroba = i;				//Posiçao atual do @, usado futuramente para saber se existem caracteres á frente do @
				contaArroba = contaArroba +1;	//Incrementa o contador de @
			}
			
			//Ciclo para verificar se existe ponto á frente do arroba
			for(int y=posicaoArroba; y<numeroLetrasEmail; y++)
			{
				if(email.charAt(y) == '.')		//Verifica cada um dos caracteres, e caso seja um @:
				{
					posicaoPonto = y;			//Indica a posicao do ponto
					verificaPonto = 1;			//Indica a existencia de um ponto
				}
			}
		}
		
		//Condiçoes finais de verificaçao de email
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
		else if(posicaoArroba + 1 == numeroLetrasEmail)		//Se nao houver informaçoes á frente do @, tera que ser preenchido
															//+1 porque o indice começa no 0, para ser o 

											//valor real tem de se adicionar +1
		{
			Utils.alertBox("Erro", "Preencher á frente do @");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(verificaPonto == 0)
		{
			Utils.alertBox("Erro", "Necessita um ponto á frente do arroba");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(posicaoArroba == 0)
		{
			Utils.alertBox("Erro", "Necessita de informaçao antes do arroba");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else if(posicaoArroba+1 == posicaoPonto)
		{
			Utils.alertBox("Erro", "Necessita de informaçao antes do ponto");//Mostra mensagem de erro
			return false;	//Devolve falso, ou seja, se o campo nao esta correcto, nao realiza o login
		}
		else	//Caso o email esteja correctamente preenchido:
		{
			return true;	//Devolve true, ou seja, encontra-se tudo bem com o campo EMAIL 
		}
	}


}
