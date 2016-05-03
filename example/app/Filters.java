import play.http.HttpFilters;
import play.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;
import javax.inject.*;

@Singleton
public class Filters implements HttpFilters {

  @Inject CSRFFilter csrfFilter;

  @Override
  public EssentialFilter[] filters() {
      return new EssentialFilter[] { csrfFilter.asJava() };
  }

}
