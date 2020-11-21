package Implementacion;

import java.util.HashMap;

import clases.Fondo;
//import clases.Jugador;
import clases.JugadorAnimado;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application {
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private Fondo fondo;
	public static boolean arriba;
	public static boolean abajo;
	public static boolean derecha;
	public static boolean izquierda;
	public static boolean salto;
	public static HashMap<String, Image> imagenes;
	
	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void start(Stage ventana) throws Exception {
		inicializarcomponentes();
		gestionEventos();
		ventana.setScene(escena);
		ventana.setTitle("Naruto Shippuden Game");
		ventana.show();
		cicloJuego();
	}
	
	public void cicloJuego() {
		long tiempoInicial = System.nanoTime();
		AnimationTimer animationTimer = new AnimationTimer() {
			
			//https://docs.google.com/spreadsheets/d/1jzNw-2yEGOq5Blegk_7K6LhWNpBnHQ5FujBw6xiu900/edit#gid=0
			//Este metodo se ejecuta aprox 60fps
			@Override
			public void handle(long tiempoActual) {
				double t = (tiempoActual - tiempoInicial)/1000000000.0;
				//System.out.println(t);
				actualizarEstado(t);
				pintar();
			}
			
		};
		
		animationTimer.start();
	}
	
	public void actualizarEstado(double t) {
		//jugador.mover();
		jugadorAnimado.calcularFrame(t);
		jugadorAnimado.mover();
		fondo.mover();
	}
	
	public void inicializarcomponentes() {
		imagenes = new HashMap<String, Image>();
		cargarImagenes();
		//jugador = new Jugador(20,40,"naruto",3,0);
		jugadorAnimado = new JugadorAnimado(20,100,"kakashi",3,0,"descanso");
		fondo = new Fondo(0,0,"background","background2", 3);
		root = new Group();
		escena = new Scene(root, 740, 414);
		lienzo = new Canvas(740, 414);
		root.getChildren().add(lienzo);
		graficos = lienzo.getGraphicsContext2D();
		
	}
	
	public void cargarImagenes() {
		imagenes.put("naruto", new Image("kakashi.png"));
		imagenes.put("background", new Image("escenario.jpg"));
		imagenes.put("background2", new Image("escenario2.jpg"));
		imagenes.put("kakashi", new Image("SpriteKakashi.png"));
	}
	
	public void pintar() {
		//graficos.fillRect(0, 0, 100, 100);
		fondo.pintar(graficos);
		//jugador.pintar(graficos);
		jugadorAnimado.pintar(graficos);
	}

	public void gestionEventos() {
		//escena.setOnKeyPressed(new Evento());
		escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			//El metodo Handle se ejecuta cada vez que se presiona una tecla
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
					case "RIGHT":
						derecha = true;
						jugadorAnimado.setDireccion(1);
						jugadorAnimado.setAnimacionActual("correr");
						break;
					case "LEFT":
						izquierda = true;
						jugadorAnimado.setDireccion(-1);
						jugadorAnimado.setAnimacionActual("correr");
						break;
					case "UP":
						arriba = true;
						break;
					case "DOWN": 
						abajo = true;
						break;
					case "SPACE":
						salto = true;
						break;
				}
			}
		});
		
		escena.setOnKeyReleased(new EventHandler<KeyEvent>() {
			//Este metodo se ejecuta cuando se suelta una tecla
			@Override
			public void handle(KeyEvent evento) {
				switch(evento.getCode().toString()){
				case "RIGHT":
					derecha = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "LEFT":
					izquierda = false;
					jugadorAnimado.setAnimacionActual("descanso");
					break;
				case "UP":
					arriba = false;
					break;
				case "DOWN": 
					abajo = false;
					break;
				case "SPACE":
					salto = false;
					break;
			}
				
			}
			
		});
	}
	
	
}

