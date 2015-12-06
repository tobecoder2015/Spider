package autonavi.poi.bean;

import java.io.FileNotFoundException;
import java.util.Map.Entry;
import java.util.Set;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredMap;
import com.sleepycat.je.DatabaseException;

import autonavi.poi.server.AbstractFrontier;

public class BDBFrontier extends AbstractFrontier {

	private StoredMap pendingUrisDB=null;
	public BDBFrontier(String homeDirectory) throws DatabaseException,
			FileNotFoundException {
		super(homeDirectory);
		
		EntryBinding keyBinding=new SerialBinding(javaCatalog, String.class);
		
		EntryBinding valueBinding=new SerialBinding(javaCatalog, CrawlUrl.class);
		
		
		pendingUrisDB=new StoredMap(database, keyBinding, valueBinding, true);
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void put(Object key, Object value) {
		// TODO Auto-generated method stub
		pendingUrisDB.put(key, value);
	}

	@Override
	protected Object get(Object key) {
		// TODO Auto-generated method stub
		CrawlUrl result=null;
		if(!pendingUrisDB.isEmpty()){
			Set entrys=pendingUrisDB.entrySet();
			System.out.println(entrys);
			Entry<String, CrawlUrl> entry=(Entry<String, CrawlUrl>) pendingUrisDB.entrySet().iterator().next();
			
			result=entry.getValue();
			delete(entry.getKey());
			
		}
		return result;
	}

	@Override
	protected Object delete(Object key) {
		// TODO Auto-generated method stub
		return pendingUrisDB.remove(key);
	}
	
	private String caculateUrl(String url){
		return url;
	}
	
	
	public static void main(String[] strs){
		try {
			BDBFrontier bdbFrontier=new BDBFrontier("d:\\bdb");
            CrawlUrl url=new CrawlUrl();
            url.setOriUrl("http://www.baidu.com");
            bdbFrontier.put(url.getUrl(),url.getUrl());
            
            System.out.println((((CrawlUrl)bdbFrontier.get(url.getUrl())).getOriUrl()));
            
            
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
