/*
 * Clicar no espaço para continuar/pausar o jogo
 * Se estiver pausado, é possível inserir e/ou matar cells
 * 
 * Tecla R reinicia as células *  
 * 
 * */

package AutomatosCelulares;

import fractals.IProcessingApp;
import processing.core.PApplet;

public class JogoDaVida implements IProcessingApp {

	private int cellTamanho = 15;

	private int[][] cells;

	// guarda valores das cells para mudar a cada iterecao
	private int[][] cellsIteracao;

	private int intervalo = 150;
	private int ultimoTempoGuardado = 0;

	private boolean pausa = false;

	private float probabilidadeVivo = 15;

	private int widthValue;
	private int heightValue;

	@Override
	public void setup(PApplet p) {

		widthValue = p.width;
		heightValue = p.height;

		cells = new int[p.width / cellTamanho][p.height / cellTamanho];
		cellsIteracao = new int[p.width / cellTamanho][p.height / cellTamanho];

		// stroke para a grelha do background
		p.stroke(50);

		// inicializacao das cells
		for (int i = 0; i < p.width / cellTamanho; i++) {
			for (int j = 0; j < p.height / cellTamanho; j++) {
				float VivoMorto = p.random(100);
				if (VivoMorto > probabilidadeVivo) {
					VivoMorto = 0; // morto
				} else {
					VivoMorto = 1; // vivo
				}
				cells[i][j] = (int) VivoMorto;
			}
		}
		p.background(0);

	}

	@Override
	public void draw(PApplet p) {

		// Grelha
		for (int i = 0; i < p.width / cellTamanho; i++) {
			for (int j = 0; j < p.height / cellTamanho; j++) {
				if (cells[i][j] == 1) {
					p.fill(255); // vivo
				} else {
					p.fill(0); // morto
				}
				p.rect(i * cellTamanho, j * cellTamanho, cellTamanho, cellTamanho);
			}
		}

		// novas cells manualmente se o jogo estiver em pausa
		if (pausa && p.mousePressed) {
			int xCell = (int) PApplet.map(p.mouseX, 0, p.width, 0, p.width / cellTamanho);
			xCell = PApplet.constrain(xCell, 0, p.width / cellTamanho - 1);
			int yCell = (int) PApplet.map(p.mouseY, 0, p.height, 0, p.height / cellTamanho);
			yCell = PApplet.constrain(yCell, 0, p.height / cellTamanho - 1);

			// verifica se onde o user clica, a cell está viva ou morta
			if (cellsIteracao[xCell][yCell] == 1) { // Cell esta viva
				cells[xCell][yCell] = 0; // matar cell
				p.fill(0);
			} else { // Cell esta morta
				cells[xCell][yCell] = 1; // meter viva
				p.fill(255); //
			}
		}

		// guarda os valores
		else if (pausa && !p.mousePressed) {
			for (int i = 0; i < p.width / cellTamanho; i++) {
				for (int j = 0; j < p.height / cellTamanho; j++) {
					cellsIteracao[i][j] = cells[i][j];
				}
			}
		}

		// iteração do tempo
		if (p.millis() - ultimoTempoGuardado > intervalo) {
			// se não estiver pausado, desenvolvem-se naturalmente
			if (!pausa) {
				iteration();
				ultimoTempoGuardado = p.millis();
			}
		}

	}

	void iteration() { // quando o clock está a contar

		for (int i = 0; i < widthValue / cellTamanho; i++) {
			for (int j = 0; j < heightValue / cellTamanho; j++) {
				cellsIteracao[i][j] = cells[i][j];
			}
		}

		// cada cell
		for (int i = 0; i < widthValue / cellTamanho; i++) {
			for (int j = 0; j < heightValue / cellTamanho; j++) {
				// cells vizinhas de cada cell
				int vizinhos = 0;
				for (int x = i - 1; x <= i + 1; x++) {
					for (int y = j - 1; y <= j + 1; y++) {
						// para não estar outOfBonds
						if (((x >= 0) && (x < widthValue / cellTamanho)) && ((y >= 0) && (y < heightValue / cellTamanho))) {
							if (!((x == i) && (y == j))) {
								if (cellsIteracao[x][y] == 1) {
									vizinhos++; // contar os vizinhos
								}
							}
						}
					}
				}
				// Aplicar as regras
				if (cellsIteracao[i][j] == 1) { // cell está viva
					if (vizinhos < 2 || vizinhos > 3) {
						cells[i][j] = 0; // Morre, a não ser que tenha 2 ou 3 vizinhos
					}
				} else { // cell morta
					if (vizinhos == 3) {
						cells[i][j] = 1; // fica viva se tiver 3 vizinhos
					}
				}
			}
		}
	}

	@Override
	public void keyPressed(PApplet p) {

		if (p.key == 'r' || p.key == 'R') {
			for (int i = 0; i < p.width / cellTamanho; i++) {
				for (int j = 0; j < p.height / cellTamanho; j++) {
					float VivoMorto = p.random(100);
					if (VivoMorto > probabilidadeVivo) {
						VivoMorto = 0;
					} else {
						VivoMorto = 1;
					}
					cells[i][j] = (int) VivoMorto; // Save state of each cell
				}
			}
		}

		if (p.key == ' ') {
			pausa = !pausa;
		}

	}

	@Override
	public void mousePressed(PApplet p) {
		// TODO Auto-generated method stub

	}

}
