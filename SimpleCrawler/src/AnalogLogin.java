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

	private static final String URL = "http://218.197.150.140/";//���ʵĵ�½��ַ

	/**
	 * ��½������ϵͳ
	 *
	 * @param userName �û���
	 * @param password ����
	 * @return �ɹ�����true ʧ�ܷ���false
	 * @author xuan
	 */
	public boolean login(String userName, String password) {
		HttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());

		HttpPost httpost = new HttpPost(URL);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("userName", userName));
		nvps.add(new BasicNameValuePair("password", password));
		//nvps.add(new BasicNameValuePair("returnUrl", "null"));

		/*�����ַ�*/
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));

		/*���Ե�½*/
		HttpResponse response;
		try {
			response = httpclient.execute(httpost);
			String result = "";
			Header[] headers = response.getAllHeaders();
			for(Header header : headers)
				result = result + header + "\n";

			System.out.println(result);
			/*��֤cookie�������Ƿ����userName*/
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