package com.coddotech.teamsubb.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class VersionUpdate {

	private static final File versionFile = new File(".version");
	private static final String versionLink = "https://github.com/coddo/TeamSubb-Updater/raw/master/.version";
	private static final String filesLink = "https://github.com/coddo/TeamSubb-Updater/raw/master/.files";

	public static boolean checkVersion() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(versionFile.getAbsoluteFile()));

			String version = reader.readLine();

			reader.close();

			reader = new BufferedReader(new FileReader(FileDownloader.downloadFile(versionLink).getAbsoluteFile()));

			String newVersion = reader.readLine();

			reader.close();

			return version.equals(newVersion);
		}
		
		catch (Exception e) {
			return false;
		}
	}

}
