package TimeSeries;

import test.Commands.DefaultIO;
import test.Server.ClientHandler;
import java.io.*;
import java.util.Scanner;

public class AnomalyDetectionHandler implements ClientHandler {

	@Override
	public void handleClient(InputStream fromClient, OutputStream toClient) {
		SocketIO socket = new SocketIO(fromClient, toClient);
		CLI aClient = new CLI(socket);
		aClient.start();
	}

	public class SocketIO implements DefaultIO {

		Scanner from;
		PrintWriter to;

		public SocketIO(InputStream fromClient, OutputStream toClient) {
			this.from = new Scanner(fromClient);
			this.to = new PrintWriter(toClient);
		}

		@Override
		public String readText() {
			return from.nextLine();
		}

		@Override
		public void write(String text) {
			to.print(text);
			to.flush();
		}

		@Override
		public float readVal() {
			return from.nextFloat();
		}

		@Override
		public void write(float val) {
			to.print(val);
			to.flush();
		}

		@Override
		public void writeln(String text) {
			write(text);
			write("\n");
		}

		public void close() {
			from.close();
			to.close();
		}
	}
}