package autonavi.poi.server;

import autonavi.poi.bean.CrawlUrl;

public interface Frontier {
	
	public CrawlUrl getNext() throws Exception;
	public boolean putUrl(CrawlUrl url) throws Exception;
	

}
