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

@NgModule({
  declarations: [
    AppComponent,
    AppPageNotFoundComponent,
    AppToolbarComponent,
    SkillTreeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
