package functionality;

public class UserHandler {

	// Sekvensmaskine initialiseres.
		public enum State {
			START {
				@Override
				State changeState(int x) {
					return STATE1;
				}
			},
			STATE1 {

				@Override
				State changeState(int x) {
					return STATE2;
				}},
			STATE2 {

				@Override
				State changeState(int x) {
					return STATE3;
				}},
			STATE3 {

				@Override
				State changeState(int x) {
					return STOP;
				}},
			INVALIDSTATE {

				@Override
				State changeState(int x) {
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
		
		public UserHandler() {
			this.state = State.START;
		}
		
		public void doSomething(int x) {
			this.state = this.state.changeState(x);
		}
		
		
		public static void main(String[] args) {
			// Start af kontrolsekvens
			System.out.println("Sekvensmaskinen startet.");
			UserHandler u = new UserHandler();
			u.doSomething(11);
		}
		
	}
