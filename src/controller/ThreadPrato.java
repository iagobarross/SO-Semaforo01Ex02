package controller;

import java.util.concurrent.Semaphore;

public class ThreadPrato extends Thread {

	private static Semaphore semaforo;
	private int idPrato;

	public ThreadPrato(int idPrato, Semaphore semaforo) {
		this.idPrato = idPrato;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		cozinhar();
		try {
			semaforo.acquire();
			entregar();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			semaforo.release();
		}
	}

	private void cozinhar() {
		String nomePrato;
		int tempoPrato;
		
		if (idPrato % 2 == 1) {
			nomePrato = "Sopa de Cebola";
			tempoPrato = (int) ((Math.random() * 301) + 500);

		} else {
			nomePrato = "Lasanha à Bolonhesa";
			tempoPrato = (int) ((Math.random() * 601) + 600);
		}	
		
			System.out.println("Prato #" + idPrato + " - " + nomePrato + " iniciado.");
			
			int tempoAtual = 0;
			int intervalo = 100;
			
			while(tempoAtual < tempoPrato) {
				int tempoRestante = tempoPrato - tempoAtual;
				int tempo = intervalo;
				
				if(tempoRestante < intervalo) {
					tempo = tempoRestante;
				}

				try {
					sleep(tempo);
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
				
				tempoAtual += tempo;
				
				int porcentagem = (tempoAtual * 100) / tempoPrato;
				System.out.println("Prato #" + idPrato + " - " + porcentagem + "% cozido");
			}
		
	}

	private void entregar() {
		int tempoEntrega = 500;
		System.out.println("O prato #" + idPrato + " foi entregue.");
		try {
			sleep(tempoEntrega);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
