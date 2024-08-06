import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CanalService } from '../canal.service';

@Component({
  selector: 'app-canal-form',
  templateUrl: './canal-form.component.html',
  styleUrls: ['./canal-form.component.css']
})
export class CanalFormComponent implements OnInit {
  canal: any = { id: null, nome: '', descricao: '', quantidadeInscritos: 0 };
  isEditMode: boolean = false;

  constructor(
    private canalService: CanalService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.canalService.getCanal(+id).subscribe(data => {
        this.canal = data;
      });
    } else {
      this.isEditMode = false;
    }
  }

  save(): void {
    if (this.isEditMode) {
      this.canalService.updateCanal(this.canal).subscribe(() => this.router.navigate(['/canal-list']));
    } else {
      this.canalService.addCanal(this.canal).subscribe(() => this.router.navigate(['/canal-list']));
    }
  }

  cancel(): void {
    this.router.navigate(['/canal-list']);
  }
}
