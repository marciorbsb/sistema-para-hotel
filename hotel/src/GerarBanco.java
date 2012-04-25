import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;




public class GerarBanco {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Configuration configuration = new Configuration();
		configuration.configure();
		SchemaExport se = new SchemaExport(configuration);
		se.create(true, true);

	}

}
