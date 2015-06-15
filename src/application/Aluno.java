package application;


import javafx.scene.image.ImageView;


public class Aluno {

    private String nome;
    private int nTurma;
    //private String imageName;
    private ImageView imgv;                


    public Aluno(){
    	
    }
    
    // Construtor sem imagem
    public Aluno(int nTurma, String nome) {
        this.nTurma = nTurma;
        this.nome = nome;
    }
    
    // Construtor com imagem
	public Aluno(int nTurma, String nome, ImageView imgv) {
        this.nTurma = nTurma;
        this.nome = nome;
        this.imgv = imgv;
    }

    public int getNTurma() {
        return nTurma;
    }

    public void setNTurma(int nTurma) {
        this.nTurma = nTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public ImageView getImgv() {
		return imgv;
	}

	public void setImgv(ImageView imgv) {
		this.imgv = imgv;
	}
}
