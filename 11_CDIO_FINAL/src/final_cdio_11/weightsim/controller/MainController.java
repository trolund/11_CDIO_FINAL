package final_cdio_11.weightsim.controller;

import final_cdio_11.weightsim.socket.ISocketController;
import final_cdio_11.weightsim.socket.ISocketObserver;
import final_cdio_11.weightsim.socket.SocketInMessage;
import final_cdio_11.weightsim.socket.SocketOutMessage;
import final_cdio_11.weightsim.weight.IWeightInterfaceController;
import final_cdio_11.weightsim.weight.IWeightInterfaceObserver;
import final_cdio_11.weightsim.weight.KeyPress;
import sun.misc.FloatingDecimal;

/**
 * MainController - integrating input from socket and ui. Implements
 * ISocketObserver and IUIObserver to handle this.
 * 
 * @author Christian Budtz
 * @version 0.1 2017-01-24
 *
 */
public class MainController implements IMainController, ISocketObserver, IWeightInterfaceObserver {

	private double brutto, tara;
	private String charsPressed = "";

	private ISocketController socketHandler;
	private IWeightInterfaceController weightController;
	private KeyState keyState = KeyState.K1;
	private SocketInMessage.SocketMessageType currentKeyState;

	public MainController(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) {
		this.init(socketHandler, weightInterfaceController);
	}

	@Override
	public void init(ISocketController socketHandler, IWeightInterfaceController weightInterfaceController) {
		this.socketHandler = socketHandler;
		this.weightController = weightInterfaceController;
	}

	@Override
	public void start() {
		if (socketHandler != null && weightController != null) {
			// Makes this controller interested in messages from the socket
			socketHandler.registerObserver(this);
			// Starts socketHandler in own thread
			new Thread(socketHandler).start();
			// Makes this controller interested in messages from the socket
			weightController.registerObserver(this);
			// Starts weightController in own thread
			new Thread(weightController).start();
		} else {
			System.err.println("No controllers injected!");
		}
	}

	// Listening for socket input
	@Override
	public void notify(SocketInMessage message) {
		switch (message.getType()) {
		case B:
			// Sætter brutto på vægt simulator til det givne antal kg.
			// eks. B 1.234 crlf
			notifyWeightChange(FloatingDecimal.parseDouble(message.getMessage() + "kg"));
			break;
		case D:
			// Max 7 characters is showed onto the display. Skriver vægt i display.
			weightController.showMessagePrimaryDisplay(message.getMessage().substring(0, message.getMessage().length()));
			socketHandler.sendMessage(new SocketOutMessage("D A"));
			break;
		case Q:
			// Programmet skal afsluttes.
			System.exit(0);
			break;
		case RM204:
			break;
		case RM208:
			// Skriv i displayet og afvent indtastning.
			// Vægten viser en besked til brugeren og afventer brugerens input.
			// Der sendes en bekræftelse. Når brugerens input er afsluttet
			// sendes dette.
			// NB. Der er 2 svar fra vægten.
			break;
		case S:
			// Send stabil afvejning.
			socketHandler.sendMessage(new SocketOutMessage("S S      " + (this.brutto - this.tara))); // netto
			break;
		case T:
			// Vægt tarares.
			this.tara = this.brutto;
			this.brutto = 0;
			weightController.showMessagePrimaryDisplay("0.000kg");
			socketHandler.sendMessage(new SocketOutMessage("T S      " + this.tara + " kg"));
			break;
		case DW:
			// Vægtens display ryddes og vægten svarer med en bekræftelse.
			this.tara = 0;
			notifyWeightChange(0);
			socketHandler.sendMessage(new SocketOutMessage("DW A"));
			break;
		case K:
			// K skifter vægtens knap tilstand. Når der trykkes på
			// funktionstaster (tara, zero, [->, send)
			// afhænger resultatet af vægtens tilstand.
			handleKMessage(message);
			break;
		case P111:
			// Skriver max 30 tegn i sekundært display.
			String txt = message.getMessage().substring(2, message.getMessage().length());
			if (txt.length() > 30) txt = txt.substring(0, 30);
			weightController.showMessageSecondaryDisplay(txt);
			socketHandler.sendMessage(new SocketOutMessage("P111 A"));
			break;
		}
	}

	private void handleKMessage(SocketInMessage message) {
		switch (message.getMessage()) {
		case "1":
			this.keyState = KeyState.K1;
			break;
		case "2":
			this.keyState = KeyState.K2;
			break;
		case "3":
			this.keyState = KeyState.K3;
			break;
		case "4":
			this.keyState = KeyState.K4;
			break;
		default:
			socketHandler.sendMessage(new SocketOutMessage("ES"));
			break;
		}
	}

	// Listening for UI input
	@Override
	public void notifyKeyPress(KeyPress keyPress) {
		// TODO implement logic for handling input from ui
		System.out.println("Tastet: " + keyPress.getCharacter());
		switch (keyPress.getType()) {
		case SOFTBUTTON:
			break;
		case TARA:
			this.tara = this.brutto;
			this.brutto = 0;
			charsPressed = "";
			weightController.showMessagePrimaryDisplay("0.000kg");
			socketHandler.sendMessage(new SocketOutMessage("T S      " + this.tara + " kg"));
			break;
		case TEXT:
			charsPressed += keyPress.getCharacter();
			weightController.showMessagePrimaryDisplay(charsPressed + "kg");
			break;
		case ZERO:
			this.tara = 0;
			this.brutto = 0;
			charsPressed = "";
			weightController.showMessagePrimaryDisplay(brutto + "kg");
			break;
		case C:
			charsPressed = "";
			weightController.showMessagePrimaryDisplay(charsPressed + "kg");
			break;
		case EXIT:
			System.exit(0);
			break;
		case SEND:
			if (keyState.equals(KeyState.K4) || keyState.equals(KeyState.K3)) {
				socketHandler.sendMessage(new SocketOutMessage("K A 3"));
			}
			if (currentKeyState == SocketInMessage.SocketMessageType.RM208) {
				socketHandler.sendMessage(new SocketOutMessage("RM20 A " + charsPressed));
			}
			currentKeyState = null;
			charsPressed = "";
			break;
		}

	}

	@Override
	public void notifyWeightChange(double newWeight) {
		this.brutto = newWeight;
		weightController.showMessagePrimaryDisplay("" + (this.brutto - this.tara));
	}

}