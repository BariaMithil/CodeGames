import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AppService {

  constructor(private http:HttpClient) { }

  downloadWordFile(message:string): Observable<any> {
    return this.http.get('http://localhost:9090/web/create/ai/generate?message='+message, { responseType: 'blob' });
  }

  // /openai/generate

  downloadWordFileAzureOpenAi(message:string): Observable<any> {
    return this.http.get('http://localhost:9090/web/create/openai/generate?message='+message, { responseType: 'blob' });
  }

}