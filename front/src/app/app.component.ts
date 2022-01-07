import { ChangeDetectorRef, Component } from '@angular/core';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { LoadService } from './shared';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  private ngUnsubscribe$ = new Subject();

  loading = false;
  loadMessage = '';
  title = 'maxmeta';

  constructor(
    private loadService: LoadService,
    private changeDetector: ChangeDetectorRef,
  ){}

  ngOnInit() {

    this.loadService.isLoading$
      .pipe(takeUntil(this.ngUnsubscribe$))
      .subscribe((isLoading) => {
        this.loading = isLoading;
        this.changeDetector.detectChanges();
      });

    this.loadService.message$
      .pipe(takeUntil(this.ngUnsubscribe$))
      .subscribe((message) => {
        this.loading = true;
        this.loadMessage = message;
        this.changeDetector.detectChanges();

        setTimeout(() => {
          this.loading = false;
          this.loadMessage = '';
        }, 2500);
      });
  }

  ngOnDestroy() {
    this.ngUnsubscribe$.next();
    this.ngUnsubscribe$.complete();
  }
}
