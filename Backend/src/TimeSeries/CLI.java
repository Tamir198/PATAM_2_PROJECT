package TimeSeries;

import java.util.ArrayList;
import java.util.List;
import test.Commands.Command;
import test.Commands.DefaultIO;

public class CLI {

	ArrayList<Command> commands;
	DefaultIO dio;
	SimpleAnomalyDetector SAD;
	List<AnomalyReport> reports;
	Commands c;
	Commands.CliCommand command;
	
	public CLI(DefaultIO dio) {
		this.dio=dio;
		c=new Commands(dio);
		commands=new ArrayList<>();
	}

	public void start() { // ok by start they meant to activate the cli commands not start the cli,
		// so you can get commands from the user but...
		// we are reading from a file and not receiving any commands at all this doesn't make any sense...

		int nCommandNo = 0;
		String strReadCommand;
		while(nCommandNo != 6){
			dio.writeln("Welcome to the Anomaly Detection Server.\n"+
					"Please choose an option:\n" +
					"1. upload a time series csv file\n" +
					"2. algorithm settings\n" +
					"3. detect anomalies\n" +
					"4. display results\n" +
					"5. upload anomalies and analyze results\n" +
					"6. exit");

			strReadCommand = dio.readText();
			nCommandNo = Integer.parseInt(strReadCommand);

			if(nCommandNo < 1 || nCommandNo > 6){
				dio.writeln("Wrong input");
			} else {
				Command cmd = c.new CliCommand(nCommandNo);
				commands.add(cmd);
				cmd.execute();
			}
		}
	}
}
