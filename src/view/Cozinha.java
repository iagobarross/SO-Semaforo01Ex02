package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPrato;

public class Cozinha {

	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		
		for(int i = 0; i < 5; i++) {
			Thread t = new ThreadPrato(i + 1, semaforo);
			t.start();
		}
	}

}
