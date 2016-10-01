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
