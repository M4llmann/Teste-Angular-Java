import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaComponentComponent } from './conta-component.component';

describe('ContaComponentComponent', () => {
  let component: ContaComponentComponent;
  let fixture: ComponentFixture<ContaComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContaComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContaComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
