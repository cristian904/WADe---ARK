import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';

import {AgmCoreModule} from '@agm/core';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MuseumComponent } from './museum/museum.component';
import { MuseumDetailComponent } from './museum/museum-detail/museum-detail.component';
import { ArtComponent } from './art/art.component';
import { ArtDetailComponent } from './art/art-detail/art-detail.component';
import { ArtistComponent } from './artist/artist.component';
import { ArtistDetailComponent } from './artist/artist-detail/artist-detail.component';
import { RouterModule } from '@angular/router';
import { ArtListComponent } from './art/art-list/art-list.component';
import { ArtService } from './services/art.service';
import { MuseumService } from './services/museum.service';
import { ArtistService } from './services/artist.service';
import {NgxChartsModule} from '@swimlane/ngx-charts';
import { PieChartComponent } from './charts/pie-chart/pie-chart.component';
import { NgxCarouselModule } from 'ngx-carousel';
import { HttpModule } from '@angular/http';
import {NgxPaginationModule} from 'ngx-pagination';

const appRoutes: Routes = [
  { path: 'museum', component: MuseumComponent },
  { path: 'art', component: ArtComponent}, 
  { path: 'art/:id', component: ArtDetailComponent},
  { path: 'artist', component: ArtistComponent},
  { path: 'artist/:id', component: ArtistDetailComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    //Charts
    PieChartComponent,
    //Museums
    MuseumComponent,
    MuseumDetailComponent,
    //Works of art
    ArtComponent,
    ArtDetailComponent,
    ArtListComponent,
    //Artists
    ArtistComponent,
    ArtistDetailComponent
  ],
  imports: [
    NgxPaginationModule,
    NgxCarouselModule,
    NgxChartsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDdbqZvUIy46zJr80oldJGLOve1BdO1pt0'
    })
  ],
  providers: [ArtService, MuseumService, ArtistService],
  bootstrap: [AppComponent]
})
export class AppModule { }
