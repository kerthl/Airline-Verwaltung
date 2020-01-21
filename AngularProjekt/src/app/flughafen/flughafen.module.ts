import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FlughafenComponent } from './flughafen.component';
import { AppModule } from '../app.module';



@NgModule({
    imports: [
        AppModule,
        RouterModule.forChild([
            {
                path: '',
                component: FlughafenComponent
            }
        ])
    ],
    declarations: [FlughafenComponent],
    exports: [FlughafenComponent]
})
export class FlughafenComponentModule {
}
