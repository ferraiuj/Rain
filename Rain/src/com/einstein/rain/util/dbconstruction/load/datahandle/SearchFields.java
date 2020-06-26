package com.einstein.rain.util.dbconstruction.load.datahandle;

import java.nio.ByteBuffer;

import com.einstein.rain.util.dbconstruction.load.Load;
import com.jacob.serialize.deserialization.ddatabase.DDataBase;
import com.jacob.serialize.deserialization.dfield.DField;

public class SearchFields {

	protected static DDataBase ddbase;
	protected short fieldSearch = 0;
	private static final long serialVersionUID = 1L;

	public SearchFields(String dbname) {
		ddbase = DDataBase.DeserializeFile(dbname, fieldSearch);
		System.out.println("IRAN");
	}

	public int getIntField(short fieldSearch) {

		this.fieldSearch = fieldSearch;
		int value = 0;
		for (int i = 0; i < ddbase.objects.size(); i++) {

			value = ByteBuffer.wrap(ddbase.objects.get(i).fields.get(fieldSearch).fieldData).getInt();
			System.out.println("IRAN");
		}
		return value;
	}

	public static short getShort(byte[] fieldData) {
		short value = ByteBuffer.wrap(fieldData).getShort();
		return value;
	}

}
