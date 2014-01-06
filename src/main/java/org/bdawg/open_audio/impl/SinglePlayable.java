package org.bdawg.open_audio.impl;

import java.io.File;
import java.util.Map;

import org.bdawg.open_audio.OpenAudioProtos.ClientCommand;
import org.bdawg.open_audio.interfaces.ISinglePlayable;
import org.bdawg.open_audio.Utils;

public class SinglePlayable implements ISinglePlayable{
	
	private int subIndex;
	private boolean canVLCPlayDirect;
	private String masterId;
	private Map<String,String> meta;
	private String dlType;
	private String owningPBId;
	
	private SinglePlayable(){
		
	}
	
	public static SinglePlayable fromClientCommand(ClientCommand incoming){
		SinglePlayable tr = new SinglePlayable();
		tr.subIndex = incoming.getItem().getSubIndex();
		tr.canVLCPlayDirect = incoming.getItem().getDirectPlaybackFlag();
		tr.masterId = incoming.getItem().getMasterId();
		tr.meta = Utils.kvListToMap(incoming.getItem().getMetaList());
		tr.dlType = incoming.getItem().getDlType();
		tr.owningPBId = incoming.getItem().getOwningPBId();
		return tr;
	}
	
	
	
	public Map<String,String> getMeta(){
		return this.meta;
	}

	public int getSubIndex() {
		return this.subIndex;
	}

	public String getMasterId() {
		return this.masterId;
	}

	public boolean canVLCPlayDirect() {
		return this.canVLCPlayDirect;
	}

	public String getDLType() {
		return this.dlType;
	}

	public String getOwningPlayableId() {
		return this.owningPBId;
	}

	public File getToDistribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
