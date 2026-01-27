import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Ropa} from "../modelos/Ropa";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RopaService {
  private apiUrl = "/api";
  private http = inject(HttpClient);

  // @ts-ignore
  constructor(private http:HttpClient) {}

  consultarRopa(): Observable<any>{
    return this.http.get(this.apiUrl + "/ropa/all")
  }


}
