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

		System.out.println("TeamSubb will now be restarted....");

		try {
			Thread.sleep(1000);
		}
		catch (InterruptedException e) {
		}

	}

}
