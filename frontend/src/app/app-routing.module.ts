import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SkillTreeComponent} from './skill-tree/skill-tree.component';
import {AppPageNotFoundComponent} from './app.page-not-found.component';


const routes: Routes = [
  { path: 'skilltree', component: SkillTreeComponent },
  { path: '',   redirectTo: '/skilltree', pathMatch: 'full' },
  { path: '**', component: AppPageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
