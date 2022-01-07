import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { AuthService, LoadService, ModalBasicComponent, UserService } from '../shared';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  user: any = {};

  @ViewChild(MatSidenav, { static: true }) sidenav!: MatSidenav;
  constructor(
    private router: Router,
    public dialog: MatDialog,
    private loadService: LoadService,
    private userService: UserService,
    private authService: AuthService,
    private observer: BreakpointObserver,
  ) {}

  ngOnInit(): void {
    console.log("to aq2312")
    this.loadService.emitLoadEvent(false);

    this.user = this.userService.getUser();

    this.userService.observeUser().subscribe((user) => {
      this.user = user ? user : {};
    });
  }

  ngAfterViewInit() {
    this.observer.observe(['(max-width: 800px)']).subscribe((res) => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      } else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });
  }

  logout() {
    // this.authService.logout();
    //   () => this.router.navigate(['login']),
    //   () => this.router.navigate(['login'])
    // );
    setTimeout(() => {
      this.router.navigate(['login'])
    }, 100)
  }

  openLogoutDialog(): void {
    const dialogRef = this.dialog.open(ModalBasicComponent, {
      width: '450px',
      data: {
        title: 'Encerrar sessão',
        body: 'Tem certeza de que deseja sair?',
        bodyParam: this.user,
        hasCancel: true,
      },
    });

    dialogRef.afterClosed().subscribe((logout) => {
      if (!logout) {
        return;
      }
      this.logout();
    });
  }
}
