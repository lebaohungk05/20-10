package object;

import java.sql.Date; // Import java.sql.Date thay vì java.util.Date

/**
 * Class DatPhong: Quản lý đặt phòng khách sạn.
 */
public class DatPhong {
    private String maDatPhong;
    private String maKH;
    private String maPhong;
    private Date ngayDat; // Sử dụng java.sql.Date
    private Date ngayTra; // Sử dụng java.sql.Date
    private double tienCoc;
    private boolean trangThai;
    private String yeuCauDacBiet;

    /**
     * Constructor khởi tạo đặt phòng.
     * @param maDatPhong Mã đặt phòng.
     * @param maKH Mã khách hàng.
     * @param maPhong Mã phòng đặt.
     * @param ngayDat Ngày đặt phòng.
     * @param ngayTra Ngày trả phòng.
     * @param tienCoc Tiền cọc.
     * @param trangThai;
     * @param yeuCauDacBiet Yêu cầu đặc biệt từ khách hàng.
     */
    public DatPhong(String maDatPhong, String maKH, String maPhong, Date ngayDat, Date ngayTra, double tienCoc, boolean trangThai, String yeuCauDacBiet) {
        this.maDatPhong = maDatPhong;
        this.maKH = maKH;
        this.maPhong = maPhong;
        setNgayDat(ngayDat);
        setNgayTra(ngayTra);
        setTienCoc(tienCoc);
        this.trangThai = trangThai;
        this.yeuCauDacBiet = yeuCauDacBiet;
    }

    public String getMaDatPhong() {
        return maDatPhong;
    }

    public void setMaDatPhong(String maDatPhong) {
        this.maDatPhong = maDatPhong;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * Đặt ngày đặt phòng, không thể là ngày trong quá khứ.
     * @param ngayDat Ngày đặt phòng.
     * @throws IllegalArgumentException nếu ngày đặt nhỏ hơn ngày hiện tại.
     */
    public void setNgayDat(Date ngayDat) {
        if (ngayDat.before(new java.util.Date())) {
            throw new IllegalArgumentException("Ngày đặt không thể là quá khứ!");
        }
        this.ngayDat = ngayDat;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    /**
     * Đặt ngày trả phòng, không thể trước ngày đặt.
     * @param ngayTra Ngày trả phòng.
     * @throws IllegalArgumentException nếu ngày trả nhỏ hơn ngày đặt.
     */
    public void setNgayTra(Date ngayTra) {
        if (ngayTra.before(ngayDat)) {
            throw new IllegalArgumentException("Ngày trả không thể trước ngày đặt!");
        }
        this.ngayTra = ngayTra;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    /**
     * Đặt tiền cọc, không thể âm.
     * @param tienCoc Tiền cọc.
     * @throws IllegalArgumentException nếu tiền cọc âm.
     */
    public void setTienCoc(double tienCoc) {
        if (tienCoc < 0) {
            throw new IllegalArgumentException("Tiền cọc không thể âm!");
        }
        this.tienCoc = tienCoc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getYeuCauDacBiet() {
        return yeuCauDacBiet;
    }

    public void setYeuCauDacBiet(String yeuCauDacBiet) {
        this.yeuCauDacBiet = yeuCauDacBiet;
    }

    @Override
    public String toString() {
        return "DatPhong{" + "maDatPhong='" + maDatPhong + '\'' + ", maKH='" + maKH + '\'' + ", maPhong='" + maPhong + '\'' +
                ", ngayDat=" + ngayDat + ", ngayTra=" + ngayTra + ", tienCoc=" + tienCoc +
                ", trangThai=" + trangThai + ", yeuCauDacBiet='" + yeuCauDacBiet + '\'' + '}';
    }
}