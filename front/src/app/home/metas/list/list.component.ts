import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Location } from '@angular/common';

import ptbrLocale from '@fullcalendar/core/locales/pt-br';
import { CalendarOptions } from '@fullcalendar/angular';

import { MetasService } from '../metas.service';
import { LoadService, ModalBasicComponent, UserService } from '../../../shared';

let eventGuid = 0;
const TODAY_STR = new Date().toISOString().replace(/T.*$/, ''); // YYYY-MM-DD of today

export interface MetasData {
	data_criacao: string;
	nome: string;
  descricao: string;
	valor: number;
	valorEntrada: number;
	prioridade: number;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
	dataSource: MatTableDataSource<MetasData> = new MatTableDataSource();
	metas = [];
  loggedUserId: number;
  locale = [ptbrLocale];
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    // events: [
    //   { title: 'event 1', date: '2019-04-01' },
    //   { title: 'event 2', date: '2019-04-02' }
    // ]
  };

	@ViewChild(MatPaginator) paginator: MatPaginator;
	@ViewChild(MatSort) sort: MatSort;
  constructor(
    private metasService: MetasService,
		public dialog: MatDialog,
    private loadService: LoadService,
    private location: Location,
		private userService: UserService,
  ) {}

  ngOnInit(): void {
    // this.loggedUserId = this.userService.getUser().id;

		// if (this.loggedUserId) {
		// 	this.retrieveList(this.loggedUserId);
		// }
	}

  ngAfterViewInit() {}

  public retrieveList(id): void {
		// this.metasService.getAllMetas(id).subscribe(
		// 	response => {
		// 		console.log(response.results)
		// 		response.results.forEach(element => {
		// 			// if (!element.concluido) {
		// 				this.metas.push(element)
		// 			// }
		// 		});
		// 		// this.metas = response.results;
		// 		this.paginator.length = response.count;
		// 		this.dataSource = new MatTableDataSource(this.metas);
		// 	},
		// 	err => {
    //     this.loadService.emitMessageEvent('Erro ao carregar as metas!');
    //     // setTimeout(() => this.backToList(), 2500);
    //   }
		// );
	}

  backToList() {
    this.location.back();
  }

  applyFilter(event: Event) {
		const filterValue = (event.target as HTMLInputElement).value;
		this.dataSource.filter = filterValue.trim().toLowerCase();

		if (this.dataSource.paginator) {
			this.dataSource.paginator.firstPage();
		}
	}
  
  openDeleteDialog(element): void {
		const dialogRef = this.dialog.open(ModalBasicComponent, {
			width: '430px',
			autoFocus: false,
			data: {
				title: 'Confirmação',
				body: 'Tem certeza que deseja excluir este item?',
				bodyParam: element,
				hasCancel: true,
			},
		});

		dialogRef.afterClosed().subscribe((deletedItem) => {
			// console.log(deleteTechnician);
			if (deletedItem) {
				this.metasService.deletarMeta(element.id).subscribe(
					(response) => {
						this.loadService.emitMessageEvent('Registro deletado com sucesso!');
						this.retrieveList(this.loggedUserId);
					},
					ex => {
            this.loadService.emitMessageEvent('Erro ao deletar meta!');
					});
			}
		});
	}

  handleDateClick(arg) {
    alert('date click! ' + arg.dateStr)
  }
}
