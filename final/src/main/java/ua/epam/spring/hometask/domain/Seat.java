package ua.epam.spring.hometask.domain;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auditorium_id", nullable = false, foreignKey = @ForeignKey(name = "seats_auditorium_id_fk"))
    private Auditorium auditorium;

    @Column(name = "is_vip")
    private boolean vip;

    @Column(name = "number")
    private int number;

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
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

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", auditorium=" + auditorium +
                ", vip=" + vip +
                ", number=" + number +
                '}';
    }
}
