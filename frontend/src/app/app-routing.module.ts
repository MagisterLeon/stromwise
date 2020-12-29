import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppPageNotFoundComponent} from './page-not-found/app.page-not-found.component';
import {AddCategoryComponent} from './categories/add-category/add-category.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {QuestionsPresenterComponent} from './questions/questions-presenter/questions-presenter.component';
import {QuestionsUnknownPresenterComponent} from './questions/questions-unknown-presenter/questions-unknown-presenter.component';


const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {path: 'add/category', component: AddCategoryComponent},
  {path: 'questions/:category', component: QuestionsPresenterComponent},
  {path: 'unknown-preview', component: QuestionsUnknownPresenterComponent},
  {path: '**', component: AppPageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
