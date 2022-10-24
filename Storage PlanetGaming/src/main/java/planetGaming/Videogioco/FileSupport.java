package planetGaming.Videogioco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import javax.servlet.http.Part;

public class FileSupport {

	public FileSupport() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String fileAssembler(Collection<Part> fileParts, String appPath, String saveDir) {
		String fileName = null, returningFileName = null;
		String saveDirPath = appPath + saveDir;

		//creo la cartella se non esiste
		File fileSaveDir = new File(saveDirPath);
		if(!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		
		//ricostruisco il file nella cartella specificata
		for(Part part : fileParts) {
			fileName = part.getSubmittedFileName();
			
			System.out.println(fileName);
			
			if(fileName != null && !fileName.equals("")) {
				try {
					part.write(saveDirPath + File.separator + fileName);
					returningFileName = fileName;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		//restituisco il percorso del file salvato nella cartella
		return returningFileName;
	}
	
	
	public FileInputStream getFileInputStream(String path) {
		FileInputStream fin = null;
	
		try {
			fin = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return fin;
	}
	
}
