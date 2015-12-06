package autonavi.poi.server;

import java.io.File;
import java.io.FileNotFoundException;

import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.rep.monitor.NewMasterEvent;

public abstract	 class AbstractFrontier {

	// 数据库环境  
    private Environment env = null;  
  
    // 数据库  
    private static Database frontierDatabase = null;  
  
    // 数据库名  
    private static String dbName = "Url";
    
	private static final String CLASS_CATALOG="java_class_catalog";
	
	protected StoredClassCatalog javaCatalog;
	
	protected Database  catalogdatabase;
	
    protected Database database;
    
    public AbstractFrontier(String homeDirectory) throws DatabaseException,FileNotFoundException{
    	
    	System.out.println("Open environment in :"+ homeDirectory);
    	EnvironmentConfig envConfig=new EnvironmentConfig();
    	envConfig.setTransactional(true);
    	envConfig.setAllowCreate(true);
    	env=new Environment(new File(homeDirectory), envConfig);
    	DatabaseConfig dbConfig=new DatabaseConfig();
    	dbConfig.setTransactional(true);
    	dbConfig.setAllowCreate(true);
    	catalogdatabase=env.openDatabase(null, CLASS_CATALOG, dbConfig);
    	javaCatalog=new StoredClassCatalog(catalogdatabase);
    	
//    	DatabaseConfig dbConfig0=new DatabaseConfig();
//    	dbConfig0.setTransactional(true);
//    	dbConfig0.setAllowCreate(true);
    	
    	database=env.openDatabase(null, dbName, dbConfig);
    	
  				
    }
    
    
    public void Close() throws DatabaseException{
    	database.close();
    	javaCatalog.close();
    	env.close();
    }
    
    
    protected abstract void put(Object key,Object value);
   
    protected abstract Object get(Object key);
    
    protected abstract Object delete(Object key);
    
    
}
