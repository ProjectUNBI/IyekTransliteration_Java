import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class iyek_transliteration {
	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir"));
//		System.out.println("ꯀ:<s=\u0126");
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
	///////////// Functions //////////
	////////////////////////////////////////
	private static File[] finder(String dirName) {//Function to List all the .ytxt files
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".txt");
			}
		});

	}

	private static String readFile(String fileName) throws IOException {//Function to read File
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

	 private static  String iyekthis(String arg){
	    
//		    arg="mayaam bakkWa cha";    
//			 String arg="eidi gikpWan gRa dA";
//		
		 String iyeking="[ãçõñθɫöðâêōåøûœ꫱꫰꯫꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹ꯀꯁꯂꯃꯄꯅꯆꯇꯈꯉꯊꯋꯌꯍꯎꯏꯐꯑꯒꯓꯔꯕꯖꯗꯘꯙꯚꯣꯤꯥꯦꯧꯨꯩꯪꯛꯜꯝꯞꯟꯠꯢ-꯭abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
//		 String eng="[abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
//		 String noteng="[^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
//		 String notiyekeng="[^꫱꫰꯫꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹ꯀꯁꯂꯃꯄꯅꯆꯇꯈꯉꯊꯋꯌꯍꯎꯏꯐꯑꯒꯓꯔꯕꯖꯗꯘꯙꯚꯣꯤꯥꯦꯧꯨꯩꯪꯛꯜꯝꯞꯟꯠꯢ-꯭abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ]";
//		 String iyekonly="[꫱꫰꯫꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹ꯀꯁꯂꯃꯄꯅꯆꯇꯈꯉꯊꯋꯌꯍꯎꯏꯐꯑꯒꯓꯔꯕꯖꯗꯘꯙꯚꯣꯤꯥꯦꯧꯨꯩꯪꯛꯜꯝꯞꯟꯠ-꯭ꯢ]";	 
//		 System.out.println(arg);
	        
	        arg=arg+"*####*Dictionary:c=k:z=j:ch=ç:sh=s:oo=u:kh=õ:ng=ñ:th=θ:ph=f:jh=ɫ:gh=ö:bh=v:dh=ð:ee=i:aa=â:ei=ê:ou=ō:ae=e:ai=å:oi=ø:ui=û:ao=œ:L-꯭ꯂã:W-꯭ꯋã:R-꯭ꯔã:Y-꯭ꯌã:ar=ꯑ꯭ꯔ:er=ꯑꯦ꯭ꯔ:ir=ꯏ꯭ꯔ:or=ꯑꯣ꯭ꯔ:ur=ꯎ꯭ꯔ:aaa=ꯑꯥ:E-ꯑꯦ:I-ꯏ:O-ꯑꯣ:U-ꯎ:A-ꯑ:r-꯭ꯔã:";//here setting things for doing conditional search and replacement

	        arg=arg.replaceAll("(?s)(\\w+)(?=.*:\\1=(.)\\b)","$2");                                                 //here leter like "ch","dh","bh" are converted to single letter like ç,ð,v and so on
	        arg=arg.replaceAll("(?s)([^aeiouAEIOU])\\B([WLRY])(?=.*:\\2\\-("+iyeking+"+)\\b:)","ã$1$3");                    //here we converting word like "kWa" to "ãk-꯭ꯋã","kRa" to"ãk-꯭ꯔã" and so on["ã" is used to protect k from converting to kok lonsum and "l" to lai lonsum etc ...................
//	        System.out.println(arg);
	        arg=arg.replaceAll("([aeiouAEIOU])r","$1ꯔ");                                                            //here we are converting word like "kar"to "kaꯔ","mer"to "meꯔ","lør"[previously it was loir before step2] to"løꯔ" and so on.......
	        arg=arg.replaceAll("(?s)([AEIOU])(?=.*:\\1\\-("+iyeking+"+)\\b:)","$2");                                        //here we are converting E-ꯑꯦ,I-ꯏ,O-ꯑꯣ,U-ꯎ,A-ꯑ
	        arg=arg.toLowerCase();                                                                                                  // now we converted all word to lowercase. Upto here the input word,for example "KYaamgei hairiba maphamsi" to "ãk-꯭ꯌâmgê håꯔiba mafamsi"
	        arg=arg.replaceAll("(?s)\\b([aeiou]r)\\B([^aeiou\\s][^aeiou])(?=.*:\\1=(\\w+)\\b)","$3>$2");            //word like"markwa" to ????????????{{{i cannot rremember properly about this line}}}???????????
	        arg=arg.replaceAll("(?s)([^âêōåøûœãaeiou\\W])r(?=.*:r\\-("+iyeking+"+)\\b)","<$1$2");                           //here search and replace of "krip" to "<k-꯭ꯔãip"
	        String[] seperate=arg.split("\\*####\\*");                                                                         //here the *####*Dictio........... is remove
	        arg=seperate[0];                                                                                                        // upto here input word "KYaamgei hairiba maphamsi" to "ãk-꯭ꯌâmgã håꯔiba mafamsi" [no change because the above two line is for handlng "r" in different words]
	        arg=arg.replaceAll("("+iyeking+"[aeiouâêōåøûœ])","<$1");                                                        //now the word is like"ãk-꯭<ꯌâm<gã <hå<ꯔi<ba <ma<fam<si>"
	        arg=arg.replaceAll("([aeiouâêōåøûœ])","$1>");                                                           //now the word is like"ãk-꯭<ꯌâ>m<gã> <hå><ꯔi><ba> <ma<fa>m<si>"
	        arg=arg+"*####*Dictionary:<k=ꯀ:<s=ꯁ:<l=ꯂ:<m=ꯃ:<p=ꯄ:<n=ꯅ:<ç=ꯆ:<t=ꯇ:<õ=ꯈ:<ñ=ꯉ:<θ=ꯊ:<w=ꯋ:<y=ꯌ:<h=ꯍ:<ŋ=ꯐ:<g=ꯒ:<ɫ=ꯓ:<r=ꯔ:<b=ꯕ:<j=ꯖ:<d=ꯗ:<ö=ꯘ:<ð=ꯙ:<v=ꯚ:œ>=ꯥꯎ:a>=ã:e>=ꯦ:â>=ꯥ:ê>=ꯩ:i>=ꯤ:o>=ꯣ:u>=ꯨ:ō>=ꯧ:å>=ꯥꯢ:ø>=ꯣꯢ:û>=ꯨꯢ:>c=ꯛ:>k=ꯛ:>l=ꯜ:>m=ꯝ:>p=ꯞ:>n=ꯟ:>t=ꯠ:>ñ=ꯪ:>x=ꯛꯁ:k=ꯀ:s=ꯁ:l=ꯂ:m=ꯃ:p=ꯄ:n=ꯅ:ç=ꯆ:t=ꯇ:õ=ꯈ:ñ=ꯉ:θ=ꯊ:w=ꯋ:y=ꯌ:h=ꯍ:u=ꯎ:i=ꯏ:f=ꯐ:a=ꯑ:g=ꯒ:ɫ=ꯓ:r=ꯔ:b=ꯕ:x=ꯖ:j=ꯖ:d=ꯗ:ö=ꯘ:ð=ꯙ:v=ꯚ:0=꯰:1=꯱:2=꯲:3=꯳:4=꯴:5=꯵:6=꯶:7=꯷:8=꯸:9=꯹:â=ꯑꯥ:e=ꯑꯦ:o=ꯑꯣ:ê=ꯑꯩ:ō=ꯑꯧ:å=ꯑꯏ:ø=ꯑꯣꯢ:û=ꯎꯢ:œ=ꯑꯥꯎ:.=꯫∆:?=꫱:,=꫰:";
	        arg=arg.replaceAll("(?s)(<.)(?=.*:\\1=("+iyeking+"+)\\b)","<$2");                                               //the word is like""ãk-꯭<ꯌâ>m<ꯒã> <ꯍå><ꯔi><ꯕa> <ꯃa<ꯐa>m<ꯁi>"
	        arg=arg.replaceAll("(?s)(<.)(.>)(?=.*:\\2=(..)\\b)","$1$3>");                                           //the word is like"ãk-꯭<ꯌꯥ>m<ꯒꯩ> <ꯍ=ꯥꯢ><ꯔꯤ><ꯕã> <ꯃã<ꯐã>m<ꯁꯤ>"
//	        System.out.println(arg);
	        arg=arg.replaceAll("(?s)(>.)(?=.*:\\1=("+iyeking+"+):)",">$2");                                               //the word is like""ãk-꯭<ꯌꯥ>ꯝ<ꯒꯩ> <ꯍ=ꯥꯢ><ꯔꯤ><ꯕã> <ꯃã<ꯐã>ꯝ<ꯁꯤ>"
//	        System.out.println(arg);
	        arg=arg.replaceAll("(?s)(.)(?=.*:\\1=(.*?):)","<$2>");                                                  //the word is like""ãꯀ-꯭<ꯌꯥ>ꯝ<ꯒꯩ> <ꯍ=ꯥꯢ><ꯔꯤ><ꯕã> <ꯃã<ꯐã>ꯝ<ꯁꯤ>"
	        String[] seperate1=arg.split("\\*####\\*");                                                                        //again the sentence is splited like we done before
	        arg=seperate1[0];
	        arg=arg.replaceAll("∆><꯫",".");                                                                         // this step is for diferentiating "." in sentence and "." in like"424.343"
	        arg=arg.replaceAll("<|>|ã|:|∆","");                                                                     //the word is like"ꯀ-꯭ꯌꯥꯝꯒꯩ ꯍ=ꯥꯢꯔꯤꯕ ꯃꯐꯝꯁꯤ"
	        arg=arg.replaceAll("([꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹])꯫([꯰꯱꯲꯳꯴꯵꯶꯷꯸꯹])","$1.$2");                                         // here is number replacement
	        arg=arg.replaceAll("([ꯣꯤꯥꯦꯧꯨꯩ])ꯪ","$1ꯡ");                                                                     //here we are doing not to make nungtap and itap,ounap etc in same time and if happen so, we replace nungtap by ngou lonsum
	     
//	        System.out.println(arg);
	        
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
//			writer = new BufferedWriter(new FileWriter(logFile));
			writer = new BufferedWriter
				    (new OutputStreamWriter(new FileOutputStream(logFile), StandardCharsets.UTF_8));//it help to use UTF-8 fonts like Meetei mayek
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
