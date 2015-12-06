package autonavi.poi.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class RetrivePage {
	private static HttpClient httpClient = new HttpClient();
	// ���ô��������
	static {
		// ���ô����������IP��ַ�Ͷ˿�
		//httpClient.getHostConfiguration().setProxy("172.17.18.84", 8080);
	}

	public static boolean downloadPage(String path) throws HttpException,
			IOException {
		InputStream input = null;
		OutputStream output = null;
		// �õ�post����
		GetMethod getMethod=new GetMethod(path);
		// ����post�����Ĳ���
		/*
		 * NameValuePair[] postData = new NameValuePair[2]; postData[0] = new
		 * NameValuePair("name","lietu"); postData[1] = new
		 * NameValuePair("password","*****");
		 * postMethod.addParameters(postData);
		 */
		// ִ�У�����״̬��
		int statusCode = httpClient.executeMethod(getMethod);
		// ���״̬����д��� (�������ֻ������ֵΪ200��״̬��)
		if (statusCode == HttpStatus.SC_OK) {
			input = getMethod.getResponseBodyAsStream();
			//�õ��ļ���
			String filename = path.substring(path.lastIndexOf('/')+1);
			//����ļ������
			output = new FileOutputStream(filename+".html");
			//������ļ�
			int tempByte = -1;
			while((tempByte=input.read())>0){
				output.write(tempByte);
			}
			//�ر����������
			if(input!=null){
				input.close();
			}
			if(output!=null){
				output.close();
			}
			return true;
		}
		//����Ҫת�������ת�����
		if ((statusCode == HttpStatus.SC_MOVED_TEMPORARILY) || (statusCode == HttpStatus.SC_MOVED_PERMANENTLY) || (statusCode == HttpStatus.SC_SEE_OTHER) || (statusCode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
		    //��ȡ�µ�URL��ַ
			Header header = getMethod.getResponseHeader("location");
			if(header!=null){
				String newUrl = header.getValue();
				if(newUrl==null||newUrl.equals("")){
					newUrl="/";
					//ʹ��postת��
					GetMethod redirect = new GetMethod(newUrl);
					//������������һ��������������
				}
			}
		}
		return false;
	}

	/**
	 * ���Դ���
	 */
	public static void main(String[] args) {
		// ץȡlietu��ҳ,���
		try {
			RetrivePage.downloadPage("http://www.baidu.com");
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
