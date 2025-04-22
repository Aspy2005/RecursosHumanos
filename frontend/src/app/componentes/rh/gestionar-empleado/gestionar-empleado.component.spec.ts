import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionarEmpleadoComponent } from './gestionar-empleado.component';

describe('GestionarEmpleadoComponent', () => {
  let component: GestionarEmpleadoComponent;
  let fixture: ComponentFixture<GestionarEmpleadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionarEmpleadoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GestionarEmpleadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
