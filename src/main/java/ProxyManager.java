import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class ProxyManager {
    public  String getProxy(){
        List<String> list = new ArrayList<String>();
        String random_proxy = "";
        try{
            Properties properties = new Properties();
            properties.load(this.getClass().getResourceAsStream("/proxies.properties"));
            Enumeration en = properties.propertyNames();
            while(en.hasMoreElements()) {
                list.add((String)en.nextElement());
            }
            String proxy_ip = list.get((int) (Math.random() * list.size()));
            String proxy_port = properties.getProperty(proxy_ip);
            random_proxy = proxy_ip+":"+proxy_port;
        }catch(IOException e){  e.printStackTrace();  }

        return random_proxy;
    }
}
