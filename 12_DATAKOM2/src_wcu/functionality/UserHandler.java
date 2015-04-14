package functionality;

import control.WeightCommunicator;

public class UserHandler{
	public static WeightCommunicator wc = new WeightCommunicator("10.16.169.246",4567);
	
	public UserHandler(){
	this.state = State.START;
	}
	// Sekvensmaskine initialiseres.
		public enum State {
			START {
				@Override
				State changeState(String svar) {
					switch(svar) {
					case :
						
						return STATE1;
					case -1:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return START;
					case -2:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STOP;
					default:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
				}
				}
			},
			STATE1 {

				@Override
				State changeState(String svar) {
					switch(x) {
					case 12:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STATE2;
					case -1:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return START;
					case -2:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STOP;
					default:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
				}
				}},
			STATE2 {

				@Override
				State changeState(String svar) {
					switch(x) {
					case 13:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STATE3;
					case -1:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return START;
					case -2:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STOP;
					default:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
				}
				}},
			STATE3 {

				@Override
				State changeState(String svar) {
					switch(x) {
					case 14:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STOP;
					case -1:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return START;
					case -2:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return STOP;
					default:
						// TODO: do something
						System.out.println("I am now changing from state " + this + " with int argument x = " + x);
						return INVALIDSTATE;
				}
				}},
			INVALIDSTATE {

				@Override
				State changeState(String svar) {
					System.out.println("invalidstate");
					return STOP;
				}},
			STOP {

				@Override
				State changeState(String svar) {
					return STOP;
				}};
			abstract State changeState(String svar);
		}

		private State state;
		
		public void doSomething(int x) {
			this.state = this.state.changeState(x);
		}
		
		public void blah(){
			WeightCommunicator wc = new WeightCommunicator("10.16.169.246",4567);
		}
		public static void main(String[] args) {
			// Start af kontrolsekvens
			System.out.println("Sekvensmaskinen startet.");
			UserHandler u = new UserHandler();
			u.doSomething(11);
			
		}
		
	}
