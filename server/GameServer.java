import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameServer {
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(5000);
        try {  
            while (true) {
                Socket socket = listener.accept();
                try {
					int i=0;
					do{
				 		BufferedReader input =
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
						char[][] game;
						String data;
						data = input.readLine();
						if(data.equals("new")){
							game= initGame();
						}
						else{


                        PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                        if(i<5)
                            out.println(playGame().toString());
                        else
                            out.println("tie");

						}
                    i++;
					}
					while(i<6);

                } finally {
                    socket.close();
                }
            }
        }
        finally {
            listener.close();
        }
    }

    public static char [][] initGame(){

        char game[][] = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = '8';
            }
        }
		return game;
	}
	public static char[][] readGame(String iStr){
		char game[][] = new char[3][3];
        game[0] = iData.substring(0,2).toCharArray();
        game[1] = iData.substring(3,5).toCharArray();
        game[2] = iData.substring(6,8).toCharArray();
		return game;
	}
	public static int checkTie(char[][] iData){

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(iData[i][j] != '_'){
					return 1;
				}
            }
        }
		return 0;
	}

    public static String playGame(char[][] iData){
		int res = checkGame(iData);
		if(res ==0){
		    String buffer = "";
			char[][] game = makeMove(iData);
		    for (int i=0; i < 3; i++) {
		        for (int j=0; j < 3; j++) {
		            buffer += game[i][j];
		        }
		    }
		}
        return buffer;
    }

	public static int checkGame( char[][] iData){
	//incomplete 0, 1 tie, 2 Xwin, 3 O win
		//check columns
		arr = null;
		char [] arr = new char[]{iData[0][0],iData[1][0],iData[2][0]};
		int res = check(arr);
		if(res !=0)
			return res;
		arr = null;
		arr = new char[]{iData[0][1],iData[1][1],iData[2][1]};
		res = check(arr);
		if(res !=0)
			return res;
		arr = null;
		arr = new char[]{iData[0][2],iData[1][2],iData[2][2]};
		res = check(arr);
		if(res !=0)
			return res;

     //		columns
		arr = null;
		arr = new char[]{iData[0][0],iData[0][1],iData[0][2]};
		res = check(arr);
		if(res !=0)
			return res;
		arr = null;
		arr = new char[]{iData[1][0],iData[1][1],iData[1][2]};
		res = check(arr);
		if(res !=0)
			return res;
		arr = null;
		arr = new char[]{iData[2][0],iData[2][1],iData[2][2]};
		res = check(arr);
		if(res !=0)
			return res;

		//check diagonals
		arr = null;
		arr = new char[]{iData[0][0],iData[1][1],iData[2][2]};
		res = check(arr);
		if(res !=0)
			return res;
		arr = null;
		arr = new char[]{iData[0][2],iData[1][1],iData[2][0]};
		res = check(arr);
		if(res !=0)
			return res;
		
		return checkTie(res);
	}
	public static int check(char[] elems){
		//Typetester typeTester = new Typetester();
		//typeTester.printType("X");
		if(elems.length>=3)
		{
			//typeTester.printType(elems[0]);			
			if(elems[0] =='X' && elems[1] =='X' && elems[2] =='X'){
				return 2;
			}
			else if(elems[] =='O' && elems[1] =='O' && elems[2] =='O'){
				return 3;
			}
			if(elems[0] =='_' || elems[1] =='_' || elems[2] =='_'){
				return 0; 
			}
			else {
				return 0;	
			}
		}
		return 0;
	}
	public static char[][] makeMove(char[][] game){
		outerloop:
		for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(game[i][j] == '_')
				{
					game[i][j] = 'O';
					break outerloop;
				}
            }
        }
		return game;
    }
}
