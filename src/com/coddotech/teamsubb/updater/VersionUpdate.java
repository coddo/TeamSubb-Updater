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
			System.out.println("Checking TeamSubb version...");

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
			System.out.println("Starting the update procedures...");

			replaceRepoFiles(downloadFiles(fetchFileLinks()));

			cleanDownloadFolder();

			return true;
		}

		catch (Exception ex) {
			return false;

		}
	}

	private static String[] fetchFileLinks() throws Exception {
		System.out.println("Fetching update file links...");

		List<String> links = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new FileReader(FileDownloader.downloadFile(filesLink)));

		String buffer = null;

		while ((buffer = reader.readLine()) != null)
			links.add(buffer);

		reader.close();

		return links.toArray(new String[links.size()]);
	}

	private static File[][] downloadFiles(String[] links) throws Exception {
		File[][] files = new File[links.length][2];

		for (int i = 0; i < links.length; i++) {
			String[] split = links[i].split("\n");

			files[i][0] = FileDownloader.downloadFile(split[0]);

			files[i][1] = new File(split[1]);
		}

		return files;
	}

	private static void replaceRepoFiles(File[][] files) throws Exception {
		System.out.println("Starting binary replacement...");

		for (File[] file : files) {
			System.out.println("Replacing: " + file[0].getName());

			FileUtils.copyFile(file[0], new File(file[1].getAbsolutePath() + File.separator + file[0].getName()));
		}
	}

	private static void cleanDownloadFolder() throws Exception {
		System.out.println("Cleaning directory....");

		FileUtils.cleanDirectory(FileDownloader.DOWNLOAD_DIR);
	}

}
