import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Vehicle } from '../../model/model.component';

const httpOptions = {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      };

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

    constructor(private http: HttpClient) { }
    
    private endpoint = environment.apiEndpoint + 'vehicle-management/vehicles';
    
    getVehicles(): Observable<Vehicle[]> {
        return this.http.get<Vehicle[]>(this.endpoint);
    }
    
    getVehicle(vehicle: Vehicle): Observable<Vehicle> {
        return this.http.get<Vehicle>(this.endpoint + "/" + vehicle.uniqueId);
    }
    
    createVehicle(vehicle: Vehicle) {
        return this.http.post<Vehicle>(this.endpoint, JSON.stringify(vehicle), httpOptions);
    }
    
    updateVehicle(vehicle: Vehicle): Observable<Vehicle> {
        return this.http.put<Vehicle>(this.endpoint, JSON.stringify(vehicle), httpOptions);
    }
    
    deleteVehicle(vehicle: Vehicle): Observable<Vehicle> {
        return this.http.delete<Vehicle>(this.endpoint + "/" + vehicle.uniqueId);
    }
}
