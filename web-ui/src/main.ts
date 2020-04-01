/**
 * @license
 * Copyright Akveo. All Rights Reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */
import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { KeycloakService } from './app/services/keycloak/keycloak.service';

if (environment.production) {
  enableProdMode();
}
KeycloakService.init()
  .then(() =>
  platformBrowserDynamic().bootstrapModule(AppModule),
  )
  .catch(e => window.location.reload());
