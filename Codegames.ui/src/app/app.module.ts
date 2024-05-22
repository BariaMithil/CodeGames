import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NewModuleComponent } from './new-module/new-module.component';
import { RouterModule } from '@angular/router';
import { UseCaseComponent } from './use-case/use-case.component';
import { TabsComponent } from './tabs/tabs.component';

@NgModule({
  declarations: [
    AppComponent,
    NewModuleComponent,
    UseCaseComponent,
    TabsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule
  ],
  exports: [
    UseCaseComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
