import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SkillTreeComponent} from './skill-tree/skill-tree.component';
import {AppPageNotFoundComponent} from './app.page-not-found.component';
import {LandingPageComponent} from './landing-page/landing-page.component';


const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {path: 'skilltree', component: SkillTreeComponent},
  {path: '**', component: AppPageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
