package autonavi.poi.server;

import autonavi.poi.bean.CrawlUrl;

public interface VisitedFrontier {

	public void add(CrawlUrl url);
	
	public void add(String value);
	
	public boolean contains(CrawlUrl url);
	
	public boolean contains(String value);

}
