declare var window: any;

export class DynamicEnvironment {
  public get environment() {
    return window.config && window.config.environment;
  }
  public get production() {
    return window.config && window.config.production;
  }

  public get apiUrl() {
    return window.config && window.config.apiUrl;
  }

  public get keycloak() {
    return window.config && window.config.keycloak;
  }

  public get ingredientsService() {
    return window.config && window.config.ingredientsService;
  }

  public get listsService() {
    return window.config && window.config.listsService;
  }

  public get profilesService() {
    return window.config && window.config.profilesService;
  }

  public get recipeService() {
    return window.config && window.config.recipeService;
  }

  public get angular() {
    return window.config && window.config.angular;
  }

}
