import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { VehicleService } from '../services/vehicle/vehicle.service';
import { Vehicle } from '../model/model.component';

@Component({
  selector: 'app-vehicle-management',
  templateUrl: './vehicle-management.component.html',
  styleUrls: ['./vehicle-management.component.css']
})

export class VehicleManagementComponent implements OnInit {

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
      this.vehicleService.getVehicles()
          .subscribe(data => { this.vehicles = data},
                     err => console.error(err));
  }
  
  vehicles: Vehicle[] = [];
  vehicleModel: Vehicle = new Vehicle();
  
  showNew: Boolean = true;
  submitType: string = 'Save';
  selectedRow: number;
  
  onNew(){
      this.vehicleModel = new Vehicle();
      this.submitType = 'Save';
      this.showNew = true;
  }
  
  onSave(){
      this.vehicleService.createVehicle(this.vehicleModel)
          .subscribe(data => {
            this.vehicles.push(data);
            this.map(data, this.vehicleModel);
            this.showNew = false;
            return true;
          },
          error => {
            console.error("Error creating vehicle!");
            return Observable.throw(error);
          }
       );
  }
  
  onUpdate(){
      this.vehicleService.updateVehicle(this.vehicleModel)
          .subscribe(data => {
            this.map(data, this.vehicles[this.selectedRow]);
            this.map(data, this.vehicleModel);
            return true;
          },
          error => {
            console.error("Error updating vehicle!");
            return Observable.throw(error);
          }
       );
  }
  
  onEdit(vehicle: Vehicle, index: number){
      this.selectedRow = index;
      this.vehicleModel = new Vehicle();
      this.vehicleModel = Object.assign({}, vehicle);
      
      this.submitType = 'Update';
      this.showNew = false;
  }
  
  onDelete(vehicle: Vehicle, index: number){
      this.vehicleService.deleteVehicle(vehicle)
          .subscribe(data => { this.vehicles.splice(index,1); },
            err => console.error(err),
            () => console.log('Vehicle ' + vehicle.uniqueId + ' deleted!')
          );
  }
  
  map(orig: Vehicle, dest: Vehicle){
      dest.uniqueId = orig.uniqueId;
      dest.brand = orig.brand;
      dest.model = orig.model;
      dest.licensePlate = orig.licensePlate;
      dest.fuelConsumption = orig.fuelConsumption;
  }
}