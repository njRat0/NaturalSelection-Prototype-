package game;

public class Player{
	//parameters
	public static float positionOfViewX = 0;
	public static float positionOfViewY = 0;
	public static float sizeOfView = 5; 
	public static float movingSpeed = 3;

	public Player() {

	}

	public static void Update(){
		if(UserInputService.keyUP){
			positionOfViewY -= movingSpeed;
		}

		if(UserInputService.keyDOWN){
			positionOfViewY += movingSpeed;
		}

		if(UserInputService.keyLEFT){
			positionOfViewY -= movingSpeed;
		}

		if(UserInputService.keyRIGHT){
			positionOfViewY += movingSpeed;
		}
	}
}

