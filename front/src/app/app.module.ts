import { registerLocaleData, DatePipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { FullCalendarModule } from '@fullcalendar/angular';
import dayGridPlugin from '@fullcalendar/daygrid';
import localeBr from '@angular/common/locales/pt';
import { Calendar } from '@fullcalendar/core';
import esLocale from '@fullcalendar/core/locales/pt';

import { LoadService } from './shared';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { SharedModule } from './shared/shared.module';
import { HomeComponent } from './home/home.component';

FullCalendarModule.registerPlugins([ // register FullCalendar plugins
  dayGridPlugin,
]);

// let calendar = new Calendar(calendarEl, {
//   locale: 'pt'
// });
// calendar.setOption('locale', 'fr');

registerLocaleData(localeBr, 'pt');

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    SharedModule,
    RouterModule,
    FullCalendarModule,
  ],
  providers: [
    LoadService,
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
