import {BrowserModule, DomSanitizer} from '@angular/platform-browser';
import {ErrorHandler, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AppToolbarComponent} from './app-toolbar/app-toolbar.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import {AppPageNotFoundComponent} from './page-not-found/app.page-not-found.component';
import {HttpClientModule} from '@angular/common/http';
import {MatListModule} from '@angular/material/list';
import {GoogleSearchListComponent} from './google/search-list/google-search-list.component';
import {GoogleSearchComponent} from './google/google-search.component';
import {MatInputModule} from '@angular/material/input';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatIconModule, MatIconRegistry} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {HeroActionsAutocompleteComponent} from './landing-page/hero-actions-autocomplete/hero-actions-autocomplete.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {AppFooterComponent} from './app-footer/app-footer.component';
import {HeroActionButtonsComponent} from './landing-page/hero-action-buttons/hero-action-buttons.component';
import {ContactMapComponent} from './app-contact/contact-map/contact-map.component';
import {AppContactComponent} from './app-contact/app-contact.component';
import {ContactFormComponent} from './app-contact/contact-form/contact-form.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCardModule} from '@angular/material/card';
import {GlobalErrorHandler} from './utils/errors/global-error-handler';
import {AddCategoryComponent} from './categories/add-category/add-category.component';
import {QuestionsPreviewComponent} from './questions/questions-preview/questions-preview.component';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import {MatBadgeIconDirective} from './utils/badge/mat-badge-icon.directive';
import {MatBadgeModule} from '@angular/material/badge';
import {QuestionsPresenterComponent} from './questions/questions-presenter/questions-presenter.component';
import {CarouselComponent, CarouselItemElementDirective} from './components/carousel/carousel.component';
import {CarouselItemDirective} from './components/carousel/carousel-item.directive';
import { QuestionsUnknownPresenterComponent } from './questions/questions-unknown-presenter/questions-unknown-presenter.component';
import { QuestionsUnknownPreviewComponent } from './questions/questions-unknown-preview/questions-unknown-preview.component';
import {GoogleChartsModule} from 'angular-google-charts';
import {MatTabsModule} from '@angular/material/tabs';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";

@NgModule({
  declarations: [
    AppComponent,
    AppPageNotFoundComponent,
    AppToolbarComponent,
    GoogleSearchListComponent,
    GoogleSearchComponent,
    HeroActionsAutocompleteComponent,
    LandingPageComponent,
    AppFooterComponent,
    HeroActionButtonsComponent,
    AppContactComponent,
    ContactMapComponent,
    ContactFormComponent,
    AddCategoryComponent,
    QuestionsPreviewComponent,
    MatBadgeIconDirective,
    QuestionsPresenterComponent,
    CarouselComponent,
    CarouselItemDirective,
    CarouselItemElementDirective,
    QuestionsUnknownPresenterComponent,
    QuestionsUnknownPreviewComponent
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
    MatSnackBarModule,
    MatCardModule,
    MatButtonToggleModule,
    MatBadgeModule,
    GoogleChartsModule,
    MatTabsModule,
    MatGridListModule,
    MatProgressSpinnerModule
  ],
  providers: [
    {provide: ErrorHandler, useClass: GlobalErrorHandler},
  ],
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
