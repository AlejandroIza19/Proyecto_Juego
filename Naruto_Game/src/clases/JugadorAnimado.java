package clases;

//import java.util.ArrayList;
import java.util.HashMap;
import Implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class JugadorAnimado extends ObjetoJuego {
	private int vida;
	private HashMap<String, Animacion> animaciones;
	private int xImagen;
	private int yImagen;
	private int anchoImagen;
	private int altoImagen;
	private String animacionActual;
	private int direccion=1; 
	
	public JugadorAnimado(int x, int y, String nombreImagen, int velocidad, int vida, String animacionActual) {
		super(x, y, nombreImagen, velocidad);
		this.vida = vida;
		this.animacionActual = animacionActual;
		animaciones = new HashMap<String, Animacion>();
		inicializarAnimaciones();
	}


	public int getVida() {
		return vida;
	}
	
	public void inicializarAnimaciones() {
		Rectangle coordenadasCorrer[] = {
				new Rectangle(2,109,90,90),
				new Rectangle(92,114,90,90),
				new Rectangle(196,101,90,90),
		};
		
		Animacion animacionCorrer = new Animacion(0.05,coordenadasCorrer);
		animaciones.put("correr", animacionCorrer);
		
		Rectangle coordenadasDescanso[] = {
			new Rectangle(24,7,90,90)	
		};
		
		Animacion animacionDescanso = new Animacion(0.1, coordenadasDescanso);
		animaciones.put("descanso", animacionDescanso);
	}

	public void setVida(int vida) {
		this.vida = vida;
	}
	
	public void calcularFrame(double t) {
		Rectangle coordenadas = animaciones.get(animacionActual).calcularFrameActual(t);
		this.xImagen = (int)coordenadas.getX();
		this.yImagen = (int)coordenadas.getY();
		this.altoImagen = (int)coordenadas.getWidth();
		this.anchoImagen = (int)coordenadas.getHeight();
	}
	
	@Override
	public void pintar(GraphicsContext graficos) {
		graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,altoImagen,x, y,ancho,alto);
	}
	
	@Override
	public void mover() {
		if(x>700)
			x=-80;
		
		if(Juego.derecha)//mover hacia la derecha 
			x+=velocidad;
		
		if(Juego.izquierda)//reduce la velocidad
			x-=velocidad;
		
		/*if(Juego.arriba)
			y-=velocidad;
		
		if(Juego.abajo)
			y+=velocidad;
		
		if(Juego.salto)
			y-=velocidad*4;*/
	}


	public String getAnimacionActual() {
		return animacionActual;
	}


	public void setAnimacionActual(String animacionActual) {
		this.animacionActual = animacionActual;
	}


	public int getDireccion() {
		return direccion;
	}


	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
}
