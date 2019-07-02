import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

import org.apache.http.message.BasicNameValuePair;

public class AnalogLogin {

	private static final String URL = "http://218.197.150.140/";//访问的登陆网址

	/**
	 * 登陆到教务系统
	 *
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功返回true 失败返回false
	 * @author xuan
	 */
	public boolean login(String userName, String password) {
		HttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());

		HttpPost httpost = new HttpPost(URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("userName", userName));
		nvps.add(new BasicNameValuePair("password", password));
		//nvps.add(new BasicNameValuePair("returnUrl", "null"));

		/*设置字符*/
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		/*尝试登陆*/
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			String result = "";
			Header[] headers = response.getAllHeaders();
			for(Header header : headers)
				result = result + header + "\n";

			System.out.println(result);
			/*验证cookie的内容是否包含userName*/
			if(result.contains(userName)) {
				return true;
			} else {
				return false;
			}
		} catch(IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	public static void main(String[] args) {
		AnalogLogin login = new AnalogLogin();
		System.out.println(login.login("2017302580196", "zl19990105"));
	}

}