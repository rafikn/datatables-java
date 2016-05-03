import com.google.inject.AbstractModule;

import services.DataService;

public class Module extends AbstractModule {

  @Override
  public void configure() {
    bind(DataService.class).asEagerSingleton();
  }

}
