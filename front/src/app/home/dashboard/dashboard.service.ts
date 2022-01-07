import { Injectable } from '@angular/core';
import { ApiService } from '../../shared';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private apiService: ApiService) {}

  getAllTopFive(id) {
    return this.apiService.get(`/rendadespesas/?usuario=${id}?limit=5&offset=0&ordering=-createdAt` + id)
  }

  getUsuarioById(id) {
    return this.apiService.get(`/usuarios/${id}/`);
  }
}
