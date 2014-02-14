package com.coddotech.teamsubb.updater;

public class Main {

	public static void main(String[] args) {
		if (VersionUpdate.checkVersion()) {
			if (VersionUpdate.performUpdate()) {
				System.out.println("UPDATE COMPLETE ! TeamSubb will now be restarted....");

				try {
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
				}
			}
		}

	}

}
