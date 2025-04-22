import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuRhComponent } from './menu-rh.component';

describe('MenuRhComponent', () => {
  let component: MenuRhComponent;
  let fixture: ComponentFixture<MenuRhComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MenuRhComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MenuRhComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
