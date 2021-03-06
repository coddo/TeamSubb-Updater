package com.coddotech.teamsubb.updater;

public class Main {

	public static void main(String[] args) {
		if (!VersionUpdate.checkVersion()) {

			if (VersionUpdate.performUpdate()) {
				System.out.println("UPDATE COMPLETE !");

			}

		}

		else
			System.out.println("TeamSubb is up to date !");

		// clean directories
		try {
			VersionUpdate.cleanDownloadFolder();
		}
		catch (Exception e1) {
		}

		// wait for 1 second to avoid conflicts
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		}

	}

}
