package functionality;

import control.WeightCommunicator;

public class UserHandler {
	public static WeightCommunicator wc = new WeightCommunicator("127.0.0.1",
			4567);
	public static boolean run = true;
	public static String vaegtSvar = "";
	int x;

	public UserHandler() {
		wc.connectToServer();
		this.state = State.START;
	}

	// Sekvensmaskine initialiseres.
	public enum State {
		START {
			@Override
			State changeState(int x) {
				switch (x) {
				case 10:
					vaegtSvar = wc
							.writeSocket("RM20 8 \"Indtast operatørID\" \"3\" \"hej\" \r\n");
					return STATE1;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE1 {

			@Override
			State changeState(int x) {
				if (vaegtSvar.equals("RM20 B")) {
					System.out.println("hejsa");
				}
				switch (x) {
				case 12:
					System.out.println("I am now changing from state " + this
							+ " with int argument x = " + x);
					System.out.println(wc.readSocket());
					return START;
				case -1:
					return START;
				case -2:
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		INVALIDSTATE {

			@Override
			State changeState(int x) {
				System.out.println("invalidstate");
				return STOP;
			}
		},
		STOP {

			@Override
			State changeState(int x) {
				return STOP;
			}
		};
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
		try {
			u.runScheme(10);
			u.runScheme(12);
		} catch (Exception e) {
			System.out.println("intet data på port");
		}
	}
}
