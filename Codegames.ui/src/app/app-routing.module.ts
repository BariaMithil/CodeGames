import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NewModuleComponent } from './new-module/new-module.component';
import { UseCaseComponent } from './use-case/use-case.component';
import { TabsComponent } from './tabs/tabs.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  {path:'',component:UseCaseComponent},
  {path:'new-module',component:NewModuleComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
