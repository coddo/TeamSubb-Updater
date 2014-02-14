package com.coddotech.teamsubb.updater;

import java.io.File;
import java.io.IOException;

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

		System.out.println("TeamSubb will now be restarted....");

		// wait for 1 second to avoid conflicts
		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		}

		// restart TeamSubb
		try {
			java.awt.Desktop.getDesktop().open(new File("TeamSubb.jar"));
		}
		catch (IOException e1) {
		}

	}

}
