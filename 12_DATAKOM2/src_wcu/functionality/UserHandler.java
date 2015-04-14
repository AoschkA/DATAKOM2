package functionality;

import control.WeightCommunicator;

public class UserHandler{
	public static WeightCommunicator wc = new WeightCommunicator("127.0.0.1",4567);
	public static boolean run = true;
	int x;
	
	public UserHandler(){
		wc.connectToServer();
		this.state = State.START;
	}
	// Sekvensmaskine initialiseres.
		public enum State {
			START {
				@Override
				State changeState(int x) {
					System.out.println(x);
					switch(x) {
					case 10:
						System.out.println("enteringstart");
						return STATE1;
					default:
						// TODO: do something
						//System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
					}
				}
			},
			STATE1 {

				@Override
				State changeState(int x) {
					switch(x) {
					case 12:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return START;
//					case -1:
//						// TODO: do something
//						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
//						return START;
//					case -2:
//						// TODO: do something
//						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
//						return STOP;
					default:
						// TODO: do something
						//System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
				}
				}},
			INVALIDSTATE {

				@Override
				State changeState(int x) {
					System.out.println("invalidstate");
					return STOP;
				}},
			STOP {

				@Override
				State changeState(int x) {
					return STOP;
				}};
			abstract State changeState(int x);
		}

		private State state;
		
		public void runScheme(int x) {
			this.state = this.state.changeState(x);
		}
		
		public static void main(String[] args) {
			// Start af kontrolsekvens
			System.out.println("Sekvensmaskinen startet.");
			UserHandler u = new UserHandler();
			//while(run){
				try{
					//wc.writeSocket("RM20 8 \"Indtast OperatørID\" \"\" \"\" crlf");
					String test = "";
					System.out.println("Operatør ID anmodet");
					test = wc.writeSocket("RM20 8 \"hejsa\" \"342\" \"kkk\" \r\n");
					System.out.println(test);
					if (test.equals("RM20 B")){
						System.out.println("hej inde i if");
						u.runScheme(10);
					}
					test = wc.readSocket();
					if (test.startsWith("RM20 A")){
						System.out.println("hej inde i anden if");
						u.runScheme(12);
					}
					
				}catch(Exception e){
					System.out.println("intet data på port");
				}
			//}
		}
		
	}
