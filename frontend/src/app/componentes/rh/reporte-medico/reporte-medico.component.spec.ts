import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReporteMedicoComponent } from './reporte-medico.component';

describe('ReporteMedicoComponent', () => {
  let component: ReporteMedicoComponent;
  let fixture: ComponentFixture<ReporteMedicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReporteMedicoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ReporteMedicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
