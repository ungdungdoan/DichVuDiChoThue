create table nhanVien
(
maNV char(15) not null,
tenNV nvarchar(50),
sdtNV nvarchar(50),
diachiNV nvarchar(50),
constraint pk_nhanVien primary key(maNV)
)
create table khachHang
(
maKH char(15) not null,
tenKH nvarchar(50),
giotinh nvarchar(50),
sdtKH nvarchar(50),
diachiKH nvarchar(50),
constraint pk_khachHang primary key(maKH)
)
create table MatHang
(
maMH char(10) not null,
tenMH nvarchar(50),
dongia int,
soluong int,
constraint pk_matHang primary key(maMH)
)
create table HoaDon
(
maHD char(15) not null,
NgayBan datetime,
maNV char(15),
maKH char(15),
constraint pk_HoaDon primary key(maHD),
constraint fk_HoaDon_nhanVien foreign key(maNV) references nhanVien(maNV),
constraint fk_HoaDon_khachHang foreign key(maKH) references khachHang(maKH)
)
create table CTHoaDon
(
maHD char(15),
maMH char(10),
soluong int,
constraint pk_CTHoaDon primary key(maHD,maMH)
)

insert into nhanVien values('001','vtm','01885667518','binhduong')
insert into nhanVien values('002','vth','01885667517','binhduong')
insert into nhanVien values('003','vtk','01885667516','binhduong')
insert into nhanVien values('004','vtl','01885667515','binhduong')
insert into nhanVien values('005','vtq','01885667514','binhduong')

insert into khachHang values('01','pvt','nam','01885667513','binhduong')
insert into khachHang values('02','pvh','nam','01885667512','binhduong')
insert into khachHang values('03','pvn','nam','01885667511','binhduong')

insert into MatHang values('A01','gaubong','5000','2')
insert into MatHang values('A02','banh','15000','3')
insert into MatHang values('A03','xetank','50000','4')
insert into MatHang values('A04','xeduaf1','10000','5')
insert into MatHang values('A05','bi','2000','6')

insert into HoaDon values ('hd1','2/7/2016','001','01')
insert into HoaDon values ('hd2','3/7/2016','002','03')
insert into HoaDon values ('hd3','4/7/2016','003','01')
insert into HoaDon values ('hd4','5/7/2016','004','02')

insert into CTHoaDon values('hd1','A01','3')
insert into CTHoaDon values('hd2','A02','2')
insert into CTHoaDon values('hd2','A03','1')
insert into CTHoaDon values('hd4','A04','5')
insert into CTHoaDon values('hd3','A05','7')