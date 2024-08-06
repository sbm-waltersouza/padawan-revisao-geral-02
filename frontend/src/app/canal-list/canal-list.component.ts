import { Component, OnInit } from '@angular/core';
import { CanalService } from '../canal.service';

@Component({
  selector: 'app-canal-list',
  templateUrl: './canal-list.component.html',
  styleUrls: ['./canal-list.component.css']
})
export class CanalListComponent implements OnInit {
  canais: any[] = [];
  isAdding: boolean = false;
  newCanal: any = { nome: '', descricao: '', quantidadeInscritos: 0 };
  filter: any = { nome: '', descricao: '', quantidadeInscritos: null };

  constructor(private canalService: CanalService) { }

  ngOnInit(): void {
    this.applyFilter(); // Carrega canais com filtro vazio inicialmente
  }

  applyFilter(): void {
    this.canalService.filterCanais(this.filter).subscribe(data => {
      this.canais = data.map(canal => ({ ...canal, editing: false }));
    });
  }

  startAdd(): void {
    this.isAdding = true;
  }

  cancelAdd(): void {
    this.isAdding = false;
    this.newCanal = { nome: '', descricao: '', quantidadeInscritos: 0 };
  }

  addNewCanal(): void {
    this.canalService.addCanal(this.newCanal).subscribe(() => {
      this.applyFilter();
      this.cancelAdd(); // Reset state after adding
    });
  }

  editCanal(canal: any): void {
    canal.editing = true;
  }

  cancelEdit(canal: any): void {
    canal.editing = false;
    this.applyFilter(); // Recarrega os dados para cancelar as alterações
  }

  saveCanal(canal: any): void {
    this.canalService.updateCanal(canal).subscribe(() => {
      canal.editing = false;
      this.applyFilter(); // Recarrega os dados para atualizar a tabela
    });
  }

  deleteCanal(id: number): void {
    if (confirm('Tem certeza de que deseja excluir este canal?')) {
      this.canalService.deleteCanal(id).subscribe(() => {
        this.applyFilter();
      });
    }
  }
}
