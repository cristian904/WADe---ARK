import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { Routes } from '@angular/router';

import {AgmCoreModule} from '@agm/core';
import {CountUpModule} from 'countup.js-angular2';

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
import { FormsModule } from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import { HomeComponent } from './home/home.component';
import { MuseumGalleryComponent } from './museum/museum-gallery/museum-gallery.component';
import { BarChartComponent } from './charts/bar-chart/bar-chart.component';
import { MuseumStatsComponent } from './museum/museum-stats/museum-stats.component';
import { PieGridChartComponent } from './charts/pie-grid-chart/pie-grid-chart.component';
import { ArtistListComponent } from './artist/artist-list/artist-list.component';

const appRoutes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'museums', component: MuseumComponent, children: [{ path:':id', component: MuseumDetailComponent }]},
  { path: 'museum/:id', component: MuseumGalleryComponent},
  { path: 'museum/:id/stats', component: MuseumStatsComponent},
  { path: 'arts', component: ArtComponent}, 
  { path: 'art/:id', component: ArtDetailComponent},
  { path: 'artists', component: ArtistComponent},
  { path: 'artist/:id', component: ArtistDetailComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    //Charts
    PieChartComponent,
    BarChartComponent,
    PieGridChartComponent,
    //Museums
    MuseumComponent,
    MuseumDetailComponent,
    MuseumStatsComponent,
    MuseumGalleryComponent,
    //Works of art
    ArtComponent,
    ArtDetailComponent,
    ArtListComponent,
    //Artists
    ArtistComponent,
    ArtistDetailComponent,
    ArtistListComponent
  ],
  imports: [
    FormsModule,
    NgxPaginationModule,
    NgxCarouselModule,
    NgxChartsModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDdbqZvUIy46zJr80oldJGLOve1BdO1pt0'
    }),
    CountUpModule
  ],
  providers: [ArtService, MuseumService, ArtistService],
  bootstrap: [AppComponent]
})
export class AppModule { }
