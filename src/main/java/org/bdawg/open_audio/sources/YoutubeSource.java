package org.bdawg.open_audio.sources;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.bdawg.open_audio.Utils.OAConstants;
import org.bdawg.open_audio.impl.AbstractPlayable;
import org.bdawg.open_audio.interfaces.ISinglePlayable;

import com.github.axet.vget.VGet;

public class YoutubeSource extends AbstractPlayable {

	private ISinglePlayable lastReturned;
	private String youtubeURI;
	private URL youtubeURL;

	public YoutubeSource(String plyableId, String masterId,
			List<String> clients, Map<String, String> meta)
			throws MalformedMetaException {
		super(plyableId, masterId, true, clients, meta);

		if (this.getMeta() == null) {
			throw new MalformedMetaException();
		}
		if (this.getMeta().containsKey(
				OAConstants.META_YOUTUBE_URI_LOCATION_KEY)) {
			this.youtubeURI = this.getMeta().get(
					OAConstants.META_YOUTUBE_URI_LOCATION_KEY);
			try {
				this.youtubeURL = new URL(this.youtubeURI);
			} catch (MalformedURLException e) {
				throw new MalformedMetaException();
			}
			
		} else {
			throw new MalformedMetaException();
		}
	}

	public ISinglePlayable getNextSinglePlayable() {
		if (lastReturned == null) {
			ISinglePlayable next = new ISinglePlayable() {

				public int getSubIndex() {
					// Only one item supported
					return 0;
				}

				public String getMasterId() {
					// Refer to the super
					return YoutubeSource.this.getMasterClientId();
				}

				public boolean canVLCPlayDirect() {
					return false;
				}

				public Map<String, String> getMeta() {
					return YoutubeSource.this.getMeta();
				}

				public String getDLType() {
					return YoutubeSource.this.getDownloadType();

				}

				public String getOwningPlayableId() {
					return YoutubeSource.this.getId();
				}

				public File getToDistribute() {
					File tmpDir = new File(System.getenv("java.io.tmpdir"));
					VGet v = new VGet(youtubeURL, tmpDir);
					v.download();
					return v.getTarget();
				}
			};
			lastReturned = next;
			return next;
		} else {
			return null;
		}
	}

	public String getDownloadType() {
		return OAConstants.DL_TYPE_TORRENT;
	}

}
