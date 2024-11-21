package Buscaminas;

import java.util.Random;

public class JuegoBuscaminas {
	private Casilla[][] tablero;
	private int numCasillas, minasPorNivel, dimension, minasDescubiertas;

	public JuegoBuscaminas(int nivel) {
		if (nivel == 1) {
			numCasillas = 64;
			minasPorNivel = 10;
			dimension = 8;
		} else if (nivel == 2) {
			numCasillas = 144;
			minasPorNivel = 30;
			dimension = 12;
		} else {
			numCasillas = 256;
			minasPorNivel = 60;
			dimension = 16;
		}
		minasDescubiertas = 0;
	}

	public int getMinasDescubiertas() {
		return minasDescubiertas;
	}

	public int getMinasPorNivel() {
		return minasPorNivel;
	}

	public void iniciarTablero() {

		tablero = new Casilla[dimension][dimension];

		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				tablero[i][j] = new Casilla();
			}
		}

		randomMinas();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (!tablero[i][j].dimeSiTieneMina()) {
					calculaMinasCercanas(i, j);
				} else {
					tablero[i][j].setNumMinasCercanas(9);
				}
			}
		}

	}
	
	public void imprimirTablero() {
        System.out.println("\n");
        for (int i = 0; i < tablero.length + 1; i++) {
            if (i == 0) {
                System.out.print("      ");
            } else {
                if (i<10) {
                    System.out.print("  " + i + "  ");
                } else {
                    System.out.print(" " + i + "  ");
                }
            }
        }
        System.out.println("\n");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length +1; j++) {
                if (j == 0) {
                    if (i<9) {
                        System.out.print("\n " + (i + 1) + "    ");
                    } else {
                        System.out.print("\n " + (i + 1) + "   ");
                    }
                }else {
                    if(tablero[i][j-1].dimeSiEstaMarcada()) {
                        System.out.print("  F  ");
                    }else if (!tablero [i][j-1].dimeSiEstaOculta()) {
                        System.out.print("  " + tablero[i][j-1].dameNumMinasCerca() + "  ");

                    }else {
                        System.out.print("  ─  ");
                    }
                }
            }
            System.out.println();
        }
    }

	public boolean movimientoValido(int niv, int fi, int co) {
		if (niv == 1) {
			if (fi < 0 || fi > dimension-1 || co < 0 || co > dimension-1) {
				return false;
			}
		}

		if (tablero[fi][co].dimeSiEstaMarcada() || !tablero[fi][co].dimeSiEstaOculta()) {
			return false;
		}

		return true;
	}

	public void descubrirCasilla(int fi, int co) {
		if (tablero[fi][co].dimeSiTieneMina()) {
			tablero[fi][co].setDescubresMina();
		} else {
			tablero[fi][co].mostrarCasilla();
			numCasillas--;
			if(tablero[fi][co].dameNumMinasCerca()==0) {
				for (int i=fi-1; i<fi+2; i++) {
		            for (int j=co-1; j<co+2; j++) {
		                if (i != -1 && j != -1 && i != dimension && j != dimension) {
		                    if (tablero[i][j].dameNumMinasCerca()==0 && tablero[i][j].dimeSiEstaOculta()) {
		                        descubrirCasilla(i,j);
		                    } else {
		                        tablero[i][j].mostrarCasilla();
		                    }
		                }
		            }
		        }
			}
			
		}
	}

	public void marcarCasilla(int fi, int co) {
		if (tablero[fi][co].dimeSiTieneMina()) {
			tablero[fi][co].marcarCasilla();
			minasDescubiertas++;

		} else {
			tablero[fi][co].setBanderaIncorrecta();
		}
	}

	private void calculaMinasCercanas(int f, int c) {
		for (int i = f - 1; i < f + 2; i++) {
			for (int j = c - 1; j < c + 2; j++) {
				if (i != -1 && j != -1 && i != dimension && j != dimension) {
					if (tablero[i][j].dimeSiTieneMina()) {
						tablero[f][c].aumentaMinas();
					}
				}

			}
		}
	}

	public int causaTerminacionJuego(int niv, int f, int c) {

		if (numCasillas == minasPorNivel) {
			return 1;
		}
		if (tablero[f][c].dameDescubresMina()) {
			return 2;
		}
		if (!tablero[f][c].dameBandera()) {
			return 3;
		}
		if (minasDescubiertas == minasPorNivel) {
			return 4;
		}
		return 0;
	}

	private void randomMinas() {

		Random aleatorio = new Random();
		int i = 0;
		while (i < minasPorNivel) {
			int f = aleatorio.nextInt(dimension);
			int c = aleatorio.nextInt(dimension);
			if (!tablero[f][c].dimeSiTieneMina()) {
				tablero[f][c].setTieneMina();
				i++;
			}
		}

	}

}
