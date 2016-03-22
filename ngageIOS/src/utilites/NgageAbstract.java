package utilites;

import org.openqa.selenium.support.PageFactory;

public class NgageAbstract {
	@SuppressWarnings("unchecked")
	public <T> T loadObject(T t)
	{
		return (T) PageFactory.initElements(Ngagedriver.Driver, t.getClass());
	}
	
}
