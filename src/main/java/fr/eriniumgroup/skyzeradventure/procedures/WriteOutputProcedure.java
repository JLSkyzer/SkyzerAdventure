package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;

public class WriteOutputProcedure {
	public static void execute(String value) {
		if (value == null)
			return;
		File file = new File("");
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/output/"), File.separator + "output.txt");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		try {
			FileWriter filewriter = new FileWriter(file, true);
			BufferedWriter filebw = new BufferedWriter(filewriter);
			{
				filebw.write(value);
				filebw.newLine();
			}
			filebw.close();
			filewriter.close();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}