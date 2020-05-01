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

}