package Modelo;

import Controlador.Inicio;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

//import core.Buscaminas;
//import core.DemasiadasMinasException;
//import core.NumeroDeVidasFueraDeRangoException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class MiControlador implements Initializable {
    // TODO Hacer que el usuario personalice su partida.

    // En este array de nodos almaceno las referencias a los botones que
    // conforman las casillas, así puedo referenciarlos en base a las
    // coordenadas x, y
    private Node[][] casillas;

    @FXML
    private GridPane terrenoMinas;

    @FXML
    private Button botonReset;

    @FXML
    private Button botonNewGame;

    @FXML
    private Button botonPersonalizar;

    @FXML
    private Label vidasRestantes;

    private Buscaminas juego;

    @FXML
    private Button botonMenu;

    private Inicio ventanaJuego;

    private int ancho;
    private int alto;
    private int minas;
    private int vidas;

    Image imgBandera = new Image("imgs/bandera.png");

    public void setProgramaPrincipal(Inicio ProgramaPrincipal) {
        this.ventanaJuego = ProgramaPrincipal;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ancho = 9;
        alto = 9;
        minas = 10;
        vidas = 5;

        juegoNuevo();

        /*
		 * He modificado las siguientes acciones reescribiéndolas en expresiones lambda.
         */
        botonNewGame.setOnAction(p -> juegoNuevo());

        botonReset.setOnAction(p -> {
            juego.reset();
            construirTablero();
        });

        botonPersonalizar.setOnAction(p -> {
            Dialog<ButtonType> personal = new Dialog<>();
            GridPane formulario = new GridPane();

            personal.setTitle("Personalizar buscaminas");
            personal.setHeaderText("Elige un modo de juego");

            Button principiante = new Button("Principiante");
            Button intermedio = new Button("Intermedio");
            Button avanzado = new Button("Avanzado");
            Button personalizar = new Button("Personalizar...");

            formulario.add(principiante, 0, 0);
            formulario.add(intermedio, 0, 1);
            formulario.add(avanzado, 0, 2);
            formulario.add(personalizar, 0, 3);

            principiante.setOnAction(q -> {
                ancho = 9;
                alto = 9;
                minas = 10;
                vidas = 5;
                juegoNuevo();
                personal.close();
            });

            intermedio.setOnAction(q -> {
                ancho = 17;
                alto = 17;
                minas = 40;
                vidas = 10;
                juegoNuevo();
                personal.close();
            });

            avanzado.setOnAction(q -> {
                ancho = 31;
                alto = 17;
                minas = 99;
                vidas = 13;
                juegoNuevo();
                personal.close();
            });

            personalizar.setOnAction(q -> {
                juegoPersonalizado();
                personal.close();
            });

            personal.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

            personal.getDialogPane().setContent(formulario);

            personal.showAndWait();
        });
    }

    public void juegoPersonalizado() {
        Dialog<ButtonType> personal = new Dialog<>();
        personal.setHeaderText("Configura tu juego personalizado");

        GridPane formulario = new GridPane();

        Label paraAltura = new Label("Alto: ");
        Label paraAnchura = new Label("Ancho: ");
        Label paraMinas = new Label("Número de minas: ");
        Label paraVidas = new Label("Número de vidas: ");

        TextField inputAltura = new TextField();
        TextField inputAnchura = new TextField();
        TextField inputMinas = new TextField();
        TextField inputVidas = new TextField();

        inputAltura.setText(Integer.toString(alto));
        inputAnchura.setText(Integer.toString(ancho));
        inputMinas.setText(Integer.toString(minas));
        inputVidas.setText(Integer.toString(vidas));

        formulario.add(paraAltura, 0, 0);
        formulario.add(paraAnchura, 0, 1);
        formulario.add(paraMinas, 0, 2);
        formulario.add(paraVidas, 0, 3);

        formulario.add(inputAltura, 1, 0);
        formulario.add(inputAnchura, 1, 1);
        formulario.add(inputMinas, 1, 2);
        formulario.add(inputVidas, 1, 3);

        personal.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        personal.getDialogPane().setContent(formulario);

        boolean continuar = true;

        while (continuar) {

            Optional<ButtonType> resultado = personal.showAndWait();

            if (resultado.get() == ButtonType.OK) {
                try {
                    alto = Integer.parseInt(inputAltura.getText());
                    ancho = Integer.parseInt(inputAnchura.getText());
                    minas = Integer.parseInt(inputMinas.getText());
                    vidas = Integer.parseInt(inputVidas.getText());

                    if (minas > ancho * alto) {
                        alerta("No puedes poner más minas que casillas");
                    } else if (vidas < 1 || vidas > minas) {
                        alerta("El número de vidas tiene que ser un valor entre 1 y el número de minas");
                    } else {
                        continuar = false;
                        juegoNuevo();
                    }
                } catch (Exception e) {
                    alerta("Introduce correctamente números enteros en cada casilla.");

                }

            } else if (resultado.get() == ButtonType.CANCEL) {
                continuar = false;
            }
        }

    }

    public void juegoNuevo() {
        try {
            juego = new Buscaminas(ancho, alto, minas, vidas);
            juego.soportaBanderas(true);
        } catch (DemasiadasMinasException e) {
            System.out.println("Has puesto demasiadas minas");
        } catch (NumeroDeVidasFueraDeRangoException e) {
            System.out.println("El número de vidas es incorrecto");
        }

        construirTablero();
    }

    public void construirTablero() {
        /*
		 * Primero limpiamos todo lo que hay dentro del GridPane y del array
		 * casillas
         */
        casillas = new Node[juego.alto()][juego.ancho()];
        terrenoMinas.getChildren().clear();

        /*
		 * Generamos las casillas en función de la configuración que haya en el
		 * objeto "juego" (de tipo Buscaminas).
         */
        for (int y = 0; y < juego.alto(); y++) {
            terrenoMinas.getColumnConstraints().add(new ColumnConstraints());
            terrenoMinas.getRowConstraints().add(new RowConstraints());

            for (int x = 0; x < juego.ancho(); x++) {
                // Creo una casilla personalizada con las coordenadas
                Casilla casilla = new Casilla(x, y, " ");

                // Le doy una acción, de tipo ratón.
                casilla.setOnMouseClicked(p -> {
                    // Si el jugador pulsa el botón izquierdo del ratón,
                    // cavo
                    // la casilla, pero si pulsa el derecho, pongo una
                    // bandera.

                    // CAVO
                    if (p.getButton() == MouseButton.PRIMARY) {
                        juego.cavar(casilla.getX(), casilla.getY());
                    } // BANDERA
                    else if (p.getButton() == MouseButton.SECONDARY) {
                        juego.marcarBandera(casilla.getX(), casilla.getY());
                    }
                    actualizarTablero();
                });

                terrenoMinas.add(casilla, x, y);

                /*
				 * Cada casilla que creo, la meto en un array de tipo Nodo que
				 * contiene los elementos a los que deseo no perder referencia.
				 * De esta forma, tengo en una matriz del tamaño del tablero las
				 * referencias a los objetos Casilla cuando necesite recorrerlas
				 * en base a su posición (coordenadas x y).
                 */
                casillas[y][x] = casilla;
            }
        }

        if (juego.getTotalVidas() > 1) {
            vidasRestantes.setText("Vidas restantes: " + Integer.toString(juego.getVidasRestantes()));
        }
    }

    public void actualizarTablero() {
        /*
		 * Este método debe actualizar el estado de cada casilla en el tablero.
		 * Se le debe llamar cada vez que se realice alguna opción.
         */
        if (juego.getTotalVidas() > 1) {
            vidasRestantes.setText("Vidas restantes: " + Integer.toString(juego.getVidasRestantes()));
        }

        for (int y = 0; y < juego.alto(); y++) {
            for (int x = 0; x < juego.ancho(); x++) {
                Button cuadro = (Button) casillas[y][x];

                // Si está destapada
                if (juego.estaDestapada(x, y)) {
                    cuadro.setDisable(true);
                    // Si hay mina
                    if (juego.hayMina(x, y)) {
                        cuadro.getStyleClass().add("casillaMina");
                    } else {
                        // Si tiene minas alrededor, le ponemos el número.
                        if (juego.hayMinasAlrededor(x, y)) {
                            if (juego.contarMinasAlrededor(x, y) > 0) {
                                cuadro.setText(Integer.toString(juego.contarMinasAlrededor(x, y)));
                            }
                        }
                    }
                } else {
                    if (juego.tieneBandera(x, y)) {
                        // cuadro.getStyleClass().add("casillaBandera");
                        ImageView imgBandera2 = new ImageView(imgBandera);
                        imgBandera2.setFitWidth(20);
                        imgBandera2.setFitHeight(30);

                        cuadro.setGraphic(imgBandera2);
                    } else {
                        // cuadro.getStyleClass().remove("casillaBandera");
                        cuadro.setGraphic(null);
                    }
                }

            }
        }
        if (juego.isGameOver()) {
            if (juego.isGanador()) {
                alerta("¡Has ganado!");
            } else {
                alerta(AlertType.WARNING, "Has perdido");
            }
        }

    }

    public void alerta(AlertType tipo, String mensaje) {
        Alert temp = new Alert(tipo);
        temp.setTitle("Nota informativa");
        temp.setHeaderText("Buscaminas");
        temp.setContentText(mensaje);
        temp.showAndWait();
    }

    public void alerta(String mensaje) {
        alerta(AlertType.INFORMATION, mensaje);
    }

    /*
	 * Si la partida termina porque el jugador ha perdido, este método mostrará
	 * dónde estaban las minas y las banderas erróneas.
     */
    public void gameOverMostrarSolucion() {
        for (int i = 0; i < juego.alto(); i++) {
            for (int j = 0; j < juego.ancho(); j++) {
                if (juego.tieneBandera(j, i)) {
                    if (!juego.hayMina(j, i)) {
                        // Mal
                    }
                } else if (juego.hayMina(j, i)) {
                    // Mal
                }
            }
        }
    }

    @FXML
    private void menuInicio(ActionEvent event) {
        try {
            
            ventanaJuego.initRootLayout();
            
        } catch (IndexOutOfBoundsException e) {
            
            System.out.println(e.getMessage());
        }
    }

    class Casilla extends Button {

        /*
		 * He creado mi propio tipo de botón para poder adjudicarle, además de
		 * un texto, las coordenadas.
		 * 
		 * Si no lo hago de esta forma, no tengo acceso a las coordenadas en las
		 * que está situado el botón.
		 * 
		 * Tengo que explorar otras posibles opciones, como otorgar a los
		 * botones atributos personalizados, pero no sé si el DOM de FXML lo
		 * permite.
         */
        private int x;
        private int y;

        public Casilla(int x, int y, String texto) {
            super(texto);
            this.x = x;
            this.y = y;

            // Adjudico una ID con las coordenadas a la casilla. Esto me será
            // útil para hacer referencia a dicha casilla en el futuro, aunque
            // aún no sé cómo, lo que hago de momento es meter cada objeto en
            // una matriz.
            this.setId("casilla_" + this.x + "_" + this.y);

            // Le adjudico el estilo llamado "casilla" de la plantilla CSS.
            this.getStyleClass().add("casilla");
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
