import { Injectable } from '@angular/core';
import { ApiService } from 'src/app/shared';

@Injectable({
  providedIn: 'root'
})
export class MetasService {

  constructor(private apiService: ApiService) {}

  criarPet(data) {
    return this.apiService.post('/pet', data);
  }

  getAllMetas(id) {
    console.log(id)
    return this.apiService.get('/metas/?usuario=' + id)
  }

  deletarMeta(id) {
    return this.apiService.delete(`/metas/${id}`)
  }

  getMetaById(id) {
    return this.apiService.get(`/metas/${id}/`);
  }

  updateMeta(meta, id) {
    return this.apiService.patch(`/metas/${id}/`, meta);
  }
}
