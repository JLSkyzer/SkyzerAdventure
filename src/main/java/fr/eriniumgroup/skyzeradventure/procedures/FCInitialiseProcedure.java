package fr.eriniumgroup.skyzeradventure.procedures;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.io.File;

public class FCInitialiseProcedure {
	public static void execute() {
		File file = new File("");
		file = new File((FMLPaths.GAMEDIR.get().toString() + "/filechecker/"), File.separator + "notread.txt");
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}
}
