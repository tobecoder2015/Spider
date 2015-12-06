package autonavi.poi.tool;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import autonavi.poi.bean.BDBFrontier;
import autonavi.poi.bean.CrawlUrl;

import com.sleepycat.je.Cursor;
import com.sleepycat.je.CursorConfig;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.LockConflictException;
import com.sleepycat.je.LockMode;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.je.Transaction;
import com.sleepycat.je.TransactionConfig;

public class BerkeleyDBUtil {

	// ���ݿ⻷��
	private Environment env = null;

	// ���ݿ�
	private static Database frontierDatabase = null;

	// ���ݿ���
	private static String dbName = "Url";

	public BerkeleyDBUtil(String homeDirectory) {

		// 1������EnvironmentConfig
		EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);

		// 2��ʹ��EnvironmentConfig����Environment
		env = new Environment(new File(homeDirectory), envConfig);

		// 3������DatabaseConfig
		DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true);
		dbConfig.setAllowCreate(true);

		// 4��ʹ��Environment��DatabaseConfig��Database
		frontierDatabase = env.openDatabase(null, dbName, dbConfig);

	}

	/*
	 * �����ݿ���д���¼�����ж��Ƿ�������ظ����ݡ� ����key��value
	 * ���������ظ����ݣ���ֱ��ʹ��put()���ɣ����������ظ����ݣ���ʹ��putNoOverwrite()��
	 */
	public boolean writeToDatabase(String key, String value, boolean isOverwrite) {
		try {
			// ����key/value,ע��DatabaseEntry��ʹ�õ���bytes����
			DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
			DatabaseEntry theData = new DatabaseEntry(value.getBytes("UTF-8"));
			OperationStatus status = null;
			Transaction txn = null;
			try {
				// 1��Transaction����
				TransactionConfig txConfig = new TransactionConfig();
				txConfig.setSerializableIsolation(true);
				txn = env.beginTransaction(null, txConfig);
				// 2��д������
				if (isOverwrite) {
					status = frontierDatabase.put(txn, theKey, theData);
				} else {
					status = frontierDatabase.putNoOverwrite(txn, theKey,
							theData);
				}
				txn.commit();
				if (status == OperationStatus.SUCCESS) {
					System.out.println("�����ݿ�" + dbName + "��д��:" + key + ","
							+ value);
					return true;
				} else if (status == OperationStatus.KEYEXIST) {
					System.out.println("�����ݿ�" + dbName + "��д��:" + key + ","
							+ value + "ʧ��,��ֵ�Ѿ�����");
					return false;
				} else {
					System.out.println("�����ݿ�" + dbName + "��д��:" + key + ","
							+ value + "ʧ��");
					return false;
				}
			} catch (LockConflictException lockConflict) {
				txn.abort();
				System.out.println("�����ݿ�" + dbName + "��д��:" + key + "," + value
						+ "����lock�쳣");
				return false;
			}
		} catch (Exception e) {
			// ������
			System.out.println("�����ݿ�" + dbName + "��д��:" + key + "," + value
					+ "���ִ���");

			return false;
		}
	}

	/*
	 * �����ݿ��ж������� ����key ����value
	 */
	public String readFromDatabase(String key) {
		try {
			DatabaseEntry theKey = new DatabaseEntry(key.getBytes("UTF-8"));
			DatabaseEntry theData = new DatabaseEntry();
			Transaction txn = null;
			try {
				// 1������ Transaction�����Ϣ
				TransactionConfig txConfig = new TransactionConfig();
				txConfig.setSerializableIsolation(true);
				txn = env.beginTransaction(null, txConfig);
				// 2����ȡ����
				OperationStatus status = frontierDatabase.get(txn, theKey,
						theData, LockMode.DEFAULT);
				txn.commit();
				if (status == OperationStatus.SUCCESS) {
					// 3�����ֽ�ת����String
					byte[] retData = theData.getData();
					String value = new String(retData, "UTF-8");
					System.out.println("�����ݿ�" + dbName + "�ж�ȡ:" + key + ","
							+ value);
					return value;
				} else {
					System.out
							.println("No record found for key '" + key + "'.");
					return "";
				}
			} catch (LockConflictException lockConflict) {
				txn.abort();
				System.out.println("�����ݿ�" + dbName + "�ж�ȡ:" + key + "����lock�쳣");
				return "";
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

			return "";
		}
	}

	/*
	 * �������ݿ��е����м�¼������list
	 */
	public ArrayList<String> getEveryItem() {
		// TODO Auto-generated method stub
		System.out.println("===========�������ݿ�" + dbName + "�е���������==========");
		Cursor myCursor = null;
		ArrayList<String> resultList = new ArrayList<String>();
		Transaction txn = null;
		try {
			txn = this.env.beginTransaction(null, null);
			CursorConfig cc = new CursorConfig();
			cc.setReadCommitted(true);
			if (myCursor == null)
				myCursor = frontierDatabase.openCursor(txn, cc);
			DatabaseEntry foundKey = new DatabaseEntry();
			DatabaseEntry foundData = new DatabaseEntry();
			// ʹ��cursor.getPrev�����������α��ȡ����
			if (myCursor.getFirst(foundKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
				String theKey = new String(foundKey.getData(), "UTF-8");
				String theData = new String(foundData.getData(), "UTF-8");
				resultList.add(theKey);
				System.out.println("Key | Data : " + theKey + " | " + theData
						+ "");
				while (myCursor.getNext(foundKey, foundData, LockMode.DEFAULT) == OperationStatus.SUCCESS) {
					theKey = new String(foundKey.getData(), "UTF-8");
					theData = new String(foundData.getData(), "UTF-8");
					resultList.add(theKey);
					System.out.println("Key | Data : " + theKey + " | "
							+ theData + "");
				}
			}
			myCursor.close();
			txn.commit();
			return resultList;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println("getEveryItem��������쳣");

			txn.abort();
			if (myCursor != null) {
				myCursor.close();
			}
			return null;
		}
	}

	/*
	 * ����keyֵɾ�����ݿ��е�һ����¼
	 */
	public boolean deleteFromDatabase(String key) {
		boolean success = false;
		long sleepMillis = 0;
		for (int i = 0; i < 3; i++) {
			if (sleepMillis != 0) {
				try {
					Thread.sleep(sleepMillis);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sleepMillis = 0;
			}
			Transaction txn = null;
			try {
				// 1��ʹ��cursor.getPrev�����������α��ȡ����
				TransactionConfig txConfig = new TransactionConfig();
				txConfig.setSerializableIsolation(true);
				txn = env.beginTransaction(null, txConfig);
				DatabaseEntry theKey;
				theKey = new DatabaseEntry(key.getBytes("UTF-8"));
				
				//2��ɾ������ ���ύ
				OperationStatus res = frontierDatabase.delete(txn, theKey);
				txn.commit();
				if (res == OperationStatus.SUCCESS) {
					System.out.println("�����ݿ�" + dbName + "��ɾ��:" + key);
					success = true;
					return success;
				} else if (res == OperationStatus.KEYEMPTY) {
					System.out.println("û�д����ݿ�" + dbName + "���ҵ�:" + key + "���޷�ɾ��");
				} else {
					System.out.println("ɾ������ʧ�ܣ�����" + res.toString());
				}
				return false;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			} catch (LockConflictException lockConflict) {
				System.out.println("ɾ������ʧ�ܣ�����lockConflict�쳣");
				sleepMillis = 1000;

				continue;
			} finally {
				if (!success) {
					if (txn != null) {
						txn.abort();
					}
				}
			}
		}
		return false;
	}

	public void closeDB() {
		if (frontierDatabase != null) {
			frontierDatabase.close();
		}
		if (env != null) {
			env.close();
		}
	}
	
	
	public static void main(String[] strs){
		try {
			BerkeleyDBUtil bdbFrontier=new BerkeleyDBUtil("d:\\bdb");
            CrawlUrl url=new CrawlUrl();
            url.setOriUrl("http://www.baidu.com");
            bdbFrontier.writeToDatabase(url.getUrl(),url.getUrl(),false);
            
            System.out.println(bdbFrontier.readFromDatabase(url.getUrl()));
            
            
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
