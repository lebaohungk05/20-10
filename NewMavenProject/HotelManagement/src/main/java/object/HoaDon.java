package object;

import java.util.Date;

/**
 * Class HoaDon: Quản lý hóa đơn thanh toán của khách hàng trong khách sạn.
 */
public class HoaDon {
    private String maHoaDon;
    private String maKH;
    private double tongTien;
    private Date ngayThanhToan;
    private String phuongThucThanhToan;
    private String trangThaiThanhToan;

    /**
     * Constructor khởi tạo hóa đơn.
     * @param maHoaDon Mã hóa đơn.
     * @param maKH Mã khách hàng liên quan đến hóa đơn.
     * @param tongTien Tổng số tiền của hóa đơn.
     * @param ngayThanhToan Ngày thanh toán hóa đơn.
     * @param phuongThucThanhToan Phương thức thanh toán (Tiền mặt, Chuyển khoản,...).
     * @param trangThaiThanhToan Trạng thái thanh toán (Đã thanh toán, Chưa thanh toán,...).
     */
    public HoaDon(String maHoaDon, String maKH, double tongTien, Date ngayThanhToan, String phuongThucThanhToan, String trangThaiThanhToan) {
        this.maHoaDon = maHoaDon;
        this.maKH = maKH;
        setTongTien(tongTien);
        this.ngayThanhToan = ngayThanhToan;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public double getTongTien() {
        return tongTien;
    }

    /**
     * Đặt tổng tiền hóa đơn, không cho phép giá trị âm.
     * @param tongTien Tổng tiền của hóa đơn.
     * @throws IllegalArgumentException nếu tổng tiền nhỏ hơn 0.
     */
    public void setTongTien(double tongTien) {
        if (tongTien >= 0) {
            this.tongTien = tongTien;
        } else {
            throw new IllegalArgumentException("Tổng tiền không thể âm!");
        }
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon='" + maHoaDon + '\'' + ", maKH='" + maKH + '\'' + ", tongTien=" + tongTien +
                ", ngayThanhToan=" + ngayThanhToan + ", phuongThucThanhToan='" + phuongThucThanhToan + '\'' +
                ", trangThaiThanhToan='" + trangThaiThanhToan + '\'' + '}';
    }
}
