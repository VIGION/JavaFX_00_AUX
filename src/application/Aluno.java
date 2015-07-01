package application;


import javafx.scene.image.ImageView;


public class Aluno {

	private int nProc;			// Nª Processo - Necessário para JavaFX_25_SQL
	private int nTurma;			// Nº Aluno na Turma
    private String nome;		// Nome
    private ImageView imgv;		// Objeto de tratamento de imagem
    
    private static int IMG_WITH = 40;		// Constante com largura de imagem


    //--------------------------------Construtores
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
        
        // Preparação da Imagem
        
        this.imgv = setImgvSize(imgv);
    }
	
	// Construtor com atributos para a aula 25 SQL - Sem Imagem
	public Aluno(int nProc, int nTurma, String nome) {
        this.nProc = nProc;
		this.nTurma = nTurma;
        this.nome = nome;
    }
	
	
	//----------------------------------------getter&Setters
	
	// Nº Processo
	public int getNProc() {
		return nProc;
	}
	public void setNProc(int nProc) {
		this.nProc = nProc;
	}
	
	
	// Nº Turma
    public int getNTurma() {
        return nTurma;
    }
    public void setNTurma(int nTurma) {
        this.nTurma = nTurma;
    }

    
    // Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
    // ImageView
    public ImageView getImgv() {
		return imgv;
	}

	public void setImgv(ImageView imgv) {
		this.imgv = setImgvSize(imgv);
	}
	
	
	/* Prepara tamanho e qualidade da imagem */
	public ImageView setImgvSize (ImageView imgv){
		imgv.setFitWidth(IMG_WITH);		// resizes the image
        imgv.setPreserveRatio(true);	// Preserva o racio original da img
        imgv.setSmooth(true);			// Filtro de alta qualidade 
        imgv.setCache(true);			// Poe em cach
        
        return imgv;
	}
}
