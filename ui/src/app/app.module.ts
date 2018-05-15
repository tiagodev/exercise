import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { NavbarComponent } from './navbar/navbar.component';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { VehicleManagementComponent } from './vehicle-management/vehicle-management.component';
import { MapComponent } from './map/map.component';

@NgModule({
  declarations: [
    AppComponent,
    VehicleManagementComponent,
    MapComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
