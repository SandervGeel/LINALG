package controller;

public class Main {

	public static void main(String[] args) {
		
		boolean running = true;
		
		Controller controller = new Controller();
		controller.start();
		
		while(running)
		{
			controller.run();
		}
	}

}
