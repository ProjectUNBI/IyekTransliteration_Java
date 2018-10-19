import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

public class iyek_transliteration {
	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir"));
		File[] hello = finder(System.getProperty("user.dir"));
		Boolean iffiles=false;
		for (File file : hello) {
			iffiles=true;
			System.out.print("directory:");
			if (file.isDirectory()) {
				System.out.print("directory:");
			} else {
				System.out.print("file:");
			}
			try {
				String curdir = file.getCanonicalPath();
				System.out.println(curdir);
				String readedtxt = readFile(curdir);
//					iyekthis(readedtxt);
//					System.out.println(iyekthis(readedtxt));

				// TODO Extract the file name from the directory
				File f = new File(curdir);
				String myDir = f.getParent();
				String curfile = f.getName();
//					System.out.println(curfile);

				// TODO Create a directory"output"
//					System.out.println(myDir);
				new File(myDir + "\\" + "output").mkdirs();
//					System.out.println(myDir);

				// TODO Write the "readtext"

				Writer(myDir + "\\" + "output\\" + curfile, iyekthis(readedtxt));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(!iffiles) {System.out.print("No accessible files found");}

	}

	////////////////////////////////////////
	///////////// Functions /////////////////
	////////////////////////////////////////
	public static File[] finder(String dirName) {//Function to List all the .ytxt files
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".txt");
			}
		});

	}

	public static String readFile(String fileName) throws IOException {//Function to read File
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			return sb.toString();
		} finally {
			br.close();
		}
	}

	private static String iyekthis(String arg) {
		arg = arg + "*####*Dictionary:c=k:z=j:ch=ç:sh=s:oo=u:kh=õ:ng=ñ:th=θ:ph=f:jh=ɫ:gh=ö:bh=v:dh=ð:ee=i:aa=â:ei=ê:ou=ō:ae=e:ai=å:oi=ø:ui=û:ao=œ:L-꯭ꯂã:W-꯭ꯋã:R-꯭ꯔã:Y-꯭ꯌã:ar=ꯑ꯭ꯔ:er=ꯑꯦ꯭ꯔ:ir=ꯏ꯭ꯔ:or=ꯑꯣ꯭ꯔ:ur=ꯎ꯭ꯔ:aaa=ꯑꯥ:E-ꯑꯦ:I-ꯏ:O-ꯑꯣ:U-ꯎ:A-ꯑ:r-꯭ꯔã:";
		arg = arg.replaceAll("(?s)(\\w+)(?=.*:\\1=(.)\\b)", "$2");
		arg = arg.replaceAll("(?s)([^aeiouAEIOU])\\B([WLRY])(?=.*:\\2\\-(\\w+)\\b:)", "ã$1$3"); 
		arg = arg.replaceAll("([aeiouAEIOU])r", "$1ꯔ"); 
		arg = arg.replaceAll("(?s)([AEIOU])(?=.*:\\1\\-(\\w+)\\b:)", "$2");
		arg = arg.toLowerCase(); arg = arg.replaceAll("(?s)\\b([aeiou]r)\\B([^aeiou\\s][^aeiou])(?=.*:\\1=(\\w+)\\b)", "$3>$2"); 
		arg = arg.replaceAll("(?s)([^âêōåøûœãaeiou\\W])r(?=.*:r\\-(\\w+)\\b)", "<$1$2"); 
		String[] seperate = arg.split("\\*####\\*"); 
        arg = seperate[0];
		arg = arg.replaceAll("(\\w[aeiouâêōåøûœ])", "<$1");
		arg = arg.replaceAll("([aeiouâêōåøûœ])", "$1>");
		arg = arg+ "*####*Dictionary:<k=ꯀ:<s=ꯁ:<l=ꯂ:<m=ꯃ:<p=ꯄ:<n=ꯅ:<ç=ꯆ:<t=ꯇ:<õ=ꯈ:<ñ=ꯉ:<θ=ꯊ:<w=ꯋ:<y=ꯌ:<h=ꯍ:<ŋ=ꯐ:<g=ꯒ:<ɫ=ꯓ:<r=ꯔ:<b=ꯕ:<j=ꯖ:<d=ꯗ:<ö=ꯘ:<ð=ꯙ:<v=ꯚ:œ>=ꯥꯎ:a>=ã:e>=ꯦ:â>=ꯥ:ê>=ꯩ:i>=ꯤ:o>=ꯣ:u>=ꯨ:ō>=ꯧ:å>=ꯥꯢ:ø>=ꯣꯢ:û>=ꯨꯢ:>c=ꯛ:>k=ꯛ:>l=ꯜ:>m=ꯝ:>p=ꯞ:>n=ꯟ:>t=ꯠ:>ñ=ꯪ:>x=ꯛꯁ:k=ꯀ:s=ꯁ:l=ꯂ:m=ꯃ:p=ꯄ:n=ꯅ:ç=ꯆ:t=ꯇ:õ=ꯈ:ñ=ꯉ:θ=ꯊ:w=ꯋ:y=ꯌ:h=ꯍ:u=ꯎ:i=ꯏ:f=ꯐ:a=ꯑ:g=ꯒ:ɫ=ꯓ:r=ꯔ:b=ꯕ:x=ꯖ:j=ꯖ:d=ꯗ:ö=ꯘ:ð=ꯙ:v=ꯚ:0=꯰:1=꯱:2=꯲:3=꯳:4=꯴:5=꯵:6=꯶:7=꯷:8=꯸:9=꯹:â=ꯑꯥ:e=ꯑꯦ:o=ꯑꯣ:ê=ꯑꯩ:ō=ꯑꯧ:å=ꯑꯏ:ø=ꯑꯣꯢ:û=ꯎꯢ:œ=ꯑꯥꯎ:.=꯫∆:?=꫱:,=꫰:";
		arg = arg.replaceAll("(?s)(<.)(?=.*:\\1=(\\w+)\\b)", "<$2"); 
		arg = arg.replaceAll("(?s)(<.)(.>)(?=.*:\\2=(..)\\b)", "$1$3>"); 
		arg = arg.replaceAll("(?s)(>.)(?=.*:\\1=(\\w+)\\b)", ">$2");
		arg = arg.replaceAll("(?s)(.)(?=.*:\\1=(.*?):)", "<$2>"); 
		String[] seperate1 = arg.split("\\*####\\*");
		arg = seperate1[0];
		arg = arg.replaceAll("∆><꯫", "."); 
		arg = arg.replaceAll("<|>|ã|:|∆", ""); 
		arg = arg.replaceAll("([꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹])꯫([꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹])", "$1.$2"); 
		arg = arg.replaceAll("([ꯣꯤꯥꯦꯧꯨꯩ])ꯪ", "$1ꯡ"); 
		return arg;
	}

	private static void Writer(String filepath, String text) {//Function to write file

//	        try {
//	        	System.out.println("Writting.....");
//	        	PrintWriter writer = new PrintWriter(filepath, "UTF-8");
//	            writer.println(text);
//	            writer.close();
//	        } catch (Exception e) {
//	        	System.out.println("Cannot Write The text file");
//	            e.printStackTrace();
//	        } 
//	        }

		BufferedWriter writer = null;
		try {
			// create a temporary file
			File logFile = new File(filepath);
			text = text.replaceAll("\n", "\r\n");
//        	System.out.println(text);
			// This will output the full path where the file will be written to...
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.write(text);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the writer regardless of what happens...
				writer.close();
			} catch (Exception e) {
			}
		}

	}
}
