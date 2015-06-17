package application;


import javafx.scene.image.ImageView;


public class Aluno {

	private int nTurma;
    private String nome;
    private ImageView imgv;
    
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
	
	
	//----------------------------------------getter&Setters
	
	
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
