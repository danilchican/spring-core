package ua.epam.spring.hometask.dto;

public class SeatDTO extends AbstractDTO {
    private AuditoriumDTO auditorium;
    private boolean vip;
    private int number;
    private int row;

    public AuditoriumDTO getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(AuditoriumDTO auditorium) {
        this.auditorium = auditorium;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
