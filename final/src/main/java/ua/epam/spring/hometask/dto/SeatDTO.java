package ua.epam.spring.hometask.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class SeatDTO extends AbstractDTO {

    @NotNull
    private AuditoriumDTO auditorium;

    @NotNull
    @NotBlank
    private boolean vip;

    @NotNull
    @NotBlank
    private int number;

    @NotNull
    @NotBlank
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
