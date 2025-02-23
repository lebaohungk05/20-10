package object;

import java.util.Date;

/**
 * Class LichSuSuDungDichVu: Theo dõi lịch sử sử dụng dịch vụ của khách hàng trong khách sạn.
 */
public class LichSuSuDungDichVu {
    private String maLichSu;
    private String maKH;
    private String maDichVu;
    private Date ngaySuDung;
    private int soLuong;
    private double tongTien;

    /**
     * Constructor khởi tạo lịch sử sử dụng dịch vụ.
     * @param maLichSu Mã lịch sử giao dịch.
     * @param maKH Mã khách hàng.
     * @param maDichVu Mã dịch vụ đã sử dụng.
     * @param ngaySuDung Ngày sử dụng dịch vụ.
     * @param soLuong Số lượng dịch vụ đã sử dụng.
     * @param tongTien Tổng tiền của dịch vụ đã sử dụng.
     */
    public LichSuSuDungDichVu(String maLichSu, String maKH, String maDichVu, Date ngaySuDung, int soLuong, double tongTien) {
        this.maLichSu = maLichSu;
        this.maKH = maKH;
        this.maDichVu = maDichVu;
        this.ngaySuDung = ngaySuDung;
        setSoLuong(soLuong);
        setTongTien(tongTien);
    }

    public String getMaLichSu() {
        return maLichSu;
    }

    public void setMaLichSu(String maLichSu) {
        this.maLichSu = maLichSu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public Date getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(Date ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }

    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong Số lượng sử dụng.
     * @throws IllegalArgumentException nếu số lượng nhỏ hơn 1.
     */
    public void setSoLuong(int soLuong) {
        if (soLuong < 1) {
            throw new IllegalArgumentException("Số lượng dịch vụ phải lớn hơn hoặc bằng 1!");
        }
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    /**
     * Đặt tổng tiền của dịch vụ, không thể âm.
     * @param tongTien Tổng tiền.
     * @throws IllegalArgumentException nếu tổng tiền nhỏ hơn 0.
     */
    public void setTongTien(double tongTien) {
        if (tongTien < 0) {
            throw new IllegalArgumentException("Tổng tiền không thể âm!");
        }
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "LichSuSuDungDichVu{" + "maLichSu='" + maLichSu + '\'' + ", maKH='" + maKH + '\'' +
                ", maDichVu='" + maDichVu + '\'' + ", ngaySuDung=" + ngaySuDung + ", soLuong=" + soLuong +
                ", tongTien=" + tongTien + '}';
    }
}
