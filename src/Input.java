import java.io.*;

public class Input {
	
	private static int space1;
	private static int space2;
	private static int space3;
	private static int space4;
	private static int space5;
	private static int space6;
	private static int space7;
	private static int space8;
	
	
	private static void spacesAuslesen(String zeile){
		
		space1=zeile.indexOf(" ");
		space2=zeile.indexOf(" ", space1+1);
		space3=zeile.indexOf(" ", space2+1);
		space4=zeile.indexOf(" ", space3+1);
		space5=zeile.indexOf(" ", space4+1);
		space6=zeile.indexOf(" ", space5+1);
		space7=zeile.indexOf(" ", space6+1);
		space8=zeile.indexOf(" ", space7+1);
	}
	
	
	public static String home = System.getProperty("user.home");
	
	public static void convertieren() throws IOException
	  {
		
	    //Zwischenspeicher (für gelesene dinge) deklarieren
	    BufferedReader reader = new BufferedReader(Gui.fr);
	    //Zwischenspeicher für geschriebene dinge deklarieren
	    
	    BufferedWriter writer = new BufferedWriter(new FileWriter (home + "/juniper.txt"));
	    int i=0;
	    String zeile ="";
	    String policy ="set security policies from zone trust to zone untrust policy";
	    
	    //eine Zeile enlesen und in der variablen zeile speichern
	    zeile = reader.readLine();
	    
	    //schleife läuft solange wie zeile nicht null ist
	    while(zeile != null){

	    		i++;
	    		
	    		//Wenn eine Zeile "object enthält dann object und object-group löschen
	    		if(zeile.contains("object")){
	    		zeile=zeile.replace(" object-group","");
	    		zeile=zeile.replace(" object","");
	    		}
	    		
	    		if(zeile.contains("eq")){
	    			zeile=zeile.replace("eq","");
	    		}
	    		
	    		//stelle Leerzeichen festlegen
	    		spacesAuslesen(zeile);
	    		
	    		if(zeile.contains("remark")){
	    			//String ab remark in b speichern
	    			int b=zeile.indexOf("remark");
	    			//b 6 zeichen weiter zählen (damit remark nicht mit ausgegeben wird)
	    			b=b+6;
	    			System.out.println(policy + i + " description \""+ zeile.substring(b).trim()+"\"");
	    	    	writer.write(policy + i + " description \""+ zeile.substring(b).trim()+"\"" + "\n");
	    	    	zeile = reader.readLine();
	    	    	
	    	    	
	    		}
	    		if(zeile.contains("extended")){
	    			int a =zeile.indexOf("extended");
	    			a=a+8;
	    			zeile=zeile.replace(" object-group","");
		    		zeile=zeile.replace(" object","");
	    			spacesAuslesen(zeile);
		    		
	    			String sourceObject = zeile.substring(space5, space6);
		    		String destinationObject = zeile.substring(space6,space7);
		    		String app = zeile.substring(space4, space5);
		    		String action = zeile.substring(space3, space4);
	    			
	    			if(sourceObject.contains("any4")){
	    				System.out.println(policy + i + " match source-address any-ipv4");
		    	    	writer.write(policy + i + " match source-address any-ipv4" + "\n");
	    				}else if(sourceObject.contains("any6")){
	    					System.out.println(policy + i + " match source-address any-ipv6");
	    					writer.write(policy + i + " match source-address any-ipv6" + "\n");
	    			}else{
	    					System.out.println(policy + i + " match source-address" + sourceObject);
	    					writer.write(policy + i + " match source-address" + sourceObject + "\n");

	    			}
	    			if(destinationObject.contains("255")){
	    				destinationObject = zeile.substring(space7,space8);
		    			System.out.println(policy + i + " match destination-address" + destinationObject);
	    			}
	    			else if(destinationObject.contains("any4")){
	    				System.out.println(policy + i + " match destination-address any-ipv4");
		    	    	writer.write(policy + i + " match destination-address any-ipv4" + "\n");
	    				}else if(destinationObject.contains("any6")){
	    					System.out.println(policy + i + " match destination-address any-ipv6");
	    					writer.write(policy + i + " match destination-address any-ipv6" + "\n");
	    			}
	    				else{
	    			System.out.println(policy + i + " match destination-address" + destinationObject);
	    	    	writer.write(policy + i + " match destination-address" + destinationObject + "\n");
	    			}
	    			
	    			if(app.contains("ip")){
	    				System.out.println(policy + i + " match application any");
		    	    	writer.write(policy + i + " match application any" + "\n");

	    			}
	    			else{
	    				String appNew = zeile.substring(space7);
	    				if(appNew.contains("echo")){
	    				System.out.println(policy + i + " match application junos-echo");
		    	    	writer.write(policy + i + " match application junos-echo" + "\n");

	    				}else{
	    					System.out.println(policy + i + " match application " + appNew.trim());
	    	    	    	writer.write(policy + i + " match application " + appNew.trim() + "\n");

	    				}
	    			}
	    			
	    			System.out.println(policy + i + " then" + action);
	    			writer.write(policy + i + " then" +  action + "\n");
	    			
	    			
	    			
	    		}if(zeile.contains("standard")){
	    			int c=zeile.indexOf("standard");
	    			c=c+8;
	    			zeile=zeile.replace(" object-group","");
		    		zeile=zeile.replace(" object","");
	    			spacesAuslesen(zeile);
	    			
		    		String destinationObject = zeile.substring(space4,space5);
		    		String action = zeile.substring(space3,space4);
	    			
	    			
	    			System.out.println(policy + i + " match source-address any");
	    	    	writer.write(policy + i + " match source-address any" + "\n");
	    			System.out.println(policy + i + " match destination-adress" + destinationObject.trim());
	    	    	writer.write(policy + i + " match destination-adress " + destinationObject.trim() + "\n");
	    			System.out.println(policy + i + " match application any");
	    	    	writer.write(policy + i + " match application any" + "\n");
	    			System.out.println(policy + i + " then" + action);
		    		writer.write(policy + i + " then" + action + "\n");
		    		

	    		}
	    		zeile = reader.readLine();	
	    	}

	    reader.close();
	    writer.close();
	
	  }
}


