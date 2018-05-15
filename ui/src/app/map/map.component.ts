import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { VehicleService } from '../services/vehicle/vehicle.service';
import { Vehicle, GoogleApiDistance, GoogleApiDuration } from '../model/model.component';
import { } from '@types/googlemaps';

declare var google: any;

@Component({
  selector: 'app-map',
  templateUrl: './map.component.html',
  styleUrls: ['./map.component.css']
})

export class MapComponent implements OnInit {
    
    selectedVehicle: Vehicle;
    fuelCost: number = 1.02;
    vehicles: Vehicle[] = [];

  constructor(private vehicleService: VehicleService, private cd: ChangeDetectorRef) { }

  ngOnInit() {
      this.vehicleService.getVehicles()
          .subscribe(data => { this.vehicles = data },
              err => console.error(err));
      
      // ###########################################################
      // # GOOGLE MAPS API
      // ###########################################################
      this.directionsService = new google.maps.DirectionsService;
      this.directionsDisplay = new google.maps.DirectionsRenderer;
      this.map = new google.maps.Map(this.gmapElement.nativeElement, {
          center: new google.maps.LatLng(38.7125, -9.1367),
          zoom: 12,
          mapTypeId: google.maps.MapTypeId.ROADMAP,
          travelMod: google.maps.TravelMode.DRIVING
      });
      
      this.directionsDisplay.setMap(this.map);
      // ###########################################################
  }
  
  onChangeVehicle(vehicle: Vehicle){
      this.selectedVehicle = vehicle;
  }
  
  // ###########################################################
  // # GOOGLE MAPS API
  // ###########################################################
  @ViewChild('gmap') gmapElement: any;
  origin: string;
  destination: string;
  originCoord: String = '';
  destinationCoord: String = '';
  fuelTotalCost: number = 0;
  distance: GoogleApiDistance = new GoogleApiDistance();
  duration: GoogleApiDuration = new GoogleApiDuration();
  map: google.maps.Map;
  directionsService: google.maps.DirectionsService;
  directionsDisplay: google.maps.DirectionsRenderer; 
  
  getDirections = function() {
      this.calculateAndDisplayRoute(this.directionsService, this.directionsDisplay, this);
  };
  
  calculateFuelTotalCost(){
      if(this.selectedVehicle){
          return this.fuelTotalCost = (this.distance.value * 0.001) * this.selectedVehicle.fuelConsumption / 100 * this.fuelCost;
      } else {
          return 0;
      }
  }
  
  calculateAndDisplayRoute(directionsService, directionsDisplay, parent) {
      directionsService.route({
        origin: this.origin,
        destination: this.destination,
        travelMode: google.maps.TravelMode.DRIVING
      }, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
          directionsDisplay.setDirections(response);
          
          parent.distance = response.routes[0].legs[0].distance;
          parent.duration = response.routes[0].legs[0].duration;
          
          parent.originCoord = response.routes[0].overview_path[0].lat() + " " + response.routes[0].overview_path[0].lng()
          parent.destinationCoord = response.routes[0].overview_path[response.routes[0].overview_path.length-1].lat() + " " + response.routes[0].overview_path[response.routes[0].overview_path.length-1].lng()
          
          parent.calculateFuelTotalCost();
          parent.cd.detectChanges();
        } else {
          window.alert('Request failed due to ' + status);
        }
      });
  }
  // ###########################################################
}
