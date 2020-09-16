import {BrowserModule, DomSanitizer} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppToolbarComponent} from './app-toolbar/app-toolbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {SkillTreeComponent} from './skill-tree/skill-tree.component';
import {AppPageNotFoundComponent} from './app.page-not-found.component';
import {HttpClientModule} from '@angular/common/http';
import {MatListModule} from '@angular/material/list';
import {GoogleSearchListComponent} from './google/search-list/google-search-list.component';
import {GoogleSearchComponent} from './google/google-search.component';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {HeroActionsAutocompleteComponent} from './landing-page/top/hero-actions-autocomplete/hero-actions-autocomplete.component';
import {LandingPageTopComponent} from './landing-page/top/landing-page-top.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {AboutUsComponent} from './landing-page/about-us/about-us.component';
import {AppFooterComponent} from './app-footer/app-footer.component';
import {HeroActionButtonsComponent} from './landing-page/top/hero-action-buttons/hero-action-buttons.component';
import {AppContactComponent} from "./app-contact/app-contact.component";
import {ContactFormComponent} from "./app-contact/contact-form/contact-form.component";
import {ContactMapComponent} from "./app-contact/contact-map/contact-map.component";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatTabsModule} from "@angular/material/tabs";

@NgModule({
  declarations: [
    AppComponent,
    AppPageNotFoundComponent,
    AppToolbarComponent,
    SkillTreeComponent,
    GoogleSearchListComponent,
    GoogleSearchComponent,
    LandingPageTopComponent,
    HeroActionsAutocompleteComponent,
    LandingPageComponent,
    AboutUsComponent,
    AppFooterComponent,
    HeroActionButtonsComponent,
    AppContactComponent,
    ContactMapComponent,
    ContactFormComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatListModule,
    MatInputModule,
    MatAutocompleteModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatGridListModule,
    MatTabsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private matIconRegistry: MatIconRegistry,
              private domSanitizer: DomSanitizer) {
    this.matIconRegistry.addSvgIcon(
      'google',
      this.domSanitizer.bypassSecurityTrustResourceUrl('../assets/social-media/google.svg')
    );
  }
}
