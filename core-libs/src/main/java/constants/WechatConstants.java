package constants;

public class WechatConstants {
	//public final static String appId = "wx9a9c389f3f005d16";	// 应用ID
	//public static String appSecret = "fa987330491e78f2a26dbfe51bb45d46";	// 应用密钥
	public final static String appId = "wx88994515cd773e81";	// 应用ID
	public static String appSecret = "96f67b5147eeeada68fed6a13ecbd639";	// 应用密钥
	
	public final static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
	public final static String callbackIpUrl = "https://api.weixin.qq.com/cgi-bin/getcallbackip";
	// 网页授权类
	public final static String authorizeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";
	public final static String authorizeTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
	public final static String freshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	public final static String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
	public final static String checkTokenUrl = "https://api.weixin.qq.com/sns/auth";
	// 菜单类
	public final static String menuCreateUrl = "https://api.weixin.qq.com/cgi-bin/menu/create";
	public final static String menuGetUrl = "https://api.weixin.qq.com/cgi-bin/menu/get";
}
