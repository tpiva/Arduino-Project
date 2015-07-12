import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

 public class Teste {
    static InputStream input;
    static OutputStream output;
    
    //\o/\o/
 
    public static void main(String[] args) throws Exception{
        //Preferences.init();
        //System.out.println("Using port: " + Preferences.get("serial.port"));
    	
    	Scanner leitor = new Scanner(System.in);  
    	
    	CommPortIdentifier portId = CommPortIdentifier.getPortIdentifier("COM6");  
        SerialPort port = (SerialPort) portId.open("Teste", 2000);  
        port.setSerialPortParams(9600, SerialPort.DATABITS_8,  
                SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
        
        input = port.getInputStream();
        output = port.getOutputStream();
        
        	//output.write('a');
        while(true){
        	String a = leitor.next();
        	
        	for(int i=0; i<a.length();i++)
        	{
        		Thread.sleep(500);
        		output.write(a.toCharArray()[i]);
        	}        	
        }
    }
}