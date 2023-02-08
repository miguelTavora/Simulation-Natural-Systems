package AutomatosCelulares;

import fractals.IProcessingApp;
import processing.core.PApplet;

public class RegraDaMinoria implements IProcessingApp {

	private int cellTamanho = 15;
	private int[][] cells;
	// guarda valores das cells para mudar a cada iterecao
	private int[][] cellsIteracao;

	private int cont = 0;
	private int widthValue;
	private int heightValue;

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
				float LandOcean = p.random(100);
				if (LandOcean > 50) {
					LandOcean = 0; // ocean
				} else {
					LandOcean = 1; // land
				}
				cells[i][j] = (int) LandOcean;
			}
		}
		p.background(0, 0, 255);
	}

	public void draw(PApplet p) {
		// Grelha
		for (int i = 0; i < p.width / cellTamanho; i++) {
			for (int j = 0; j < p.height / cellTamanho; j++) {
				if (cells[i][j] == 1) {
					p.fill(0, 255, 0); // land
				} else {
					p.fill(0, 0, 255); // ocean
				}

				p.rect(i * cellTamanho, j * cellTamanho, cellTamanho, cellTamanho);
			}
		}

		// aplicar regra 8 vezes
		if (cont < 8) {
			iteration();
			cont++;
		}

	}

	void iteration() {

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
				if (cellsIteracao[i][j] == 1) { // if land
					if (vizinhos < 4) {
						cells[i][j] = 0;
					}
				} else { // if ocean
					if (vizinhos >= 5) {
						cells[i][j] = 1;
					}
				}
			}
		}
	}

	public void mousePressed(PApplet p) { // mouse click cria mapa diferente
		for (int i = 0; i < p.width / cellTamanho; i++) {
			for (int j = 0; j < p.height / cellTamanho; j++) {
				float LandOcean = p.random(100);
				if (LandOcean > 50) {
					LandOcean = 0;
				} else {
					LandOcean = 1;
				}
				cells[i][j] = (int) LandOcean; // Save state of each cell
			}
		}

		for (int i = 0; i < 8; i++) {
			iteration();
		}
	}

	@Override
	public void keyPressed(PApplet p) {
		// TODO Auto-generated method stub

	}

}
