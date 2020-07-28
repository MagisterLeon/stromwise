import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppToolbarComponent } from './app-toolbar/app-toolbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { SkillTreeComponent } from './skill-tree/skill-tree.component';
import {AppPageNotFoundComponent} from './app.page-not-found.component';
import {HttpClientModule} from '@angular/common/http';
import {MatListModule} from '@angular/material/list';
import {GoogleSearchListComponent} from './google/search-list/google-search-list.component';
import {GoogleSearchComponent} from './google/google-search.component';
import { LandingPageComponent } from './landing-page/landing-page.component';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import { HeroActionsAutocompleteComponent } from './landing-page/hero-actions-autocomplete/hero-actions-autocomplete.component';

@NgModule({
  declarations: [
    AppComponent,
    AppPageNotFoundComponent,
    AppToolbarComponent,
    SkillTreeComponent,
    GoogleSearchListComponent,
    GoogleSearchComponent,
    LandingPageComponent,
    HeroActionsAutocompleteComponent
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
    MatButtonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
