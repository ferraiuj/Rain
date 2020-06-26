package com.einstein.rain.util.dbconstruction.save;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.einstein.rain.level.Level;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SaveLocation;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SavePlayer;
import com.einstein.rain.util.dbconstruction.save.database.playerdata.SaveSettings;
import com.jacob.serialize.serialization.database.DataBase;
import com.jacob.serialize.serialization.field.Field;
import com.jacob.serialize.serialization.field.fieldtypes.FieldInt;
import com.jacob.serialize.serialization.object.Obj;
import com.einstein.rain.util.dbconstruction.save.datahandle.CreateFieldType;

public class Save {

	
//	public DataBase saveLocation;
//	public DataBase saveDatabase;
//	public DataBase saveSettings;
//	public DataBase saveLevelData;
//	public DataBase saveUIDetails;
//	public DataBase savePlayer;
	public static CreateFieldType createField;
	public static SaveDatabase saveDB;
	public Level level;
	public SaveLocation saveLocation;
	//public DataBase saveDatabase;
	public SaveSettings saveSettings;
	//public SaveLevel saveLevelData;
	
	public SavePlayer savePlayer;
	private static final String PLAYER_DATA_FILE = "PlayerData.pd";
	private static final String SETTINGS_FILE = "Settings.pd";
	private static final String UIDETAILS_FILE = "UIDetails.pd";
	private static final String LEVEL_DATA_FILE = "LevelData.pd";
	private static final String LOCATIONS_FILE = "Locations.pd";
	private List<String> databases = new ArrayList<String>();
	public Save(Level level) { 
		
		this.level = level;
		databases.add(PLAYER_DATA_FILE);
		databases.add(SETTINGS_FILE);
		databases.add(UIDETAILS_FILE);
		databases.add(LEVEL_DATA_FILE );
		databases.add(LOCATIONS_FILE);
//		saveLocation = new DataBase(LOCATIONS_FILE);
//		saveSettings = new DataBase(SETTINGS_FILE);
//		savePlayer = new DataBase(PLAYER_DATA_FILE);
		
		//saveDB = new SaveDatabase(level, saveDatabase);
	}

	public void construct() {
		//savePlayerData();
		for(int i = 0; i < databases.size(); i++) {
			saveGameToFile(databases.get(i));
		}
//		saveGameToFile(LOCATIONS_FILE);
//		saveGameToFile(SETTINGS_FILE);
//		saveGameToFile(PLAYER_DATA_FILE);
	
	}
	public void saveGameToFile(String path) {

		DataBase database = new DataBase(path);
		if(path == PLAYER_DATA_FILE ) {
			savePlayer = new SavePlayer(level, database);
			database = savePlayer.savePlayer(database);
		}
		else if(path == SETTINGS_FILE ) {
			saveSettings = new SaveSettings(database);
			database = savePlayer.savePlayer(database);
		}else if(path == LOCATIONS_FILE ) {
			//saveLocation = new SaveLocation(database);
			database = savePlayer.savePlayer(database);
		}
		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		
	}
	public void savePlayerData(String path, DataBase database) {

		
		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		
	}

	public DataBase saveSettings(String path, DataBase database) {

		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		return database;
	}

	public DataBase saveUIDetails(String path, DataBase database) {

		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		return database;
	}

	public DataBase saveLevelData(String path, DataBase database) {

		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);
		return database;
	}

	public void saveLocations(String path, DataBase database) {
		
		byte[] stream = new byte[database.getDBSize()];

		database.getDBBytes(stream, 0);

		saveToFile(path, stream);

	}
	protected static void saveToFile(String path, byte[] data) {
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(path));
			stream.write(data);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
