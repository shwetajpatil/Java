package com.jspiders.jdbcmultiplayer.main;

import java.util.Scanner;
public class MultiPlayer {
	static Scanner sc = new Scanner(System.in);
	static boolean loop = true;
	static SongOperation songOperation = new SongOperation();

	public static void main(String[] args) {
		MultiPlayer music = new MultiPlayer();

		while (loop) {
			music.multiPlayer();

		}
	}

	public void multiPlayer() {
		System.out.println("select Option :"
				+ "\n1.play song "
				+ "\n2.Add or remove song "
				+ "\n3.Update Song "
				+ "\n4.Exit");
		
		System.out.println("-----------------------");
		int choose = sc.nextInt();
		switch (choose) {
		
		case 1:
			System.out.println("Choose option to play a song:"
					+ "\n1.Play Song"
					+ "\n2.Go Back");
			int choose1 = sc.nextInt();
			switch(choose1) {
			
			case 1:
				songOperation.playSong();
				break;
				
			case 2:
				System.out.println("Going back...");
			}
			break;


		case 2:
			System.out.println("choose option to add a song or remove a song :"
					+ "\n1.Add Song "
					+ "\n2.Remove Song "
					+ "\n3.Go back ");
			int choose2 = sc.nextInt();
			
			switch (choose2) {
			case 1:
				songOperation.addSong();
				break;
				
			case 2:
				songOperation.removeSong();
				break;
				
			case 3:
				System.out.println("Going Back...");
			}
			break;
			
		case 3:
			System.out.println(
					"choose option to Update song :"
					+ "\n1.Update Song Name\\Singer Name\\Movie Name\\Song Duration"
					+ "\n2.Go Back");
			int choose3 = sc.nextInt();
			switch (choose3) {
			
			case 1:
				songOperation.updateSong();
				break;
			case 2:
				System.out.println("Going back.....");
				break;
			}
			break;

		case 4:
			System.out.println("Wait.....Exiting....");
			loop = false;
			
		default:
			System.out.println("Enter valid Input.....");
			break;
		}

	}

}