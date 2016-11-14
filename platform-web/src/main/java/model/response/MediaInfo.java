package model.response;

/**
 * 多媒体信息实体
 * 
 * @author zengxing
 *
 */
public class MediaInfo {
	private String MediaId; // 素材id
	private String Title; // 标题
	private String Description; // 描述
	private String MusicURL; // 音乐链接
	private String HQMusicUrl; // 高质量音乐链接
	private String ThumbMediaId; // 缩略图的媒体id

	public MediaInfo() {
	}

	public MediaInfo(String mediaId) {
		this.MediaId = mediaId;
	}

	public MediaInfo(String mediaId, String title, String description) {
		this.MediaId = mediaId;
		this.Title = title;
		this.Description = description;
	}

	public MediaInfo(String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
		this.Title = title;
		this.Description = description;
		this.MusicURL = musicUrl;
		this.HQMusicUrl = hqMusicUrl;
		this.ThumbMediaId = thumbMediaId;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicURL() {
		return MusicURL;
	}

	public void setMusicURL(String musicURL) {
		MusicURL = musicURL;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
}
