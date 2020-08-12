import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SkillTreeComponent} from './skill-tree/skill-tree.component';
import {AppPageNotFoundComponent} from './app.page-not-found.component';
import {LandingPageComponent} from './landing-page/landing-page.component';
import {SkillTreeResolver} from './skill-tree/skill-tree.resolver';


const routes: Routes = [
  {path: '', component: LandingPageComponent},
  {
    path: 'skilltree/:name',
    component: SkillTreeComponent,
    resolve: {
      skilltree: SkillTreeResolver
    }
  },
  {path: '**', component: AppPageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
