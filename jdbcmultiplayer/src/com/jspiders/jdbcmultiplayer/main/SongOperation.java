package com.jspiders.jdbcmultiplayer.main;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class SongOperation {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static FileReader fileReader;
	private static ResultSet resultSet;
	private static Properties properties;
	private static String query;
	private static int result;
	private static Scanner scanner = new Scanner(System.in);
	private static String filePath = "C:\\Users\\patil\\eclipse-workspace\\WEJA1\\jdbc1\\resources\\db_info.properties";
	
	Song song = new Song();
	
	private static void openConnection() {
		try {
			fileReader = new FileReader(filePath);
			properties = new Properties();
			properties.load(fileReader);
			
			Class.forName(properties.getProperty("driverPath"));
			connection = DriverManager.getConnection(properties.getProperty("dburl"), properties);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void closeConnection() {
		try {

				if(connection != null) {
				connection.close();
				}
				
				if(resultSet != null) {
					resultSet.close();
				}
				
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				
				if(fileReader != null) {
					fileReader.close();
				}
		}catch (Exception e) {
			e.printStackTrace();
		}
				
	}
		
	public static void main(String[] args) {
		
	}
			
		//Add a song
		public void addSong(){
			
			System.out.println("Add a new song");
			openConnection();
			
			query = "insert into multimedia" 
					+ "values(?, ?, ?, ?, ?)";		
			try {
				preparedStatement = connection.prepareStatement(query);
				
				Song song = new Song();
				song.setId(1);
				song.setSongName("Malang Sajana");
				song.setSingerName("Parampara");
				song.setMovieName("Album Song");
				song.setDuration(3.45f);
				
				song.setId(2);
				song.setSongName("Pehli Dafa");
				song.setSingerName("Atif Aslam");
				song.setMovieName("Album Song");
				song.setDuration(3.50f);
				
				song.setId(3);
				song.setSongName("Jab mila tu");
				song.setSingerName("Vishal Dadlani");
				song.setMovieName("I hate love storys");
				song.setDuration(4.56f);
				
				preparedStatement.setInt(1, song.getId());
				preparedStatement.setString(2, song.getSongName());
				preparedStatement.setString(3, song.getSingerName());
				preparedStatement.setString(4, song.getMovieName());
				preparedStatement.setFloat(5, song.getDuration());
				
				result = preparedStatement.executeUpdate();
				
				System.out.println("Song added successfully.");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
			
		}
		
		//Remove a song
		public void removeSong() {
			System.out.println("Remove a song.");
			openConnection();
			
			query = "delete from multimedia " + "where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setInt(1, 2);
				
				result = preparedStatement.executeUpdate();
				System.out.println("Song removed Successfully.");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}
		}
		
		//Update the song
		public void updateSong() {
		openConnection();
		
		query = "update multimedia " 
				+ "set SingerName = ? " 
				+ "where id = ?";
		
		try {
			preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(3, "Sachet");
			preparedStatement.setInt(1, 1);
			
			result = preparedStatement.executeUpdate();
			System.out.println("Song updated successful.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeConnection();
		}
	}

		//Play song
		public void playSong() {
			openConnection();
			
			query = "select * from multimedia" + "where id = ?";
			
			try {
				preparedStatement = connection.prepareStatement(query);
				System.out.println("Enter song id to play a song");
				int id = scanner.nextInt();
				song.setId(id);
				
				preparedStatement.setInt(1, song.getId());
				resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					song.setId(resultSet.getInt(1));
					song.setSongName(resultSet.getString(2));
					song.setSingerName(resultSet.getString(3));
					song.setMovieName(resultSet.getString(4));
					song.setDuration(resultSet.getFloat(5));
					System.out.println(song);
				} 
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeConnection();
			}		
		}
}

	