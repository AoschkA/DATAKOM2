package functionality;

import control.WeightCommunicator;

public class UserHandler {
	public static WeightCommunicator wc = new WeightCommunicator("127.0.0.1",
			4567);
	public static OprControl oc = new OprControl();
	public static RaavareControl rc = new RaavareControl();
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
			State changeState(String svar) {
				switch (svar) {
				case "ja":
					vaegtSvar = wc
							.writeSocket("RM20 8 \"OperatoerID?\" \" \" \" \" \r\n");
					vaegtSvar = wc.readSocket();
					vaegtSvar = vaegtSvar.substring(7, vaegtSvar.length());
					return STATE1;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE1 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					vaegtSvar = wc.writeSocket("RM20 8 \"Varenummer?\" \" \" \" \" \r\n");
					vaegtSvar = wc.readSocket();
					vaegtSvar = vaegtSvar.substring(7, vaegtSvar.length());
					return STATE2;
				case "nej":
					return START;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE2 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					vaegtSvar = wc.writeSocket("RM20 8 \"" + rc.getRaavare(Integer.parseInt(vaegtSvar)).getRaavareName() + "?\"\"ja \" \" nej\" \r\n");
					vaegtSvar = wc.readSocket();
					vaegtSvar = vaegtSvar.substring(7, vaegtSvar.length());
					return STATE3;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE3 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					System.out.println("KLAPHAT");
					return STATE4;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},		
		STATE4 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					System.out.println("State 4!");
					return STATE5;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE5 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					return STATE6;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},		
		STATE6 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					return STATE7;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE7 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					return STATE8;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE8 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					return STATE9;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		STATE9 {

			@Override
			State changeState(String svar) {
				
				switch (svar) {
				case "ja":
					return START;
				case "Q":
					return STOP;
				default:
					return INVALIDSTATE;
				}
			}
		},
		INVALIDSTATE {

			@Override
			State changeState(String svar) {
				System.out.println("invalidstate " + svar);
				return STOP;
			}
		},
		STOP {

			@Override
			State changeState(String svar) {
				return STOP;
			}
		};
		abstract State changeState(String svar);
	}

	private State state;

	public void runScheme(String svar) {
		this.state = this.state.changeState(svar);
	}

	public static void main(String[] args) {
		// Start af kontrolsekvens
		UserHandler u = new UserHandler();
		try {
			u.runScheme("ja");
			System.out.println(vaegtSvar);
			if(oc.getOperatoer(Integer.parseInt(vaegtSvar)).getOprID() != 999999){
				u.runScheme("ja");
			}
			System.out.println(vaegtSvar);
			if(rc.getRaavare(Integer.parseInt(vaegtSvar)).getRaavareID() != 999999) {
				u.runScheme("ja");
			}
			System.out.println(vaegtSvar);
			if(vaegtSvar.equals("ja")){
				u.runScheme("ja");
			}
		} catch (Exception e) {
			System.out.println("intet data på port");
		}
	}
}
