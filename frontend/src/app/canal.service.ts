import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CanalService {
  private apiUrl = 'http://localhost:8080/canais'; // Ajuste a URL conforme necessário

  constructor(private http: HttpClient) { }

  getCanais(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getCanal(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  addCanal(canal: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, canal);
  }

  updateCanal(canal: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${canal.id}`, canal);
  }

  deleteCanal(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  filterCanais(filter: any): Observable<any[]> {
    let params = new HttpParams();
    if (filter.nome) {
      params = params.set('nome', filter.nome);
    }
    if (filter.descricao) {
      params = params.set('descricao', filter.descricao);
    }
    if (filter.quantidadeInscritos !== null) {
      params = params.set('quantidadeInscritos', filter.quantidadeInscritos.toString());
    }

    return this.http.get<any[]>(`${this.apiUrl}/filtrar`, { params });
  }
}


