package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/* Propriedadades: 	é um atributo especial, semelhantes a C# que 
					que tem funcionalidades próprias como Listeners
					
					Passo1 - Criar a Proprieade
					Passo2 - Criar os getters e settters JavaFX
*/



public class Pessoa {

	// Passo1 - criar a Propriedades
	private StringProperty nome; 
	
	
	public Pessoa(){
		nome = new SimpleStringProperty(this, "Nome", "");
	}
	/*	StringProperty é uma classe abstract
	 * 	SimpleStringProperty() - cria Propriedades do tipo string 
	 * 		Recebe 3 parametros:
	 * 		- this é o proprio o objeto
	 * 		- String com o nome da propriedade
	 * 		- Default value. Inicializa a propriedade
	 */	
	 
	
	 
	// Passo2 - Rato dtº - Source - Generate JavaFX getters & setters


	// Devolve o objeto stringProperty
	public final StringProperty nomeProperty() {
		return this.nome;
	}

	// getter - devolve a string do atributo
	public String getNome() {
		return this.nomeProperty().get();
	}

	//setter - recebe uma string para o atributo
	public final void setNome(String nome) {
		this.nomeProperty().set(nome);
	}
}
