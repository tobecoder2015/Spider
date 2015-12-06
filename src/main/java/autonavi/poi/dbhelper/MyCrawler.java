package autonavi.poi.dbhelper;

import java.util.Set;

import autonavi.poi.base.LinkQueue;
import autonavi.poi.server.LinkFilter;
import autonavi.poi.tool.DownLoadFile;
import autonavi.poi.tool.HtmlParserTool;

public class MyCrawler {
	/**
	 * ʹ�����ӳ�ʼ�� URL ����
	 * @return
	 * @param seeds ����URL
	 */ 
	private void initCrawlerWithSeeds(String[] seeds)
	{
		for(int i=0;i<seeds.length;i++)
			LinkQueue.addUnvisitedUrl(seeds[i]);
	}	
	/**
	 * ץȡ����
	 * @return
	 * @param seeds
	 */
	public void crawling(String[] seeds)
	{   //�������������ȡ��http://www.lietu.com��ͷ������
		LinkFilter filter = new LinkFilter(){
			public boolean accept(String url) {
				if(url.indexOf("sina")!=-1)
					return true;
				else
					return false;
			}
		};
		//��ʼ�� URL ����
		initCrawlerWithSeeds(seeds);
		//ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		while(!LinkQueue.unVisitedUrlsEmpty()&&LinkQueue.getVisitedUrlNum()<=1000)
		{
			System.out.println("δ���ʵ�URL��Ŀ  ��"+LinkQueue.getUnVisitedUrlNum());
			System.out.println("�Ѿ����ʵ�URL��Ŀ  ��"+LinkQueue.getVisitedUrlNum());
			//��ͷURL������
			String visitUrl=(String)LinkQueue.unVisitedUrlDeQueue();
			if(visitUrl==null)
				continue;
			DownLoadFile downLoader=new DownLoadFile();
			//������ҳ
			downLoader.downloadFile(visitUrl);
			System.out.println("downloadFile:  "+ visitUrl);
			//�� url ���뵽�ѷ��ʵ� URL ��
			LinkQueue.addVisitedUrl(visitUrl);
			//��ȡ��������ҳ�е� URL
			
			Set<String> links=HtmlParserTool.extracLinks(visitUrl,filter);
			//�µ�δ���ʵ� URL ���
			for(String link:links)
			{
					LinkQueue.addUnvisitedUrl(link);
			}
		}
	}
	//main �������
	public static void main(String[]args)
	{
		MyCrawler crawler = new MyCrawler();
		crawler.crawling(new String[]{"http://mil.news.sina.com.cn/"});
	}

}
