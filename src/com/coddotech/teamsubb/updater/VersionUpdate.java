package com.coddotech.teamsubb.updater;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class VersionUpdate {

	private static final File versionFile = new File(".version");

	private static final String versionLink = "https://github.com/coddo/TeamSubb-Updater/raw/master/.version";
	private static final String filesLink = "https://github.com/coddo/TeamSubb-Updater/raw/master/.files";

	public static boolean checkVersion() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(versionFile.getAbsoluteFile()));

			String version = reader.readLine();

			reader.close();

			File newVersionFile = FileDownloader.downloadFile(versionLink);

			reader = new BufferedReader(new FileReader(newVersionFile.getAbsoluteFile()));

			String newVersion = reader.readLine();

			reader.close();

			// replace the local version file
			FileUtils.copyFile(newVersionFile, versionFile);

			return version.equals(newVersion);
		}

		catch (Exception e) {
			return false;
		}
	}

	public static boolean performUpdate() {
		try {
			replaceRepoFiles(downloadFiles(fetchFileLinks()));

			cleanDownloadFolder();

			return true;
		}

		catch (Exception ex) {
			return false;

		}
	}

	private static String[] fetchFileLinks() throws Exception {
		List<String> links = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(FileDownloader.downloadFile(filesLink)));

		String buffer = null;

		while ((buffer = reader.readLine()) != null)
			links.add(buffer);
		
		reader.close();

		return links.toArray(new String[links.size()]);
	}

	private static File[] downloadFiles(String[] links) throws Exception {
		File[] files = new File[links.length];

		for (int i = 0; i < links.length; i++)
			files[i] = FileDownloader.downloadFile(links[i]);

		return files;
	}

	private static void replaceRepoFiles(File[] files) throws Exception {
		for (File file : files)
			FileUtils.copyFile(file, new File(file.getName()));
	}

	private static void cleanDownloadFolder() throws Exception {
		FileUtils.cleanDirectory(FileDownloader.DOWNLOAD_DIR);
	}

}
