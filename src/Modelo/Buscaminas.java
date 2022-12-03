/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Mateo
 */
public class Buscaminas {

    private boolean[][] tablero_minas;
    private int[][] minas_alrededor;
    private boolean[][] destapadas;
    private boolean[][] banderas;

    private int vidas;
    private int totalMinas;
    private int ancho;
    private int alto;
    private boolean soportaBanderas;

    private boolean gameOver;
    private boolean ganador;
    private int vidasRestantes;
    private int casillasTapadasRestantes;
    private int minasSinBanderaRestante;

    //En el contructor crearemos una exepcion llamada demasiadasMinasException
    //con el objetivo de construir un tablero dado su tamanio y el numero de minas
    //Por lo tanto no se puede crear un tablero con mas minas que casillas disponibles
    public Buscaminas(int x, int y, int minas, int vidas)// X->ancho del tablero (numero de columnas) y Y->alto del tablero (numero de filas)
            throws DemasiadasMinasException, NumeroDeVidasFueraDeRangoException {
        if (minas > x * y) {
            throw new DemasiadasMinasException();
        }
        if (vidas > minas || vidas < 1) {
            throw new NumeroDeVidasFueraDeRangoException();
        }

        tablero_minas = new boolean[y][x];
        minas_alrededor = new int[y][x];
        destapadas = new boolean[y][x];
        banderas = new boolean[y][x];

        this.vidas = vidas;
        vidasRestantes = vidas;
        totalMinas = minas;
        ancho = x;
        alto = y;

        casillasTapadasRestantes = ancho * alto - totalMinas;
        minasSinBanderaRestante = minas;

        soportaBanderas = false;
        gameOver = false;
        ganador = false;

        // ponemos las minas al rededor.
        ponerMinas(minas);

        // generamos la matriz de pistas.
        minasAlrededor();
    }

    //Este constructor crea un juego de buscaminas sin nesecidad de especificar
    //el numero de vidas, el numero de vidas sera el mismo que el numero de minas.
    public Buscaminas(int x, int y, int minas) throws DemasiadasMinasException, NumeroDeVidasFueraDeRangoException {
        this(x, y, minas, minas);
    }

    public void marcarBandera(int x, int y) {
        if (soportaBanderas && !gameOver) {
            banderas[y][x] = !banderas[y][x];

            // Al poner una bandera, calculamos si es correcta o no para saber si
            // el juego ha terminado.
            if (hayMina(x, y) && tieneBandera(x, y)) {
                minasSinBanderaRestante--;
            } else if (hayMina(x, y) && !tieneBandera(x, y)) {
                minasSinBanderaRestante++;
            } else if (!hayMina(x, y) && tieneBandera(x, y)) {
                minasSinBanderaRestante++;
            } else if (!hayMina(x, y) && !tieneBandera(x, y)) {
                minasSinBanderaRestante--;
            }

            if (minasSinBanderaRestante == 0 && casillasTapadasRestantes == 0) {
                gameOver = true;
                ganador = true;
            }
        }
    }

    //Metodo tipo booleano me sirve para verificar si hay minas en cada posicion de
    //la matriz
    public boolean hayMina(int x, int y) {
        if (x < 0 || x >= tablero_minas[0].length) {
            return true;
        }
        if (y < 0 || y >= tablero_minas.length) {
            return true;
        }
        return tablero_minas[y][x];
    }

    public boolean tieneBandera(int x, int y) {
        return banderas[y][x];
    }

    private void ponerMinas(int minas) {
        int x, y, totalMinas;
        totalMinas = minas;
        while (totalMinas > 0) {
            x = (int) (Math.random() * ancho);
            y = (int) (Math.random() * alto);
            if (!tablero_minas[y][x]) {
                tablero_minas[y][x] = true;
                totalMinas--;
            }
        }
    }

    //Este es un metodo privado, donde solo lo va usar esta clase
    //basicamente lo que hace este metodo es representar un mapa de minas
    //detectadas alrededor de la casilla.
    private void minasAlrededor() {
        int[] lasI = {-1, -1, 0, 1, 0, 1, 1, -1, 0};
        int[] lasJ = {-1, 0, -1, 1, 1, 0, -1, 1, 0};

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < ancho; j++) {
                for (int k = 0; k < 9; k++) {
                    // Este try-catch evita que cuente las minas alrededor en
                    // aquellas casillas fuera del rango.
                    try {
                        if (tablero_minas[i + lasI[k]][j + lasJ[k]]) {
                            minas_alrededor[i][j]++;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        //si sale error, no nesecita hacer nada, simplemente que
                        //continue el bucle.
                    }
                }
            }
        }
    }

    public int ancho() {
        return ancho;
    }

    public int alto() {
        return alto;
    }

    // Metodo importante porque da informacion si el juego continua o termina
    public boolean isGameOver() {
        return gameOver;
    }

    //Este metodo cavar sirve mas que todo si la casilla seleccionada
    //tiene mina, el numero de vidas descrementa.
    //En dado caso de que se acaven las vidas, el juego terminara. Por tanto consultara 
    // si es un gameOver
    public void cavar(int x, int y) {
        //si el juego termina, o se la casilla esta protegida por una bandera,
        // no ejecutamos nada. Si la casilla tiene bandera y el usuario quiere cavar en ella,
        //tendra que retirar la bandera previamente.
        if (gameOver || tieneBandera(x, y)) {
            return;
        }

        //Si hay mina
        if (hayMina(x, y)) {

            if (soportaBanderas) {
                minasSinBanderaRestante--;
            }

            //si quedan vidas
            if (vidasRestantes > 0) {
                vidasRestantes--;
            } else {
                //Acaba el juego
                gameOver = true;
                ganador = true;
            }
        }
            destapar(x, y);
        if(vidasRestantes == 0)
            gameOver = true;
    }

    private void destapar(int x, int y) {
        //En esta parte ademas de destapar las casillas se deside si ha finalizado el juego

        if (!destapadas[y][x] && !banderas[y][x]) {
            destapadas[y][x] = true;

            if (!hayMina(x, y)) {
                casillasTapadasRestantes--;
            }
            if (soportaBanderas) {
                if (casillasTapadasRestantes == 0 && minasSinBanderaRestante == 0) {
                    gameOver = true;
                }
            } else {
                if (casillasTapadasRestantes == 0) {
                    gameOver = true;
                }
            }

            if (gameOver && vidasRestantes > 0) {
                ganador = true;
            }

            if (!hayMinasAlrededor(x, y)) {

                //Compruebo los lados
                //Compruebo arriba y abajo
                if (y > 0 && !estaDestapada(x, y - 1)) {
                    destapar(x, y - 1);
                }
                if (y < alto() && !estaDestapada(x, y + 1)) {
                    destapar(x, y + 1);
                }

                //Ahora compruebo de izquierda a derecha
                if (x > 0 && !estaDestapada(x - 1, y)) {
                    destapar(x - 1, y);
                }
                if (x < ancho() && !estaDestapada(x + 1, y)) {
                    destapar(x + 1, y);
                }

                // Compruebo en las diagonales
                // Diagonal superior izquierda
                if (x > 0 && y > 0 && !estaDestapada(x - 1, y - 1)) {
                    destapar(x - 1, y - 1);
                }

                // Diagonal superior derecha
                if (x < ancho() && y > 0 && !estaDestapada(x + 1, y - 1)) {
                    destapar(x + 1, y - 1);
                }

                // Diagonal inferior izquierda
                if (x > 0 && y < alto() && !estaDestapada(x - 1, y + 1)) {
                    destapar(x - 1, y + 1);
                }

                // Diagonal inferior derecha
                if (x < ancho() && y < alto() && !estaDestapada(x + 1, y + 1)) {
                    destapar(x + 1, y + 1);
                }
            }
        }
    }

    public boolean hayMinasAlrededor(int x, int y) {
        return minas_alrededor[y][x] > 0;
    }

    //Si la casilla queda destapada mostrara el numero de minas al rededor de esa casilla
    public boolean estaDestapada(int x, int y) {
        if (x < 0 || x >= destapadas[0].length) {
            return true;
        }
        if (y < 0 || y >= destapadas.length) {
            return true;
        }
        return destapadas[y][x];
    }
    
    public int contarMinasAlrededor(int x, int y){
        return minas_alrededor[y][x];
    }
    
    public boolean[][] mapaDeMinas(){
        return tablero_minas;
    }
    
    public int[][] mapaMinasAlrededor(){
        return minas_alrededor;
    }
    
    //Metodo importantes depues de haber jugado para que no se ropa el programa 
    //es nesesario crear un reset, para empezar otra partida.
    public void reset(){
        vidasRestantes = vidas;
        
        gameOver = false;
        ganador = false;
        
        destapadas = new boolean[alto][ancho];
        banderas = new boolean[alto][ancho];
        
        casillasTapadasRestantes = ancho * alto - totalMinas;
        minasSinBanderaRestante = totalMinas;
    }
    
    public int getTotalVidas(){
        return vidas;
    }
    
    public int getVidasRestantes(){
        return vidasRestantes;
    }
    
    public boolean  isGanador(){
        return ganador;
    }
    
    public boolean hayBanderas(){
        return soportaBanderas;
    }
    
    public void soportaBanderas(boolean hayBanderas){
        this.soportaBanderas = hayBanderas;
    }
}
