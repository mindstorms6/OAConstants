package org.bdawg.open_audio.interfaces;

import java.io.File;
import java.util.Map;

public interface ISinglePlayable {
	
	public int getSubIndex();
	public String getMasterId();
	public boolean canVLCPlayDirect();
	public Map<String,String> getMeta();
	public String getDLType();
	public String getOwningPlayableId();
	public File getToDistribute();
}
