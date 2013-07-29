package br.com.amil.predojo.file;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class LogInformation {

	private Date occuredTime;
	
	public LogInformation(String log) {
		
		try {
			String idString = processTime(log);
			
			process(idString);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
	}

	public Date getOccuredTime() {
		return occuredTime;
	}

	public void setOccuredTime(Date occuredTime) {
		this.occuredTime = occuredTime;
	}
	
	private String processTime(String log) throws ParseException {
		setOccuredTime(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(log.substring(0, 20)));
		return log.substring(22);
	}

	protected abstract void process(String log);

}
