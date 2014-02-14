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

		System.out.println("TeamSubb will now be restarted....");

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		}

		try {
			java.awt.Desktop.getDesktop().open(new File("TeamSubb.jar"));
		}
		catch (IOException e1) {
		}

	}

}
