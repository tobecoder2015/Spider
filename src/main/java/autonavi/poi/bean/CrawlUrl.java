package autonavi.poi.bean;

import java.io.Serializable;
import java.sql.Date;

import com.sleepycat.je.utilint.Timestamp;

public class CrawlUrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5874124230337334885L;
	
	private String oriUrl;
	private String url;
	private int urlNo;
    private int statusCode;
    private int hitNum;
    private String charSet;
    private String abstractText;
    private String author;
    private int weight;
    private String description;
    private int fileSize;
    private Timestamp lastUpdateTime;
    private Date timeToLive;
    private String title;
    private String type;
    private String[] urlReferences;
    private int layer;
	public String getAbstractText() {
		return abstractText;
	}
	public String getAuthor() {
		return author;
	}
	public String getCharSet() {
		return charSet;
	}
	public String getDescription() {
		return description;
	}
	public int getFileSize() {
		return fileSize;
	}
	public int getHitNum() {
		return hitNum;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public int getLayer() {
		return layer;
	}
	public String getOriUrl() {
		return oriUrl;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public Date getTimeToLive() {
		return timeToLive;
	}
	public String getTitle() {
		return title;
	}
	public String getType() {
		return type;
	}
	public String getUrl() {
		return url;
	}
	public int getUrlNo() {
		return urlNo;
	}
	public String[] getUrlReferences() {
		return urlReferences;
	}
	public int getWeight() {
		return weight;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}
	public void setHitNum(int hitNum) {
		this.hitNum = hitNum;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public void setLayer(int layer) {
		this.layer = layer;
	}
	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public void setTimeToLive(Date timeToLive) {
		this.timeToLive = timeToLive;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUrlNo(int urlNo) {
		this.urlNo = urlNo;
	}
	public void setUrlReferences(String[] urlReferences) {
		this.urlReferences = urlReferences;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
    
    
    
    
    
    
    
    
    
}
