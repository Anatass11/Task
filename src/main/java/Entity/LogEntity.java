package Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "log", schema = "task")
public class LogEntity { //сущность таблицы бд
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "PAIR_ID")
    private Integer pairId;
    @Basic
    @Column(name = "START")
    private Timestamp start;
    @Basic
    @Column(name = "END")
    private Timestamp end;
    @Basic
    @Column(name = "POINT_A")
    private String pointA;
    @Basic
    @Column(name = "POINT_B")
    private String pointB;
    @Basic
    @Column(name = "DISTANCE")
    private Double distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPairId() {
        return pairId;
    }

    public void setPairId(Integer pairId) {
        this.pairId = pairId;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }

    public String getPointA() {
        return pointA;
    }

    public void setPointA(String pointA) {
        this.pointA = pointA;
    }

    public String getPointB() {
        return pointB;
    }

    public void setPointB(String pointB) {
        this.pointB = pointB;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return id == logEntity.id && Objects.equals(pairId, logEntity.pairId) && Objects.equals(start, logEntity.start) && Objects.equals(end, logEntity.end) && Objects.equals(pointA, logEntity.pointA) && Objects.equals(pointB, logEntity.pointB) && Objects.equals(distance, logEntity.distance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pairId, start, end, pointA, pointB, distance);
    }

    public Object[] getLog(){
        return new Object[] {id, pairId, start, end, pointA, pointB, distance};
    }
}
