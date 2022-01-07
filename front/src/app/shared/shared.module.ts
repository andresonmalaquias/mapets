import { NgModule, Injector } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  HttpClientModule,
  HttpClientJsonpModule,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgxWebstorageModule } from 'ngx-webstorage';

import { NgxMaskModule, IConfig } from 'ngx-mask';

import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatMenuModule } from '@angular/material/menu';
import { MatStepperModule } from '@angular/material/stepper';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatSelectModule } from '@angular/material/select';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatRadioModule } from '@angular/material/radio';
import { MatDividerModule } from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import {MatProgressBarModule} from '@angular/material/progress-bar';

import {
  VersionComponent,
  LoadingComponent,
  ApiService,
  AuthService,
  UserService,
  LocationService,
  UserRouteAccessService,
  ValidatorUtil,
  NoWhiteSpaceDirective,
  SpecialCharactersDirective,
  AuthExpiredInterceptor,
  ModalBasicComponent,
  CardComponent,
  ImgUrlPipe,
  ReplacePipe,
} from '.';

export const options: Partial<IConfig> | (() => Partial<IConfig>) = null;

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxWebstorageModule.forRoot(),
    HttpClientModule,
    HttpClientJsonpModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatCardModule,
    MatDialogModule,
    MatSelectModule,
    MatStepperModule,
    MatSidenavModule,
    MatRadioModule,
    MatDividerModule,
    MatListModule,
    MatProgressBarModule,
    NgxMaskModule.forRoot(),
  ],
  declarations: [
    VersionComponent,
    LoadingComponent,
    ModalBasicComponent,
    NoWhiteSpaceDirective,
    SpecialCharactersDirective,
    CardComponent,
    ImgUrlPipe,
    ReplacePipe,
  ],
  entryComponents: [ModalBasicComponent],
  exports: [
    HttpClientModule,
    VersionComponent,
    CardComponent,
    FormsModule,
    LoadingComponent,
    RouterModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatRadioModule,
    MatIconModule,
    MatCardModule,
    MatTableModule,
    MatPaginatorModule,
    MatDialogModule,
    MatCheckboxModule,
    MatMenuModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMaskModule,
    MatSortModule,
    NoWhiteSpaceDirective,
    SpecialCharactersDirective,
    MatTooltipModule,
    MatExpansionModule,
    MatSidenavModule,
    MatStepperModule,
    ImgUrlPipe,
    ReplacePipe,
    MatDividerModule,
    MatListModule,
    MatProgressBarModule,
  ],
  providers: [
    ApiService,
    AuthService,
    UserService,
    ValidatorUtil,
    ImgUrlPipe,
    LocationService,
    UserRouteAccessService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthExpiredInterceptor,
      multi: true,
      deps: [Injector],
    },
  ],
})
export class SharedModule {}
