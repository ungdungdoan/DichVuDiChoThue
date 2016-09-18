use master
go
create database QLCH
go
create table nhanVien
(
maNV char(15) not null,
tenNV varchar(50),
sdtNV varchar(50),
diachiNV varchar(50),
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
create table nhaCC
(
maNCC char(15) not null,
tenNCC nvarchar(50),
sdtNCC nvarchar(50),
diachiNCC nvarchar(50),
fax varchar(5),
constraint pk_nhaCC primary key(maNCC)
)
create table Kho
(
maKho char(10) not null,
tenKho nvarchar(50),
diachiKho nvarchar(50),
constraint pk_Kho primary key(makho)
)
create table matHang
(
maMH char(10) not null,
tenMH nvarchar(50),
soluong int,
dongia int,
donvitinh nvarchar(5),
constraint pk_matHang primary key(maMH)
)
create table dongHDN
(
soHDN char(10) not null,
ngayHDN Datetime,
maNCC char(15),
maKho char(10),
constraint pk_dongHDN primary key(soHDN),
constraint fk_dongHDN_nhaCC foreign key(maNCC) references nhaCC(maNCC),
constraint fk_dongHDN_Kho foreign key(maKho) references Kho(maKho),
)
create table chitietHDN
(
soHDN char(10) not null,
maMH char(10) not null,
soluong int,
dongia int,
constraint pk_chitietHDN primary key(soHDN,maMH),
constraint fk_chitietHDN_dongHDN foreign key(soHDN) references dongHDN(soHDN),
constraint fk_chitietHDN_matHang foreign key(maMH) references matHang(maMH),
)
create table dongHDX
(
soHDX char(10) not null,
ngayHDX Datetime,
maMH char(10) not null,
maNV char(15) not null, 
maKho char(10) not null,
constraint pk_dongHDX primary key(soHDX),
constraint fk_dongHDx_matHang foreign key(maMH) references matHang(maMH),
constraint fk_dongHDX_nhanVien foreign key(maNV ) references nhanVien(maNV ),
constraint fk_dongHDX_Kho foreign key(maKho) references Kho(maKho),
)
create table chitietHDX
(
soHDX char(10) not null,
maMH char(10) not null,
soluong int,
dongia int,
constraint pk_chitietHDX primary key(soHDX,maMH),
constraint fk_chitietHDX_dongHDX foreign key(soHDX) references dongHDX(soHDX),
constraint fk_chitietHDX_matHang foreign key(maMH) references matHang(maMH),
)
create table dongDNH
(
soDNH char(10) not null,
soHDN char(10) not null,
ngayDNH Datetime,
maNCC char(15) ,
maNV char(15) , 
maKho char(10) ,
constraint pk_dongDNH primary key(soDNH),
constraint fk_dongDNH_dongHDN foreign key(soHDN) references dongHDN(soHDN),
constraint fk_dongDNH_nhaCC foreign key(maNCC) references nhaCC(maNCC),
constraint fk_dongDNH_nhanVien foreign key(maNV ) references nhanVien(maNV ),
constraint fk_dongDNH_Kho foreign key(maKho) references Kho(maKho),
)
create table chitietDNH
(
soDNH char(10) not null,
maMH char(10) not null,
soluong int,
donvitinh nvarchar(50),
constraint pk_chitietDNH primary key(soDNH,maMH),
constraint fk_chitietDNH_dongDNH foreign key(soDNH) references dongDNH(soDNH),
constraint fk_chitietDNH_matHang foreign key(maMH) references matHang(maMH),
)
create table dongDXH
(
soDXH char(10) not null,
soHDX char(10) not null, 
ngayHDX Datetime,
maMH char(10) not null,
maNV char(15) not null, 
maKho char(10) not null,
constraint pk_dongDXH primary key(soHDX),
constraint fk_dongDXH_dongHDX foreign key(soHDX) references dongHDX(soHDX),
constraint fk_dongDXH_matHang foreign key(maMH) references matHang(maMH),
constraint fk_dongDXH_nhanVien foreign key(maNV ) references nhanVien(maNV),
constraint fk_dongDXH_Kho foreign key(maKho) references Kho(maKho),
)
create table chitietDXH
(
soDXH char(10) not null,
maMH char(10) not null,
soluong int,
donvitinh nvarchar(50),
constraint pk_chitietDXH primary key(soDXH,maMH),
constraint fk_chitietDXH_dongDXH foreign key(soDXH) references dongDXH(soDXH),
constraint fk_chitietDXH_matHang foreign key(maMH) references matHang(maMH),
)