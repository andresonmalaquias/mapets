import { Injectable } from '@angular/core';
import { ApiService } from '../shared';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(private apiService: ApiService) { }

  getTopFive() {
    this.apiService.get('/rendadespesas/?limit=5&offset=0&ordering=-createdAt');
  }
}
