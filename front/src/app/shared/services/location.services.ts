import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const urlLocation =
  'https://servicodados.ibge.gov.br/api/v1/localidades/estados';

const urlZipCode = 'https://viacep.com.br/ws';

@Injectable()
export class LocationService {
  constructor(private http: HttpClient) {}

  getUF(): Observable<any> {
    return this.http.get(`${urlLocation}`);
  }

  getCity(UFCode): Observable<any> {
    return this.http.get(`${urlLocation}/${UFCode}/municipios`);
  }

  getLocationByZipCode(ZipCode): Observable<any> {
    return this.http.get(`${urlZipCode}/${ZipCode}/json/`);
  }
}
