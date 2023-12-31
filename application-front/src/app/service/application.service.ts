import { Injectable, ɵɵinjectPipeChangeDetectorRef } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { Project } from '../model/project.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  private baseUrl = 'http://localhost:8080/application';
  private httpOptions = {
    headers: new HttpHeaders({
      Accept: 'application/json',
      'Content-Type': 'application/json'
    })
  };

  constructor(
    private http: HttpClient
  ) {
  }

  login(params: { username: string; password: string; }): Observable<User> {
    const url = this.baseUrl + '/login';
    return this.http.post<User>(url, params, this.httpOptions);
  }

  // TODO : refactorer les paramètres de `register` sur le modèle de `login`
  // -> On doit avoir un seul paramètre nommé `params` et il doit contenir
  //    les trois champs username, password et email
  register(params: { username: string; password: string; email: string}): Observable<User> {
    const url = this.baseUrl + '/register';
    return this.http.post<User>(url, params, this.httpOptions);
  }

  /**
   * TODO : Implémenter les requêtes pour la sauvegarde et le chargement des projets
   * -> Il faut s'inspirer des fonctions ci-dessus
   */
  saveProject(params: { name: string; amount: number; description: string; ownerUsername: string}): Observable<Project> {
    const url = this.baseUrl + '/saveProject';
    return this.http.post<Project>(url, params, this.httpOptions);
  }

  getProjects(ownerUsername: string): Observable<Project[]> {
    const url = this.baseUrl + '/getProjects?ownerUsername=' + ownerUsername;
    return this.http.get<Project[]>(url, this.httpOptions);
  }

  deleteProjects(id: number): Observable<void> {
    const url = this.baseUrl + '/deleteProject/' + id;
    return this.http.delete<void>(url, this.httpOptions);
  }

}
