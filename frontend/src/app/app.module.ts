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
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {GoogleSearchListComponent} from './google/search-list/google-search-list.component';
import {GoogleSearchComponent} from './google/google-search.component';

@NgModule({
  declarations: [
    AppComponent,
    AppPageNotFoundComponent,
    AppToolbarComponent,
    SkillTreeComponent,
    GoogleSearchListComponent,
    GoogleSearchComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
