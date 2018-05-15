import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { VehicleManagementComponent } from './vehicle-management/vehicle-management.component';
import { MapComponent } from './map/map.component';

const routes: Routes = [
    { path: '', component: VehicleManagementComponent },
    { path: 'map', component: MapComponent }
];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})

export class AppRoutingModule { }