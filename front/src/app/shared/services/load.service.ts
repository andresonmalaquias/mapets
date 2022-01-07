import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoadService {
  private isLoadingSource = new BehaviorSubject<boolean>(false);
  private messageSource = new BehaviorSubject<string>('');

  isLoading$ = this.isLoadingSource.asObservable();
  message$ = this.messageSource.asObservable();

  constructor() {}

  getIsLoading(): boolean {
    return this.isLoadingSource.getValue();
  }

  emitLoadEvent(isLoading: boolean) {
    this.isLoadingSource.next(isLoading);
  }

  emitMessageEvent(message: string) {
    this.messageSource.next(message);
  }
}
