package util;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientUtils {
	private static CloseableHttpClient httpClient;
	private static int maxConn = 200; // 最大连接数
	private static int maxPerRoute = 20; // 路由基础连接
	private static int maxRetry = 3; // 最大重试
	private static int defaultTimeOut = 3000; // 默认超时设置
	private static String encoding = "UTF-8"; // 编码
	// private final static int threadNum = 1; // 线程数

	static {
		try {
			// http
			ConnectionSocketFactory connsf = PlainConnectionSocketFactory.getSocketFactory();
			// https
			LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
			Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", connsf).register("https", sslsf).build();
			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
			// 将最大连接数增加到200
			cm.setMaxTotal(maxConn);
			// 将每个路由基础的连接增加到20
			cm.setDefaultMaxPerRoute(maxPerRoute);

			// 请求重试处理
			HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
				@Override
				public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
					if (executionCount >= maxRetry) {// 如果已经重试了5次，就放弃
						return false;
					} else if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
						return true;
					} else if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
						return false;
					} else if (exception instanceof InterruptedIOException) {// 超时
						return false;
					} else if (exception instanceof UnknownHostException) {// 目标服务器不可达
						return false;
					} else if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
						return false;
					} else if (exception instanceof SSLException) {// ssl握手异常
						return false;
					}

					HttpClientContext clientContext = HttpClientContext.adapt(context);
					HttpRequest request = clientContext.getRequest();
					// 如果请求是幂等的，就再次尝试
					if (!(request instanceof HttpEntityEnclosingRequest)) {
						return true;
					}
					return false;

				}
			};
			// 初始化httpClient
			httpClient = HttpClients.custom().setConnectionManager(cm).setRetryHandler(httpRequestRetryHandler).build();
		} catch (Throwable t) {
			LogUtils.logError(t, "httpClient init fail!");
		}
	}

	/**
	 * 设置基本参数
	 */
	private static void config(HttpRequestBase httpRequestBase) {
		httpRequestBase.setHeader("User-Agent", "Mozilla/5.0");
		httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		httpRequestBase.setHeader("Accept-Charset", "ISO-8859-1,utf-8,gbk,gb2312;q=0.7,*;q=0.7");

		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(defaultTimeOut)
				.setConnectTimeout(defaultTimeOut).setSocketTimeout(defaultTimeOut).build();
		httpRequestBase.setConfig(requestConfig);
	}

	/**
	 * 处理GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) throws Exception {
		long start = System.currentTimeMillis();
		HttpGet httpGet = new HttpGet(url);
		config(httpGet);

		CloseableHttpResponse response = httpClient.execute(httpGet, HttpClientContext.create());
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, encoding);

		long end = System.currentTimeMillis();
		LogUtils.logInfo("Finish handle url:{},cost:{}", url, end - start);

		return result;
	}

	/**
	 * 处理POST请求
	 * 
	 * @param url
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String data) throws Exception {
		long start = System.currentTimeMillis();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("Content-type","application/json; charset=utf-8");  
		httpPost.setEntity(new StringEntity(data, Charset.forName("UTF-8")));
		config(httpPost);

		CloseableHttpResponse response = httpClient.execute(httpPost, HttpClientContext.create());
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, encoding);
		long end = System.currentTimeMillis();
		LogUtils.logInfo("Finish handle url:{},data:{},cost:{}", url, data, end - start);

		return result;
	}
}
